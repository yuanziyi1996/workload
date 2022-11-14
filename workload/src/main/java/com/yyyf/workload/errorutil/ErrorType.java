package com.yyyf.workload.errorutil;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorType {
    /**
     * 系统异常 1
     */
    SYSTEM(1,"系统异常"),
    /**
     * 业务通知 2
     */
    BUSINESS_INFO(2,"业务通知"),
    /**
     * 业务警告 3
     */
    BUSINESS_WARN(3,"业务警告"),
    /**
     * 业务异常 4
     */
    BUSINESS_ERROR(4,"业务异常")
    ;
    @Getter
    private int code;
    @Getter
    private String desc;

    public static ErrorType getErrorTypeByCode(Integer code) {
        return (ErrorType) Holder.mapByCode.get(code);
    }

    private static final class Holder {
        private static Map<Integer, ErrorType> mapByCode =
                Arrays.stream(values()).collect(Collectors.toMap(ErrorType::getCode, o -> o));
    }

    public static ErrorType fromCode(Integer code) {
        return Holder.mapByCode.get(code);
    }
}
