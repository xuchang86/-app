package com.rogrand.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.EasyuiUtil;
import com.rogrand.sys.domain.Org;
import com.rogrand.sys.domain.Role;
import com.rogrand.sys.domain.User;
import com.rogrand.sys.service.OrgService;
import com.rogrand.sys.service.UserService;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：用户管理控制器
 */
@Controller("sysUserController")
@RequestMapping("/sys/user_*")
public class UserController extends BaseController {
    
    @Autowired
    @Qualifier("sysUserService")
    private UserService userService;
    
    @Autowired
    @Qualifier("sysOrgService")
    private OrgService orgService;
    
    @ActionAnnotation(name = "用户管理入口", group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
        return getView(request);
    }
    
    @ActionAnnotation(name = "机构树", group = "查询")
    public ModelAndView tree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String tree = orgService.tree(new Org());
        return responseText(response, tree);
    }
    
    @ActionAnnotation(name = "用户分页查询", group = "查询")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam param = BeanUtil.wrapPageBean(request);
        param.put("su_admin", getLoginUser(request).getSu_admin());
        PageResult pageResult = userService.pageList(param);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }
    
    @ActionAnnotation(name = "用户详细查询", group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.query(request.getParameter("su_id"));
        map.put("user", user);
        if (user.getSu_admin().equals("0")) {
            List<Role> roleList = userService.listRole(user);
            map.put("roleList", roleList);
        }
        return getView(request, map);
    }
    
    @ActionAnnotation(name = "用户添加", group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String tree = orgService.tree(new Org());
        List<Role> roleList = userService.listRole(new User("su_id", "0"));
        map.put("tree", tree);
        map.put("roleList", roleList);
        map.put("so_id", request.getParameter("so_id"));
        return getView(request, map);
    }
    
    @ActionAnnotation(name = "用户添加保存", group = "添加", log = true)
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = BeanUtil.wrapBean(User.class, request.getParameter("user"));
        String result = userService.create(user);
        return responseText(response, result);
    }
    
    @ActionAnnotation(name = "用户修改", group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.query(request.getParameter("su_id"));
        String tree = orgService.tree(new Org());
        map.put("user", user);
        map.put("tree", tree);
        if (user.getSu_admin().equals("0")) {
            List<Role> roleList = userService.listRole(user);
            map.put("roleList", roleList);
        }
        return getView(request, map);
    }
    
    @ActionAnnotation(name = "用户修改保存", group = "修改", log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = BeanUtil.wrapBean(User.class, request.getParameter("user"));
        String result = userService.update(user);
        return responseText(response, result);
    }
    
    @ActionAnnotation(name = "用户删除", group = "删除", log = true)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = userService.delete(request.getParameter("su_id"));
        return responseText(response, result);
    }
    
    @ActionAnnotation(name = "密码修改", group = "修改", log = true)
    public ModelAndView editPass(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        if (request.getMethod().equalsIgnoreCase("GET")) {
            Map<String, Object> map = new HashMap<String, Object>();
            User user = userService.query(request.getParameter("su_id"));
            map.put("user", user);
            return getView(request, map);
        } else if (request.getMethod().equalsIgnoreCase("POST")) {
            User user = BeanUtil.wrapBean(User.class, request.getParameter("user"));
            String result = userService.updatePassword(user);
            if (result.equals("1")) {
                User loginUser = getLoginUser(request);
                if (loginUser.getSu_id().equals(user.getSu_id())) {
                    loginUser.setSu_password(user.getSu_password());
                }
            }
            
            return responseText(response, "1");
        }
        return null;
    }
}
