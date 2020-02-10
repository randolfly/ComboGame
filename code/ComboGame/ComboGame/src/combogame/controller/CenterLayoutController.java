package combogame.controller;

import combogame.MainApp;
import combogame.model.FoodSquare;
import combogame.model.ObjectType;
import combogame.model.RemoveType;
import combogame.util.HandleCombo;
import combogame.view.CenterLayout;

/**
 * @Author: rando
 * @Date: 2019/11/13 19:33
 * @Description:
 */
public class CenterLayoutController {
    // Reference to the main application
    private MainApp mainApp;
    private CenterLayout centerLayout;

    public CenterLayoutController(MainApp mainApp, CenterLayout centerLayout) {
        setMainApp(mainApp);
        setCenterLayout(centerLayout);

        // bind the click handler to the buttons
        for (int i = centerLayout.sideBuffer; i < centerLayout.sideBuffer + centerLayout.width; i++) {
            for (int j = centerLayout.sideBuffer; j < centerLayout.sideBuffer + centerLayout.height; j++) {
                FoodSquare foodSquare = centerLayout.gridPane[i][j];
                foodSquare.setOnMouseClicked((e) -> handleFoodClick(foodSquare));
            }
        }
        System.out.println("CenterLayoutController initialized");
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setCenterLayout(CenterLayout centerLayout) {
        this.centerLayout = centerLayout;
    }

    /**
     * @Description: 每个食物格子被点击的方法
     * 
     * @param: FoodSquare foodSquare: 被点击的食物格子
     * 
     * @return: none
     */
    public void handleFoodClick(FoodSquare foodSquare) {
        mainApp.foodSquare = foodSquare;
        mainApp.isFoodSquareSelected = true;
        mainApp.isMainThreadProcessing = true;
        System.out.println("#############################");
        System.out.println("foodSquare selected: " + mainApp.foodSquare.getTypeName());
        System.out.println("Plate selected: " + mainApp.plateSquare.getTypeName());

        System.out.println("x location: " + foodSquare.xLoc + ", y location: " + foodSquare.yLoc);
        System.out.println("this foodSquare is stuck? " + foodSquare.isStuck);

        System.out.println("foodSquare clicked: " + mainApp.isFoodSquareSelected);
        System.out.println("plateSquare clicked: " + mainApp.isPlateSquareSelected);
        if (mainApp.isFoodSquareSelected && mainApp.isMineSquareSelected) {
            if (foodSquare.getTypeNumber() != ObjectType.Wall.getNCode()) {
                for (int i = foodSquare.xLoc - 2; i <= foodSquare.xLoc + 2; i++) {
                    FoodSquare tempSquare = centerLayout.gridPane[i][foodSquare.yLoc];
                    if (HandleCombo.isFoodType(tempSquare.getTypeNumber())) {
                        centerLayout.gridPane[i][foodSquare.yLoc].setTypeNull();
                    } else if (tempSquare.getTypeNumber() == ObjectType.Ice.getNCode()) {
                        centerLayout.gridPane[i][foodSquare.yLoc].iceNumber--;
                    }
                }
                for (int i = foodSquare.yLoc - 2; i <= foodSquare.yLoc + 2; i++) {
                    FoodSquare tempSquare = centerLayout.gridPane[foodSquare.xLoc][i];
                    if (HandleCombo.isFoodType(tempSquare.getTypeNumber())) {
                        centerLayout.gridPane[foodSquare.xLoc][i].setTypeNull();
                    } else if (tempSquare.getTypeNumber() == ObjectType.Ice.getNCode()) {
                        centerLayout.gridPane[foodSquare.xLoc][i].iceNumber--;
                    }
                }
                FoodSquare tempSquare = centerLayout.gridPane[foodSquare.xLoc - 1][foodSquare.yLoc - 1];
                if (HandleCombo.isFoodType(tempSquare.getTypeNumber())) {
                    centerLayout.gridPane[foodSquare.xLoc - 1][foodSquare.yLoc - 1].setTypeNull();
                } else if (tempSquare.getTypeNumber() == ObjectType.Ice.getNCode()) {
                    centerLayout.gridPane[foodSquare.xLoc - 1][foodSquare.yLoc - 1].iceNumber--;
                }
                tempSquare = centerLayout.gridPane[foodSquare.xLoc + 1][foodSquare.yLoc - 1];
                if (HandleCombo.isFoodType(tempSquare.getTypeNumber())) {
                    centerLayout.gridPane[foodSquare.xLoc + 1][foodSquare.yLoc - 1].setTypeNull();
                } else if (tempSquare.getTypeNumber() == ObjectType.Ice.getNCode()) {
                    centerLayout.gridPane[foodSquare.xLoc + 1][foodSquare.yLoc - 1].iceNumber--;
                }
                tempSquare = centerLayout.gridPane[foodSquare.xLoc + 1][foodSquare.yLoc + 1];
                if (HandleCombo.isFoodType(tempSquare.getTypeNumber())) {
                    centerLayout.gridPane[foodSquare.xLoc + 1][foodSquare.yLoc + 1].setTypeNull();
                } else if (tempSquare.getTypeNumber() == ObjectType.Ice.getNCode()) {
                    centerLayout.gridPane[foodSquare.xLoc + 1][foodSquare.yLoc + 1].iceNumber--;
                }
                tempSquare = centerLayout.gridPane[foodSquare.xLoc - 1][foodSquare.yLoc + 1];
                if (HandleCombo.isFoodType(tempSquare.getTypeNumber())) {
                    centerLayout.gridPane[foodSquare.xLoc - 1][foodSquare.yLoc + 1].setTypeNull();
                } else if (tempSquare.getTypeNumber() == ObjectType.Ice.getNCode()) {
                    centerLayout.gridPane[foodSquare.xLoc - 1][foodSquare.yLoc + 1].iceNumber--;
                }
                System.out.println("Mine used!");
            } else {
                System.out.println("Invalid");
            }
        } else if (mainApp.isFoodSquareSelected && mainApp.isRocketSquareSelected) {
            if (foodSquare.getTypeNumber() != ObjectType.Wall.getNCode()) {
                for (int i = centerLayout.sideBuffer; i < centerLayout.width + centerLayout.sideBuffer; i++) {
                    FoodSquare tempSquare = centerLayout.gridPane[i][foodSquare.yLoc];
                    if (HandleCombo.isFoodType(tempSquare.getTypeNumber())) {
                        centerLayout.gridPane[i][foodSquare.yLoc].setTypeNull();
                    } else if (tempSquare.getTypeNumber() == ObjectType.Ice.getNCode()) {
                        centerLayout.gridPane[i][foodSquare.yLoc].iceNumber--;
                    }
                }
                for (int i = centerLayout.sideBuffer; i < centerLayout.height + centerLayout.sideBuffer; i++) {
                    FoodSquare tempSquare = centerLayout.gridPane[foodSquare.xLoc][i];
                    if (HandleCombo.isFoodType(tempSquare.getTypeNumber())) {
                        centerLayout.gridPane[foodSquare.xLoc][i].setTypeNull();
                    } else if (tempSquare.getTypeNumber() == ObjectType.Ice.getNCode()) {
                        centerLayout.gridPane[foodSquare.xLoc][i].iceNumber--;
                    }
                }
                System.out.println("Rocket used!");
            } else {
                System.out.println("Invalid");
            }
        } else if (mainApp.isFoodSquareSelected && mainApp.isPlateSquareSelected) {
            if (!HandleCombo.isFoodType(mainApp.foodSquare.getTypeNumber())) {
                System.out.println("food exchange invalid!");
                return;
            }
            HandleCombo.foodExchange(mainApp.foodSquare, mainApp.plateSquare);
            RemoveType lineType = HandleCombo.getLineType(centerLayout, foodSquare.xLoc, foodSquare.yLoc);
            if (lineType.getTypeNumber() != RemoveType.NULL.getTypeNumber()) {
                mainApp.topLayout.stepNumber--;
                HandleCombo.removeSquare(centerLayout, mainApp, foodSquare, lineType);
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
        mainApp.isFoodSquareSelected = false;
        mainApp.isPlateSquareSelected = false;

        mainApp.isRocketSquareSelected = false;
        mainApp.isMineSquareSelected = false;
        mainApp.isMainThreadProcessing = false;
        mainApp.isGridPaneClear = false;

    }

}
