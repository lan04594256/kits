package common;

public class BizException extends CommonException{
    public BizException(Integer code, String message) {
        super(code, message);
    }

    public BizException(Integer code, String message, Exception e) {
        super(code, message, e);
    }
}
