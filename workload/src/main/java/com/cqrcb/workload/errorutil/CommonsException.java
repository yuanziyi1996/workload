/*
 * Baijiahulian.com Inc.Copyright(c) 2019 All Rights Reserved.
 */
package com.cqrcb.workload.errorutil;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CommonsException extends RuntimeException {
    private String msg;
    private ErrorType errorType;

    public static CommonsException of(String msg) {
        return new CommonsException(msg, ErrorType.SYSTEM);
    }

    public static CommonsException of(String message, ErrorType errorType) {
        return new CommonsException(message, errorType);
    }
    private CommonsException(String message, ErrorType errorType) {
        super(message);
        this.msg = message;
        this.errorType = errorType;
    }

}
