package com.rogrand.buynew.controller;

import java.util.Date;
import java.util.List;
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
import com.rogrand.buynew.domain.FashionClassify;
import com.rogrand.buynew.service.FashionClassifyService;

/**
 * 版权：LAB <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-06 <br/>
 * 描述：服装分类 Controller
 */
@Controller("FashionClassifyController")
@RequestMapping("/buynew/fashionClassify_*.do")
public class FashionClassifyController extends BaseController{

    @Autowired
    @Qualifier("FashionClassifyService")
    private FashionClassifyService fashionClassifyService;

    @ActionAnnotation(name = "服装分类入口",group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "服装分类分页",group = "查询")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam fashionClassify = BeanUtil.wrapPageBean(request);
        PageResult pageResult = fashionClassifyService.pageList(fashionClassify);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }
    
    @ActionAnnotation(name = "获取服装分类",group = "查询")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam fashionClassify = BeanUtil.wrapPageBean(request);
        List<FashionClassify> list = fashionClassifyService.list(fashionClassify);
        return responseText(response, BeanUtil.toJsonString(list));
    }

    @ActionAnnotation(name = "服装分类详细",group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        FashionClassify fashionClassify = fashionClassifyService.query(request.getParameter("id"));
        model.put("fashionClassify",fashionClassify);
        return getView(request,model);
    }

    @ActionAnnotation(name = "服装分类添加",group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "服装分类添加保存",group = "添加",log = true)
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        FashionClassify fashionClassify = BeanUtil.wrapBean(FashionClassify.class, request.getParameter("fashionClassify"));
        User user = getLoginUser(request);
        fashionClassify.setCreate_user(user.getSu_code());
        fashionClassify.setCreate_time(new Date());
        String result = fashionClassifyService.create(fashionClassify);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装分类修改",group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        FashionClassify fashionClassify = fashionClassifyService.query(request.getParameter("id"));
        model.put("fashionClassify",fashionClassify);
        return getView(request,model);
    }

    @ActionAnnotation(name = "服装分类修改保存",group = "修改",log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        FashionClassify fashionClassify = BeanUtil.wrapBean(FashionClassify.class, request.getParameter("fashionClassify"));
        User user = getLoginUser(request);
        fashionClassify.setUpdate_user(user.getSu_code());
        fashionClassify.setUpdate_time(new Date());
        String result = fashionClassifyService.update(fashionClassify);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装分类删除",group = "删除",log = true)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = fashionClassifyService.delete(request.getParameter("id"));
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装分类批量删除",group = "删除",log = true)
    public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] ids = BeanUtil.wrapArray(String.class, request.getParameter("ids"));
        String result = fashionClassifyService.delete(ids);
        return responseText(response, result);
    }
}
