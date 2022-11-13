/*
 * Baijiahulian.com Inc.Copyright(c) 2019 All Rights Reserved.
 */
package com.cqrcb.workload.common;

import com.cqrcb.workload.errorutil.CommonCodeEnum;
import com.cqrcb.workload.errorutil.CommonsException;

public class RestResponse<T> {

    /**
     * default success response
     */
    public static final RestResponse SUCCESS = success(null);

    private final int code;
    private final T data;
    private final String msg;
    private Pager pager;

    private RestResponse(int code, T result, String msg, Pager pager) {
        this.code = code;
        this.data = result;
        this.msg = msg;
        this.pager = pager;
    }

    public static <T> RestResponse<T> success(T result) {
        return new RestResponse<>(CommonCodeEnum.SUCCESS.getCode(), result, (String) null, (Pager) null);
    }

    public static <T> RestResponse<T> success(T result, Pager pager) {
        return new RestResponse<>(CommonCodeEnum.SUCCESS.getCode(), result, (String) null, pager);
    }

    public static <T> RestResponse<T> fail(T result, String msg, Pager pager) {
        return new RestResponse<>(CommonCodeEnum.FAIL.getCode(), result, msg, pager);
    }

    public static <T> RestResponse<T> fail(String msg, Pager pager) {
        return new RestResponse<>(CommonCodeEnum.FAIL.getCode(), null, msg, pager);
    }

    public static <T> RestResponse<T> fail(String msg) {
        return new RestResponse<>(CommonCodeEnum.FAIL.getCode(), null, msg, (Pager) null);
    }

    public static <T> RestResponse<T> fail(CommonsException ex) {
        return new RestResponse<T>(CommonCodeEnum.FAIL.getCode(), null, ex.getMsg(), null);
    }

    @Override
    public String toString() {
        return "RestResponse(code=" + this.getCode() + ", data=" + this.getData() + ", msg=" + this.getMsg() + ", pager" +
                "=" + this.getPager() + ")";
    }

    public int getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public Pager getPager() {
        return this.pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }


}
