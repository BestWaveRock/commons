package utils;

import entiry.Result;
import org.springframework.http.ResponseEntity;


/**
 * @Author ljx
 * @Date 2020/12/25 17:51
 **/
public class ResultUtils {

    /**
     * 200
     * @param object
     * @return
     */
    public static ResponseEntity wrapSuccess(Object object) {
        Result result = new Result();
        result.setData(object);
        return ResponseEntity.ok(result);
    }

    /**
     * 失败
     * @param object
     * @return
     */
    public static ResponseEntity wrapFail(Object object, Integer errorCode, String errorMsg) {
        Result result = new Result();
        result.setData(object);
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        return ResponseEntity.ok(result);
    }

}
