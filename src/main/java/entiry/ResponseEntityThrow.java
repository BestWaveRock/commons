package entiry;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author ljx
 * @Date 2020/12/25 17:51
 **/
public class ResponseEntityThrow implements Serializable {

    private static final long serialVersionUID = 5819174480253773214L;

    public ResponseEntityThrow() {}

    public ResponseEntityThrow(String errorMsg) {
        if (!Objects.isNull(errorMsg)) {
            setErrorMsg(errorMsg);
        }
    }

    private Integer code = 200;

    private Object data = null;

    private Integer errorCode = 500;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String errorMsg = "出错了";

}
