package utils;

import entiry.ResponseEntity;

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
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setData(object);
        return responseEntity;
    }

    /**
     * 失败
     * @param object
     * @return
     */
    public static ResponseEntity wrapFail(Object object, Integer errorCode, String errorMsg) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setData(object);
        responseEntity.setErrorCode(errorCode);
        responseEntity.setErrorMsg(errorMsg);
        return responseEntity;
    }

}
