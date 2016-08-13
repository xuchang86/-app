package com.rogrand.core.advice;

import static com.rogrand.core.annotation.ActionAnnotation.Type.LOGIN;
import static com.rogrand.core.annotation.ActionAnnotation.Type.LOGIN_GROUP;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.exception.NotAccessException;
import com.rogrand.core.exception.NotLoginException;
import com.rogrand.core.system.SystemConst;
import com.rogrand.core.util.WebUtil;
import com.rogrand.sys.domain.Log;
import com.rogrand.sys.domain.User;
import com.rogrand.sys.service.LogService;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：通过spring切面，记录controller中的方法日志
 */
@SuppressWarnings({"unchecked"})
@Service("rightAdvice")
public class RightAdvice extends HandlerInterceptorAdapter implements ServletContextAware {

    protected org.apache.commons.logging.Log logger = LogFactory.getLog(RightAdvice.class);

    private static ThreadLocal<Log> logThreadLocal = new ThreadLocal<Log>();

    @Autowired
    @Qualifier("sysLogService")
    private LogService logService;

    @SuppressWarnings("unused")
	private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String className = handler.getClass().getName();//访问的控制器名称
        if(handler.getClass().isAnnotationPresent(Controller.class)){        //读取controller注解名称
            Controller controller = handler.getClass().getAnnotation(Controller.class);
            className = controller.value();
        }

        String methodName = ((MultiActionController) handler).getMethodNameResolver().getHandlerMethodName(request);
        int index = methodName.lastIndexOf("_");

        methodName = index != -1 ? methodName.substring(index + 1) : methodName;
        Class[] classes = {HttpServletRequest.class,HttpServletResponse.class};
        Method method = BeanUtils.findMethod(handler.getClass(), methodName,classes);
        if (method != null) {//方法存在
            if (method.isAnnotationPresent(ActionAnnotation.class)) {//存在动作注解
                ActionAnnotation annotation = method.getAnnotation(ActionAnnotation.class);
                HttpSession session = request.getSession(true);
                User user = getLoginUser(session);
                if (annotation.check() == LOGIN) {
                    if (user == null) {
                        throw new NotLoginException("您未登录系统或者登录已超时,请重新登录");
                    }
                } else if (annotation.check() == LOGIN_GROUP) {
                    if (user == null) {
                        throw new NotLoginException("您未登录系统或者登录已超时,请重新登录");
                    }

                    //不需要功能权限，这段代码屏蔽掉
                    /*if (!user.getSu_admin().equals("1")) {
                        String rightAction = className + "." + annotation.group();
                        if (!checkAction(session, rightAction)) {
                            throw new NotAccessException("未经授权的访问请求,请和管理员联系");
                        }
                    }*/
                }
                if (annotation.log()) {
                    Log log = new Log();
                    log.setSl_class(handler.getClass().getName());
                    log.setSl_method(methodName);
                    log.setSl_description(annotation.name());
                    log.setSl_date(new Date());
                    log.setSl_ip(WebUtil.getClientIP(request));
                    if (user != null) {
                        log.setSl_user_code(user.getSu_code());
                        log.setSl_user_name(user.getSu_name());
                        log.setSl_org_name(user.getSo_name());
                    }
                    logThreadLocal.set(log);
                }
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Log log = logThreadLocal.get();

        if (log != null && log.getSl_content() != null) {
            try {
                logService.create(log);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        logThreadLocal.remove();
        super.afterCompletion(request, response, handler, ex);
    }


    /**
     * 得到登录用户对象
     *
     * @param session 会话对象
     * @return User
     */
    private User getLoginUser(HttpSession session) {
        if (session == null) return null;
        Object obj = session.getAttribute(SystemConst.LOGIN_USER);
        return obj != null && obj instanceof User ? (User) obj : null;
    }

    /**
     * 验证访问动作
     *
     * @param session     会话对象
     * @param rightAction 动作
     * @return boolean
     */
    private boolean checkAction(HttpSession session, String rightAction) {
        if (session == null) return false;
        Object obj = session.getAttribute(SystemConst.LOGIN_ACTION);
        if (obj == null || !(obj instanceof List)) return false;
        List<String> actions = (List<String>) obj;
        return actions.contains(rightAction);
    }

    /**
     * 判断是否写日志
     *
     * @return
     */
    public static boolean isLog() {
        return logThreadLocal.get() != null;
    }

    /**
     * sqlDao 执行create update delete 时将更新内容加入到日志对象中
     *
     * @param content
     * @throws Exception
     */
    public static void appendContent(String content) throws Exception {
        Log log = logThreadLocal.get();
        if (log == null) return;
        String text = log.getSl_content();
        if (text == null) {
            log.setSl_content(content);
        } else {
            log.setSl_content(text + "\n" + content);
        }
    }

}
