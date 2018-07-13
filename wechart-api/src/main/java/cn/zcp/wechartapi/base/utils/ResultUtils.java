package cn.zcp.wechartapi.base.utils;


import cn.zcp.wechartapi.base.constant.BaseConstant;
import cn.zcp.wechartapi.frame.enums.BaseEnum;
import cn.zcp.wechartapi.base.pojo.BaseResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回对象工具类
 * Created by Administrator on 2017/11/20.
 */
public class ResultUtils {
    private ResultUtils(){}

    public static BaseResult baseResult(Integer code, String msg){
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(code);
        baseResult.setMsg(msg);
        return baseResult;
    }
    public static BaseResult baseResult(Integer code, String msg,Object data){
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(code);
        baseResult.setMsg(msg);
        baseResult.setData(data);
        return baseResult;
    }
    public static BaseResult baseResult(BaseEnum baseEnum){
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(baseEnum.getCode());
        baseResult.setMsg(baseEnum.getMsg());
        return baseResult;
    }
    /**
     * 接口返回结果集合
     * @param status 状态：true表示成功、false表示失败
     * @param code 大于等于0就成功，小于0就失败
     * @param msg 状态描述
     * @param data 返回结果
     * @return
     */
    public static Map<String, Object> getResultMap(boolean status, int code, String msg,
                                Object data) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", status);
        resultMap.put("code", code);
        resultMap.put("msg", msg);
        resultMap.put("data", data == null ? "" : data);
        return resultMap;
    }
    /**
     * 接口返回结果集合
     * @param baseEnum 枚举
     * @param data 返回结果
     * @return
     */
    public static Map<String, Object> getResultMap(BaseEnum baseEnum, Object data) {
        Map<String, Object> resultMap = new HashMap<>();
        Integer code = baseEnum.getCode();
        boolean status = BaseConstant.SUCCESS_CODE <= code ? true : false;
        String msg = baseEnum.getMsg();
        resultMap.put("status", status);
        resultMap.put("code", code);
        resultMap.put("msg", msg);
        resultMap.put("data", data == null ? "" : data);
        return resultMap;
    }

    /**
     * 接口返回成功结果集合
     * @param msg
     * @param data
     * @return
     */
    public static Map<String, Object> getSuccessResultMap(String msg,Object data) {
        return getResultMap(true,BaseConstant.SUCCESS_CODE,msg,data);
    }
}
