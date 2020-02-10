package combogame.view;

import combogame.MainApp;
import combogame.model.FoodSquare;
import combogame.model.ObjectType;
import combogame.util.HandleCombo;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * @Auther: rando
 * @Date: 2019/11/5 14:55
 * @Description:
 */
public class CenterLayout {
    public MainApp mainApp;
    // public AnchorPane centerPane;
    public GridPane gamePane;
    /// gridPane设置为13x13的(9+4)x(9+4)
    public FoodSquare[][] gridPane;
    public int width = 9;
    public int height = 9;
    // 设置这个buffer避免边界时的if/else
    public int buffer = 10;
    public int sideBuffer = 5;

    // 可以提供的随机的食物种类
    private HashSet<Integer> liveFoodSet = new HashSet<Integer>();
    // 用来生成随机数
    private Random random;

    public CenterLayout(MainApp mainApp) {
        this.mainApp = mainApp;
        // centerPane = new AnchorPane();
        gridPane = new FoodSquare[this.width + this.buffer][this.height + this.buffer];
        gamePane = new GridPane();

        // game pane setting
        // gamePane.setAlignment(Pos.CENTER);
        // gamePane.setVgap(10);
        // gamePane.setHgap(5);

        initLiveFood();
        initGridPane();
        initMap();
        initObstacle();
        // game pane setting
        initGamePane();

        // for (int i = sideBuffer; i < sideBuffer + width; i++) {
        // for (int j = sideBuffer; j < sideBuffer + height; j++) {
        // System.out.println("XPos: " + i + " YPos: " + j + " Type: " +
        // (gridPane[i][j]).getTypeName());
        // }
        // }
        for (int i = 0; i < this.buffer + this.width; i++) {
            for (int j = 0; j < this.buffer + this.height; j++) {
                System.out.println(gridPane[i][j]);
            }
        }
    }

    public Pane getCenterPane() {
        return this.gamePane;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    /**
     * @Description:初始化可供填充格子的食物种类
     * 
     * @return:none
     */
    public void initLiveFood() {
        liveFoodSet = new HashSet<Integer>();
        ObjectType[] foodList = ObjectType.values();
        // ! 注意FoodType下含有一个NUll类型、一个Wall类型、一个Ice类型
        for (int i = 0; i < mainApp.taskType.foodNumber; i++) {
            liveFoodSet.add(foodList[i].getNCode());
        }
    }

    /**
     * @Description: 初始化gridPane的值，避免空指针
     * 
     * @return:none
     */
    private void initGridPane() {
        gridPane = new FoodSquare[this.width + this.buffer][this.height + this.buffer];
        for (int i = 0; i < width + buffer; i++) {
            // ObjectType[] foodTypes = ObjectType.values();
            for (int j = 0; j < height + buffer; j++) {
                // 随机食物种类
                // random = new Random();
                // // ! 注意FoodType下含有一个NUll类型
                // int RadomNumber = random.nextInt(foodTypes.length - 3);
                // 全部初始化为空
                gridPane[i][j] = new FoodSquare(i, j);
            }
        }
    }

    /**
     * @Description: 初始化gridPane的Map,只修改种类
     * @return:none
     */
    public void initMap() {
        this.initLiveFood();
        random = new Random();
        int foodTypeNumber;
        List<Integer> liveFoodList = new ArrayList<Integer>(liveFoodSet);

        for (int i = this.sideBuffer; i < this.sideBuffer + this.width; i++) {
            for (int j = this.sideBuffer; j < this.sideBuffer + this.height; j++) {
                // 可供选择的食物种类数
                int liveFoodSize = liveFoodSet.size();
                liveFoodList = new ArrayList<Integer>(liveFoodSet);
                if (liveFoodSize > 0) {
                    // 随机食物种类~在LiveFoodSet中随机
                    int RadomNumber = random.nextInt(liveFoodSize);
                    foodTypeNumber = liveFoodList.get(RadomNumber);
                    if (HandleCombo.isFoodTypeMoveAvailable(this.gridPane[i][j].getTypeNumber())) {
                        this.gridPane[i][j].setTypeByNumber(foodTypeNumber);
                    } else {
                        continue;
                    }
                    // 判断该格子是不是有3个连在一起
                    if (HandleCombo.isLine(this, i, j)) {
                        j -= 1;
                        liveFoodSet.remove(foodTypeNumber);
                    } else {
                        this.initLiveFood();
                    }
                } else {
                    // 没有可以随机的颜色了
                    // 重新初始化地图
                    this.initGridPane();
                    this.initMap();
                    return;
                }
            }
        }
    }

    /**
     * @Description: 修改gridPane, 添加Wall和Ice
     * @return:none
     */
    public void initObstacle() {
        // 产生冰块与墙
        ArrayList<int[]> icePosition = new ArrayList<int[]>();
        ArrayList<int[]> wallPosition = new ArrayList<int[]>();
        wallPosition = this.mainApp.taskType.wallPosition;
        icePosition = this.mainApp.taskType.icePosition;
        for (int i = 0; i < icePosition.size(); i++) {
            gridPane[sideBuffer + icePosition.get(i)[0]][sideBuffer + icePosition.get(i)[1]].setType(ObjectType.Ice);
            gridPane[sideBuffer + icePosition.get(i)[0]][sideBuffer + icePosition.get(i)[1]].iceNumber = icePosition
                    .get(i)[2];
        }
        for (int i = 0; i < wallPosition.size(); i++) {
            gridPane[sideBuffer + wallPosition.get(i)[0]][sideBuffer + wallPosition.get(i)[1]].setType(ObjectType.Wall);
            gridPane[sideBuffer + wallPosition.get(i)[0]][sideBuffer + wallPosition.get(i)[1]].iceNumber = 999999;
        }
    }

    /**
     * @Description: 初始化图形化的界面, 修改棋子的种类
     * 
     * @return: none
     */
    public void initGamePane() {
        // if (gamePane != null) {
        // gamePane.getChildren().removeAll();
        // gamePane = new GridPane();

        // // game pane setting
        // gamePane.setAlignment(Pos.CENTER);
        // gamePane.setVgap(10);
        // gamePane.setHgap(5);
        // }
        for (int i = this.sideBuffer; i < this.sideBuffer + this.width; i++) {
            for (int j = this.sideBuffer; j < this.sideBuffer + this.height; j++) {
                FoodSquare foodSquare = gridPane[i][j];
                // foodSquare.setType(ObjectType.Null);
                foodSquare.setLoc(i, j);
                foodSquare.setPrefSize(50, 50);
                foodSquare.setStyle(foodSquare.getCssType());
                gamePane.add(foodSquare, i, j);
            }
        }

        gamePane.setAlignment(Pos.CENTER);
        gamePane.setVgap(10);
        gamePane.setHgap(5);
    }

}
