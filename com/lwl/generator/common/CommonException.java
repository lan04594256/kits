package common;

public class CommonException extends RuntimeException{
    protected Integer code;

    public Integer getCode() {
        return this.code;
    }

    protected CommonException(Integer code, String message, Exception e) {
        super(message, e);
        this.code = code;
    }

    protected CommonException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    protected CommonException(Exception e) {
        super(e);
    }
}
