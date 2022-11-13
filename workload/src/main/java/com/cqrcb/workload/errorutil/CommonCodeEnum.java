/*
 * Baijiahulian.com Inc.Copyright(c) 2019 All Rights Reserved.
 */
package com.cqrcb.workload.errorutil;

import lombok.Getter;

public enum CommonCodeEnum {

    SUCCESS(0, "success", ErrorType.BUSINESS_INFO),
    FAIL(1, "fail with detailed error info", ErrorType.BUSINESS_INFO),
    NOT_FOUND(2, "not found", ErrorType.BUSINESS_INFO),
    INVALIDATED_PARAM(3, "invalid param", ErrorType.BUSINESS_ERROR),
    NOT_AUTHORIZED(4, "access denied", ErrorType.BUSINESS_INFO),
    BROKEN_PIPE(5, "broken pipe", ErrorType.BUSINESS_ERROR),
    METHOD_ERROR(6, "request method error", ErrorType.BUSINESS_ERROR),
    PARAM_EXCEPTION(7, "param out of expect", ErrorType.BUSINESS_ERROR),
    SYSTEM_ERROR(8, "server error", ErrorType.SYSTEM),
    SYSTEM_BUSY(9, "system busy", ErrorType.BUSINESS_INFO);

    @Getter
    private Integer code;
    @Getter
    private String msg;
    @Getter
    private ErrorType errType;

    CommonCodeEnum(Integer code, String msg, ErrorType errType) {
        this.code = code;
        this.msg = msg;
        this.errType = errType;
    }
}
