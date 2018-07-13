package cn.zcp.bgmange.frame.pojo;

import java.util.List;

/**
 * 系统菜单实体类的扩展类
 * Created by Administrator on 2017/11/24.
 */
public class SysMenuCustom extends SysMenu {
    //子节点
    private List<SysMenuCustom> children;

    //state：节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
    private String state;

    public List<SysMenuCustom> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenuCustom> children) {
        this.children = children;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
