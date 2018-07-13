package cn.zcp.wechartapi.base.pojo;

/**
 * 下拉列表框实体类
 * Created by Administrator on 2017/10/31.
 */
public class ComboBox {
    //值
    private String id;
    //文本
    private String text;

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "ComboBox{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
