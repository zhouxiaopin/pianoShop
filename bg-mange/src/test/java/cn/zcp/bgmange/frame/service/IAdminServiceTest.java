package cn.zcp.bgmange.frame.service;

import cn.zcp.bgmange.base.BaseServiceTest;
import cn.zcp.bgmange.frame.dao.IAdminDao;
import cn.zcp.bgmange.frame.dao.ISysMenuDao;
import cn.zcp.bgmange.frame.dao.ISysRoleDao;
import cn.zcp.bgmange.frame.pojo.AdminCustom;
import cn.zcp.bgmange.frame.pojo.SysMenuCustom;
import cn.zcp.bgmange.frame.pojo.SysRoleCustom;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class IAdminServiceTest extends BaseServiceTest {
    @Autowired
    private IAdminService adminService;
    @Autowired
    private IAdminDao adminDao;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysRoleDao sysRoleDao;
    @Autowired
    private ISysMenuDao sysMenuDao;
    @Test
    public void initData() throws Exception {
        //查询菜单
/*        SysMenuCustom sysMenuCustom = new SysMenuCustom();
        sysMenuCustom.
        sysMenuService.insert(sysMenuCustom);*/
        List<SysMenuCustom> sysMenuCustoms = sysMenuDao.queryObjs(null);
        List<String> sysMenuNos = new ArrayList<>();
        for (SysMenuCustom s:sysMenuCustoms){
            sysMenuNos.add(s.getSysMenuNo());
        }
        //添加角色
        SysRoleCustom sysRoleCustom = new SysRoleCustom();
        sysRoleCustom.setRoleName("系统管理员");
        sysRoleService.insert(sysRoleCustom);


        //菜单角色关联
        SysRoleCustom sysMenuSysRole = sysRoleDao.queryObjs(null).get(0);
        sysMenuSysRole.setSysMenuNos(sysMenuNos);
        sysRoleService.insertSysMenuSysRole(sysMenuSysRole);

        List<String> sysRoleNos = new ArrayList<>();
        sysRoleNos.add(sysMenuSysRole.getRoleNo());

        //添加管理员
        AdminCustom adminCustom = new AdminCustom();
        adminCustom.setAdminName("admin");
        adminCustom.setAccount("admin");
        adminCustom.setPassword("admin");
        adminService.insert(adminCustom);

        //角色管理员关联
        adminCustom = adminDao.queryObjs(null).get(0);
        adminCustom.setRoleNos(sysRoleNos);
        adminService.insertAdminRole(adminCustom);
    }
}