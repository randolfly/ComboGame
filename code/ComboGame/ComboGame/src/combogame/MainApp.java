package combogame;

import java.io.IOException;

import combogame.controller.BottomLayoutController;
import combogame.controller.CenterLayoutController;
import combogame.controller.RootLayoutController;
import combogame.model.FoodSquare;
import combogame.model.PlateSquare;
import combogame.model.TaskType;
import combogame.util.HandleCombo;
import combogame.util.HandleMusic;
import combogame.util.Monitor;
import combogame.view.BottomLayout;
import combogame.view.CenterLayout;
import combogame.view.RightLayout;
import combogame.view.TopLayout;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @Auther: rando
 * @Date: 2019/10/25 18:40
 * @Description:
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    // Layout权限
    public CenterLayout centerLayout;
    public BottomLayout bottomLayout;
    public RightLayout rightLayout;
    public TopLayout topLayout;

    // the selected plateSquare and the selected foodSquare
    public FoodSquare foodSquare;
    public PlateSquare plateSquare;
    public boolean isFoodSquareSelected = false;
    public boolean isPlateSquareSelected = false;
    public boolean isRocketSquareSelected = false;
    public boolean isMineSquareSelected = false;

    // 全局是否有Null Square
    public boolean hasNullSquare = true;
    // ! 是否主线程在工作~监听点击事件~避免线程冲突
    public boolean isMainThreadProcessing = true;
    // 是否棋盘中所有待消除的都消除完了
    public boolean isGridPaneClear = true;
    // game difficulty
    public TaskType taskType;
    // 是否在暂停中
    public static boolean isInPause = true;
    public static boolean isGameInitialized = false;

    // 动画元素
    // 过渡动画-下落
    public TranslateTransition transition;

    // 全局控制元素
    public static boolean isGameFinished = false;
    public double mouseXLoc = 0.0;
    public double mouseYLoc = 0.0;

    // 监视器
    public Monitor monitor;
    // 监视monitor进程，使其自动重启
    // public ThreadObserver threadMonitor;
    // 负责运行监视器的进程

    public static void main(String[] args) {
        launch(args);
    }

    // TODO:给鼠标增加粒子特效->ref:https://www.cnblogs.com/javafx/archive/2013/04/18/3027940.html

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ComboGame");
        this.primaryStage.setResizable(false); // 设置舞台的尺寸是否允许变化
        this.primaryStage.setWidth(1200);
        this.primaryStage.setHeight(800);
        initApp();
        initRootLayout();
        initCenterLayout();
        initBottomLayout();
        initRightLayout();
        initTopLayout();
        // centerLayout.gamePane.getChildren().forEach((e) -> System.out.println(e));
        // centerLayout.gamePane.getChildren().remove(2 * 9 + 5);
        HandleCombo.disableAllSquare(this.centerLayout);
        BottomLayoutController.disableBottomSquare(this.bottomLayout);
        // 监视器
        monitor.start();
    }

    private void initApp() throws Exception {
        transition = new TranslateTransition();

        // load musics
        HandleMusic.loadMusics();
        HandleMusic.playBackgroundMusic();
        // set the background music stop
        this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                MainApp.isGameFinished = true;
                HandleMusic.stopBackgroundMusic();
                // TODO 弹出Score窗口
                System.out.println("Goodbye!");
            }
        });

        // game difficulty type
        taskType = TaskType.Difficult;

        // init monitor
        this.monitor = new Monitor(this);
        // this.threadMonitor = new ThreadObserver(this);
        // this.monitor.addObserver(this.threadMonitor);
    }

    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            scene.getStylesheets().add(MainApp.class.getResource("css/backGroundMaterial.css").toExternalForm());
            // Give the combogame.controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            rootLayout.addEventFilter(MouseEvent.MOUSE_MOVED, e -> {
                mouseXLoc = e.getX();
                mouseYLoc = e.getY();
            });
            primaryStage.show();
            System.out.println("root layout finished!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // // Try to load last opened person file.
        // String filePath = contact.util.XmlOperator.getPersonFilePath();
        // if (filePath != null && !filePath.isEmpty()) {
        // contact.util.XmlOperator.readXml(filePath);
        // }

    }

    public void initCenterLayout() {
        try {
            // load game panel
            CenterLayout centerLayout = new CenterLayout(this);

            rootLayout.setLeft(centerLayout.gamePane);
            // Give the combogame.controller access to the main app.
            CenterLayoutController controller = new CenterLayoutController(this, centerLayout);

            this.centerLayout = centerLayout;
            System.out.println("center layout finished!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initBottomLayout() {
        try {
            // load game panel
            BottomLayout bottomLayout = new BottomLayout();
            rootLayout.setBottom(bottomLayout.bottomPane);

            this.bottomLayout = bottomLayout;
            // Give the combogame.controller access to the main app.
            BottomLayoutController controller = new BottomLayoutController(this, bottomLayout);
            System.out.println("bottom layout finished!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initRightLayout() {
        try {
            RightLayout rightLayout = new RightLayout(this);
            rootLayout.setRight(rightLayout.stackPane);
            this.rightLayout = rightLayout;
            System.out.println("Right layout finished!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initTopLayout() {
        try {
            TopLayout topLayout = new TopLayout(this);
            rootLayout.setTop(topLayout.targetPane);
            this.topLayout = topLayout;
            System.out.println("Top layout finished!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /*
     * @Description:set the current selected foodSquare.
     * 
     * @param:FoodSquare foodSquare the current selected foodSquare
     * 
     * @return:none
     */
    public void setFoodSquare(FoodSquare foodSquare) {
        this.foodSquare = foodSquare;
    }

    /*
     * @Description: set the current selected plateSquare.
     * 
     * @param: PlateSquare plateSquare the selected plateSquare
     * 
     * @return:none
     */
    public void setPlateSquare(PlateSquare plateSquare) {
        this.plateSquare = plateSquare;
    }

    /**
     * Returns the current selected foodSquare.
     */
    public FoodSquare getCurrentFoodSquare() {
        return this.foodSquare;
    }

    /**
     * Returns the current selected plateSquare.
     */
    public PlateSquare getCurrentPlateSquare() {
        return this.plateSquare;
    }
}
