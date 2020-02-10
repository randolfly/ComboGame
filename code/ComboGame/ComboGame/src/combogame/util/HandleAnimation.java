/* File Info 
 * Author:      Randolf 
 * CreateTime:  2019/11/21 下午7:15:42 
 * LastEditor:  Randolf 
 * ModifyTime:  2019/11/21 下午7:15:51 
 * Description: 处理动画类
*/
package combogame.util;

import combogame.model.FallAnimation;
import combogame.model.FoodSquare;

public class HandleAnimation {
    public static void handleFallAnimation(FoodSquare foodSquare) {
        Thread foodSquareThread = new Thread(new FallAnimation(foodSquare));
        foodSquareThread.start();
    }
}