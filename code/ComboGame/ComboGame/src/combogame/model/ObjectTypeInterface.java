package combogame.model;

/**
 * @Auther: rando
 * @Date: 2019/10/25 21:28
 * @Description:
 */
public interface ObjectTypeInterface<Type> {
    // 返回对应的enum下的种类的键值
    public Type getType();
    // 返回enum下的名字
    public String getTypeName();
    // 设置object的Type(按照type更改object)
    public void setType(Type type);
}
