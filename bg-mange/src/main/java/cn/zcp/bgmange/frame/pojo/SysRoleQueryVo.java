package cn.zcp.bgmange.frame.pojo;


import cn.zcp.bgmange.base.pojo.DatagridPage;

/**
 * 系统角色实体类的包装对象
 * Created by Administrator on 2017/11/28.
 */
public class SysRoleQueryVo  extends DatagridPage {
    private SysRoleCustom sysRoleCustom;

    public SysRoleCustom getSysRoleCustom() {
        return sysRoleCustom;
    }

    public void setSysRoleCustom(SysRoleCustom sysRoleCustom) {
        this.sysRoleCustom = sysRoleCustom;
    }
}
