package com.yyyf.workload.util;

import com.alibaba.fastjson.JSON;
import com.yyyf.workload.common.RestResponse;
import com.yyyf.workload.errorutil.CommonCodeEnum;
import com.yyyf.workload.errorutil.CommonsException;
import com.yyyf.workload.errorutil.ErrorType;
import com.yyyf.workload.filter.RequestInfoHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author pangbo@baijiahulian.com
 * @version version
 * @desc description
 * @date 2018-08-02
 */
@RestControllerAdvice
@Slf4j
@Order(10)
public class ExceptionHandlerProcessor {

    private static final int INFO = 0;
    private static final int WARN = 1;
    private static final int ERROR = 2;
    private static final int STATE_FAILED = 520;

    @Resource
    private HttpServletRequest request;


    @ExceptionHandler({
            SQLException.class,
            Exception.class,
            IllegalArgumentException.class,
            MissingServletRequestParameterException.class,
            HttpRequestMethodNotSupportedException.class,
            MultipartException.class,
            MethodArgumentTypeMismatchException.class,
    })
    RestResponse handleControllerApiException(HttpServletRequest request, HttpServletResponse response, Throwable ex) {
        RestResponse resp;
        int logLevel = ERROR;

        // 缺少参数，直接处理
        if (ex instanceof MissingServletRequestParameterException) {
            RestResponse responseVO = RestResponse.fail(CommonCodeEnum.INVALIDATED_PARAM.getMsg());
            printRequestInfo(request, ex, INFO, responseVO);
            return responseVO;
        }

        // 不支持的请求方式，直接处理
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            RestResponse responseVO = RestResponse.fail(CommonCodeEnum.METHOD_ERROR.getMsg());
            printRequestInfo(request, ex, INFO, responseVO);
            return responseVO;
        }
        //请求参数异常
        if (ex instanceof MethodArgumentTypeMismatchException) {
            RestResponse responseVO = RestResponse.fail(CommonCodeEnum.PARAM_EXCEPTION.getMsg());
            printRequestInfo(request, ex, INFO, responseVO);
            return responseVO;
        }


        CommonsException commonsExceptions = null;

        if (ex instanceof CommonsException) {
            commonsExceptions = (CommonsException) ex;
        } else if (ex.getCause() instanceof CommonsException) {
            commonsExceptions = (CommonsException) ex.getCause();
        } else if (ex.getCause() != null && ex.getCause().getCause() instanceof CommonsException) {
            commonsExceptions = (CommonsException) ex.getCause().getCause();
        }

        if (commonsExceptions != null) {
            resp = RestResponse.fail(commonsExceptions);
            if (isError(commonsExceptions)) {
                logLevel = ERROR;
            } else if (isWarn(commonsExceptions)) {
                logLevel = WARN;
            } else {
                logLevel = INFO;
            }
        } else if (ex instanceof MultipartException) {
            //附件解析异常
            resp = RestResponse.fail(CommonCodeEnum.SYSTEM_BUSY.getMsg());
            logLevel = WARN;
        } else {
            // 给前端返回，永远是"系统错误"
            // 细节特别是SQL语句，不允许反馈给前端
            resp = RestResponse.fail(CommonCodeEnum.SYSTEM_ERROR.getMsg());
        }
        printRequestInfo(request, ex, logLevel, resp);
        return resp;
    }

    private void printRequestInfo(HttpServletRequest request, Throwable ex, int infoLevel, RestResponse responseVO) {
        RequestInfoHandler requestInfoHandler = new RequestInfoHandler(request);
        Map<String, String> queryMap = requestInfoHandler.getQueryParams();
        Map<String, String> neoQueryMap = new HashMap<>(queryMap.size());
        for (String key : queryMap.keySet()) {
            if (key.contains("password")) {
                neoQueryMap.put(key, "****");
            } else {
                neoQueryMap.put(key, queryMap.get(key));
            }
        }
        String json = "";
        if (HttpUtil.isAjaxRequest(request)) {
            json = HttpUtil.getRequestBodyString(request);
        }

        requestInfoHandler.setQueryParams(neoQueryMap);
        final String requestInfo = JSON.toJSONString(requestInfoHandler);
        switch (infoLevel) {
            case ERROR:
                log.error("request info: {},  json[{}], system error: {}", requestInfo, json, getStackTraceAsString(ex));
                break;
            case WARN:
                log.warn("request info: {},  json[{}], system error: {}", requestInfo, json, getStackTraceAsString(ex));
                break;
            default:
                log.info("request info: {},  json[{}], system error: {}", requestInfo, json, getStackTraceAsString(ex));
                break;
        }
        if (Objects.nonNull(responseVO)) {
            handleExceptionMonitor(responseVO.getCode(), ex);
        }
    }

    private static boolean isError(CommonsException e) {
        return ErrorType.SYSTEM == e.getErrorType() || ErrorType.BUSINESS_ERROR == e.getErrorType();
    }

    private static boolean isWarn(CommonsException e) {
        return ErrorType.BUSINESS_WARN == e.getErrorType();
    }

    /**
     * monitor 异常
     */
    private void handleExceptionMonitor(Integer errorCode, Throwable e) {
        try {
            String exception = getException(e);
            String location = findLocationFromException(e);
            String uri = request.getRequestURI();
            log.error(" errorCode :{}, exception:{}, location:{}, uri: {}", errorCode, exception, location, uri);
        } catch (Exception ex) {
            log.error("metricService error ExceptionHandlerProcessor ", ex);
        }
    }

    /**
     * 从 异常 堆栈解析直接报错位置
     */
    private String findLocationFromException(Throwable e) {
        String location = "";
        StackTraceElement[] stackTrace = e.getStackTrace();
        Optional<StackTraceElement> first = Optional.ofNullable(stackTrace).map(Arrays::stream).orElse(
                Stream.empty()).filter(
                item -> item.getClassName().contains("com.gaotu") && !item.getClassName().contains("Exception")).limit(
                1).findFirst();
        if (first.isPresent()) {
            StackTraceElement stackTraceElement = first.get();
            location = stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ":" + stackTraceElement.getLineNumber();
        }
        return location;
    }

    /**
     * 获取异常类型字符串
     */
    private String getException(Throwable e) {
        String res = "Exception";
        if (e instanceof SQLException) {
            res = "SQLException";
        } else if (e instanceof CommonsException) {
            res = "CommonsException";
        } else if (e instanceof IllegalArgumentException) {
            res = "IllegalArgumentException";
        } else if (e instanceof MissingServletRequestParameterException) {
            res = "MissingServletRequestParameterException";

        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            res = "HttpRequestMethodNotSupportedException";
        } else if (e instanceof MultipartException) {
            res = "MultipartException";
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            res = "MethodArgumentTypeMismatchException";
        }
        return res;
    }

    private String getStackTraceAsString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

}
