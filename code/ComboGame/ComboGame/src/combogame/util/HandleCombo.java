/* File Info 
 * Author:      Randolf 
 * CreateTime:  2019/11/14 下午3:55:14 
 * LastEditor:  Randolf 
 * ModifyTime:  2019/11/14 下午3:55:34 
 * Description: combogame的逻辑判断
*/
package combogame.util;

import java.util.Random;

import combogame.MainApp;
import combogame.controller.TopLayoutController;
import combogame.model.FoodSquare;
import combogame.model.ObjectType;
import combogame.model.PlateSquare;
import combogame.view.CenterLayout;
import combogame.model.RemoveType;

public class HandleCombo {
    /**
     * @Description exchange the given foodSquare and the plateSquare
     * 
     * @param FoodSquare  foodSquare: the foodSquare to exchange;
     * 
     * @param PlateSquare plateSquare: the plateSquare to exchange;
     * 
     * @return none
     */
    public static void foodExchange(FoodSquare foodSquare, PlateSquare plateSquare) {
        ObjectType type = foodSquare.getType();
        foodSquare.setType(plateSquare.getType());
        plateSquare.setType(type);
        // refresh Css
        foodSquare.setStyle(foodSquare.getCssType() + " -fx-background-size: 50px;");
        plateSquare.setStyle(plateSquare.getCssType() + " -fx-background-size: 80px;");
    }

    /**
     * @Description:判断三个格子是否食物种类相同
     * 
     * @param:ObjectType food1: 食物格子1
     * 
     * @param:ObjectType food2: 食物格子2
     * 
     * @param:ObjectType food3: 食物格子3
     * 
     * @return:boolean
     */
    public static boolean isCellFoodEqual(FoodSquare food1, FoodSquare food2, FoodSquare food3) {
        if (food1 != null && food2 != null && food3 != null) {
            int type1 = food1.getTypeNumber();
            int type2 = food2.getTypeNumber();
            int type3 = food3.getTypeNumber();
            return (type1 == type2 && type2 == type3);
        }
        return false;
    }

