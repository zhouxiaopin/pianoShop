package cn.zcp.bgmange.frame.service;


import cn.zcp.bgmange.base.pojo.BaseResult;
import cn.zcp.bgmange.base.service.IBaseMyBatisService;
import cn.zcp.bgmange.frame.pojo.AdminCustom;
import cn.zcp.bgmange.frame.pojo.AdminQueryVo;

/**
 * 管理员的业务逻辑接口
 * @author pin
 *
 */
public interface IAdminService extends IBaseMyBatisService<AdminCustom,AdminQueryVo> {
    AdminCustom loginVerify(String account, String password);
    //添加管理员与角色的关联关系
    BaseResult insertAdminRole(AdminCustom adminCustom);

}
