package cn.zcp.wechartapi.frame.exception;


import cn.zcp.wechartapi.base.constant.BaseConstant;
import cn.zcp.wechartapi.frame.enums.BaseEnum;

/**
 * 自定义异常
 * Created by Administrator on 2017/11/20.
 */
public class BaseException extends RuntimeException{
    private Integer code;

    public BaseException(BaseEnum baseEnum) {
        super(baseEnum.getMsg());
        this.code = baseEnum.getCode();
    }
    public BaseException(String msg) {
        super(msg);
        this.code = BaseConstant.EXCEPTION_CODE;
    }
    public BaseException(String msg,boolean success) {
        super(msg);
        if(success) {
            this.code = BaseConstant.SUCCESS_CODE;
        }else {
            this.code = BaseConstant.EXCEPTION_CODE;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
