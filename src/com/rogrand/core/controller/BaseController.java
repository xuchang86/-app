package com.rogrand.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.util.UrlPathHelper;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.system.SystemConst;
import com.rogrand.sys.domain.User;

import freemarker.ext.beans.BeansWrapper;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：多请求映射控制器
 */
public class BaseController extends MultiActionController {


    protected UrlPathHelper urlPathHelper = new UrlPathHelper();

    /**
     * 获得web.xml中配置的servlet的名称
     *
     * @return String
     */
    protected String getServletName() {
        XmlWebApplicationContext context = (XmlWebApplicationContext) getWebApplicationContext();
        return context.getServletConfig().getServletName();
    }

    /**
     * 获得contextPath
     *
     * @param request 请求对象
     * @return String
     */
    protected String getPath(HttpServletRequest request) {
        return urlPathHelper.getContextPath(request);
    }

    /**
     * 根据请求对象得到控制器方法
     *
     * @param request 请求对象
     * @return String
     * @throws NoSuchRequestHandlingMethodException
     *          异常
     */
    protected String getMethod(HttpServletRequest request) throws NoSuchRequestHandlingMethodException {
        String methodName = this.getMethodNameResolver().getHandlerMethodName(request);
        int index = methodName.lastIndexOf("_");
        return index != -1 ? methodName.substring(index+1) : methodName;
    }

    /**
     * 得到域名端口 http://localhost:8080/
     *
     * @param request 请求对象
     * @return String
     */
    protected String getDomain(HttpServletRequest request) {
        String path = getPath(request);
        return request.getScheme() + "://" + request.getHeader("Host") + path;
    }

    /**
     * 通过请求url得到页面路径
     *
     * @param request 请求对象
     * @return String
     */
    private String takeView(HttpServletRequest request) {
        String path = urlPathHelper.getPathWithinApplication(request);
        int index = path.lastIndexOf("."); //后缀
        if (index != -1) {
            path = path.substring(0, index);
        }
        return  path;
    }

    /**
     * 通过请求url得到路径,并替换视图名称
     *
     * @param request 请求对象
     * @param view    视图名称
     * @return String
     */
//    private String takeView(HttpServletRequest request, String view) {
//        String path = takeView(request);
//        int index = path.lastIndexOf("/") + 1;
//        return path.substring(0, index) + view;
//    }

       /**
     * 得到返回视图和数据模型
     *
     * @param view 页面模板
     * @return ModelAndView
     */
    protected ModelAndView getView(String view){
        return new ModelAndView(view.startsWith("/")?view:"/"+view);
    }


     /**
     * 得到返回视图和数据模型
     *
     * @param view 页面模板
     * @param model 数据模型
     * @return ModelAndView
     */
    @SuppressWarnings("rawtypes")
	protected ModelAndView getView(String view,Map model){
        return new ModelAndView(view.startsWith("/")?view:"/"+view,model);
    }

     /**
     * 得到返回视图和数据模型
     *
     * @param view 页面模板
     * @param name 数据名称
     * @param obj 数据对象
     * @return ModelAndView
     */
    protected ModelAndView getView(String view,String name, Object obj){
        return new ModelAndView(view.startsWith("/")?view:"/"+view,name,obj);
    }


    /**
     * 得到返回视图和数据模型
     *
     * @param request 请求对象
     * @return ModelAndView
     */
    protected ModelAndView getView(HttpServletRequest request) {
        String vw = takeView(request);
        return new ModelAndView(vw);
    }

    /**
     * 得到返回视图和数据模型
     *
     * @param request 请求对象
     * @param name    返回单个对象的名称
     * @param obj     返回对象
     * @return ModelAndView
     */
    protected ModelAndView getView(HttpServletRequest request, String name, Object obj) {
        String vw = takeView(request);
        return new ModelAndView(vw, name, obj);
    }

    /**
     * 得到返回视图和数据模型
     *
     * @param request 请求对象
     * @param map     返回map对象
     * @return ModelAndView
     */
    @SuppressWarnings("rawtypes")
    protected ModelAndView getView(HttpServletRequest request, Map map) {
        String vw = takeView(request);
        return new ModelAndView(vw, map);
    }

