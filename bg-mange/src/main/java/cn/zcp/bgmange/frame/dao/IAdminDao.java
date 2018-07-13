package cn.zcp.bgmange.frame.dao;


import cn.zcp.bgmange.base.dao.IBaseDao;
import cn.zcp.bgmange.frame.pojo.AdminCustom;
import cn.zcp.bgmange.frame.pojo.AdminQueryVo;
import cn.zcp.bgmange.frame.pojo.AdminRole;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 管理员mapper接口
 */
@Component
public interface IAdminDao extends IBaseDao<AdminCustom,AdminQueryVo> {
    //批量插入管理员角色表的记录
    int insertAdminRole(List<AdminRole> adminRole);
    //删除管理员角色表的记录
    int deleteAdminRole(String adminId);
    //查询管理员角色表的记录
    List<AdminRole> queryAdminRole(String adminId);

    //删除管理员角色表的记录
    int deleteMulAdminSysRole(String[] adminIds);

}
