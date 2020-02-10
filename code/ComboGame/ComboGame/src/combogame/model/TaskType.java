/* File Info 
 * Author:      Randolf 
 * CreateTime:  11/29/2019, 12:33:52 AM 
 * LastEditor:  Randolf 
 * ModifyTime:  11/29/2019, 12:33:55 AM 
 * Description: 定义游戏任务类
*/

package combogame.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TaskType {
    Easy(1), Medium(2), Difficult(3);

    // 本局游戏总步数
    public int stepNumber;
    // 本局游戏目标
    // 本局游戏消除食物目标~ArrayList排序按照Object中来，如foodAmount.get(0)表示ObjectType.values().get(0)的种类所需的个数
    public List<Integer> foodAmount;
    // 本局游戏食物种类
    public int foodNumber;
    // 本局游戏消除冰块目标
    public int iceAmount;
    // 冰块位置:x,y,冰块厚度
    public ArrayList<int[]> icePosition;
    // 墙壁位置:x,y
    public ArrayList<int[]> wallPosition;

    private TaskType(int taskNumber) {
        switch (taskNumber) {
        case 1:
            this.stepNumber = 20;
            this.foodNumber = 3;
            this.foodAmount = Arrays.asList(5, 5, 5);
            this.iceAmount = 0;
            this.icePosition = new ArrayList<int[]>();
            this.wallPosition = new ArrayList<int[]>();
            break;
        case 2:
            this.stepNumber = 15;
            this.foodNumber = 4;
            this.foodAmount = Arrays.asList(10, 10, 10, 10);
            this.iceAmount = 4;
            this.icePosition = new ArrayList<int[]>();
            this.wallPosition = new ArrayList<int[]>();
            this.icePosition.add(new int[] { 3, 4, 3 });
            this.icePosition.add(new int[] { 5, 4, 3 });
            this.icePosition.add(new int[] { 7, 4, 3 });

            this.wallPosition.add(new int[] { 5, 5 });
            this.wallPosition.add(new int[] { 6, 5 });
            this.wallPosition.add(new int[] { 7, 5 });
            break;
        case 3:
            this.stepNumber = 8;
            this.foodNumber = 5;
            this.foodAmount = Arrays.asList(10, 10, 10, 10, 10);
            this.iceAmount = 6;
            this.icePosition = new ArrayList<int[]>();
            this.wallPosition = new ArrayList<int[]>();
            this.icePosition.add(new int[] { 3, 4, 3 });
            this.icePosition.add(new int[] { 5, 4, 3 });
            this.icePosition.add(new int[] { 7, 4, 3 });
            this.icePosition.add(new int[] { 0, 0, 5 });
            this.icePosition.add(new int[] { 4, 0, 5 });
            this.icePosition.add(new int[] { 7, 0, 5 });

            this.wallPosition.add(new int[] { 5, 5 });
            this.wallPosition.add(new int[] { 6, 5 });
            this.wallPosition.add(new int[] { 7, 5 });
            break;
        }
    }
}
