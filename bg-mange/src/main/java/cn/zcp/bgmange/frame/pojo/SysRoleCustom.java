package cn.zcp.bgmange.frame.pojo;

import java.util.List;

/**
 * 系统角色实体类的扩展类
 * Created by Administrator on 2017/11/28.
 */
public class SysRoleCustom extends SysRole {

    //菜单编号
    private List<String> sysMenuNos;
    private List<SysMenuSysRole> sysMenuSysRoles;

    public List<String> getSysMenuNos() {
        return sysMenuNos;
    }

    public void setSysMenuNos(List<String> sysMenuNos) {
        this.sysMenuNos = sysMenuNos;
    }

    public List<SysMenuSysRole> getSysMenuSysRoles() {
        return sysMenuSysRoles;
    }

    public void setSysMenuSysRoles(List<SysMenuSysRole> sysMenuSysRoles) {
        this.sysMenuSysRoles = sysMenuSysRoles;
    }
}
