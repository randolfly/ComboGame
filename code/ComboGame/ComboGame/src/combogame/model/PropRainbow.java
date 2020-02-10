/* File Info 
 * Author:      Randolf 
 * CreateTime:  2019/11/19 上午12:29:01 
 * LastEditor:  Randolf 
 * ModifyTime:  2019/11/19 上午12:29:04 
 * Description: 道具-彩虹
*/

package combogame.model;

// import javafx.scene.control.Button;
import com.jfoenix.controls.JFXButton;

public class PropRainbow extends JFXButton implements PropTypeInterface<String> {

    String name = "rainbow";
    String image = "-fx-background-image: url('combogame/images/rainbow.png');";

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