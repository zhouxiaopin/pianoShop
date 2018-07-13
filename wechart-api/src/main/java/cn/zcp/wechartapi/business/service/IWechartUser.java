package cn.zcp.wechartapi.business.service;

/**
 * 微信用户业务逻辑接口
 */
public interface IWechartUser {
    /**
     * 微信登录
     * 1.小程序调用wx.login() 获取 临时登录凭证code ，并回传到开发者服务器。
     * 2.开发者服务器以code换取 用户唯一标识openid 和 会话密钥session_key。
     * @param code 临时登录凭证code
     * @return
     */
    String login(String code);
}
