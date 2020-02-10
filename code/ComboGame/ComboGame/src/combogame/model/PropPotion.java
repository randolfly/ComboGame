/* File Info 
 * Author:      Randolf 
 * CreateTime:  2019/11/19 上午12:48:27 
 * LastEditor:  Randolf 
 * ModifyTime:  2019/11/19 上午12:48:42 
 * Description: 道具-地雷
*/
package combogame.model;

// import javafx.scene.control.Button;
import com.jfoenix.controls.JFXButton;

public class PropPotion extends JFXButton implements PropTypeInterface<String> {

    String name = "potion";
    String image = "-fx-background-image: url('combogame/images/potion.png');";

    @Override
    public String getTypeName() {
        // TODO Auto-generated method stub
        return name;
    }

    @Override
    public String getImageType() {
        return this.image;
    }

}