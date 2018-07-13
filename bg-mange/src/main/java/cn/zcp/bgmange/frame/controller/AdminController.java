package cn.zcp.bgmange.frame.controller;


import cn.zcp.bgmange.base.constant.BaseConstant;
import cn.zcp.bgmange.base.controller.BaseController;
import cn.zcp.bgmange.base.pojo.BaseResult;
import cn.zcp.bgmange.frame.enums.BaseEnum;
import cn.zcp.bgmange.frame.exception.BaseException;
import cn.zcp.bgmange.frame.pojo.AdminCustom;
import cn.zcp.bgmange.frame.pojo.AdminQueryVo;
import cn.zcp.bgmange.frame.service.IAdminService;
import cn.zcp.bgmange.frame.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController<AdminCustom,AdminQueryVo> {
    @Autowired
    private IAdminService adminService;

    @RequestMapping(value = "/initSetRole", method = RequestMethod.POST)
    public String initSetRole(Model model, String id) throws Exception{
        model.addAttribute("oprt","setRole");
        try {
            init(model,id);
        }catch (Exception e){
            model.addAttribute("retMsg", BaseConstant.FAIL_MSG);
        }
        return jsp();
    }
    @RequestMapping(value = "/setRole", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult setRole(AdminCustom adminCustom) throws Exception{
        return adminService.insertAdminRole(adminCustom);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/mainPage", method = RequestMethod.GET)
    public String mainPage(){
        return "common/mainPage";
//        return "index3";
    }

    @RequestMapping(value = "/initLogin", method = RequestMethod.GET)
    public String initLogin(Model model){
        model.addAttribute("oprt","login");
        return "login";
    }
//    public String initLogin(Model model){
//        model.addAttribute("oprt","login");
//        return "login";
//    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult login(AdminCustom adminCustom,HttpSession session) throws Exception{

        BaseResult baseResult = new BaseResult();
        try {
            Subject currentUser = SecurityUtils.getSubject();

            if (!currentUser.isAuthenticated()) {
                // 把用户名和密码封装为 UsernamePasswordToken 对象
                String account = adminCustom.getAccount();
                String password = adminCustom.getPassword();
                UsernamePasswordToken token = new UsernamePasswordToken(account, password);
//            token.setRememberMe(true);
                // 执行登录.
                currentUser.login(token);

                AdminCustom adminInfo = (AdminCustom) currentUser.getPrincipal();
//                currentUser.getSession().setAttribute("adminInfo",adminInfo);
                session.setAttribute("adminInfo",adminInfo);

                baseResult.setMsg(BaseConstant.LOGIN_SUCCESS_MSG);
            }

        }catch (UnknownAccountException uae) {
            throw new BaseException(BaseEnum.LOGIN_ADMIN_NO_EXIST);
        }catch (LockedAccountException lae)  {
            throw new BaseException(BaseEnum.LOGIN_ADMIN_NO_USE);
        }catch (IncorrectCredentialsException lce){
            throw new BaseException(BaseEnum.LOGIN_PWD_FAIL);
        }catch (AuthenticationException ae) { // 所有认证时异常的父类.
            throw new BaseException(BaseEnum.LOGIN_FAIL);
        }
        return baseResult;

//        BaseResult baseResult = new BaseResult();
//        AdminCustom adminInfo = adminService.loginVerify(adminCustom.getAccount(),adminCustom.getPassword());
//        if(null != adminInfo) {
//            session.setAttribute("adminInfo", adminInfo);
//        }else {
//            baseResult.setCode(BaseConstant.LOGIN_FAIL_CODE);
//            baseResult.setMsg(BaseConstant.LOGIN_FAIL_MSG);
//        }




//        try {
//            Map<String,Object> result = adminService.loginVerify(adminCustom.getAccount(),adminCustom.getPassword());
//            int flag = (int) result.get("flag");
//            if(0 == flag) {
//                session.setAttribute("adminInfo", result.get("obj"));
//            }else{
//                baseResult.setCode(BaseConstant.LOGIN_FAIL_CODE);
//            }
//            String msg = (String) result.get("msg");
//            baseResult.setMsg(msg);
//        }catch (Exception e){
//            baseResult.setCode(BaseConstant.EXCEPTION_CODE);
//            baseResult.setMsg(BaseConstant.EXCEPTION_MSG);
//        }

//        return baseResult;
//        model.addAttribute("result",baseResult);
        //0登录成功,1用户不存在,2密码错误,3用户被禁用
//        return "redirect:toMainPage.action";

//        return "redirect:/index.jsp";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        session.setAttribute("adminInfo", null);
        session.invalidate();

        return "login";


//        return "redirect:/index.jsp";
    }

    @Override
    protected BaseResult updateBefore(AdminCustom adminCustom, AdminCustom param) {
        adminCustom.setAdminName(param.getAdminName());
        adminCustom.setAccount(param.getAccount());
        if(!param.getPassword().equals(adminCustom.getPassword())) {
            adminCustom.setPassword(ShiroUtils.getMd5Pwd(null,param.getPassword()));
        }
        adminCustom.setUseStatus(param.getUseStatus());
        return new BaseResult();
    }

    @Override
    protected String getJsp() {
        return "frame/admin";
    }

    @Override
    protected String getId(AdminCustom adminCustom) {
        return adminCustom.getId().toString();
    }
}
