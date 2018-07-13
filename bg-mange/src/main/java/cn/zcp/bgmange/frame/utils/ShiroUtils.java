package cn.zcp.bgmange.frame.utils;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by Administrator on 2017/11/28.
 */
public class ShiroUtils {

    /**
     * 获取MD5加密的密码串
     * @param account
     * @param pwd
     * @return
     */
    public static String getMd5Pwd(String account, String pwd){
        String hashAlgorithmName = "MD5";
        //密码
        Object credentials = pwd;
        //账号
        if(null == account) {
            account = "ticc";
        }
        Object salt = ByteSource.Util.bytes(account);
        int hashIterations = 1024;

        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        return result.toString();
    }

    public static String getBase64Pwd(String account, String pwd){
        String hashAlgorithmName = "SHA1";
        //密码
        Object credentials = pwd;
        //账号
        Object salt = ByteSource.Util.bytes(account);
        int hashIterations = 1024;

        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        return result.toString();
    }

    public static String encBase64(String str) {
        return Base64.encodeToString(str.getBytes());
    }
    public static String decBase64(String str){
        return Base64.decodeToString(str);
    }


}
