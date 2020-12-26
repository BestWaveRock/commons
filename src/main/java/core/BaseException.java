package core;

import constant.HttpConstant;
import entiry.Result;
import utils.StringUtils;

public abstract class BaseException extends RuntimeException {

    private Object[] params;

    public BaseException() {}

    public BaseException(Throwable throwable) {
        super(throwable);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Object... params) {
        super(message);
        this.params = params;
    }

    public BaseException(Throwable throwable, Object... params) {
        super(throwable);
        this.params = params;
    }

    public BaseException(String message, Throwable throwable, Object... params) {
        super(message, throwable);
        this.params = params;
    }

    public void handler(Result result) {
        String message = getMessage();
        result.setErrorCode(HttpConstant.ErrorCode.ERROR);
        result.setErrorMsg(message);
        result.setTimestamp(System.currentTimeMillis());
    }


    @Override
    public String getMessage() {
        if (StringUtils.isNotBlank(super.getMessage())) {
            //是否消息代码
//            if (StringUtils.isAsciiPrintable(super.getMessage())) {
//                return Resources.getMessage(super.getMessage(), params);
//            }
            return super.getMessage();
        } else {
            return HttpConstant.ErrorCode.ERROR.toString();
        }
    }

}
