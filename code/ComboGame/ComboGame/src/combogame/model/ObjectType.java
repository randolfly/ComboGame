/* File Info 
 * Author:      Randolf 
 * CreateTime:  2019/10/25 下午9:40:33 
 * LastEditor:  Randolf
 * ModifyTime:  2019/11/19 上午12:34:29
 * Description: 
*/
package combogame.model;

import java.util.ArrayList;
import java.util.HashMap;

import combogame.MainApp;

/**
 * @Author: rando
 * @Date: 2019/10/25 21:28
 * @Description:
 */
public enum ObjectType {
    // 物体的种类,Null表示物体空了->保证每一个方块都有物体种类!!
    Apple(1, "Apple"), Banana(2, "Banana"), Durian(3, "Durian"), Grape(4, "Grape"), Pear(5, "Pear"), Null(6, "Null"),
    Wall(7, "Wall"), Ice(8, "Ice");

    // "-fx-background-image: url('combogame/images/grape.png');",
    // "-fx-background-image: url('combogame/images/pear.png');",
    public static String[] imageTypes = { "-fx-background-image: url('combogame/images/fruit/apple.png');",
            "-fx-background-image: url('combogame/images/fruit/banana.png');",
            "-fx-background-image: url('combogame/images/fruit/durian.png');",
            "-fx-background-image: url('combogame/images/fruit/grape.png');",
            "-fx-background-image: url('combogame/images/fruit/pear.png');",
            "-fx-background-image: url('combogame/images/fruit/null.png');",
            "-fx-background-image: url('combogame/images/fruit/wall.png');",
            "-fx-background-image: url('combogame/images/fruit/water.png');" };
    public static ArrayList<String> imageUrls;

    // 物体的编号
    private int nCode;
    // 物体名字
    private String name;
    // 物体名字与编号对应的hashMap
    public static HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
    // 食物的HashMap
    public static HashMap<Integer, String> foodHashMap = new HashMap<Integer, String>();

    // 初始化HashMap与Url
    static {
        imageUrls = new ArrayList<String>(ObjectType.values().length);
        for (final ObjectType objectType : ObjectType.values()) {
            hashMap.put(objectType.getNCode(), objectType.getName());
            imageUrls.add(MainApp.class.getResource("images").toExternalForm() + "/"
                    + objectType.getName().toLowerCase() + ".png");
        }
        for (int i = 0; i < ObjectType.values().length - 3; i++) {
            foodHashMap.put(ObjectType.values()[i].getNCode(), ObjectType.values()[i].getName());
        }

    }

    private ObjectType(final int nCode, final String name) {
        this.nCode = nCode;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.valueOf(this.nCode) + ":" + this.name;
    }

    public int getNCode() {
        return nCode;
    }

    public String getName() {
        return this.name;
    }

    public static int getTypeLength() {
        return ObjectType.values().length;
    }

    // wall、Ice、Null
    public static int getFoodTypeLength() {
        return ObjectType.values().length - 3;
    }

    /**
     * @Description:返回食物种类对应的HashMap
     * 
     * @return:HashMap<String,int>
     */
    public static HashMap<Integer, String> getFoodTypeHashMap() {
        return ObjectType.foodHashMap;
    }

    /**
     * @Description:返回所有种类对应的HashMap
     * 
     * @return:HashMap<String,int>
     */
    public static HashMap<Integer, String> getTypeHashMap() {
        return ObjectType.hashMap;
    }

    //// 放弃不用代码~改为ncode引用即可
    // /*
    // * @Description: 获取对应string下的css代码
    // *
    // * @param:String name 事物的种类
    // *
    // * @return:String css 对应物体种类的css;"null"代表未查找到, 但我提倡非防御式编程,没做更多检查
    // */
    // public static String getImageTypeByName(String name) {
    // switch (name.toLowerCase()) {
    // case "apple":
    // return imageTypes[0];
    // case "banana":
    // return imageTypes[1];
    // case "durian":
    // return imageTypes[2];
    // case "grape":
    // return imageTypes[3];
    // case "pear":
    // return imageTypes[4];
    // default:
    // return "null";
    // }
    // }

    /**
     * @Description: 获取对应number下的css代码
     * 
     * @param:int number 事物的种类
     * 
     * @return:String css 对应物体种类的css;"null"代表未查找到, 但我提倡非防御式编程,没做更多检查
     */
    public static String getImageTypeByNumber(final int number) {
        return imageTypes[number - 1];
    }
}
