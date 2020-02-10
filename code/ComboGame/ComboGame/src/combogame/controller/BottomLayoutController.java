package combogame.controller;

import combogame.MainApp;
import combogame.model.RemoveType;
import combogame.model.TaskType;
import combogame.util.HandleCombo;
import combogame.view.BottomLayout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

/**
 * @Auther: rando
 * @Date: 2019/11/13 19:33
 * @Description:
 */
public class BottomLayoutController {
    // Reference to the main application
    private MainApp mainApp;
    private BottomLayout bottomLayout;

    public BottomLayoutController(MainApp mainApp, BottomLayout bottomLayout) {
        setBottomLayout(bottomLayout);
        setMainApp(mainApp);

        bottomLayout.plate.setOnMouseClicked((e) -> handlePlateClick());
        bottomLayout.startSquare.setOnMouseClicked((e) -> handleStart());
        bottomLayout.pauseSquare.setOnMouseClicked((e) -> handlePause());
        bottomLayout.stopSquare.setOnMouseClicked((e) -> handleStop());
        bottomLayout.propMines.setOnMouseClicked((e) -> handleMineClick());
        bottomLayout.propPotion.setOnMouseClicked((e) -> handlePotionClick());
        bottomLayout.propRainbow.setOnMouseClicked((e) -> handleRainbowClick());
        bottomLayout.propRocket.setOnMouseClicked((e) -> handleRocketClick());

        bottomLayout.taskTypeComboBox.valueProperty().addListener(new ChangeListener<Label>() {

            @Override
            public void changed(ObservableValue<? extends Label> observable, Label oldValue, Label newValue) {
                // TODO Auto-generated method stub
                handleTaskType(newValue.getText());
            }
        });
        System.out.println("BottomLayoutController initialized");
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.mainApp.plateSquare = this.bottomLayout.plate;
        System.out.println("Plate Type: " + this.mainApp.plateSquare);
    }

    public void setBottomLayout(BottomLayout bottomLayout) {
        this.bottomLayout = bottomLayout;
    }

    private void handlePlateClick() {
        mainApp.isPlateSquareSelected = true;
        mainApp.isMainThreadProcessing = true;
        mainApp.isRocketSquareSelected = false;
        mainApp.isMineSquareSelected = false;
        mainApp.plateSquare = bottomLayout.plate;
        System.out.println("#############################");
        System.out.println("Plate clicked: " + mainApp.plateSquare.getTypeName());
        System.out.println("foodSquare clicked: " + mainApp.isFoodSquareSelected);
        System.out.println("plateSquare clicked: " + mainApp.isPlateSquareSelected);
        if (mainApp.isFoodSquareSelected && mainApp.isPlateSquareSelected) {
            if (!HandleCombo.isFoodType(mainApp.foodSquare.getTypeNumber())) {
                System.out.println("food exchange invalid!");
                return;
            }
            HandleCombo.foodExchange(mainApp.foodSquare, mainApp.plateSquare);
            RemoveType lineType = HandleCombo.getLineType(mainApp.centerLayout, mainApp.foodSquare.xLoc,
                    mainApp.foodSquare.yLoc);
            mainApp.isPlateSquareSelected = false;
            mainApp.isFoodSquareSelected = false;
            if (lineType.getTypeNumber() != RemoveType.NULL.getTypeNumber()) {
                mainApp.topLayout.stepNumber--;
                HandleCombo.removeSquare(mainApp.centerLayout, mainApp, mainApp.foodSquare, lineType);
                // 处理之后的消除
                // HandleCombo.RemoveLine(centerLayout, mainApp, foodSquare.xLoc,
                // foodSquare.yLoc);
                mainApp.isGridPaneClear = false;
                System.out.println("ExChanged");
            } else {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                HandleCombo.foodExchange(mainApp.foodSquare, mainApp.plateSquare);
                System.out.println("food exchange invalid!");
            }
        }
        mainApp.isMainThreadProcessing = false;
    }

    private void handleStart() {
        MainApp.isInPause = false;
        HandleCombo.enableAllSquare(this.mainApp.centerLayout);
        enableBottomSquare(bottomLayout);

    }

    private void handleStop() {
        System.out.println("Game Over");
        // 这里写的不好，应该统一一处退出
        System.exit(0);
    }

    private void handlePause() {
        MainApp.isInPause = true;
        HandleCombo.disableAllSquare(this.mainApp.centerLayout);
        disableBottomSquare(bottomLayout);

    }

    private void handleTaskType(String type) {
        if (type == "EASY") {
            mainApp.taskType = TaskType.Easy;
        } else if (type == "MEDIUM") {
            mainApp.taskType = TaskType.Medium;
        } else {
            mainApp.taskType = TaskType.Difficult;
        }
        System.out.println(mainApp.taskType.name());
        mainApp.initCenterLayout();
        mainApp.initTopLayout();
        HandleCombo.disableAllSquare(this.mainApp.centerLayout);
        disableBottomSquare(bottomLayout);
    }

    private void handlePotionClick() {
        mainApp.isMainThreadProcessing = true;

        mainApp.isFoodSquareSelected = false;
        mainApp.isRocketSquareSelected = false;
        mainApp.isMineSquareSelected = false;
        mainApp.isPlateSquareSelected = false;

        mainApp.topLayout.stepNumber += 3;
        System.out.println("Potion used!");
        mainApp.isGridPaneClear = false;
        mainApp.isMainThreadProcessing = false;

    }

    private void handleRocketClick() {
        mainApp.isMainThreadProcessing = true;
        mainApp.isFoodSquareSelected = false;
        mainApp.isRocketSquareSelected = true;
        mainApp.isMineSquareSelected = false;
        mainApp.isPlateSquareSelected = false;

        mainApp.isGridPaneClear = false;
        System.out.println("Rocket clicked");
        mainApp.isMainThreadProcessing = false;
    }

    private void handleMineClick() {
        mainApp.isMainThreadProcessing = true;
        mainApp.isFoodSquareSelected = false;
        mainApp.isRocketSquareSelected = false;
        mainApp.isMineSquareSelected = true;
        mainApp.isPlateSquareSelected = false;

        System.out.println("Mine clicked");
        mainApp.isGridPaneClear = false;
        mainApp.isMainThreadProcessing = false;
    }

    private void handleRainbowClick() {
        mainApp.isMainThreadProcessing = true;

        mainApp.isRocketSquareSelected = false;
        mainApp.isMineSquareSelected = false;
        mainApp.isPlateSquareSelected = false;

        HandleCombo.removeType(this.mainApp.centerLayout, HandleCombo.randomFoodType(mainApp).getNCode());
        System.out.println("Rainbow used!");
        mainApp.isFoodSquareSelected = false;
        mainApp.isMainThreadProcessing = false;
        mainApp.isGridPaneClear = false;

    }

    public static void disableBottomSquare(BottomLayout bottomLayout) {
        bottomLayout.plate.setDisable(true);
        bottomLayout.propMines.setDisable(true);
        bottomLayout.propPotion.setDisable(true);
        bottomLayout.propRainbow.setDisable(true);
        bottomLayout.propRocket.setDisable(true);
    }

    public static void enableBottomSquare(BottomLayout bottomLayout) {
        bottomLayout.plate.setDisable(false);
        bottomLayout.propMines.setDisable(false);
        bottomLayout.propPotion.setDisable(false);
        bottomLayout.propRainbow.setDisable(false);
        bottomLayout.propRocket.setDisable(false);
    }
}
