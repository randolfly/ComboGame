/* File Info 
 * Author:      Randolf 
 * CreateTime:  11/25/2019, 8:10:17 PM 
 * LastEditor:  Randolf
 * ModifyTime:  2019/12/26 下午3:26:36
 * Description: 
*/
package combogame.model;

// import javafx.scene.control.Button;
import com.jfoenix.controls.JFXButton;

/**
 * @Author: rando
 * @Date: 2019/10/25 18:43
 * @Description:
 */
public class FoodSquare extends JFXButton implements ObjectTypeInterface<ObjectType> {
    // 默认设置为Apple
    ObjectType objectType;
    // Css格式
    String cssType;

    // 是否在棋盘中被卡住
    public boolean isStuck = false;

    // xLoc:0--(height-1)...
    public int xLoc, yLoc;
    // 冰块的层数, 食物为0, Wall为999999
    public int iceNumber;

    public FoodSquare(ObjectType objectType, int iceNumber) {
        super();
        this.objectType = objectType;
        this.cssType = ObjectType.getImageTypeByNumber(this.getTypeNumber());
        xLoc = 0;
        yLoc = 0;
        this.isStuck = false;
        this.iceNumber = iceNumber;
    }

    public FoodSquare(ObjectType objectType, String text) {
        super(text);
        this.objectType = objectType;
        this.cssType = ObjectType.getImageTypeByNumber(this.getTypeNumber());
        xLoc = 0;
        yLoc = 0;
        this.isStuck = false;
        this.iceNumber = 99999;
    }

    public FoodSquare(FoodSquare foodSquare) {
        this.cssType = foodSquare.cssType;
        this.objectType = foodSquare.objectType;
        this.xLoc = foodSquare.xLoc;
        this.yLoc = foodSquare.yLoc;
        this.isStuck = false;
        this.iceNumber = foodSquare.iceNumber;
    }

    /**
     * @Description:默认构造函数,食物种类为无
     * 
     * @return:none
     */
    public FoodSquare() {
        super();
        this.objectType = ObjectType.Null;
        this.cssType = ObjectType.getImageTypeByNumber(this.getTypeNumber());
        xLoc = 0;
        yLoc = 0;
        this.isStuck = false;
        this.iceNumber = 0;
    }

    public FoodSquare(int xLoc, int yLoc) {
        super();
        this.objectType = ObjectType.Null;
        this.cssType = ObjectType.getImageTypeByNumber(this.getTypeNumber());
        this.xLoc = xLoc;
        this.isStuck = false;
        this.yLoc = yLoc;
        this.iceNumber = 0;
    }

    /**
     * @Description:返回foodType
     * 
     * @return:Objectype 对应FoodType
     */
    @Override
    public ObjectType getType() {
        return objectType;
    }

    /**
     * @Description:返回foodType的编号,方便比较
     * 
     * @return:String 对应FoodType的编号
     */
    public int getTypeNumber() {
        return objectType.getNCode();
    }

    /**
     * @Description:返回foodType的名字
     * 
     * @return:String 对应FoodType的名字
     */
    @Override
    public String getTypeName() {
        return objectType.getName();
    }

    public String getCssType() {
        return this.cssType;
    }

    /**
     * @Description: 设置方块的FoodType，注意并不更新UI，UI更新统一放到更新线程中
     * 
     * @param:String objectType 对应食物种类的名字
     * 
     * @return: none
     */
    @Override
    public void setType(ObjectType objectType) {
        this.objectType = objectType;
        this.cssType = ObjectType.getImageTypeByNumber(this.getTypeNumber());
    }

    public void setTypeByName(String objectType) {
        this.objectType = ObjectType.valueOf(objectType);
        this.cssType = ObjectType.getImageTypeByNumber(this.getTypeNumber());

    }

    public void setTypeByNumber(int foodTypeNumber) {
        String foodTypeName = ObjectType.hashMap.get(foodTypeNumber);
        this.setTypeByName(foodTypeName);

    }

    /*
     * @Description: 设置该方块种类为Null，即移除该方块
     * 
     * @return: none
     */
    public void setTypeNull() {
        this.objectType = ObjectType.Null;
        this.cssType = ObjectType.getImageTypeByNumber(this.getTypeNumber());
        this.iceNumber = 0;
    }

    /**
     * @Description: 设置square的xLoc
     * 
     * @param:int x x位置
     * 
     * @return:int x位置
     */
    public int setXLoc(int x) {
        this.xLoc = x;
        return xLoc;
    }

    /**
     * @Description: 设置square的yLoc
     * 
     * @param:int y y位置
     * 
     * @return:int y位置
     */
    public int setYLoc(int y) {
        this.yLoc = y;
        return yLoc;
    }

    /**
     * @Description: 设置square的Loc
     * 
     * @param:int x x位置; int y y位置
     * 
     * @return:none
     */
    public void setLoc(int x, int y) {
        this.xLoc = x;
        this.yLoc = y;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ObjectType: " + this.getTypeName() + "； ");
        sb.append("Location: (" + this.xLoc + ", " + this.yLoc + ")");
        return sb.toString();
    }

    /**
     * @Description: 刷新该格子的UI
     * 
     * @return:none
     */
    public void refreshSquareUI() {
        switch (this.iceNumber) {
        case 0:
            this.setStyle(this.cssType + "-fx-background-color: rgba(62, 125, 129, 0.4);");
            break;
        case 1:
            this.setStyle(this.cssType + "-fx-background-color: rgba(10, 150, 100, 0.6);");
            break;
        case 2:
            this.setStyle(this.cssType + "-fx-background-color: rgba(170, 180, 150, 0.6);");
            break;
        case 3:
            this.setStyle(this.cssType + "-fx-background-color: rgba(190, 60, 190, 0.8);");
            break;
        case 4:
            this.setStyle(this.cssType + "-fx-background-color: rgba(180,180, 180, 0.9);");
            break;
        case 5:
            this.setStyle(this.cssType + "-fx-background-color: rgba(250,200, 10, 0.8);");
            break;
        default:
            this.setStyle(this.cssType + "-fx-background-color: rgba(10, 10, 10, 0.8);");
            break;
        }
    }

    public void setUnMoveable() {
        this.isStuck = true;
    }

    public void setMoveable() {
        this.isStuck = false;
    }
}
