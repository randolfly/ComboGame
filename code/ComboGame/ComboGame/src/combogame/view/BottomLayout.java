/* File Info 
 * Author:      Randolf 
 * CreateTime:  2019/11/6 下午8:25:39 
 * LastEditor:  Randolf 
 * ModifyTime:  2019/11/19 上午12:39:25 
 * Description: 
*/
package combogame.view;

import combogame.model.ObjectType;
import combogame.model.PlateSquare;
import combogame.model.PropMines;
import combogame.model.PropPotion;
import combogame.model.PropRainbow;
import combogame.model.PropRocket;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import com.jfoenix.controls.JFXComboBox;

/**
 * @Auther: rando
 * @Date: 2019/11/6 20:25
 * @Description:
 */
// ! 注意, 部件中的Action留在Controller中绑定
public class BottomLayout {
    // 底部容器
    public HBox bottomPane;
    public PlateSquare plate;
    public PlateSquare startSquare;
    public PlateSquare pauseSquare;
    public PlateSquare stopSquare;

    public PropRocket propRocket;
    public PropMines propMines;
    public PropRainbow propRainbow;
    public PropPotion propPotion;

    public JFXComboBox<Label> taskTypeComboBox;
    ObjectType[] foodTypes = ObjectType.values();

    public BottomLayout() {
        bottomPane = new HBox();
        // -----盘子-----
        // 初始时假定为Banana
        plate = new PlateSquare(ObjectType.Banana);
        // plate setting
        plate.setPrefSize(80, 80);
        plate.setStyle(plate.getCssType() + " -fx-background-size: 80px;");

        // -----道具-----
        // ---火箭---
        propRocket = new PropRocket();
        propRocket.setPrefSize(80, 80);
        propRocket.setStyle(propRocket.getImageType() + " -fx-background-size: 80px;");

        // ---地雷---
        propMines = new PropMines();
        propMines.setPrefSize(80, 80);
        propMines.setStyle(propMines.getImageType() + " -fx-background-size: 80px;");

        // ---彩虹---
        propRainbow = new PropRainbow();
        propRainbow.setPrefSize(80, 80);
        propRainbow.setStyle(propRainbow.getImageType() + " -fx-background-size: 80px;");

        // ---药水---
        propPotion = new PropPotion();
        propPotion.setPrefSize(80, 80);
        propPotion.setStyle(propPotion.getImageType() + " -fx-background-size: 80px;");

        // 开始游戏
        this.startSquare = new PlateSquare();

        this.startSquare.setText("START");
        this.startSquare.setTextFill(Color.FIREBRICK);
        this.startSquare.setFont(Font.font("Arial", 24));
        this.startSquare.setPrefSize(80, 80);
        // 结束游戏
        this.stopSquare = new PlateSquare();
        this.stopSquare.setText("END");
        this.stopSquare.setTextFill(Color.FIREBRICK);
        this.stopSquare.setFont(Font.font("Arial", 24));
        this.stopSquare.setPrefSize(80, 80);
        // 暂停游戏
        this.pauseSquare = new PlateSquare();
        this.pauseSquare.setText("PAUSE");
        this.pauseSquare.setTextFill(Color.FIREBRICK);
        this.pauseSquare.setFont(Font.font("Arial", 24));
        this.pauseSquare.setPrefSize(80, 80);
        // 选择难度
        this.taskTypeComboBox = new JFXComboBox<>();
        taskTypeComboBox.getItems().add(new Label("EASY"));
        taskTypeComboBox.getItems().add(new Label("MEDIUM"));
        taskTypeComboBox.getItems().add(new Label("DIFFICULT"));
        taskTypeComboBox.setPromptText("TASKTYPE");
        taskTypeComboBox.setPrefSize(80, 80);

        // bottomPane setting
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.getChildren().addAll(startSquare, stopSquare, propRocket, propMines, plate, propRainbow, propPotion,
                pauseSquare, taskTypeComboBox);
    }

    public Pane getBottomPane() {
        return this.bottomPane;
    }
}
