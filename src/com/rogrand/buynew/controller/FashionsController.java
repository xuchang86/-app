package com.rogrand.buynew.controller;

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
import com.rogrand.buynew.domain.Fashions;
import com.rogrand.buynew.service.FashionsService;

/**
 * 版权：小月科技 <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-01 <br/>
 * 描述：服装款式 Controller
 */
@Controller("FashionsController")
@RequestMapping("/buynew/fashions_*.do")
public class FashionsController extends BaseController{

    @Autowired
    @Qualifier("FashionsService")
    private FashionsService fashionsService;

    @ActionAnnotation(name = "服装款式入口",group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "服装款式分页",group = "查询")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam fashions = BeanUtil.wrapPageBean(request);
        PageResult pageResult = fashionsService.pageList(fashions);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }
    
    @ActionAnnotation(name = "服装款式分页",group = "查询")
    public ModelAndView page2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam fashions = BeanUtil.wrapPageBean(request);
        PageResult pageResult = fashionsService.pageList2(fashions);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }

    @ActionAnnotation(name = "服装款式详细",group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        Fashions fashions = fashionsService.query(request.getParameter("num_iid"));
        model.put("fashions",fashions);
        return getView(request,model);
    }

    @ActionAnnotation(name = "服装款式添加",group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "服装款式添加保存",group = "添加",log = true)
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Fashions fashions = BeanUtil.wrapBean(Fashions.class, request.getParameter("fashions"));
        String result = fashionsService.create(fashions);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装款式修改",group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        Fashions fashions = fashionsService.query(request.getParameter("num_iid"));
        model.put("fashions",fashions);
        return getView(request,model);
    }

    @ActionAnnotation(name = "服装款式修改保存",group = "修改",log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Fashions fashions = BeanUtil.wrapBean(Fashions.class, request.getParameter("fashions"));
        String result = fashionsService.update(fashions);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装款式删除",group = "删除",log = true)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = fashionsService.delete(request.getParameter("num_iid"));
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装款式批量删除",group = "删除",log = true)
    public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] num_iids = BeanUtil.wrapArray(String.class, request.getParameter("num_iids"));
        String result = fashionsService.delete(num_iids);
        return responseText(response, result);
    }
}
