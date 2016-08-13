package com.rogrand.buynew.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rogrand.buynew.domain.HotClass;
import com.rogrand.buynew.service.HotClassService;
import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.EasyuiUtil;
import com.rogrand.sys.domain.User;

/**
 * 版权：小月科技 <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-02 <br/>
 * 描述：热门品类 Controller
 */
@Controller("HotClassController")
@RequestMapping("/buynew/hotClass_*.do")
public class HotClassController extends BaseController{

    @Autowired
    @Qualifier("HotClassService")
    private HotClassService hotClassService;
    
    @ActionAnnotation(name = "热门品类入口",group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "热门品类分页",group = "查询")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam hotClass = BeanUtil.wrapPageBean(request);
        PageResult pageResult = hotClassService.pageList(hotClass);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }

    @ActionAnnotation(name = "热门品类详细",group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        HotClass hotClass = hotClassService.query(request.getParameter("id"));
        model.put("hotClass",hotClass);
        return getView(request,model);
    }

    @ActionAnnotation(name = "热门品类添加",group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "热门品类添加保存",group = "添加",log = true)
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HotClass hotClass = BeanUtil.wrapBean(HotClass.class, request.getParameter("hotClass"));
        User user = getLoginUser(request);
        String result = hotClassService.create(hotClass, user);
        return responseText(response, result);
    }
    
    @ActionAnnotation(name = "热门品类修改",group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        HotClass hotClass = hotClassService.query(request.getParameter("id"));
        model.put("hotClass",hotClass);
        return getView(request,model);
    }

    @ActionAnnotation(name = "热门品类修改保存",group = "修改",log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HotClass hotClass = BeanUtil.wrapBean(HotClass.class, request.getParameter("hotClass"));
        User user = getLoginUser(request);
        String result = hotClassService.update(hotClass, user);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "热门品类删除",group = "删除",log = true)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = hotClassService.delete(request.getParameter("id"));
        return responseText(response, result);
    }

    @ActionAnnotation(name = "热门品类批量删除",group = "删除",log = true)
    public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] ids = BeanUtil.wrapArray(String.class, request.getParameter("ids"));
        String result = hotClassService.delete(ids);
        return responseText(response, result);
    }
}
