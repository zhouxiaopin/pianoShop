package cn.zcp.bgmange.frame.enums;

/**
 * 基本枚举
 * Created by Administrator on 2017/11/20.
 */
public enum BaseEnum {
    SUCCESS(0,"操作成功"),
    ADD_SUCCESS(1,"增加操作成功"),
    DEL_SUCCESS(2,"删除操作成功"),
    MDF_SUCCESS(3,"修改操作成功"),
    QRY_SUCCESS(4,"查询操作成功"),
    AUD_SUCCESS(5,"审核操作成功"),
    LOGIN_SUCCESS(6,"登录成功"),
    RECORD_EXISTS(7,"记录已存在"),
    REQUEST_API_SUCCESS(8,"请求接口成功"),
    FAIL(-1,"操作失败"),
    ADD_FAIL(-2,"增加操作失败"),
    DEL_FAIL(-3,"删除操作失败"),
    MDF_FAIL(-4,"修改操作失败"),
    QRY_FAIL(-5,"查询操作失败"),
    AUD_FAIL(-6,"审核操作失败"),
    RECORD_NO_EXISTS(-7,"记录不存在"),
    LOGIN_ADMIN_NO_EXIST(-8,"用户不存在"),
    LOGIN_ADMIN_NO_USE(-9,"用户被禁用"),
    LOGIN_PWD_FAIL(-10,"密码错误"),
    NO_SET_JSP(-11,"没有配置jsp页面"),
    NO_SET_PK(-12,"没有配置主键ID)"),
    LOGIN_FAIL(-13,"登录失败"),
    REQUEST_API_FAIL(-14,"请求接口失败"),
    SYS_UNKNOWN_ERROR(-200,"系统繁忙,请稍后再试");


    private Integer code;
    private String msg;

    BaseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
