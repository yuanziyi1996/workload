///**
// * Baijiahulian.com Inc. Copyright (c) 2014-2017 All Rights Reserved.
// */
//package com.yyyf.workload.filter;
//
//import com.alibaba.fastjson.JSON;
//import com.yyyf.workload.util.HttpUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Map;
//
//@Slf4j
//@Component
//public class LogCommonFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//        throws IOException, ServletException {
//
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        RequestInfoHandler requestInfoHandler = new RequestInfoHandler(httpServletRequest);
//        final String requestHeadInfo = JSON.toJSONString(requestInfoHandler);
//        Map<String, String> params = HttpUtil.getParameterMap(httpServletRequest);
//        String ip = HttpUtil.getIpAddr(httpServletRequest);
//        String path = httpServletRequest.getRequestURI();
//        String json = "";
//        if (httpServletRequest.getContentType() == null || HttpUtil.isAjaxRequest(httpServletRequest)) {
//            json = HttpUtil.getRequestBodyString(httpServletRequest);
//        }
//        //1、开始时间
//        long beginTime = System.currentTimeMillis();
//        log.info("request log path[{}],header [{}], ip[{}], params[{}], json[{}]", path, requestHeadInfo, ip, params,
//                 json);
//        chain.doFilter(request, response);
//        long endTime = System.currentTimeMillis();
//        //3、消耗的时间
//        long consumeTime = endTime - beginTime;
//        if (consumeTime > 500) {
//            //此处认为处理时间超过500毫秒的请求为慢请求
//            log.info(String.format("slow request %s consume %d millis", ((HttpServletRequest) request).getRequestURI(), consumeTime));
//        }
//        log.info("response log path[{}],return[{}] , cost : {} ms" ,path, response, consumeTime);
//
//    }
//
//    @Override
//    public void destroy() {
//    }
//
//}
