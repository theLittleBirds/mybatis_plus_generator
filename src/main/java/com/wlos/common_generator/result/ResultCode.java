package com.wlos.common_generator.result;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {

    SUCCESS(1200,"成功"),

    FAIL(1400,"失败"),
    UNAUTHORIZED(1401,"未认证"),
    CHILD(1402,"有子集询问删除"),
    NOT_FOUND(1404,"接口不存在"),
    INTERNAL_SERVER_ERROR(1500,"服务器内部错误");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

}
