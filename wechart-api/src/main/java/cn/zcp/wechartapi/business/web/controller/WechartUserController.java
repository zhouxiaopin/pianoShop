package cn.zcp.wechartapi.business.web.controller;

import cn.zcp.wechartapi.base.utils.ResultUtils;
import cn.zcp.wechartapi.business.service.IWechartUser;
import cn.zcp.wechartapi.frame.enums.BaseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *微信用户控制器
 */
@RestController
@RequestMapping("/wechartUser")
public class WechartUserController {
    @Autowired
    private IWechartUser wechartUser;
    @GetMapping(value = "/login")
//    @PostMapping(value = "/login")
    public Object login(String code){
        return ResultUtils.getResultMap(BaseEnum.REQUEST_API_SUCCESS, wechartUser.login(code));
    }
}
