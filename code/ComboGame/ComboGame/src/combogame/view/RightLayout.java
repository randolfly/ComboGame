/* File Info 
 * Author:      Randolf 
 * CreateTime:  2019/11/20 下午11:31:12 
 * LastEditor:  Randolf 
 * ModifyTime:  2019/11/20 下午11:31:21 
 * Description: 放置在右边的Tank
*/
package combogame.view;

import combogame.MainApp;
import combogame.model.ObjectType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class RightLayout {
    public static final String tankBaseUrl = MainApp.class.getResource("images/tank-base.png").toExternalForm();
    public static final String tankHeadUrl = MainApp.class.getResource("images/tank-head.png").toExternalForm();

    public MainApp mainApp;

    ObjectType[] foodTypes = ObjectType.values();

    public StackPane stackPane;

    public Image tankBaseImage;
    public Image tankHeadImage;

    public ImageView tankBase;
    public ImageView tankHead;
    // 坦克图片位置
    public final double tankXLoc = 947.0;
    public final double tankYLoc = 452.0;

    public RightLayout(MainApp mainApp) {
        this.mainApp = mainApp;

        // load tank picture
        stackPane = new StackPane();
        tankBaseImage = new Image(tankBaseUrl);
        tankHeadImage = new Image(tankHeadUrl);
        tankBase = new ImageView();
        tankHead = new ImageView();
        tankBase.setImage(tankBaseImage);
        tankHead.setImage(tankHeadImage);

        stackPane.getChildren().addAll(tankBase, tankHead);
    }

}