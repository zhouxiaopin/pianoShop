package cn.zcp.wechartapi.base.utils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/31.
 */
public class BaseUtils {
    private BaseUtils(){}
    public static boolean collectionIsEmpty(Collection collection){
        if(null == collection || 1 > collection.size()) {
            return true;
        }
        return false;
    }
    public static boolean mapIsEmpty(Map map){
        if(null == map || 1 > map.size()) {
            return true;
        }
        return false;
    }
}
