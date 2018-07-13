package cn.zcp.bgmange.frame.pojo;


import cn.zcp.bgmange.base.pojo.BaseQueryVo;

/**
 * 系统菜单实体类的包装对象
 * Created by Administrator on 2017/11/24.
 */
public class SysMenuQueryVo extends BaseQueryVo {
    //为了系统 可扩展性，对原始生成的pojo进行扩展
    private SysMenuCustom sysMenuCustom;
    //1是左边菜单请求
    private String requestFlag;

    public SysMenuCustom getSysMenuCustom() {
        return sysMenuCustom;
    }

    public void setSysMenuCustom(SysMenuCustom sysMenuCustom) {
        this.sysMenuCustom = sysMenuCustom;
    }

    public String getRequestFlag() {
        return requestFlag;
    }

    public void setRequestFlag(String requestFlag) {
        this.requestFlag = requestFlag;
    }

}
