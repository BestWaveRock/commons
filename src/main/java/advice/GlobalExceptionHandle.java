package advice;

import constant.HttpConstant;
import entiry.Result;
import exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;
import utils.ResultUtils;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;


/**
 * 全局统一异常处理
 * @version 1.0
 */
@Order
@ControllerAdvice
public class GlobalExceptionHandle extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);

    /** 异常处理 */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> exceptionHandler(Exception ex) {
        String msg = ex.getMessage();
        System.err.println("    " + msg);
        return ResultUtils.wrapFail(null, HttpConstant.ErrorCode.ERROR, msg.length() > 100 ? "系统走神了,请稍候再试." : msg);
    }

    @ExceptionHandler({BusinessException.class})
    @ResponseBody
    public ResponseEntity exceptionHandle (BusinessException businessException) {
        logger.error("sys robot: catch >> bug: {}", businessException.getMessage());
        if (businessException.getStackTrace().length > 0) {
            Integer line = 0;
            for (StackTraceElement stackTraceElement : businessException.getStackTrace()) {
                System.err.println("    " + stackTraceElement.toString());
                line += 1;
                // 只打印前9行错误
                if (line == 9) {
                    break;
                }
            }
            // 全日志等级调低 trace < #debug# < info < warn < error <fatal
            Arrays.stream(businessException.getStackTrace()).forEach(stackTraceElement -> {
                logger.debug(stackTraceElement.toString());
            });
        }
        Result result = new Result();
        result.setCode(HttpConstant.Code.SUCCESS);
        result.setErrorCode(businessException.getErrorCode());
        result.setErrorMsg(businessException.getMessage());
        return ResponseEntity.ok(result);
    }
}
