package com.rogrand.buynew.controller;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.EasyuiUtil;
import com.rogrand.sys.domain.User;
import com.rogrand.buynew.domain.HotLable;
import com.rogrand.buynew.service.HotLableService;

/**
 * 版权：LAB <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-06 <br/>
 * 描述：热门标签 Controller
 */
@Controller("HotLableController")
@RequestMapping("/buynew/hotLable_*.do")
public class HotLableController extends BaseController{

    @Autowired
    @Qualifier("HotLableService")
    private HotLableService hotLableService;

    @ActionAnnotation(name = "热门标签入口",group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "热门标签分页",group = "查询")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam hotLable = BeanUtil.wrapPageBean(request);
        PageResult pageResult = hotLableService.pageList(hotLable);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }

    @ActionAnnotation(name = "热门标签详细",group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        HotLable hotLable = hotLableService.query(request.getParameter("id"));
        model.put("hotLable",hotLable);
        return getView(request,model);
    }

    @ActionAnnotation(name = "热门标签添加",group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "热门标签添加保存",group = "添加",log = true)
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HotLable hotLable = BeanUtil.wrapBean(HotLable.class, request.getParameter("hotLable"));
        User user = getLoginUser(request);
        hotLable.setCreate_user(user.getSu_code());
        hotLable.setCreate_time(new Date());
        String result = hotLableService.create(hotLable);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "热门标签修改",group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        HotLable hotLable = hotLableService.query(request.getParameter("id"));
        model.put("hotLable",hotLable);
        return getView(request,model);
    }

    @ActionAnnotation(name = "热门标签修改保存",group = "修改",log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HotLable hotLable = BeanUtil.wrapBean(HotLable.class, request.getParameter("hotLable"));
        User user = getLoginUser(request);
        hotLable.setUpdate_user(user.getSu_code());
        hotLable.setUpdate_time(new Date());
        String result = hotLableService.update(hotLable);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "热门标签删除",group = "删除",log = true)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = hotLableService.delete(request.getParameter("id"));
        return responseText(response, result);
    }

    @ActionAnnotation(name = "热门标签批量删除",group = "删除",log = true)
    public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] ids = BeanUtil.wrapArray(String.class, request.getParameter("ids"));
        String result = hotLableService.delete(ids);
        return responseText(response, result);
    }
}
