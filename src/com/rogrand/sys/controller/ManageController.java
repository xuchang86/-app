package com.rogrand.sys.controller;

import static com.rogrand.core.annotation.ActionAnnotation.Type.LOGIN;
import static com.rogrand.core.annotation.ActionAnnotation.Type.NO;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.security.ValidateCode;
import com.rogrand.core.system.SystemConst;
import com.rogrand.core.system.SystemParameter;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.DateUtil;
import com.rogrand.core.util.StringUtil;
import com.rogrand.core.util.WebUtil;
import com.rogrand.sys.domain.Log;
import com.rogrand.sys.domain.Menu;
import com.rogrand.sys.domain.User;
import com.rogrand.sys.service.LogService;
import com.rogrand.sys.service.LoginService;
import com.rogrand.sys.service.UserService;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：系统管理（后台管理）控制器，管理系统或网站后台入口控制器
 */
@Controller("manageController")
@RequestMapping({"/sys/welcome.do","/sys/main.do","/sys/login*.do"})
public class ManageController extends BaseController {
    @Autowired
    @Qualifier("loginService")
    private LoginService loginService;

    @Autowired
    @Qualifier("sysLogService")
    private LogService logService;
    
    @Autowired
    @Qualifier("sysUserService")
    private UserService userService;

    /**
     * 记录登录日志
     *
     * @param loginUser
     * @throws Exception
     */
    private void logLogin(User loginUser, String method) throws Exception {
        Log log = new Log();
        log.setSl_date(new Date());
        log.setSl_class(this.getClass().getName());
        log.setSl_method(method);
        log.setSl_description("用户登录");
        log.setSl_user_code(loginUser.getSu_code());
        log.setSl_user_name(loginUser.getSu_name());
        log.setSl_org_name(loginUser.getSo_name());
        log.setSl_ip(loginUser.getSu_last_ip());
        log.setSl_content("用户登录系统");
        logService.create(log);
    }


    /**
     * 系统入口动作，在保存登录状态下直接进入系统，否则进入登录页面
     * @param request 请求对象
     * @param response 响应对象
     * @return ModelAndView
     * @throws Exception 异常
     */
    @ActionAnnotation(name = "系统入口动作", check = LOGIN)
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(true);
        User loginUser = getLoginUser(session);
        List<Menu> menuList = loginService.queryMenu(loginUser);
        if (menuList == null || menuList.size() == 0) {
            session.removeAttribute(SystemConst.LOGIN_USER);
            return getView("/sys/login", "status", "3"); //用户没有可用的菜单
        }
        if (loginUser.getSu_admin().equals("0")) {
            List<String> actionList = loginService.queryAction(loginUser);
            session.setAttribute(SystemConst.LOGIN_ACTION, actionList);
        }

