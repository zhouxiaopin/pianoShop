package cn.zcp.bgmange.frame.controller;


import cn.zcp.bgmange.base.constant.BaseConstant;
import cn.zcp.bgmange.base.controller.BaseController;
import cn.zcp.bgmange.base.pojo.BaseResult;
import cn.zcp.bgmange.base.pojo.ComboTree;
import cn.zcp.bgmange.frame.pojo.SysRoleCustom;
import cn.zcp.bgmange.frame.pojo.SysRoleQueryVo;
import cn.zcp.bgmange.frame.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统角色控制器
 * Created by Administrator on 2017/11/28.
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController<SysRoleCustom,SysRoleQueryVo> {
    @Autowired
    private ISysRoleService sysRoleService;


    @RequestMapping(value = "/getTree", method = RequestMethod.POST)
    @ResponseBody
    public List<ComboTree> getTree(SysRoleQueryVo sysRoleQueryVo){
        return sysRoleService.getComboTree(sysRoleQueryVo);
    }

    @RequestMapping(value = "/initSetAuthority", method = RequestMethod.POST)
    public String initSetAuthority(Model model, String id) throws Exception{
        model.addAttribute("oprt","setAuthoritye");
        try {
            init(model,id);
        }catch (Exception e){
            model.addAttribute("retMsg", BaseConstant.FAIL_MSG);
        }
        return jsp();
    }

    @RequestMapping(value = "/setAuthoritye", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult setAuthoritye(SysRoleCustom sysRoleCustom) throws Exception{
        return sysRoleService.insertSysMenuSysRole(sysRoleCustom);
    }

    @Override
    protected BaseResult updateBefore(SysRoleCustom sysRoleCustom, SysRoleCustom param) {
        sysRoleCustom.setRoleName(param.getRoleName());
        sysRoleCustom.setRemark(param.getRemark());
        sysRoleCustom.setUseStatus(param.getUseStatus());

        return new BaseResult();
    }

    @Override
    protected String getJsp() {
        return "frame/sysRole";
    }

    @Override
    protected String getId(SysRoleCustom sysRoleCustom) {
        return sysRoleCustom.getRoleId().toString();
    }
}
