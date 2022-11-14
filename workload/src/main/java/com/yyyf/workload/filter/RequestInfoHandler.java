/*
 * Baijiahulian.com Inc.Copyright(c) 2019 All Rights Reserved.
 */
package com.yyyf.workload.filter;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by chenjiajun on 16/7/27.
 * <p>
 *     只获取 请求参数:
 *     final RequestInfoHandler apiRequest = new RequestInfoHandler();
 *     Map<String, String> queryParams = apiRequest.resolveArgument(request);
 * </p>
 * </br>
 * <p>
 *     获取HttpServletRequest 请求参数、请求头、请求mapinngUrl
 *     final RequestInfoHandler apiRequest = new RequestInfoHandler(request);
 * </p>
 */
public class RequestInfoHandler {
    /**
     * 请求的URL
     */
    private String requestURL;
    /**
     * 查询请求参数
     */
    private Map<String, String> queryParams = new LinkedHashMap<String, String>();
    /**
     * Http请求头中的信息
     */
    private Map<String, String> headers = new LinkedHashMap<String, String>();
    public static final String PARAM_MISS_MESSAGE = "param missing";
    public RequestInfoHandler(){

    }
    public RequestInfoHandler(HttpServletRequest request) {
        this.requestURL = request.getServletPath();
        this.queryParams = resolveArgument(request);
        this.headers = resolveHeader(request);
    }
    public RequestInfoHandler(Map<String, String> args) {
        this.queryParams = args;
    }
    public RequestInfoHandler(String path) {
        this.requestURL = path;
        this.queryParams = new HashMap<String, String>();
        this.headers = new HashMap<String, String>();
    }

    public static RequestInfoHandler getGlobalInstance(HttpServletRequest request){
        return new RequestInfoHandler(request);
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public static RequestInfoHandler resolveArgumentHandler(HttpServletRequest request) {
        return new RequestInfoHandler(resolveArgument(request));
    }
    public static Map<String, String> resolveArgument(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> result = new LinkedHashMap<String, String>(parameterMap.size());
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            final String[] value = entry.getValue();
            if (value.length > 0) {
                result.put(entry.getKey(), StringUtils.trimToNull(value[0]));
            }
        }
        return result;
    }
    public Map<String, String> resolveHeader(HttpServletRequest request) {
        Map<String, String> result = new LinkedHashMap<String, String>();
        for (Enumeration<String> iterator = request.getHeaderNames(); iterator.hasMoreElements();) {
            String headerName = iterator.nextElement();
            String headerValue = request.getHeader(headerName);
            if (StringUtils.isNotBlank(headerValue)) {
                result.put(headerName, StringUtils.trimToNull(headerValue));
            }
        }
        return result;
    }

    /**
     * 获取必传参数 int 值
     * @param paramName
     * @return
     * @throws IllegalArgumentException
     */
    public int getRequiredIntValue(String paramName) throws IllegalArgumentException {
        Integer paramVal = MapUtils.getInteger(queryParams, paramName);
        checkParamVal(paramName, paramVal);
        return paramVal.intValue();
    }

    /**
     * 获取可选参数的int 值 没有值时返回0
     * @param paramName
     * @return
     * @throws IllegalArgumentException
     */
    public int getIntValue(String paramName) {
        return MapUtils.getIntValue(queryParams, paramName);
    }

    /**
     * 获取可选参数的int 值 没有值时返回默认值
     * @param paramName
     * @param defaultVal
     * @return
     * @throws IllegalArgumentException
     */
    public int getOrDefaultIntValue(String paramName, int defaultVal) {
        return MapUtils.getIntValue(queryParams, paramName, defaultVal);
    }

    /**
     * 获取必传参数 字符串值
     * @param paramName
     * @return
     * @throws IllegalArgumentException
     */
    public String getRequiredStringValue(String paramName) throws IllegalArgumentException {
        final String paramVal = MapUtils.getString(queryParams, paramName);
        checkParamVal(paramName, paramVal);
        return paramVal;
    }

    /**
     *
     * 获取可选参数 string 值 默认值 null
     *
     * @param paramName
     * @return string
     */
    public String getStringValue(String paramName) {
        return MapUtils.getString(queryParams, paramName);
    }

    /**
     * 获取可选参数 string 值 空值时 返回默认值
     * @param paramName
     * @param defaultVal
     * @return
     */
    public String getStringValue(String paramName, String defaultVal) {
        return MapUtils.getString(queryParams, paramName, defaultVal);
    }

    private void checkParamVal(String paramName, Object paramVal) throws IllegalArgumentException {
        if (paramVal == null) {
            throwParamError(paramName);
        }
    }
    private void throwParamError(String param) throws IllegalArgumentException {
        throw new IllegalArgumentException(String.format("%s, paramName:%s", PARAM_MISS_MESSAGE, param));
    }
}