    /**
     * 得到返回视图和数据模型
     *
     * @param request 请求对象
     * @param view    视图名称
     * @return ModelAndView
     */
//    protected ModelAndView getView(HttpServletRequest request, String view) {
//        String vw = takeView(request, view);
//        return new ModelAndView(vw);
//    }

    /**
     * 得到返回视图和数据模型
     *
     * @param request 请求对象
     * @param view    视图名称
     * @param map     返回map对象
     * @return ModelAndView
     */
//    protected ModelAndView getView(HttpServletRequest request, String view, Map map) {
//        String vw = takeView(request, view);
//        return new ModelAndView(vw, map);
//    }

    /**
     * 得到返回视图和数据模型
     *
     * @param request 请求对象
     * @param view    视图名称
     * @param name    返回单个对象的名称
     * @param obj     返回对象
     * @return ModelAndView
     */
//    protected ModelAndView getView(HttpServletRequest request, String view, String name, Object obj) {
//        String vw = takeView(request, view);
//        return new ModelAndView(vw, name, obj);
//    }

    /**
     * 重定向一个请求动作
     *
     * @param url 动作
     * @return View
     */
    protected ModelAndView getRedirect(String url) {
        String vw = UrlBasedViewResolver.REDIRECT_URL_PREFIX + url;
        return new ModelAndView(vw);
    }

    /**
     * 重定向一个请求动作
     *
     * @param url  动作
     * @param name 返回单个对象的名称
     * @param obj  返回对象
     * @return View
     */
    protected ModelAndView getRedirect(String url, String name, Object obj) {
        String vw = UrlBasedViewResolver.REDIRECT_URL_PREFIX + url;
        return new ModelAndView(vw, name, obj);
    }

    /**
     * 重定向一个请求动作
     *
     * @param url 动作
     * @param map 参数
     * @return View
     */
    @SuppressWarnings("rawtypes")
    protected ModelAndView getRedirect(String url, Map map) {
        String vw = UrlBasedViewResolver.REDIRECT_URL_PREFIX + url;
        return new ModelAndView(vw, map);
    }

    /**
     * 转发请求,从一个请求动作中转发到另一个请求动作
     *
     * @param url 请求动作
     * @return view 视图对象
     */
    protected ModelAndView getForward(String url) {
        String vw = UrlBasedViewResolver.FORWARD_URL_PREFIX + url;
        return new ModelAndView(vw);
    }


    /**
     * 转发请求,从一个请求动作中转发到另一个请求动作
     *
     * @param url  请求动作
     * @param name 返回单个对象的名称
     * @param obj  返回对象
     * @return view 视图对象
     */
    protected ModelAndView getForward(String url, String name, Object obj) {
        String vw = UrlBasedViewResolver.FORWARD_URL_PREFIX + url;
        return new ModelAndView(vw, name, obj);
    }

    /**
     * 转发请求,从一个请求动作中转发到另一个请求动作
     *
     * @param url 请求动作
     * @param map 参数
     * @return view 视图对象
     */
    @SuppressWarnings("rawtypes")
    protected ModelAndView getForward(String url, Map map) {
        String vw = UrlBasedViewResolver.FORWARD_URL_PREFIX + url;
        return new ModelAndView(vw, map);
    }


