/* File Info 
 * Author:      Randolf 
 * CreateTime:  2019/11/19 上午12:23:53 
 * LastEditor:  Randolf
 * ModifyTime:  2019/11/19 上午12:24:44
 * Description: 道具类需要实现的接口
*/
package combogame.model;

public interface PropTypeInterface<Type> {
    // 返回道具类的名字
    public Type getTypeName();
    // 返回道具类的图片
    public Type getImageType();
}
