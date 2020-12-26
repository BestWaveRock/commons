package advice;

import constant.HttpConstant;
import entiry.ResponseEntity;
import exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import sun.dc.pr.PRError;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * 全局异常捕获
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);

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
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCode(HttpConstant.Code.SUCCESS);
        responseEntity.setErrorCode(businessException.getErrorCode());
        responseEntity.setErrorMsg(businessException.getMessage());
        return responseEntity;
    }

}
