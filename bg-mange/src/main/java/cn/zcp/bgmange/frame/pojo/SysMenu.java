package cn.zcp.bgmange.frame.pojo;

import java.util.Date;

/**
 * 系统菜单实体类
 * Created by Administrator on 2017/11/24.
 */
public class SysMenu {
    //主键ID
    private Long sysMenuId;
    //系统菜单编号
    private String sysMenuNo;
    //系统菜单名
    private String sysMenuName;
    //系统菜单的链接
    private String sysMenuUrl;
    //系统菜单的父菜单编号（导航节点的为0)
    private String parentNo;
    //菜单级别（00导航节点,01一级,02二级,03三级,04按钮）
    private String levelCode;
    //系统菜单的图标
    private String sysMenuIconCls;
    //系统菜单的执行的js
    private String sysMenuExecuteJs;
    //系统菜单的打开方式
    private String sysMenuOpenStyle;
    //使用的状态(00禁用,01可用)
    private String useStatus;
    //创建时间
    private Date createTime;

    public Long getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(Long sysMenuId) {
        this.sysMenuId = sysMenuId;
    }

    public String getSysMenuNo() {
        return sysMenuNo;
    }

    public void setSysMenuNo(String sysMenuNo) {
        this.sysMenuNo = sysMenuNo;
    }

    public String getSysMenuName() {
        return sysMenuName;
    }

    public void setSysMenuName(String sysMenuName) {
        this.sysMenuName = sysMenuName;
    }

    public String getSysMenuUrl() {
        return sysMenuUrl;
    }

    public void setSysMenuUrl(String sysMenuUrl) {
        this.sysMenuUrl = sysMenuUrl;
    }

    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getSysMenuIconCls() {
        return sysMenuIconCls;
    }

    public void setSysMenuIconCls(String sysMenuIconCls) {
        this.sysMenuIconCls = sysMenuIconCls;
    }

    public String getSysMenuExecuteJs() {
        return sysMenuExecuteJs;
    }

    public void setSysMenuExecuteJs(String sysMenuExecuteJs) {
        this.sysMenuExecuteJs = sysMenuExecuteJs;
    }

    public String getSysMenuOpenStyle() {
        return sysMenuOpenStyle;
    }

    public void setSysMenuOpenStyle(String sysMenuOpenStyle) {
        this.sysMenuOpenStyle = sysMenuOpenStyle;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
