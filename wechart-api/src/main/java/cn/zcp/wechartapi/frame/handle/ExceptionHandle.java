package cn.zcp.wechartapi.frame.handle;


import cn.zcp.wechartapi.base.constant.BaseConstant;
import cn.zcp.wechartapi.frame.enums.BaseEnum;
import cn.zcp.wechartapi.frame.exception.BaseException;
import cn.zcp.wechartapi.frame.exception.TipException;
import cn.zcp.wechartapi.base.pojo.BaseResult;
import cn.zcp.wechartapi.base.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理器
 * Created by Administrator on 2017/11/20.
 */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResult handle(Exception e) {
        //以后要添加判断是否是ajax请求
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            return ResultUtils.baseResult(baseException.getCode(), baseException.getMessage());
        }else if (e instanceof TipException) {
            TipException tipException = (TipException) e;
            return ResultUtils.baseResult(tipException.getCode(), tipException.getMessage());
        }else if (e instanceof HttpRequestMethodNotSupportedException) {
            logger.error("【请求方式异常】{}", e);
            return ResultUtils.baseResult(BaseConstant.EXCEPTION_CODE, e.getMessage());
        }else {
            logger.error("【系统异常】{}", e);
            return ResultUtils.baseResult(BaseEnum.SYS_UNKNOWN_ERROR.getCode(), BaseEnum.SYS_UNKNOWN_ERROR.getMsg());
        }
    }
}
