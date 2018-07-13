package cn.zcp.bgmange.base.pojo;


import cn.zcp.bgmange.base.constant.BaseConstant;

/**
 * 基本返回实体类
 */
public class BaseResult {
    private boolean status = true;
    private int code = BaseConstant.SUCCESS_CODE;
    private String msg = BaseConstant.SUCCESS_MSG;
    private Object data = "";

    public void setCode(int code) {
        this.code = code;
        this.status = BaseConstant.SUCCESS_CODE <= code ? true : false;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getData() {
//        return data;
        return  data == null ? "" : data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
