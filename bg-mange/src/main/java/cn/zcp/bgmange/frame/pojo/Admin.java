package cn.zcp.bgmange.frame.pojo;

import java.util.Date;

/**
 * 管理员实体类
 */
public class Admin {
    //管理员主键id
    private Long id;
    //管理员姓名
    private String adminName;
    //管理员的账号
    private String account;
    //管理员的密码
    private String password;
    //记录的状态
    private String useStatus;
    //创建时间
    private Date createTime;

    public void setId(Long id) {
        this.id = id;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
