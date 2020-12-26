package entiry;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author ljx
 * @Date 2020/12/25 17:51
 **/
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 5819174480253773214L;

    private Integer code = 200;

    private Object data;

    private Integer errorCode = 0;

    private Long timestamp;

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

    public String errorMsg;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Result() {
        setTimestamp(System.currentTimeMillis());
    }
}
