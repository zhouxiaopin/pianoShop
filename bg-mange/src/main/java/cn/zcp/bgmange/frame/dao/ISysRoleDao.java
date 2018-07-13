package cn.zcp.bgmange.frame.dao;


import cn.zcp.bgmange.base.dao.IBaseDao;
import cn.zcp.bgmange.frame.pojo.SysMenuSysRole;
import cn.zcp.bgmange.frame.pojo.SysRoleCustom;
import cn.zcp.bgmange.frame.pojo.SysRoleQueryVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统角色的mapper接口
 * Created by Administrator on 2017/11/28.
 */
@Component
public interface ISysRoleDao extends IBaseDao<SysRoleCustom,SysRoleQueryVo> {
    //批量插入菜单角色表的记录
    int insertSysMenuSysRole(List<SysMenuSysRole> sysMenuSysRoles);
    //删除管理员角色表的记录
    int deleteAdminSysRole(String sysRoleNo);
    //删除管理员角色表的记录
    int deleteMulAdminSysRole(String[] sysRoleNos);
    //删除菜单角色表的记录
    int deleteSysMenuSysRole(String sysRoleNo);
    //删除多个菜单角色表的记录
    int deleteMulSysMenuSysRole(String[] sysRoleNos);
    //查询菜单角色表表的记录
    List<SysMenuSysRole> querySysMenuSysRole(String sysRoleNo);
    /**
     * 查询中间表记录通过多个角色编号
     * @param sysRoleNos  角色编号
     * @return
     */
    List<SysMenuSysRole> querySysMenuSysRoleBySysRoleNos(List<String> sysRoleNos);

    List<SysRoleCustom> queryObjsBypks(List<String> pks);
}