        request.setAttribute("menu",createMenuBar(menuList));
        return getView(request);
    }


    /**
     * 登录验证
     * @param request 请求对象
     * @param response 响应对象
     * @return ModelAndView
     * @throws Exception 异常
     */
    @ActionAnnotation(name = "系统登录验证", check = NO)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getMethod().equalsIgnoreCase("GET")){
            return getView(request);
        }
        else if(request.getMethod().equalsIgnoreCase("POST")){
            String checkRandomCode = SystemParameter.get("checkRandomCode");
            if (checkRandomCode != null && checkRandomCode.equals("true")) {        //验证码开关
                if (!checkRandomCode(request)) {
                    return getView(request, "status", "0");   //验证码错误
                }
            }
            User user = BeanUtil.wrapBean(User.class,request);
            if (StringUtil.isEmpty(user.getSu_code()))  return getView(request, "status", "2");     //用户名或密码错误
            if (StringUtil.isEmpty(user.getSu_password()))  return getView(request, "status", "2"); //用户名或密码错误
            User loginUser = loginService.checkLogin(user);  //验证登录
            if (loginUser == null) return getView(request, "status", "2");    //用户名或密码错误
            if(!loginUser.getSu_admin().equals("1") && StringUtil.isEmpty(loginUser.getSr_id())){
                return getView(request, "status", "3");    //用户没有可用的角色
            }
            HttpSession session = request.getSession(true);
            session.setAttribute(SystemConst.LOGIN_USER, loginUser);
            session.removeAttribute(SystemConst.RANDOM_CODE);
            //更新登录ip,登录时间，登录次数
            int count = loginUser.getSu_login_count()+1;
            loginUser.setSu_login_count(count);
            loginUser.setSu_last_ip(WebUtil.getClientIP(request));
            loginUser.setSu_last_time(DateUtil.getNow());
            userService.update(loginUser);
            logLogin(loginUser,"login");
            return getRedirect("/sys/main.do");    //重定向后台管理页
        }
        return null;
    }

    /**
     * 退出登录
     * @param request 请求对象
     * @param response 响应对象
     * @return ModelAndView
     * @throws Exception 异常
     */
    @ActionAnnotation(name = "退出登录",check = NO)
    public ModelAndView loginExit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(true);
        session.removeAttribute(SystemConst.LOGIN_USER);
        return responseText(response, "1");
    }

    /**
     * 登录验证码
     * @param request 请求对象
     * @param response 响应对象
     * @return ModelAndView
     * @throws Exception 异常
     */
    @ActionAnnotation(name = "登录验证码",check = NO)
    public ModelAndView loginRandom(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("image/jpeg");
        HttpSession session = request.getSession(true);
        ValidateCode validateCode = new ValidateCode(80, 20, 4, 20);
        session.setAttribute(SystemConst.RANDOM_CODE, validateCode.getCode());
        validateCode.write(response.getOutputStream());
        return null;
    }

    @ActionAnnotation(name = "欢迎页面",check = ActionAnnotation.Type.LOGIN)
    public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response) throws Exception{
        return getView(request);
    }

    /**
     * 检查随机验证码
     *
     * @param request 请求对象
     * @return boolean
     */
    private boolean checkRandomCode(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session == null) return false;
        String requestCode = request.getParameter(SystemConst.RANDOM_CODE);
        String sessionCode = (String) session.getAttribute(SystemConst.RANDOM_CODE);
        session.removeAttribute(SystemConst.RANDOM_CODE);
        return requestCode != null && sessionCode != null && requestCode.equalsIgnoreCase(sessionCode);
    }

    /**
     * 创建菜单
     * @param menuList 菜单列表
     * @return String
     */
    private String createMenuBar(List<Menu> menuList) {
        StringBuilder menuBar = new StringBuilder();
        StringBuilder menuDrop = new StringBuilder();

        for (Menu menu : menuList) {
            if ("0".equals(menu.getSm_parentid())) {
                menuBar.append("<a href=\"javascript:void(0);\"");
                menuBar.append(" menu=\"#m").append(menu.getSm_id()).append("\"");
                if (!StringUtil.isEmpty(menu.getSm_icon())) {//有菜单图标
                    menuBar.append(" iconCls=\"").append(disposeHtml(menu.getSm_icon())).append("\"");
                }
                if (menu.getSm_child().equals("1")) {//有子菜单
                    menuBar.append(" class=\"easyui-menubutton\">");
                    menuDrop.append("<div id=\"m").append(menu.getSm_id()).append("\" class=\"menuDrop\">\n");
                    menuDrop.append(createMenu(menuList, menu.getSm_id()));
                    menuDrop.append("</div>\n");
                } else {
                    menuBar.append(" class=\"easyui-linkbutton lnk\" plain=\"true\"");
                    if (!StringUtil.isEmpty(menu.getSm_action())) {
                        menuBar.append(" onclick=\"menuClick('");
                        menuBar.append(menu.getSm_id());
                        menuBar.append("','");
                        menuBar.append(disposeJs(menu.getSm_name()));
                        menuBar.append("','");
                        menuBar.append(disposeAction(menu.getSm_action()));
                        menuBar.append("','");
                        menuBar.append(disposeJs(menu.getSm_icon()));
                        menuBar.append("')\"");
                    }
                    menuBar.append(">");
                }
                menuBar.append(disposeHtml(menu.getSm_name())).append("</a>\n");
            }
        }
        return "\n"+menuBar.toString() + menuDrop.toString();
    }

    /**
     * 创建二线和以下菜单
     * @param menuList 菜单列表
     * @param sm_parentid 父菜单id
     * @return String
     */
    private String createMenu(List<Menu> menuList, String sm_parentid) {
        StringBuilder menuBuffer = new StringBuilder();
        boolean find = false;
        for (Menu menu : menuList) {
            if (menu.getSm_parentid().equals(sm_parentid)) {
                find = true;
                if (menu.getSm_type().equals("0")) {   //菜单分割线
                    menuBuffer.append("<div class=\"menu-sep\"></div>\n");
                } else if (menu.getSm_type().equals("1")) { //菜单项
                    if (menu.getSm_child().equals("1")) {  //有子菜单
                        menuBuffer.append("<div");
                        if (!StringUtil.isEmpty(menu.getSm_icon())) {//有菜单图标
                            menuBuffer.append(" iconCls=\"").append(disposeHtml(menu.getSm_icon())).append("\"");
                        }
                        menuBuffer.append("><span>").append(disposeHtml(menu.getSm_name())).append("</span>\n");
                        String childMenu = createMenu(menuList, menu.getSm_id());
                        if (!StringUtil.isEmpty(childMenu)) {
                            menuBuffer.append("<div class=\"menuDrop\">\n");
                            menuBuffer.append(childMenu);
                            menuBuffer.append("</div>\n");
                        }
                        menuBuffer.append("</div>\n");
                    } else if (menu.getSm_child().equals("0")) { //无子菜单
                        menuBuffer.append("<div");
                        if (!StringUtil.isEmpty(menu.getSm_icon())) {//有菜单图标
                            menuBuffer.append(" iconCls=\"").append(disposeHtml(menu.getSm_icon())).append("\"");
                        }
                        if (!StringUtil.isEmpty(menu.getSm_action())) {
                            menuBuffer.append(" onclick=\"menuClick('");
                            menuBuffer.append(menu.getSm_id());
                            menuBuffer.append("','");
                            menuBuffer.append(disposeJs(menu.getSm_name()));
                            menuBuffer.append("','");
                            menuBuffer.append(disposeAction(menu.getSm_action()));
                            menuBuffer.append("','");
                            menuBuffer.append(disposeJs(menu.getSm_icon()));
                            menuBuffer.append("')\"");
                        }
                        menuBuffer.append(">") ;
                        menuBuffer.append(disposeHtml(menu.getSm_name())).append("</div>\n");
                    }
                }
            } else if (find) {
                break;
            }
        }
        return menuBuffer.toString();
    }


    private static String disposeHtml(String s){
        if(s==null) return "";
        return freemarker.template.utility.StringUtil.HTMLEnc(s);
    }

    private static String disposeJs(String s){
        if(s==null) return "";
        return freemarker.template.utility.StringUtil.javaScriptStringEnc(disposeHtml(s));
    }

    private static String disposeAction(String action){
        if(action==null) return "";
        return disposeJs("/" + StringUtil.trimChar(action, '/'));
    }

}
