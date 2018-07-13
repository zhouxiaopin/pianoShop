package cn.zcp.bgmange.frame.pojo;

import java.util.List;

/**
 * 管理员实体类的扩展类
 */
public class AdminCustom extends Admin{
    //管理员的角色编号
    private List<String> roleNos;
    //管理员的角色
    private List<AdminRole> adminRoles;

    public List<String> getRoleNos() {
        return roleNos;
    }

    public void setRoleNos(List<String> roleNos) {
        this.roleNos = roleNos;
    }

    public List<AdminRole> getAdminRoles() {
        return adminRoles;
    }

    public void setAdminRoles(List<AdminRole> adminRoles) {
        this.adminRoles = adminRoles;
    }
}
