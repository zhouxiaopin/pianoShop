package cn.zcp.bgmange.frame.dao;


import cn.zcp.bgmange.base.dao.IBaseDao;
import cn.zcp.bgmange.frame.pojo.SysMenuCustom;
import cn.zcp.bgmange.frame.pojo.SysMenuQueryVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统菜单的mapper接口
 * Created by Administrator on 2017/11/24.
 */
@Component("SysMenuMapper")
public interface ISysMenuDao extends IBaseDao<SysMenuCustom,SysMenuQueryVo> {
    //删除菜单角色表的记录
    int deleteSysMenuSysRole(String sysMenuNo);
    //删除多个菜单角色表的记录
    int deleteMulSysMenuSysRole(String[] sysMenuNos);

    List<SysMenuCustom> queryObjsBypks(List<String> pks);
}
