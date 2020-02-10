/* File Info 
 * Author:      Randolf 
 * CreateTime:  2019/12/27 下午7:20:44 
 * LastEditor:  Randolf
 * ModifyTime:  2019/12/27 下午10:31:00
 * Description: 
*/
package combogame.controller;

import combogame.MainApp;
import combogame.model.ObjectType;
import combogame.view.TopLayout;
import javafx.scene.paint.Color;

public class TopLayoutController {
    public MainApp mainApp;
    public TopLayout topLayout;

    public TopLayoutController(MainApp mainApp) {
        this.mainApp = mainApp;
        this.topLayout = mainApp.topLayout;
    }

    /**
     * @Description:减少TopLayout中的未完成任务的个数，如苹果-1
     * 
     * @param:int objectTypeNumber 物资种类名单
     * 
     * @return:none
     */
    public static void reduceCount(TopLayout topLayout, int objectTypeNumber) {
        if (objectTypeNumber == ObjectType.Ice.getNCode()) {
            topLayout.iceAmount--;
        } else if ((objectTypeNumber == ObjectType.Null.getNCode() || objectTypeNumber == ObjectType.Wall.getNCode())) {
            return;
        } else {
            topLayout.foodAmount[objectTypeNumber - 1]--;
        }
    }

    public static void refreshTopLayout(MainApp mainApp) {
        // 刷新topLayout
        if (mainApp.topLayout.stepNumber > 0) {
            mainApp.topLayout.stepText.setText(String.valueOf(mainApp.topLayout.stepNumber));
        } else {
            mainApp.topLayout.stepText.setText(String.valueOf(0));
            // 设置背景为红色！
            mainApp.topLayout.stepText.setTextFill(Color.web("#ee1111"));
        }
        if (mainApp.topLayout.iceAmount > 0) {
            mainApp.topLayout.iceText.setText(String.valueOf(mainApp.topLayout.iceAmount));
        } else {
            mainApp.topLayout.iceText.setText(String.valueOf(0));
            // 设置背景为红色！
            mainApp.topLayout.iceText.setTextFill(Color.web("#ee1111"));
        }
        for (int i = 0; i < mainApp.topLayout.foodAmount.length; i++) {
            if (mainApp.topLayout.foodAmount[i] > 0) {
                mainApp.topLayout.foodTexts.get(i).setText(String.valueOf(mainApp.topLayout.foodAmount[i]));
            } else {
                mainApp.topLayout.foodTexts.get(i).setText(String.valueOf(0));
                mainApp.topLayout.foodTexts.get(i).setTextFill(Color.web("#ee1111"));
            }
        }
    }
}