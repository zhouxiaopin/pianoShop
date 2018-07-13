package cn.zcp.bgmange.frame.pojo;


import cn.zcp.bgmange.base.pojo.BaseQueryVo;

/**
 * 管理员实体包装类
 */
public class AdminQueryVo extends BaseQueryVo {
    //为了系统 可扩展性，对原始生成的pojo进行扩展
    private AdminCustom adminCustom;

    public void setAdminCustom(AdminCustom adminCustom) {
        this.adminCustom = adminCustom;
    }

    public AdminCustom getAdminCustom() {
        return adminCustom;
    }
}
