/* File Info 
 * Author:      Randolf 
 * CreateTime:  2019/11/19 上午12:47:21 
 * LastEditor:  Randolf 
 * ModifyTime:  2019/11/19 上午12:47:32 
 * Description: 道具-火箭
*/
package combogame.model;

// import javafx.scene.control.Button;
import com.jfoenix.controls.JFXButton;

public class PropRocket extends JFXButton implements PropTypeInterface<String> {

    String name = "rocket";
    String image = "-fx-background-image: url('combogame/images/rocket.png');";

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