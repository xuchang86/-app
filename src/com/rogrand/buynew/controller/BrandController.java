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

import com.rogrand.buynew.domain.Brand;
import com.rogrand.buynew.service.BrandService;
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
 * 生成日期：2016-07-16 <br/>
 * 描述：品牌 Controller
 */
@Controller("BrandController")
@RequestMapping("/buynew/brand_*.do")
public class BrandController extends BaseController{

    @Autowired
    @Qualifier("BrandService")
    private BrandService brandService;
    
    @ActionAnnotation(name = "品牌入口",group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "品牌分页",group = "查询")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam brand = BeanUtil.wrapPageBean(request);
        PageResult pageResult = brandService.pageList(brand);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }

    @ActionAnnotation(name = "品牌详细",group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        Brand brand = brandService.query(request.getParameter("brand_id"));
        model.put("brand",brand);
        return getView(request,model);
    }

    @ActionAnnotation(name = "品牌添加",group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }
    
    @ActionAnnotation(name = "添加品牌服装",group = "添加")
    public ModelAndView addfashion(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "品牌添加保存",group = "添加",log = true)
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Brand brand = BeanUtil.wrapBean(Brand.class, request.getParameter("brand"));
        User user = getLoginUser(request);
        String result = brandService.create(brand, user);
        return responseText(response, result);
    }
    
    @ActionAnnotation(name = "品牌修改",group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        Brand brand = brandService.query(request.getParameter("brand_id"));
        model.put("brand",brand);
        return getView(request,model);
    }

    @ActionAnnotation(name = "品牌修改保存",group = "修改",log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Brand brand = BeanUtil.wrapBean(Brand.class, request.getParameter("brand"));
        User user = getLoginUser(request);
        String result = brandService.update(brand, user);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "品牌删除",group = "删除",log = true)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = brandService.delete(request.getParameter("brand_id"));
        return responseText(response, result);
    }

    @ActionAnnotation(name = "品牌批量删除",group = "删除",log = true)
    public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] brand_ids = BeanUtil.wrapArray(String.class, request.getParameter("brand_ids"));
        String result = brandService.delete(brand_ids);
        return responseText(response, result);
    }
}