    /**
     * @Description: 返回对应的消除的线的类型
     * 
     * @param: centerLayout 副本
     * 
     * @param: xPos
     * 
     * @param: yPos
     * 
     * @return: RemoveType
     */
    public static RemoveType getLineType(CenterLayout centerLayout, int xPos, int yPos) {
        // ! 判断爆炸不做了。。。时间不太够
        // 消除效果越好，越在前面检查
        // length 5
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos][yPos - 4].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos - 3].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos - 2].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos - 1].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber() })) {
            return RemoveType.A5L0040;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos][yPos - 3].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos - 2].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos - 1].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 1].getTypeNumber() })) {
            return RemoveType.A5L0031;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos][yPos - 2].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos - 1].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 1].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 2].getTypeNumber() })) {
            return RemoveType.A5L0022;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos][yPos - 1].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 1].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 2].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 3].getTypeNumber() })) {
            return RemoveType.A5L0013;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 1].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 2].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 3].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 4].getTypeNumber() })) {
            return RemoveType.A5L0004;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos - 4][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos - 3][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos - 2][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos - 1][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber() })) {
            return RemoveType.A5L4000;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos - 3][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos - 2][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos - 1][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 1][yPos].getTypeNumber() })) {
            return RemoveType.A5L3100;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos - 2][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos - 1][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 1][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 2][yPos].getTypeNumber() })) {
            return RemoveType.A5L2200;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos - 1][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 1][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 2][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 3][yPos].getTypeNumber() })) {
            return RemoveType.A5L1300;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 1][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 2][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 3][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 4][yPos].getTypeNumber() })) {
            return RemoveType.A5L0400;
        }
        // length 4
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos][yPos - 3].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos - 2].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos - 1].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber() })) {
            return RemoveType.A4L0030;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos][yPos - 2].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos - 1].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 1].getTypeNumber() })) {
            return RemoveType.A4L0021;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos][yPos - 1].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 1].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 2].getTypeNumber() })) {
            return RemoveType.A4L0012;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 1].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 2].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 3].getTypeNumber() })) {
            return RemoveType.A4L0003;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos - 3][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos - 2][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos - 1][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber() })) {
            return RemoveType.A4L3000;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos - 2][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos - 1][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 1][yPos].getTypeNumber() })) {
            return RemoveType.A4L2100;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos - 1][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 1][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 2][yPos].getTypeNumber() })) {
            return RemoveType.A4L1200;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 1][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 2][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 3][yPos].getTypeNumber() })) {
            return RemoveType.A4L0300;
        }
        // length 3
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos][yPos - 1].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 1].getTypeNumber() })) {
            return RemoveType.A3L0011;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos - 1][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 1][yPos].getTypeNumber() })) {
            return RemoveType.A3L1100;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos][yPos - 2].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos - 1].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber() })) {
            return RemoveType.A3L0020;
        }
        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 1].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos + 2].getTypeNumber() })) {
            return RemoveType.A3L0002;
        }

        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos - 2][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos - 1][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos][yPos].getTypeNumber() })) {
            return RemoveType.A3L2000;
        }

        if (isFoodTypeEqual(new int[] { centerLayout.gridPane[xPos][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 1][yPos].getTypeNumber(),
                centerLayout.gridPane[xPos + 2][yPos].getTypeNumber() })) {
            return RemoveType.A3L0200;
        }
        return RemoveType.NULL;
    }

    /**
     * @Description: 返回是否是一条线
     * 
     * @param: centerLayout 副本
     * 
     * @param: xPos
     * 
     * @param: yPos
     * 
     * @return: boolean
     */
    public static boolean isLine(CenterLayout centerLayout, int xPos, int yPos) {
        if (getLineType(centerLayout, xPos, yPos).getTypeNumber() == RemoveType.NULL.getTypeNumber()) {
            return false;
        } else {
            return true;
        }
    }

    // TODO: 使用Monitor监视全局的删除操作？每次只remove一次

    public static boolean isExchangeValid(CenterLayout centerLayout, int xPos, int yPos) {
        return isLine(centerLayout, xPos, yPos);
    }

    public static ObjectType randomFoodType(MainApp mainApp) {
        Random random = new Random();
        ObjectType[] foodTypes = ObjectType.values();
        int randomNumber = random.nextInt(mainApp.taskType.foodNumber);
        return foodTypes[randomNumber];
    }

    public static boolean isFoodTypeEqual(int[] foodSquare) {
        boolean equal = true;
        for (int i = 1; i < foodSquare.length; i++) {
            if (foodSquare[i] != foodSquare[0]) {
                equal = false;
                break;
            }
        }
        if (!((foodSquare[0] == ObjectType.Null.getNCode()) || (foodSquare[0] == ObjectType.Wall.getNCode())
                || (foodSquare[0] == ObjectType.Ice.getNCode()))) {
            return equal;
        }
        return false;
    }

    /*
     * @Description:对于给定的foodSquare实现下落功能
     * 
     * @param:centerLayout: 棋盘所处的Layout，获得处理权限
     * 
     * @param:foodSquare: 下落的物块
     * 
     * @return:none
     */
    public static void removeSquare(CenterLayout centerLayout, MainApp mainApp, FoodSquare foodSquare,
            RemoveType lineType) {
        // 更新链表
        // ! 动画太难做！！先做其他简单的逻辑吧...
        int xLoc = foodSquare.xLoc, yLoc = foodSquare.yLoc;
        int localFoodTypeNumber = foodSquare.getTypeNumber();
        centerLayout.gridPane[xLoc][yLoc].setTypeNull();
        TopLayoutController.reduceCount(mainApp.topLayout, localFoodTypeNumber);
        switch (lineType.getTypeNumber()) {
        case 1:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 2][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 1][yLoc].getTypeNumber());
            centerLayout.gridPane[xLoc - 2][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc - 1][yLoc].setTypeNull();

            // 消除冰块
            for (int i = xLoc - 2; i <= xLoc; i++) {
                if (centerLayout.gridPane[i][yLoc + 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc + 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc + 1].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[i][yLoc - 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc - 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc - 1].setTypeNull();
                    }
                }

            }
            if (centerLayout.gridPane[xLoc - 3][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc - 3][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc - 3][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc - 3][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc - 3][yLoc].setTypeNull();
                }
            }
            if (centerLayout.gridPane[xLoc + 1][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc + 1][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc + 1][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc + 1][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc + 1][yLoc].setTypeNull();
                }
            }
            break;
        case 2:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 1][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 1][yLoc].getTypeNumber());

            centerLayout.gridPane[xLoc - 1][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 1][yLoc].setTypeNull();
            // 消除冰块
            for (int i = xLoc - 1; i <= xLoc + 1; i++) {
                if (centerLayout.gridPane[i][yLoc + 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc + 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc + 1].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[i][yLoc - 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc - 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc - 1].setTypeNull();
                    }
                }

            }
            if (centerLayout.gridPane[xLoc - 2][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc - 2][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc - 2][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc - 2][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc - 2][yLoc].setTypeNull();
                }
            }
            if (centerLayout.gridPane[xLoc + 2][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc + 2][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc + 2][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc + 2][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc + 2][yLoc].setTypeNull();
                }
            }

            break;
        case 3:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 1][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 2][yLoc].getTypeNumber());

            centerLayout.gridPane[xLoc + 1][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 2][yLoc].setTypeNull();
            // 消除冰块
            for (int i = xLoc; i <= xLoc + 2; i++) {
                if (centerLayout.gridPane[i][yLoc + 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc + 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc + 1].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[i][yLoc - 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc - 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc - 1].setTypeNull();
                    }
                }

            }
            if (centerLayout.gridPane[xLoc - 1][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc - 1][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc - 1][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc - 1][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc - 1][yLoc].setTypeNull();
                }
            }
            if (centerLayout.gridPane[xLoc + 3][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc + 3][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc + 3][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc + 3][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc + 3][yLoc].setTypeNull();
                }
            }

            break;
        case 4:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 1][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 2][yLoc].getTypeNumber());

            centerLayout.gridPane[xLoc][yLoc + 1].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc + 2].setTypeNull();
            // 消除冰块
            for (int i = yLoc; i <= yLoc + 2; i++) {
                if (centerLayout.gridPane[xLoc - 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc - 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[xLoc + 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc + 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }

            }
            if (centerLayout.gridPane[xLoc][yLoc + 3].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc + 3].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc + 3].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc + 3].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc + 3].setTypeNull();
                }
            }

            if (centerLayout.gridPane[xLoc][yLoc - 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc - 1].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc - 1].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc - 1].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc - 1].setTypeNull();
                }
            }

            break;
        case 5:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 1].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 1].getTypeNumber());

            centerLayout.gridPane[xLoc][yLoc + 1].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc - 1].setTypeNull();
            // 消除冰块
            for (int i = yLoc - 1; i <= yLoc + 1; i++) {
                if (centerLayout.gridPane[xLoc - 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc - 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[xLoc + 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc + 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }

            }
            if (centerLayout.gridPane[xLoc][yLoc + 2].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc + 2].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc + 2].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc + 2].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc + 2].setTypeNull();
                }
            }

            if (centerLayout.gridPane[xLoc][yLoc - 2].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc - 2].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc - 2].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc - 2].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc - 2].setTypeNull();
                }
            }

            break;
        case 6:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 1].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 2].getTypeNumber());

            centerLayout.gridPane[xLoc][yLoc - 1].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc - 2].setTypeNull();
            // 消除冰块
            for (int i = yLoc - 2; i <= yLoc; i++) {
                if (centerLayout.gridPane[xLoc - 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc - 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[xLoc + 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc + 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }

            }
            if (centerLayout.gridPane[xLoc][yLoc - 3].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc - 3].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc - 3].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc - 3].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc - 3].setTypeNull();
                }
            }

            if (centerLayout.gridPane[xLoc][yLoc + 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc + 1].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc + 1].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc + 1].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc + 1].setTypeNull();
                }
            }

            break;
        case 7:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 3][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 2][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 1][yLoc].getTypeNumber());

            centerLayout.gridPane[xLoc - 3][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc - 2][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc - 1][yLoc].setTypeNull();
            // 消除冰块
            for (int i = xLoc - 3; i <= xLoc; i++) {
                if (centerLayout.gridPane[i][yLoc + 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc + 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc + 1].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[i][yLoc - 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc - 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc - 1].setTypeNull();
                    }
                }
            }
            if (centerLayout.gridPane[xLoc - 4][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc - 4][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc - 4][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc - 4][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc - 4][yLoc].setTypeNull();
                }
            }
            if (centerLayout.gridPane[xLoc + 1][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc + 1][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc + 1][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc + 1][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc + 1][yLoc].setTypeNull();
                }
            }
            // 连带竖向消除
            for (int i = centerLayout.sideBuffer; i < centerLayout.sideBuffer + centerLayout.height; i++) {
                int foodTypeNumber = centerLayout.gridPane[xLoc][i].getTypeNumber();
                if (isFoodType(foodTypeNumber)) {
                    TopLayoutController.reduceCount(mainApp.topLayout, foodTypeNumber);
                    centerLayout.gridPane[xLoc][i].setTypeNull();
                } else if (foodTypeNumber == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc][i].setTypeNull();
                }
            }
            break;
        case 8:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 2][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 1][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 1][yLoc].getTypeNumber());

            centerLayout.gridPane[xLoc - 2][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc - 1][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 1][yLoc].setTypeNull();
            // 消除冰块
            for (int i = xLoc - 2; i <= xLoc + 1; i++) {
                if (centerLayout.gridPane[i][yLoc + 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc + 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc + 1].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[i][yLoc - 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc - 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc - 1].setTypeNull();
                    }
                }
            }
            if (centerLayout.gridPane[xLoc - 3][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc - 3][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc - 3][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc - 3][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc - 3][yLoc].setTypeNull();
                }
            }
            if (centerLayout.gridPane[xLoc + 2][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc + 2][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc + 2][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc + 2][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc + 2][yLoc].setTypeNull();
                }
            }
            // 连带竖向消除
            for (int i = centerLayout.sideBuffer; i < centerLayout.sideBuffer + centerLayout.height; i++) {
                int foodTypeNumber = centerLayout.gridPane[xLoc][i].getTypeNumber();
                if (isFoodType(foodTypeNumber)) {
                    TopLayoutController.reduceCount(mainApp.topLayout, foodTypeNumber);
                    centerLayout.gridPane[xLoc][i].setTypeNull();
                } else if (foodTypeNumber == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc][i].setTypeNull();
                }
            }
            break;

        case 9:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 1][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 1][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 2][yLoc].getTypeNumber());

            centerLayout.gridPane[xLoc - 1][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 1][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 2][yLoc].setTypeNull();
            // 消除冰块
            for (int i = xLoc - 1; i <= xLoc + 2; i++) {
                if (centerLayout.gridPane[i][yLoc + 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc + 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc + 1].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[i][yLoc - 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc - 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc - 1].setTypeNull();
                    }
                }
            }
            if (centerLayout.gridPane[xLoc - 2][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc - 2][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc - 2][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc - 2][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc - 2][yLoc].setTypeNull();
                }
            }
            if (centerLayout.gridPane[xLoc + 3][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc + 3][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc + 3][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc + 3][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc + 3][yLoc].setTypeNull();
                }
            }
            // 连带竖向消除
            for (int i = centerLayout.sideBuffer; i < centerLayout.sideBuffer + centerLayout.height; i++) {
                int foodTypeNumber = centerLayout.gridPane[xLoc][i].getTypeNumber();
                if (isFoodType(foodTypeNumber)) {
                    TopLayoutController.reduceCount(mainApp.topLayout, foodTypeNumber);
                    centerLayout.gridPane[xLoc][i].setTypeNull();
                } else if (foodTypeNumber == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc][i].setTypeNull();
                }
            }
            break;
        case 10:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 1][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 2][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 3][yLoc].getTypeNumber());

            centerLayout.gridPane[xLoc + 1][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 2][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 3][yLoc].setTypeNull();
            // 消除冰块
            for (int i = xLoc; i <= xLoc + 3; i++) {
                if (centerLayout.gridPane[i][yLoc + 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc + 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc + 1].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[i][yLoc - 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc - 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc - 1].setTypeNull();
                    }
                }
            }
            if (centerLayout.gridPane[xLoc - 1][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {

                if (centerLayout.gridPane[xLoc - 1][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc - 1][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc - 1][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc - 1][yLoc].setTypeNull();
                }
            }
            if (centerLayout.gridPane[xLoc + 4][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc + 4][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc + 4][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc + 4][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc + 4][yLoc].setTypeNull();
                }
            }
            // 连带竖向消除
            for (int i = centerLayout.sideBuffer; i < centerLayout.sideBuffer + centerLayout.height; i++) {
                int foodTypeNumber = centerLayout.gridPane[xLoc][i].getTypeNumber();
                if (isFoodType(foodTypeNumber)) {
                    TopLayoutController.reduceCount(mainApp.topLayout, foodTypeNumber);
                    centerLayout.gridPane[xLoc][i].setTypeNull();
                } else if (foodTypeNumber == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc][i].setTypeNull();
                }
            }
            break;
        case 11:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 1].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 2].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 3].getTypeNumber());

            centerLayout.gridPane[xLoc][yLoc + 1].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc + 2].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc + 3].setTypeNull();
            // 消除冰块
            for (int i = yLoc; i <= yLoc + 3; i++) {
                if (centerLayout.gridPane[xLoc - 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc - 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[xLoc + 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc + 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }

            }
            if (centerLayout.gridPane[xLoc][yLoc + 4].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc + 4].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc + 4].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc + 4].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc + 4].setTypeNull();
                }
            }

            if (centerLayout.gridPane[xLoc][yLoc - 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc - 1].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc - 1].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc - 1].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc - 1].setTypeNull();
                }
            }
            // 连带横向消除
            for (int i = centerLayout.sideBuffer; i < centerLayout.sideBuffer + centerLayout.width; i++) {
                int foodTypeNumber = centerLayout.gridPane[i][yLoc].getTypeNumber();
                if (isFoodType(foodTypeNumber)) {
                    TopLayoutController.reduceCount(mainApp.topLayout, foodTypeNumber);
                    centerLayout.gridPane[i][yLoc].setTypeNull();
                } else if (foodTypeNumber == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc].setTypeNull();
                }
            }
            break;
        case 12:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 2].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 1].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 1].getTypeNumber());

            centerLayout.gridPane[xLoc][yLoc + 2].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc + 1].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc - 1].setTypeNull();
            // 消除冰块
            for (int i = yLoc - 1; i <= yLoc + 2; i++) {
                if (centerLayout.gridPane[xLoc - 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc - 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[xLoc + 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc + 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }

            }
            if (centerLayout.gridPane[xLoc][yLoc + 3].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc + 3].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc + 3].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc + 3].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc + 3].setTypeNull();
                }
            }

            if (centerLayout.gridPane[xLoc][yLoc - 2].getTypeNumber() == ObjectType.Ice.getNCode()) {

                if (centerLayout.gridPane[xLoc][yLoc - 2].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc - 2].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc - 2].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc - 2].setTypeNull();
                }
            }
            // 连带横向消除
            for (int i = centerLayout.sideBuffer; i < centerLayout.sideBuffer + centerLayout.width; i++) {
                int foodTypeNumber = centerLayout.gridPane[i][yLoc].getTypeNumber();
                if (isFoodType(foodTypeNumber)) {
                    TopLayoutController.reduceCount(mainApp.topLayout, foodTypeNumber);
                    centerLayout.gridPane[i][yLoc].setTypeNull();
                } else if (foodTypeNumber == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc].setTypeNull();
                }
            }
            break;
        case 13:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 1].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 1].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 2].getTypeNumber());

            centerLayout.gridPane[xLoc][yLoc + 1].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc - 1].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc - 2].setTypeNull();
            // 消除冰块
            for (int i = yLoc - 2; i <= yLoc + 1; i++) {
                if (centerLayout.gridPane[xLoc - 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc - 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[xLoc + 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc + 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }

            }
            if (centerLayout.gridPane[xLoc][yLoc + 2].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc + 2].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc + 2].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc + 2].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc + 2].setTypeNull();
                }
            }

            if (centerLayout.gridPane[xLoc][yLoc - 3].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc - 3].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc - 3].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc - 3].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc - 3].setTypeNull();
                }
            }
            // 连带横向消除
            for (int i = centerLayout.sideBuffer; i < centerLayout.sideBuffer + centerLayout.width; i++) {
                int foodTypeNumber = centerLayout.gridPane[i][yLoc].getTypeNumber();
                if (isFoodType(foodTypeNumber)) {
                    TopLayoutController.reduceCount(mainApp.topLayout, foodTypeNumber);
                    centerLayout.gridPane[i][yLoc].setTypeNull();
                } else if (foodTypeNumber == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc].setTypeNull();
                }
            }
            break;
        case 14:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 1].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 2].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 3].getTypeNumber());

            centerLayout.gridPane[xLoc][yLoc - 1].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc - 2].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc - 3].setTypeNull();
            // 消除冰块
            for (int i = yLoc - 3; i <= yLoc; i++) {
                if (centerLayout.gridPane[xLoc - 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc - 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[xLoc + 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc + 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }

            }
            if (centerLayout.gridPane[xLoc][yLoc - 4].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc - 4].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc - 4].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc - 4].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc - 4].setTypeNull();
                }
            }

            if (centerLayout.gridPane[xLoc][yLoc + 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc + 1].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc + 1].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc + 1].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc + 1].setTypeNull();
                }
            }
            // 连带横向消除
            for (int i = centerLayout.sideBuffer; i < centerLayout.sideBuffer + centerLayout.width; i++) {
                int foodTypeNumber = centerLayout.gridPane[i][yLoc].getTypeNumber();
                if (isFoodType(foodTypeNumber)) {
                    TopLayoutController.reduceCount(mainApp.topLayout, foodTypeNumber);
                    centerLayout.gridPane[i][yLoc].setTypeNull();
                } else if (foodTypeNumber == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc].setTypeNull();
                }
            }
            break;

        case 15:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 4][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 3][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 2][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 1][yLoc].getTypeNumber());

            centerLayout.gridPane[xLoc - 4][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc - 3][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc - 2][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc - 1][yLoc].setTypeNull();
            // 消除冰块
            for (int i = xLoc - 4; i <= xLoc; i++) {
                if (centerLayout.gridPane[i][yLoc + 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc + 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc + 1].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[i][yLoc - 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc - 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc - 1].setTypeNull();
                    }
                }
            }
            if (centerLayout.gridPane[xLoc - 5][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc - 5][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc - 5][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc - 5][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc - 5][yLoc].setTypeNull();
                }
            }
            if (centerLayout.gridPane[xLoc + 1][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc + 1][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc + 1][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc + 1][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc + 1][yLoc].setTypeNull();
                }
            }
            // 消除所有相同方块
            int count = removeType(centerLayout, localFoodTypeNumber);
            for (int i = 0; i < count; i++) {
                TopLayoutController.reduceCount(mainApp.topLayout, localFoodTypeNumber);
            }
            break;
        case 16:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 3][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 2][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 1][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 1][yLoc].getTypeNumber());

            centerLayout.gridPane[xLoc - 3][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc - 2][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc - 1][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 1][yLoc].setTypeNull();
            // 消除冰块
            for (int i = xLoc - 3; i <= xLoc + 1; i++) {
                if (centerLayout.gridPane[i][yLoc + 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc + 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc + 1].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[i][yLoc - 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc - 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc - 1].setTypeNull();
                    }
                }
            }
            if (centerLayout.gridPane[xLoc - 4][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc - 4][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc - 4][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc - 4][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc - 4][yLoc].setTypeNull();
                }
            }
            if (centerLayout.gridPane[xLoc + 2][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc + 2][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc + 2][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc + 2][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc + 2][yLoc].setTypeNull();
                }
            }
            // 消除所有相同方块
            count = removeType(centerLayout, localFoodTypeNumber);
            for (int i = 0; i < count; i++) {
                TopLayoutController.reduceCount(mainApp.topLayout, localFoodTypeNumber);
            }
            break;
        case 17:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 2][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 1][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 1][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 2][yLoc].getTypeNumber());

            centerLayout.gridPane[xLoc - 2][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc - 1][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 1][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 2][yLoc].setTypeNull();
            // 消除冰块
            for (int i = xLoc - 2; i <= xLoc + 2; i++) {
                if (centerLayout.gridPane[i][yLoc + 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc + 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc + 1].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[i][yLoc - 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc - 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc - 1].setTypeNull();
                    }
                }
            }
            if (centerLayout.gridPane[xLoc - 3][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc - 3][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc - 3][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc - 3][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc - 3][yLoc].setTypeNull();
                }
            }
            if (centerLayout.gridPane[xLoc + 3][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc + 3][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc + 3][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc + 3][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc + 3][yLoc].setTypeNull();
                }
            }
            // 消除所有相同方块
            count = removeType(centerLayout, localFoodTypeNumber);
            for (int i = 0; i < count; i++) {
                TopLayoutController.reduceCount(mainApp.topLayout, localFoodTypeNumber);
            }
            break;
        case 18:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc - 1][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 1][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 2][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 3][yLoc].getTypeNumber());

            centerLayout.gridPane[xLoc - 1][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 1][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 2][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 3][yLoc].setTypeNull();
            // 消除冰块
            for (int i = xLoc - 1; i <= xLoc + 3; i++) {
                if (centerLayout.gridPane[i][yLoc + 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc + 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc + 1].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[i][yLoc - 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc - 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc - 1].setTypeNull();
                    }
                }
            }
            if (centerLayout.gridPane[xLoc - 2][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc - 2][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc - 2][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc - 2][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc - 2][yLoc].setTypeNull();
                }
            }
            if (centerLayout.gridPane[xLoc + 4][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc + 4][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc + 4][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc + 4][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc + 4][yLoc].setTypeNull();
                }
            }
            // 消除所有相同方块
            count = removeType(centerLayout, localFoodTypeNumber);
            for (int i = 0; i < count; i++) {
                TopLayoutController.reduceCount(mainApp.topLayout, localFoodTypeNumber);
            }
            break;
        case 19:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 1][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 2][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 3][yLoc].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc + 4][yLoc].getTypeNumber());

            centerLayout.gridPane[xLoc + 1][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 2][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 3][yLoc].setTypeNull();
            centerLayout.gridPane[xLoc + 4][yLoc].setTypeNull();
            // 消除冰块
            for (int i = xLoc; i <= xLoc + 4; i++) {
                if (centerLayout.gridPane[i][yLoc + 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc + 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc + 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc + 1].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[i][yLoc - 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[i][yLoc - 1].iceNumber--;
                    if (centerLayout.gridPane[i][yLoc - 1].iceNumber <= 0) {
                        centerLayout.gridPane[i][yLoc - 1].setTypeNull();
                    }
                }
            }
            if (centerLayout.gridPane[xLoc - 1][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc - 1][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc - 1][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc - 1][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc - 1][yLoc].setTypeNull();
                }
            }
            if (centerLayout.gridPane[xLoc + 5][yLoc].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc + 5][yLoc].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc + 5][yLoc].iceNumber--;
                if (centerLayout.gridPane[xLoc + 5][yLoc].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc + 5][yLoc].setTypeNull();
                }
            }
            // 消除所有相同方块
            count = removeType(centerLayout, localFoodTypeNumber);
            for (int i = 0; i < count; i++) {
                TopLayoutController.reduceCount(mainApp.topLayout, localFoodTypeNumber);
            }
            break;
        case 20:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 1].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 2].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 3].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 4].getTypeNumber());

            centerLayout.gridPane[xLoc][yLoc + 1].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc + 2].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc + 3].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc + 4].setTypeNull();
            // 消除冰块
            for (int i = yLoc; i <= yLoc + 4; i++) {
                if (centerLayout.gridPane[xLoc - 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc - 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[xLoc + 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc + 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }

            }
            if (centerLayout.gridPane[xLoc][yLoc + 5].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc + 5].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc + 5].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc + 5].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc + 5].setTypeNull();
                }
            }

            if (centerLayout.gridPane[xLoc][yLoc - 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc - 1].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc - 1].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc - 1].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc - 1].setTypeNull();
                }
            }
            // 消除所有相同方块
            count = removeType(centerLayout, localFoodTypeNumber);
            for (int i = 0; i < count; i++) {
                TopLayoutController.reduceCount(mainApp.topLayout, localFoodTypeNumber);
            }
            break;
        case 21:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 1].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 1].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 2].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 3].getTypeNumber());

            centerLayout.gridPane[xLoc][yLoc - 1].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc + 1].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc + 2].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc + 3].setTypeNull();

            // 消除冰块
            for (int i = yLoc - 1; i <= yLoc + 3; i++) {
                if (centerLayout.gridPane[xLoc - 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc - 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[xLoc + 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc + 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }

            }
            if (centerLayout.gridPane[xLoc][yLoc + 4].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc + 4].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc + 4].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc + 4].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc + 4].setTypeNull();
                }
            }

            if (centerLayout.gridPane[xLoc][yLoc - 2].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc - 2].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc - 2].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc - 2].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc - 2].setTypeNull();
                }
            }
            // 消除所有相同方块
            count = removeType(centerLayout, localFoodTypeNumber);
            for (int i = 0; i < count; i++) {
                TopLayoutController.reduceCount(mainApp.topLayout, localFoodTypeNumber);
            }
            break;
        case 22:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 2].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 1].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 1].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 2].getTypeNumber());

            centerLayout.gridPane[xLoc][yLoc - 2].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc - 1].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc + 1].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc + 2].setTypeNull();

            // 消除冰块
            for (int i = yLoc - 2; i <= yLoc + 2; i++) {
                if (centerLayout.gridPane[xLoc - 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc - 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[xLoc + 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc + 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }

            }
            if (centerLayout.gridPane[xLoc][yLoc + 3].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc + 3].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc + 3].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc + 3].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc + 3].setTypeNull();
                }
            }

            if (centerLayout.gridPane[xLoc][yLoc - 3].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc - 3].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc - 3].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc - 3].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc - 3].setTypeNull();
                }
            }
            // 消除所有相同方块
            count = removeType(centerLayout, localFoodTypeNumber);
            for (int i = 0; i < count; i++) {
                TopLayoutController.reduceCount(mainApp.topLayout, localFoodTypeNumber);
            }
            break;
        case 23:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 3].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 2].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 1].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc + 1].getTypeNumber());

            centerLayout.gridPane[xLoc][yLoc - 3].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc - 2].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc - 1].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc + 1].setTypeNull();

            // 消除冰块
            for (int i = yLoc - 3; i <= yLoc + 1; i++) {
                if (centerLayout.gridPane[xLoc - 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc - 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[xLoc + 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc + 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }
            }
            if (centerLayout.gridPane[xLoc][yLoc + 2].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc + 2].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc + 2].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc + 2].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc + 2].setTypeNull();
                }
            }

            if (centerLayout.gridPane[xLoc][yLoc - 4].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc - 4].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc - 4].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc - 4].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc - 4].setTypeNull();
                }
            }
            // 消除所有相同方块
            count = removeType(centerLayout, localFoodTypeNumber);
            for (int i = 0; i < count; i++) {
                TopLayoutController.reduceCount(mainApp.topLayout, localFoodTypeNumber);
            }
            break;
        case 24:
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 4].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 3].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 2].getTypeNumber());
            TopLayoutController.reduceCount(mainApp.topLayout, centerLayout.gridPane[xLoc][yLoc - 1].getTypeNumber());

            centerLayout.gridPane[xLoc][yLoc - 4].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc - 3].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc - 2].setTypeNull();
            centerLayout.gridPane[xLoc][yLoc - 1].setTypeNull();

            // 消除冰块
            for (int i = yLoc - 4; i <= yLoc; i++) {
                if (centerLayout.gridPane[xLoc - 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc - 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc - 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }
                if (centerLayout.gridPane[xLoc + 1][i].getTypeNumber() == ObjectType.Ice.getNCode()) {
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber > 0) {
                        TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                    }
                    centerLayout.gridPane[xLoc + 1][i].iceNumber--;
                    if (centerLayout.gridPane[xLoc + 1][i].iceNumber <= 0) {
                        centerLayout.gridPane[xLoc - 1][i].setTypeNull();
                    }
                }

            }
            if (centerLayout.gridPane[xLoc][yLoc + 1].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc + 1].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc + 1].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc + 1].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc + 1].setTypeNull();
                }
            }

            if (centerLayout.gridPane[xLoc][yLoc - 5].getTypeNumber() == ObjectType.Ice.getNCode()) {
                if (centerLayout.gridPane[xLoc][yLoc - 5].iceNumber > 0) {
                    TopLayoutController.reduceCount(mainApp.topLayout, ObjectType.Ice.getNCode());
                }
                centerLayout.gridPane[xLoc][yLoc - 5].iceNumber--;
                if (centerLayout.gridPane[xLoc][yLoc - 5].iceNumber <= 0) {
                    centerLayout.gridPane[xLoc][yLoc - 5].setTypeNull();
                }
            }
            // 消除所有相同方块
            count = removeType(centerLayout, localFoodTypeNumber);
            for (int i = 0; i < count; i++) {
                TopLayoutController.reduceCount(mainApp.topLayout, localFoodTypeNumber);
            }
            break;
        }
        System.out.println(lineType);

        // centerLayout.gridPane.get(xLoc).get(0).setType(ObjectType.Wall);
    }

    /**
     * @Description:判断一个方块是否为食物
     * 
     * @param:int foodTypeNumber 方块的种类编号
     * 
     * @return: boolean True: 是食物
     */
    public static boolean isFoodType(int foodTypeNumber) {
        return !((foodTypeNumber == ObjectType.Wall.getNCode()) || (foodTypeNumber == ObjectType.Ice.getNCode())
                || (foodTypeNumber == ObjectType.Null.getNCode()));
    }

    /**
     * @Description:判断一个方块是否可以移动/其是否为墙或者冰块
     * 
     * @param:int foodTypeNumber 方块的种类编号
     * 
     * @return: boolean True: 可以移动
     */
    public static boolean isFoodTypeMoveAvailable(int foodTypeNumber) {
        return !((foodTypeNumber == ObjectType.Wall.getNCode()) || (foodTypeNumber == ObjectType.Ice.getNCode()));
    }

    /**
     * @Description:判断一个方块是否为Null
     * 
     * @param:int foodTypeNumber 方块的种类编号
     * 
     * @return: boolean True: 为Null
     */
    public static boolean isFoodTypeNull(int foodTypeNumber) {
        return foodTypeNumber == ObjectType.Null.getNCode();
    }

    /**
     * @Description:生成随机坐标
     * 
     * @param:
     * 
     * @param:
     * 
     * @param:
     * 
     * @param:
     * 
     * @return:坐标数组{xPos, yPos}
     */
    public static int[] randomCoordinate(int xStart, int yStart, int xLength, int yLength) {
        Random random = new Random();
        int xPos = random.nextInt(xLength) + xStart;
        int yPos = random.nextInt(yLength) + yStart;
        return new int[] { xPos, yPos };
    }

    /**
     * @Description: 禁止棋盘中所有方块的点击事件
     * 
     * @param:CenterLayout centerlayout副本(权限)
     * 
     * @return:none
     */
    public static void disableAllSquare(CenterLayout centerLayout) {
        for (int i = centerLayout.sideBuffer; i < centerLayout.sideBuffer + centerLayout.width; i++) {
            for (int j = centerLayout.sideBuffer; j < centerLayout.sideBuffer + centerLayout.height; j++) {
                centerLayout.gridPane[i][j].setDisable(true);
            }
        }
    }

    /**
     * @Description: 允许棋盘中所有方块的点击事件
     * 
     * @param:CenterLayout centerlayout副本(权限)
     * 
     * @return:none
     */
    public static void enableAllSquare(CenterLayout centerLayout) {
        for (int i = centerLayout.sideBuffer; i < centerLayout.sideBuffer + centerLayout.width; i++) {
            for (int j = centerLayout.sideBuffer; j < centerLayout.sideBuffer + centerLayout.height; j++) {
                centerLayout.gridPane[i][j].setDisable(false);
            }
        }
    }

    /*
     * @Description:删除centerLayout中foodType类型方块
     * 
     * @param:
     * 
     * @param:
     * 
     * @return:删除的个数
     */
    public static int removeType(CenterLayout centerLayout, int foodTypeNumber) {
        int count = 0;
        for (int i = centerLayout.sideBuffer; i < centerLayout.sideBuffer + centerLayout.width; i++) {
            for (int j = centerLayout.sideBuffer; j < centerLayout.sideBuffer + centerLayout.height; j++) {
                if (foodTypeNumber == centerLayout.gridPane[i][j].getTypeNumber()) {
                    centerLayout.gridPane[i][j].setTypeNull();
                    count++;
                }
            }
        }
        return count;
    }
}