package cn.zcp.bgmange.frame.service;


import cn.zcp.bgmange.base.pojo.ComboTree;
import cn.zcp.bgmange.base.pojo.DatagridResult;
import cn.zcp.bgmange.base.service.IBaseMyBatisService;
import cn.zcp.bgmange.frame.pojo.SysMenuCustom;
import cn.zcp.bgmange.frame.pojo.SysMenuQueryVo;

import java.util.List;

/**
 * 系统菜单业务逻辑接口
 * Created by Administrator on 2017/11/24.
 */
public interface ISysMenuService extends IBaseMyBatisService<SysMenuCustom,SysMenuQueryVo> {
    List<ComboTree> getComboTrees(SysMenuQueryVo sysMenuQueryVo);
    DatagridResult<SysMenuCustom> queryTreeGrid(SysMenuQueryVo sysMenuQueryVo);
}
