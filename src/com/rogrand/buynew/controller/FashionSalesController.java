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
import com.rogrand.buynew.domain.FashionSales;
import com.rogrand.buynew.service.FashionSalesService;

/**
 * 版权：小月科技 <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-07-13 <br/>
 * 描述：服装促销 Controller
 */
@Controller("FashionSalesController")
@RequestMapping("/buynew/fashionSales_*.do")
public class FashionSalesController extends BaseController{

    @Autowired
    @Qualifier("FashionSalesService")
    private FashionSalesService fashionSalesService;

    @ActionAnnotation(name = "服装促销入口",group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "服装促销分页",group = "查询")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam fashionSales = BeanUtil.wrapPageBean(request);
        PageResult pageResult = fashionSalesService.pageList(fashionSales);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }

    @ActionAnnotation(name = "服装促销详细",group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        FashionSales fashionSales = fashionSalesService.query(request.getParameter("fashion_sales_id"));
        model.put("fashionSales",fashionSales);
        return getView(request,model);
    }

    @ActionAnnotation(name = "服装促销添加",group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "服装促销添加保存",group = "添加",log = true)
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        FashionSales fashionSales = BeanUtil.wrapBean(FashionSales.class, request.getParameter("fashionSales"));
        String result = fashionSalesService.create(fashionSales);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装促销修改",group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        FashionSales fashionSales = fashionSalesService.query(request.getParameter("fashion_sales_id"));
        model.put("fashionSales",fashionSales);
        return getView(request,model);
    }

    @ActionAnnotation(name = "服装促销修改保存",group = "修改",log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        FashionSales fashionSales = BeanUtil.wrapBean(FashionSales.class, request.getParameter("fashionSales"));
        String result = fashionSalesService.update(fashionSales);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装促销删除",group = "删除",log = true)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = fashionSalesService.delete(request.getParameter("fashion_sales_id"));
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装促销批量删除",group = "删除",log = true)
    public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] fashion_sales_ids = BeanUtil.wrapArray(String.class, request.getParameter("fashion_sales_ids"));
        String result = fashionSalesService.delete(fashion_sales_ids);
        return responseText(response, result);
    }
}
