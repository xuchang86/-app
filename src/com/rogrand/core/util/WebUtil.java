package com.rogrand.core.util;

import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：web工具,获取request,response,session,servletcontext
 */
public class WebUtil {

    
    private static ThreadLocal<HttpServletRequest> requests = new ThreadLocal<HttpServletRequest>();
    private static ThreadLocal<HttpServletResponse> responses = new ThreadLocal<HttpServletResponse>();
    private static ServletContext servletContext = null;


    /**
     * 将请求对象存储到本地线程中
     *
     * @param request 请求对象
     */
    public static void setRequest(HttpServletRequest request) {
        requests.remove();
        requests.set(request);
    }

    /**
     * 从本地线程中得到请求对象
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return requests.get();
    }

    /**
     * 移除请求对象
     */
    public static void removeRequest(){
        requests.remove();
    }

    /**
     * 将响应对象存储到本地线程
     *
     * @param response 响应对象
     */
    public static void setResponse(HttpServletResponse response) {
        responses.remove();
        responses.set(response);
    }

    /**
     * 从本地线程中得到响应对象
     *
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        return responses.get();
    }

    /**
     * 移除响应对象
     */
    public static void removeResponse(){
        responses.remove();
    }

    /**
     * 从本地线程中得到会话对象
     *
     * @return HttpServletResponse
     */
    public static HttpSession getSession() {
        return getRequest().getSession(true);
    }


    /**
     * 将全局对象存储到本地线程
     *
     * @param context 全局对象
     */
    public static void setServletContext(ServletContext context) {
        servletContext = context;
    }

    /**
     * 从本地线程中得到全局对象
     *
     * @return ServletContext
     */
    public static ServletContext getServletContext() {
        return servletContext;
    }

    public static WebApplicationContext getWebApplicationContext(){
        return WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    }

    /**
     * 移除全局对象
     */
    public static void removeServletContext(){
        servletContext=null;
    }


    public static String getClientIP(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if( ip == null ) {
        	ip = request.getRemoteAddr();
        }
        
        if(ip==null) return "unknown";
        ip=ip.trim().replace("'","");
        String[] ips=ip.split(",");
        for(String s:ips){
            String t=s.trim();
            if( t != null && t.length() > 0 ) {
            	return t;
            }
        }
        return "unknown";
    }

}
