package cn.zcp.bgmange.frame.service;


import cn.zcp.bgmange.base.pojo.BaseResult;
import cn.zcp.bgmange.base.pojo.ComboTree;
import cn.zcp.bgmange.base.service.IBaseMyBatisService;
import cn.zcp.bgmange.frame.pojo.SysRoleCustom;
import cn.zcp.bgmange.frame.pojo.SysRoleQueryVo;

import java.util.List;

/**
 * 系统角色业务逻辑接口
 * Created by Administrator on 2017/11/28.
 */
public interface ISysRoleService extends IBaseMyBatisService<SysRoleCustom,SysRoleQueryVo> {
    public List<ComboTree> getComboTree(SysRoleQueryVo sysRoleQueryVo);

    //添加管理员与角色的关联关系
    BaseResult insertSysMenuSysRole(SysRoleCustom sysRoleCustom);
}
