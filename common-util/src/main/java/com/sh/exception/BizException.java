package com.sh.exception;

/**
 * Created By Sunhu At 2020/6/4 9:49
 *
 * @author Sun
 */
public class BizException extends RuntimeException {
    int code;
    String errorMsg;

    public BizException(int code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public BizException(String message, int code, String errorMsg) {
        super(message);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public BizException(String message, Throwable cause, int code, String errorMsg) {
        super(message, cause);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public BizException(Throwable cause, int code, String errorMsg) {
        super(cause);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String errorMsg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
