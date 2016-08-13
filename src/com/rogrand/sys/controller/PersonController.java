package com.rogrand.sys.controller;

import static com.rogrand.core.annotation.ActionAnnotation.Type.LOGIN;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.security.MD5;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.sys.domain.Role;
import com.rogrand.sys.domain.User;
import com.rogrand.sys.service.UserService;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：个人信息、密码修改控制器
 */
@Controller("sysPersonController")
@RequestMapping("/sys/person_*")
public class PersonController extends BaseController {
    
    @Autowired
    @Qualifier("sysUserService")
    private UserService userService;
    
    @ActionAnnotation(name = "个人信息查看", check = LOGIN)
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.query(getLoginUser(request).getSu_id());
        map.put("user", user);
        if (user.getSu_admin().equals("0")) {
            List<Role> roleList = userService.listRole(user);
            map.put("roleList", roleList);
        }
        return getView(request, map);
    }
    
    @ActionAnnotation(name = "个人信息修改", check = LOGIN)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return view(request, response);
    }
    
    @ActionAnnotation(name = "个人信息修改", check = LOGIN , log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = BeanUtil.wrapBean(User.class, request.getParameter("user"));
        return userService.updatePersonInfo(user) ? responseText(response, "1") : responseText(response, "0");
    }
    
    @ActionAnnotation(name = "个人密码修改", check = LOGIN)
    public ModelAndView editPass(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }
    
    @ActionAnnotation(name = "个人密码修改", check = LOGIN, log = true)
    public ModelAndView editPassSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String originalPwd = request.getParameter("su_password0");
        String newPwd = request.getParameter("su_password");
        
        if (StringUtils.isEmpty(originalPwd) || StringUtils.isEmpty(newPwd)) {
            return responseText(response, "原始密码或新密码不能为空!");
        }
        
        User user = userService.query(getLoginUser(request).getSu_id());
        if (user != null) {// 用户信息不存在
            if (user.getSu_password().equals(MD5.getEncrypt(originalPwd))) {// 原始密码匹配
                user.setSu_password(MD5.getEncrypt(newPwd));
                return responseText(response, userService.updatePassword(user));
            } else {// 原始密码不匹配
                return responseText(response, "输入的原始密码不正确!");
            }
        } else {
            return responseText(response, "用户信息不存在!");
        }
    }
    
}
