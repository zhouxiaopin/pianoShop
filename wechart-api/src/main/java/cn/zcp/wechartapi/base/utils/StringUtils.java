package cn.zcp.wechartapi.base.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/9/22.
 */
public class StringUtils {
    private StringUtils(){}
    public static boolean isEmpty(String s){
        if(null == s || 0 >= s.length()) {
            return true;
        }
        return false;
    }
    // Clob类型 转String
    public static String clobToString(Clob clob) throws SQLException, IOException {
        if(null == clob) {
            return "";
        }
        String reString = "";
        Reader is = clob.getCharacterStream();// 得到流
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
        StringBuffer sb = new StringBuffer();
        while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            sb.append(s);
            s = br.readLine();
        }
        reString = sb.toString();
        if(br!=null){
            br.close();
        }
        if(is!=null){
            is.close();
        }
        return reString;
    }

}
