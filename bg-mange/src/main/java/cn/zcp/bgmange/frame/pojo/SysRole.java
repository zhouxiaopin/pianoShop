package cn.zcp.bgmange.frame.pojo;

import java.util.Date;

/**
 * 系统角色实体类
 * Created by Administrator on 2017/11/28.
 */
public class SysRole {
    //主键id
    private Long roleId;
    //角色编号
    private String roleNo;
    //角色名
    private String roleName;
    //备注
    private String remark;
    //记录的状态(00禁用,01可用)
    private String useStatus;
    //创建时间
    private Date createTime;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
