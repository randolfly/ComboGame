/* File Info 
 * Author:      Randolf 
 * CreateTime:  7019/12/15 上午12:27:56 
 * LastEditor:  Randolf
 * ModifyTime:  7019/12/19 下午3:22:55
 * Description: 监控类
*/
package combogame.util;

import combogame.MainApp;
import combogame.controller.TopLayoutController;
import combogame.model.ObjectType;
import combogame.model.RemoveType;
import combogame.view.CenterLayout;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

//TODO: 增加监视，保证Monitor异常后自动重启
// TODO: 改变Thread使用,改为使用Service守护线程，方便更新GUI
public class Monitor extends Service<Number> {

    MonitorTask monitorTask;
    MainApp mainApp;

    public Monitor(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    protected Task<Number> createTask() {
        // TODO Auto-generated method stub
        this.monitorTask = new MonitorTask(this.mainApp);
        return this.monitorTask;
    }

    @Override
    protected void executeTask(Task<Number> task) {
        try {
            super.executeTask(task);
        } catch (Exception e) {
            System.out.println("Meet exception, restart");
            this.cancel();
            this.restart();
        }
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        // 更新UI
        // 重启service?
        refreshPaneUI();
        try {
            Thread.sleep(80);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.restart();
    }

    @Override
    protected void failed() {
        super.failed();
        if (!MainApp.isInPause) {
            this.restart();
            System.out.println("restart monitor");
        }

    }

    public void refreshPaneUI() {
        for (int i = mainApp.centerLayout.sideBuffer; i < mainApp.centerLayout.sideBuffer
                + mainApp.centerLayout.width; i++) {
            for (int j = mainApp.centerLayout.sideBuffer; j < mainApp.centerLayout.sideBuffer
                    + mainApp.centerLayout.height; j++) {
                mainApp.centerLayout.gridPane[i][j].refreshSquareUI();
            }
        }
        // refresh topLayout
        TopLayoutController.refreshTopLayout(this.mainApp);
        boolean isGameOver = true;
        if (mainApp.topLayout.iceAmount > 0) {
            isGameOver = false;
        }
        for (int i = 0; i < mainApp.topLayout.foodAmount.length; i++) {
            if (mainApp.topLayout.foodAmount[i] > 0) {
                isGameOver = false;
            }
        }
        // refresh right tank
        double rotateAng = (Math.atan(
                (mainApp.mouseYLoc - mainApp.rightLayout.tankYLoc) / (mainApp.mouseXLoc - mainApp.rightLayout.tankXLoc))
                / Math.PI * 180.0) - 90.0;
        // System.out.println(mainApp.mouseXLoc);
        // System.out.println(mainApp.mouseYLoc);
        // System.out.println(rotateAng);
        mainApp.rightLayout.tankHead.setRotate(rotateAng);
        if (mainApp.isGridPaneClear) {
            // 游戏是否结束
            if (isGameOver) {
                // 游戏结束
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game Over");
                alert.setHeaderText("You Win, Young Man");
                alert.setContentText("Congratulation!!");

                alert.showAndWait();
                System.out.println("Game Over! You Win!");
                System.exit(0);
            } else {
                if (mainApp.topLayout.stepNumber <= 0) {
                    // 游戏结束
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Game Over");
                    alert.setHeaderText("You Lose, Young Man");
                    alert.setContentText("哦豁!!");

                    alert.showAndWait();
                    System.out.println("Game Over! You Lose!");
                    System.exit(0);
                }
            }

        }
    }
}

class MonitorTask extends Task<Number> {

    MainApp mainApp;

    public MonitorTask(final MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void watchMusic() {
        if (!HandleMusic.backgroundMusic.isAlive()) {
            HandleMusic.playBackgroundMusic();
        }
    }

    // TODO: 扫描全局是否有要消除的
    // TODO: scanPane中刷新每一个UI顺便，避免由于刷新速率不够造成的未刷新现象
    // TODO: UI刷新还是不对
    // !直接调用setType似乎不太能够更新UI，详见文件image目录下截图
    // ! gridPane书信频率太低！！！亏了，应该直接用canvas绘图

    // TODO: 如果有时间把更新界面全部挪到主线程(Javafx的线程)，避免bug:Exception in thread "JavaFX
    // Application Thread" java.lang.ArrayIndexOutOfBoundsException
    /**
     * @Description:扫描棋盘，remove应该remove的方块
     * 
     * @param: centerLayout: 棋盘备份
     * 
     * @return: boolean isPaneRemoved: 是否棋盘有要消除的
     */
    public boolean scanPane(final CenterLayout centerLayout) throws InterruptedException {
        int xLoc = 0, yLoc = 0;
        boolean isPaneRemoved = false;
        // 游戏是否结束?

        for (xLoc = centerLayout.sideBuffer; xLoc < centerLayout.sideBuffer + centerLayout.width; xLoc++) {
            for (yLoc = centerLayout.sideBuffer; yLoc < centerLayout.sideBuffer + centerLayout.height; yLoc++) {
                // final ObjectType foodSquareType =
                // centerLayout.gridPane[xLoc][yLoc].getType();
                // centerLayout.gridPane[xLoc][yLoc].setType(foodSquareType);

                final RemoveType lineType = HandleCombo.getLineType(centerLayout, xLoc, yLoc);
                if (lineType.getTypeNumber() != RemoveType.NULL.getTypeNumber()) {
                    HandleCombo.removeSquare(centerLayout, mainApp, centerLayout.gridPane[xLoc][yLoc], lineType);
                    isPaneRemoved = true;
                }
                // System.out.println("Into Scan Process");
            }
        }
        return isPaneRemoved;
    }

    /*
     * @Description:扫描全局的棋盘，将空的格子冒泡飘出棋盘~~
     * 
     * @param:centerlayout: 棋盘副本
     * 
     * @return:是否有空格,true->有空格
     */
    public boolean bubbleNullSquare(final CenterLayout centerLayout) throws InterruptedException {
        // TODO 写好bubble算法，加上判断语句
        int xLoc = 0, yLoc = 0;
        boolean hasNullSquare = false;
        for (xLoc = centerLayout.sideBuffer; xLoc < centerLayout.sideBuffer + centerLayout.width; xLoc++) {
            // 寻找每一列第一个空格,开始bubble
            yLoc = centerLayout.sideBuffer;
            while ((!HandleCombo.isFoodTypeNull(centerLayout.gridPane[xLoc][yLoc].getTypeNumber()))
                    || centerLayout.gridPane[xLoc][yLoc].isStuck) {
                yLoc++;
                if (yLoc >= centerLayout.height + centerLayout.sideBuffer) {
                    break;
                }
            }
            // 没有Null
            if (yLoc >= (centerLayout.height + centerLayout.sideBuffer)) {
                continue;
            } else {
                // 这一个空格上方是否有Wall/Ice
                hasNullSquare = true;
                boolean isLineMoveAvailable = true;
                int obstaclePos = -1;

                for (obstaclePos = yLoc - 1; obstaclePos >= centerLayout.sideBuffer; obstaclePos--) {
                    if ((!HandleCombo.isFoodTypeMoveAvailable(centerLayout.gridPane[xLoc][obstaclePos].getTypeNumber())
                            || centerLayout.gridPane[xLoc][obstaclePos].isStuck)) {
                        isLineMoveAvailable = false;
                        break;
                    }
                }
                ObjectType objectType_left = centerLayout.gridPane[xLoc - 1][obstaclePos].getType();
                ObjectType objectType_right = centerLayout.gridPane[xLoc + 1][obstaclePos].getType();

                // 上方两个障碍
                if (!(HandleCombo.isFoodTypeMoveAvailable(objectType_left.getNCode())
                        || HandleCombo.isFoodTypeMoveAvailable(objectType_right.getNCode()))) {
                    centerLayout.gridPane[xLoc][obstaclePos + 1].setUnMoveable();
                }

                ObjectType foodSquareType = ObjectType.Null;
                if (isLineMoveAvailable) {
                    for (int i = yLoc; i >= centerLayout.sideBuffer; i--) {
                        if (i == centerLayout.sideBuffer) {
                            centerLayout.gridPane[xLoc][centerLayout.sideBuffer]
                                    .setType(HandleCombo.randomFoodType(this.mainApp));
                        } else {
                            foodSquareType = centerLayout.gridPane[xLoc][i - 1].getType();
                            centerLayout.gridPane[xLoc][i].setType(foodSquareType);
                            centerLayout.gridPane[xLoc][i - 1].setTypeNull();
                        }
                    }
                } else {
                    for (int i = yLoc; i > obstaclePos; i--) {
                        if (i == obstaclePos + 1) {
                            centerLayout.gridPane[xLoc][i].setTypeNull();
                        } else {
                            foodSquareType = centerLayout.gridPane[xLoc][i - 1].getType();
                            centerLayout.gridPane[xLoc][i].setType(foodSquareType);
                            centerLayout.gridPane[xLoc][i - 1].setTypeNull();
                        }
                    }
                    // 判断左上角与右上角情况

                    // TODO: 随机选取一个落下
                    if (HandleCombo.isFoodType(objectType_left.getNCode())) {
                        centerLayout.gridPane[xLoc][obstaclePos + 1].setType(objectType_left);
                        centerLayout.gridPane[xLoc - 1][obstaclePos].setType(ObjectType.Null);
                    } else if (HandleCombo.isFoodType(objectType_right.getNCode())) {
                        centerLayout.gridPane[xLoc][obstaclePos + 1].setType(objectType_right);
                        centerLayout.gridPane[xLoc + 1][obstaclePos].setType(ObjectType.Null);
                    }
                }
            }

        }
        return hasNullSquare;
    }

    // 不可更新线程，留到Service里更新UI
    @Override
    protected Number call() throws Exception {
        // TODO Auto-generated method stub
        if (MainApp.isInPause) {
            Thread.sleep(100);
            return 0.0;
        }
        watchMusic();
        // scanPane(this.mainApp.centerLayout);
        try {
            if (!mainApp.isMainThreadProcessing) {
                mainApp.hasNullSquare = bubbleNullSquare(this.mainApp.centerLayout);
                if (!mainApp.hasNullSquare) {
                    if (!mainApp.isGridPaneClear) {
                        // 禁止鼠标在这个时候点击
                        mainApp.isGridPaneClear = !scanPane(this.mainApp.centerLayout);
                        // HandleCombo.disableAllSquare(this.mainApp.centerLayout);
                    } else {
                        // HandleCombo.enableAllSquare(this.mainApp.centerLayout);
                    }
                }
            }
        } catch (final Exception e) {
            // e.printStackTrace();
            System.out.println("--!!!!!!!!!!!!!Error!!!!!!!!!!!!!--");
            e.printStackTrace();
        }
        return 1.0;
    }

}