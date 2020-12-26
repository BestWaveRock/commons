package advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.StringEscapeEditor;
import constant.HttpConstant;
import core.BaseException;
import entiry.Result;
import exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import utils.ResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
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
