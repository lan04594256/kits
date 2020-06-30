package common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultJson implements Serializable {
    private static final long serialVersionUID = 123126L;
    private Integer code;
    private String message;
    private Object data;
    protected boolean canEqual(Object other) {
        return other instanceof ResultJson;
    }
    @Override
    public String toString() {
        return "ResultJson(code=" + this.getCode() + ", message=" + this.getMessage() + ", data=" + this.getData() + ")";
    }

    public ResultJson(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultJson() {
    }
}
