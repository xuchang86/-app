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
import com.rogrand.buynew.domain.BuyPools;
import com.rogrand.buynew.service.BuyPoolsService;

/**
 * 版权：LAB <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-08 <br/>
 * 描述：商品专区 Controller
 */
@Controller("BuyPoolsController")
@RequestMapping("/buynew/buyPools_*.do")
public class BuyPoolsController extends BaseController{

    @Autowired
    @Qualifier("BuyPoolsService")
    private BuyPoolsService buyPoolsService;

    @ActionAnnotation(name = "商品专区入口",group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "商品专区分页",group = "查询")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam buyPools = BeanUtil.wrapPageBean(request);
        PageResult pageResult = buyPoolsService.pageList(buyPools);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }

    @ActionAnnotation(name = "商品专区详细",group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        BuyPools buyPools = buyPoolsService.query(request.getParameter("id"));
        model.put("buyPools",buyPools);
        return getView(request,model);
    }

    @ActionAnnotation(name = "商品专区添加",group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "商品专区添加保存",group = "添加",log = true)
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BuyPools buyPools = BeanUtil.wrapBean(BuyPools.class, request.getParameter("buyPools"));
        User user = getLoginUser(request);
        buyPools.setCreate_user(user.getSu_code());
        buyPools.setCreate_time(new Date());
        String result = buyPoolsService.create(buyPools);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "商品专区修改",group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        BuyPools buyPools = buyPoolsService.query(request.getParameter("id"));
        model.put("buyPools",buyPools);
        return getView(request,model);
    }

    @ActionAnnotation(name = "商品专区修改保存",group = "修改",log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BuyPools buyPools = BeanUtil.wrapBean(BuyPools.class, request.getParameter("buyPools"));
        User user = getLoginUser(request);
        buyPools.setUpdate_user(user.getSu_code());
        buyPools.setUpdate_time(new Date());
        String result = buyPoolsService.update(buyPools);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "商品专区删除",group = "删除",log = true)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = buyPoolsService.delete(request.getParameter("id"));
        return responseText(response, result);
    }

    @ActionAnnotation(name = "商品专区批量删除",group = "删除",log = true)
    public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] ids = BeanUtil.wrapArray(String.class, request.getParameter("ids"));
        String result = buyPoolsService.delete(ids);
        return responseText(response, result);
    }
}
