package com.rogrand.sys.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.EasyuiUtil;
import com.rogrand.core.util.FileUtil;
import com.rogrand.sys.domain.Action;
import com.rogrand.sys.domain.App;
import com.rogrand.sys.domain.Menu;
import com.rogrand.sys.service.AppService;
import com.rogrand.sys.service.MenuService;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：菜单管理控制器
 */
@Controller("sysMenuController")
@RequestMapping("/sys/menu_*")
public class MenuController extends BaseController {
    @Autowired
    @Qualifier("sysMenuService")
    private MenuService menuService;

    @Autowired
    @Qualifier("sysAppService")
    private AppService appService;

    @ActionAnnotation(name = "菜单管理入口", group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<App> appList = appService.list(new App("order", "sap_id"));
        if (appList != null && appList.size() > 0) map.put("app", appList.get(0));
        return getView(request, map);
    }

    @ActionAnnotation(name = "菜单树", group = "查询")
    public ModelAndView tree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Menu menu = new Menu("mode", "treeForMain");
        String tree = menuService.tree(menu);
        return responseText(response, tree);
    }

    @ActionAnnotation(name = "菜单列表", group = "查询")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Menu param = BeanUtil.wrapBean(Menu.class, request);
        param.setMode("list");
        param.setOrder("a.sm_parentid,a.sm_order");
        List<Menu> menuList = menuService.list(param);
        return responseText(response, EasyuiUtil.toDataGridData(menuList));
    }

    @ActionAnnotation(name = "菜单详细", group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Menu menu = menuService.query(request.getParameter("sm_id"));
        map.put("menu", menu);
        if (menu.getSm_type().equals("1")) {
            Action action = new Action("sm_id", menu.getSm_id());
            action.setOrder("sa_group");
            List<Action> actionList = menuService.listAction(action);
            map.put("actionList", actionList);
        }
        return getView(request, map);
    }

    @ActionAnnotation(name = "菜单添加", group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Menu menu = BeanUtil.wrapBean(Menu.class, request);
        menu.setMode("treeForAdd");
        String tree = menuService.tree(menu);
        map.put("menu", menu);
        map.put("tree", tree);
        map.put("icons", getIcons());
        return getView(request, map);
    }

    @ActionAnnotation(name = "菜单添加保存", group = "添加", log = true)
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Menu menu = BeanUtil.wrapBean(Menu.class, request.getParameter("menu"));
        List<Action> actionList = BeanUtil.wrapBeanList(Action.class, request.getParameter("actionList"));
        String result = menuService.create(menu, actionList);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "菜单修改", group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Menu menu = menuService.query(request.getParameter("sm_id"));
        menu.setMode("treeForEdit");
        String tree = menuService.tree(menu);
        map.put("menu", menu);
        map.put("tree", tree);
        map.put("icons", getIcons());
        if (menu.getSm_type().equals("1")) {
            Action action = new Action("sm_id", menu.getSm_id());
            action.setOrder("sa_group");
            List<Action> actionList = menuService.listAction(action);
            map.put("actionList", actionList);
        }
        return getView(request, map);
    }

    @ActionAnnotation(name = "菜单修改保存", group = "修改", log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Menu menu = BeanUtil.wrapBean(Menu.class, request.getParameter("menu"));
        List<Action> actionList = BeanUtil.wrapBeanList(Action.class, request.getParameter("actionList"));
        String result = menuService.update(menu, actionList);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "菜单删除", group = "删除", log = true)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sm_id = request.getParameter("sm_id");
        String result = menuService.delete(sm_id);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "菜单排序", group = "修改")
    public ModelAndView order(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Menu menu = BeanUtil.wrapBean(Menu.class, request);
        menu.setMode("order");
        menu.setOrder("a.sm_order");
        List<Menu> menuList = menuService.list(menu);
        map.put("menu", menu);
        map.put("menuList", menuList);
        return getView(request, map);
    }

    @ActionAnnotation(name = "菜单排序保存", group = "修改", log = true)
    public ModelAndView orderSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Menu> menuList = BeanUtil.wrapBeanList(Menu.class, request.getParameter("menuList"));
        String status = menuService.updateOrder(menuList);
        return responseText(response, status);
    }


    private Map<String, String> getIcons() {
        Map<String, String> map = new HashMap<String, String>();
        File file = new File(getServletContext().getRealPath("/css/iconMenu.css"));
        String css = FileUtil.read(file);

        String regex = "/\\*(.*?)\\*/";    //样式注释正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(css);
        List<String> commentList = new ArrayList<String>();
        while (matcher.find()) {
            if (matcher.group(1) != null) commentList.add(matcher.group(1).trim());
        }

        regex = "\\.(.*?)\\{";    //样式名称正则表达式
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(css);
        List<String> styleList = new ArrayList<String>();

        while (matcher.find()) {
            if (matcher.group(1) != null) styleList.add(matcher.group(1).trim());
        }

        for (int i = 0; i < styleList.size(); i++) {
            String key = styleList.get(i);
            String value = "";
            if (commentList.size() > i) value = commentList.get(i);
            map.put(key, value);
        }

        LinkedHashMap<String, String> sortMap = new LinkedHashMap<String, String>();
        Object[] keys = map.keySet().toArray();
        Arrays.sort(keys);
        for (Object key : keys) {
            sortMap.put((String) key, map.get(key));
        }
        return sortMap;
    }


}
