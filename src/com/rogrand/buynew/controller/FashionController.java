package com.rogrand.buynew.controller;

import java.util.ArrayList;
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
import com.rogrand.buynew.domain.Fashion;
import com.rogrand.buynew.domain.FashionClassify;
import com.rogrand.buynew.service.FashionClassifyService;
import com.rogrand.buynew.service.FashionService;

/**
 * 版权：小月科技 <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-07-13 <br/>
 * 描述：服装 Controller
 */
@Controller("FashionController")
@RequestMapping("/buynew/fashion_*.do")
public class FashionController extends BaseController{

    @Autowired
    @Qualifier("FashionService")
    private FashionService fashionService;
    
    @Autowired
    @Qualifier("FashionClassifyService")
    private FashionClassifyService fashionClassifyService;

    @ActionAnnotation(name = "服装入口",group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }
    
    @ActionAnnotation(name = "服装新品入口",group = "查询")
    public ModelAndView news(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }
    
    @ActionAnnotation(name = "服装热卖入口",group = "查询")
    public ModelAndView hot(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }
    
    @ActionAnnotation(name = "服装特惠入口",group = "查询")
    public ModelAndView cheap(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "服装分页",group = "查询")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String type = request.getParameter("type");
    	PageParam fashion = BeanUtil.wrapPageBean(request);
    	fashion.put("sale_type", type);
        PageResult pageResult = fashionService.pageList(fashion);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }

    @ActionAnnotation(name = "服装详细",group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        Fashion fashion = fashionService.query(request.getParameter("fashion_id"));
        model.put("fashion",fashion);
        return getView(request,model);
    }

    @ActionAnnotation(name = "服装添加",group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String type = request.getParameter("type");
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("type", type);
        return getView(request);
    }

    @ActionAnnotation(name = "服装添加保存",group = "添加",log = true)
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Fashion fashion = BeanUtil.wrapBean(Fashion.class, request.getParameter("fashion"));
        String type = request.getParameter("type");
        User user = getLoginUser(request);
        String result = fashionService.create(fashion, user, type);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装修改",group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        Fashion fashion = fashionService.query(request.getParameter("fashion_id"));
        model.put("fashion",fashion);
        return getView(request,model);
    }

    @ActionAnnotation(name = "服装修改保存",group = "修改",log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Fashion fashion = BeanUtil.wrapBean(Fashion.class, request.getParameter("fashion"));
        User user = getLoginUser(request);
        String result = fashionService.update(fashion, user);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装删除",group = "删除",log = true)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = fashionService.delete(request.getParameter("fashion_id"));
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装批量删除",group = "删除",log = true)
    public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] fashion_ids = BeanUtil.wrapArray(String.class, request.getParameter("fashion_ids"));
        String result = fashionService.delete(fashion_ids);
        return responseText(response, result);
    }
    
    @ActionAnnotation(name = "查询所有的叶子节点分类",group = "查询")
    public ModelAndView getLeafTypes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //List<FashionClassify> list = fashionClassifyService.selectLeafClassify();
    	List<FashionClassify> list = new ArrayList<FashionClassify>();
        return responseText(response, EasyuiUtil.toDataGridData(list));
    }
}
