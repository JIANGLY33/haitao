package com.jalinyiel.haitao.haitao.common;


public enum CommonResultCode implements AbsResultCode {

    SUCCESS(1, ""),
    FAILED(100, "failed"),
    EXCEPTION(101, "exception"),
    NULL_RESULT(102, "result is null"),
    ARGUMENT_ERROR(103, "argument error"),
    UNAUTHORIZED(104, "unauthorized"),
    TIMEOUT(105, "timeout"),
    FASTVALIDATOR_ERROR(106, "fastvalidator error"),

    TOKEN_NOT_VALID(107, "token无效"),
    WEBTYPE_NOT_VALID(108, "网页类型不合法（1：PC端网页，2：移动端网页）"),
    //签名相关
    SING_TIME_NOT_VALID(1000, "请求时间戳不合法"),
    SING_KEY_NOT_VALID(1001, "accessKey不合法"),
    SING_ERROR(1003, "签名错误"),
    SING_TIMEOUT(1002, "请求超时"),
    SING_AK_NOT_EXIST(1004, "ak不存在"),

    BIZ_ERROR(300, "业务错误"),
    NOT_FOUND(404, "api不存在"),
    UNKNOWN_ERROR(500, "未知异常");

    private int code;
    private String msg;

    private CommonResultCode() {
    }

    private CommonResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}