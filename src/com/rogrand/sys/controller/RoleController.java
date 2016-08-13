package com.rogrand.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.EasyuiUtil;
import com.rogrand.core.util.StringUtil;
import com.rogrand.sys.domain.Role;
import com.rogrand.sys.service.RoleService;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：角色管理控制器
 */
@Controller("sysRoleController")
@RequestMapping("/sys/role_*.do")
public class RoleController extends BaseController {

    @Autowired
    @Qualifier("sysRoleService")
    private RoleService roleService;

    @ActionAnnotation(name = "角色管理入口", group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "角色列表", group = "查询")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Role> roleList = roleService.list(new Role("order", "sr_id"));
        return responseText(response,EasyuiUtil.toDataGridData(roleList));
    }

    @ActionAnnotation(name = "菜单分配情况", group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sr_id = request.getParameter("sr_id");
        if (StringUtil.isEmpty(sr_id)) return responseText(response, "[]");
        Role role = new Role("sr_id", sr_id);
        String tree = roleService.tree(role);
        return responseText(response, tree);
    }

    @ActionAnnotation(name = "角色添加", group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String tree = roleService.tree(new Role());
        map.put("tree", tree);
        return getView(request, map);
    }

    @ActionAnnotation(name = "角色添加保存", group = "添加", log = true)
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Role role = BeanUtil.wrapBean(Role.class,request.getParameter("role"));

        JavaType javaType = TypeFactory.mapType(Map.class,String.class, String[].class);
        Map<String,String[]> menuAction = BeanUtil.wrapBean(javaType,request.getParameter("menuAction"));
        String result = roleService.create(role,menuAction);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result",result);
        map.put("sr_id",role.getSr_id());
        return responseText(response,BeanUtil.toJsonString(map));
    }

    @ActionAnnotation(name = "角色修改", group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Role role = roleService.query(request.getParameter("sr_id"));
        String tree = roleService.tree(role);
        map.put("role", role);
        map.put("tree", tree);
        return getView(request, map);
    }

    @ActionAnnotation(name = "角色修改保存", group = "修改", log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Role role = BeanUtil.wrapBean(Role.class,request.getParameter("role"));

        JavaType javaType = TypeFactory.mapType(Map.class,String.class, String[].class);
        Map<String,String[]> menuAction = BeanUtil.wrapBean(javaType,request.getParameter("menuAction"));
        String result = roleService.update(role,menuAction);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result",result);
        map.put("sr_id",role.getSr_id());
        return responseText(response,BeanUtil.toJsonString(map));
    }

    @ActionAnnotation(name = "角色删除", group = "删除", log = true)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = roleService.delete(request.getParameter("sr_id"));
        return responseText(response,result);
    }


}
