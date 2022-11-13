package com.cqrcb.workload.common;

import lombok.Getter;

public enum CommonCodeEnum {

    FAIL(0, "fail with detailed error info"),
    SUCCESS(1, "success"),
    NOT_FOUND(2, "not found"),
    INVALIDATED_PARAM(3, "invalid param"),
    NOT_AUTHORIZED(4, "access denied"),
    BROKEN_PIPE(5, "broken pipe"),
    METHOD_ERROR(6, "request method error"),
    PARAM_EXCEPTION(7, "param out of expect"),
    SYSTEM_ERROR(8, "server error"),
    SYSTEM_BUSY(9, "system busy");

    @Getter
    private Integer code;
    @Getter
    private String msg;

    CommonCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getErrorMsg() {
        return getMsg();
    }
}
