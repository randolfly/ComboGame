/* File Info 
 * Author:      Randolf 
 * CreateTime:  2019/11/22 下午5:26:58 
 * LastEditor:  Randolf 
 * ModifyTime:  2019/11/22 下午5:27:07 
 * Description: 下落动画model类
*/
package combogame.model;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class FallAnimation implements Runnable {

    // 准备下落的物块
    FoodSquare square;
    TranslateTransition translation;

    public FallAnimation(FoodSquare square) {
        this.square = square;
        this.translation = new TranslateTransition();
    }

    @Override
    public void run() {
        handleFall();
    }

    public void handleFall() {
        translation.setDuration(Duration.seconds(1.5d));
        translation.setNode(square);
        translation.setFromY(0.0d);
        // translation.setByY(square.getHeight() + square.getPadding().getTop() +
        // square.getPadding().getBottom());
        translation.setByY(60.0d);
        translation.play();
    }

}