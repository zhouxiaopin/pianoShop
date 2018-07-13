package cn.zcp.bgmange.frame.controller;


import cn.zcp.bgmange.base.controller.BaseController;
import cn.zcp.bgmange.base.pojo.BaseResult;
import cn.zcp.bgmange.base.pojo.ComboTree;
import cn.zcp.bgmange.base.pojo.DatagridResult;
import cn.zcp.bgmange.frame.constant.FrameConstant;
import cn.zcp.bgmange.frame.pojo.SysMenuCustom;
import cn.zcp.bgmange.frame.pojo.SysMenuQueryVo;
import cn.zcp.bgmange.frame.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统菜单控制器
 * Created by Administrator on 2017/11/24.
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController extends BaseController<SysMenuCustom,SysMenuQueryVo> {
    @Autowired
    private ISysMenuService sysMenuService;

    @RequestMapping(value = "/getComboTree", method = RequestMethod.POST)
    @ResponseBody
    public List<ComboTree> getComboTree(SysMenuQueryVo sysMenuQueryVo) throws Exception{
        return sysMenuService.getComboTrees(sysMenuQueryVo);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @Override
    public DatagridResult<SysMenuCustom> list(SysMenuQueryVo sysMenuQueryVo){
        return sysMenuService.queryTreeGrid(sysMenuQueryVo);
    }

    @Override
    public String initAdd(Model model, SysMenuCustom sysMenuCustom) throws Exception {
        //使父级菜单默认选中无
        sysMenuCustom = new SysMenuCustom();
        sysMenuCustom.setParentNo(FrameConstant.NAVIGATE_SYS_MENU_PARENTNO);
        model.addAttribute("obj",sysMenuCustom);
        return super.initAdd(model,sysMenuCustom);
    }

    @Override
    protected BaseResult updateBefore(SysMenuCustom sysMenuCustom, SysMenuCustom param) {
        sysMenuCustom.setSysMenuName(param.getSysMenuName());
        sysMenuCustom.setSysMenuUrl(param.getSysMenuUrl());
        sysMenuCustom.setParentNo(param.getParentNo());
        sysMenuCustom.setSysMenuIconCls(param.getSysMenuIconCls());
        sysMenuCustom.setSysMenuExecuteJs(param.getSysMenuExecuteJs());
        sysMenuCustom.setSysMenuOpenStyle(param.getSysMenuOpenStyle());
        sysMenuCustom.setLevelCode(param.getLevelCode());
        sysMenuCustom.setUseStatus(param.getUseStatus());

        return new BaseResult();
    }

    @Override
    protected String getJsp() {
        return "frame/sysMenu";
    }

    @Override
    protected String getId(SysMenuCustom sysMenuCustom) {
        return sysMenuCustom.getSysMenuId().toString();
    }
}
