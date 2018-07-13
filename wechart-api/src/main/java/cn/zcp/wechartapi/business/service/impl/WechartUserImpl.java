package cn.zcp.wechartapi.business.service.impl;

import cn.zcp.wechartapi.base.utils.JSONUtils;
import cn.zcp.wechartapi.base.utils.RestTemplateUtil;
import cn.zcp.wechartapi.business.pojo.WeChatSession;
import cn.zcp.wechartapi.business.service.IWechartUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 微信用户业务逻辑接口实现类
 */
@Service
public class WechartUserImpl implements IWechartUser {
    private static Logger logger = LogManager.getLogger(RestTemplateUtil.class);
    //小程序唯一标识
    private static final String APPID = "wx9xxxxxxxxxxx9b4";
    //小程序的 app secret
    private static final String SECRET = "685742***************84xs859";

    //获取凭证校检接口
    @Override
    public String login(String code) {
        //微信的接口
        try {
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+
                    "&secret="+SECRET+"&js_code="+ code +"&grant_type=authorization_code";

            String sessionData = RestTemplateUtil.get(url);
            //解析从微信服务器获得的openid和session_key;
            WeChatSession weChatSession = JSONUtils.json2pojo(sessionData,WeChatSession.class);
            //获取用户的唯一标识
            String openid = weChatSession.getOpenid();
            //获取会话秘钥
            String session_key = weChatSession.getSession_key();
            //下面就可以写自己的业务代码了
            //最后要返回一个自定义的登录态,用来做后续数据传输的验证

            return sessionData;
        }catch (Exception e){
            logger.error("微信登录失败：{}",e);
            return "";
        }

    }
}
