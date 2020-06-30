package common;

public enum ErrorCode {
    API_REPEAT_ORDER(10001, "已存在该订单"),
    API_NOT_ENOUGH_MONEY(10002, "客户帐户余额不足"),
    API_NOT_EXIST_ACT(10003, "活动不存在"),
    API_ILLEGAL_REQUEST(10004, "客户非法请求"),
    STORAGE_ERROR(90004, "库存不足！"),
    NOT_EXIST_GOODS_ERROR(90005, "商品不存在！"),
    SYS_EXCEPTION(9000, "系统异常,操作失败"),
    ILLEGAL_ARGUMENT(9001, "参数非法"),
    ARGUMENT_NOT_VALID(9002, "参数校验不通过"),
    FEIGN_CONNECT_TIME_OUT(9003, "服务连接超时，操作失败"),
    UNCATCH_EXCEPTION(9999, "系统异常");

    private int code;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    private ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(String code) {
        ErrorCode[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            ErrorCode errorCode = var4[var2];
            if (code.equals(errorCode.getCode())) {
                return errorCode.getMsg();
            }
        }

        return null;
    }
}