    /**
     * 响应文本
     *
     * @param response 响应对象
     * @param text     输出文本
     * @return null
     * @throws java.io.IOException 异常
     */
    protected ModelAndView responseText(HttpServletResponse response, String text) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        return new ModelAndView("/response", "data", text);
    }


    /**
     * 将字符串以xml形式输出到客户端
     *
     * @param response 响应对象
     * @param xml      输出的xml字符串
     * @return null
     * @throws IOException 抛出异常
     */
    protected ModelAndView responseXML(HttpServletResponse response, String xml) throws IOException {
        response.setContentType("text/xml; charset=UTF-8");
        return new ModelAndView("/response", "data", xml);
    }

    /**
     * 设置excel响应头
     *
     * @param response 响应对象
     * @param filename 文件名
     * @throws IOException 抛出异常
     */
    protected void setExcelHead(HttpServletResponse response, String filename) throws IOException {
        String name = encodingFileName(filename);
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + name + ".xls\"");
    }

    /**
     * 对下载文件名进行编码
     *
     * @param fileName 文件名
     * @return String
     */
    protected String encodingFileName(String fileName) {
        String returnFileName = "";
        try {
            returnFileName = URLEncoder.encode(fileName, "UTF-8");
            if (returnFileName.length() > 150) {
                returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());

        }
        return returnFileName;
    }


    /**
     * 设置word响应头
     *
     * @param response 响应对象
     * @param filename 文件名
     * @throws IOException 抛出异常
     */
    protected void setWordHead(HttpServletResponse response, String filename) throws IOException {
//        String name = new String(filename.getBytes(), "ISO-8859-1");
        String name = encodingFileName(filename);
        response.setContentType("application/vnd.ms-word;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + name + ".doc\"");
    }

    @SuppressWarnings("rawtypes")
	@Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            request.setAttribute("controller", getBeanName());
            request.setAttribute("path", getPath(request));
            request.setAttribute("domain", getDomain(request));
            request.setAttribute("sessionId", request.getSession().getId());
            request.setAttribute("url", urlPathHelper.getPathWithinApplication(request));
            request.setAttribute("factory", getWebApplicationContext());
            request.setAttribute("statics", BeansWrapper.getDefaultInstance().getStaticModels());
            response.addHeader("P3P", "CP=CAO PSA OUR");
            preHandle(request, response);
            String methodName = getMethod(request);
            request.setAttribute("methodName",methodName);
            Class[] classes = {HttpServletRequest.class,HttpServletResponse.class};
            Method method = BeanUtils.findMethod(this.getClass(), methodName,classes);
            if (method != null) {
                if (method.isAnnotationPresent(ActionAnnotation.class)) {//存在动作注解
                    ActionAnnotation annotation = method.getAnnotation(ActionAnnotation.class);
                    request.setAttribute("methodDescription", annotation.name());
                }
                return invokeNamedMethod(methodName, request, response);
            }
            else {
                return getView(request);
            }
        }
        catch (ModelAndViewDefiningException e) {
            throw e;
        }
        catch (NoSuchRequestHandlingMethodException ex) {
            return handleNoSuchRequestHandlingMethod(ex, request, response);
        }
        catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            logger.error(sw.toString());
            sw.close();
            throw e;
        }
    }

    @Override
    protected ModelAndView handleNoSuchRequestHandlingMethod(NoSuchRequestHandlingMethodException ex, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }


    /**
     * 得到登录用户对象
     *
     * @param request 请求对象
     * @return User
     */
    protected User getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        return getLoginUser(session);
    }

    /**
     * 得到登录用户对象
     *
     * @param session 会话
     * @return User
     */
    protected User getLoginUser(HttpSession session) {
        if (session == null) return null;
        Object obj = session.getAttribute(SystemConst.LOGIN_USER);
        return (obj != null && obj instanceof User) ? (User) obj : null;
    }

    /**
     * 控制器方法前执行
     *
     * @param request  请求对象
     * @param response 响应对象
     * @throws Exception 异常
     */
    protected void preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //DataSourceUtil.resetSqlDao();
    }

    /**
     * 得到工厂中的bean
     *
     * @param id  bean id
     * @param <T> 对象类
     * @return 对象实例
     */
    @SuppressWarnings("unchecked")
	protected <T> T getBean(String id) {
        return (T) getApplicationContext().getBean(id);
    }


    protected String getBeanName() {
        String className = this.getClass().getName();//访问的控制器名称
        if (this.getClass().isAnnotationPresent(Controller.class)) {        //读取controller注解名称
            className =  this.getClass().getAnnotation(Controller.class).value();
        }
        return className;
    }

}
