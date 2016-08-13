package com.rogrand.buynew.controller;

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
import com.rogrand.buynew.domain.Venue;
import com.rogrand.buynew.service.VenueService;

/**
 * 版权：LAB <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-02 <br/>
 * 描述：品牌馆 Controller
 */
@Controller("VenueController")
@RequestMapping("/buynew/venue_*.do")
public class VenueController extends BaseController{

    @Autowired
    @Qualifier("VenueService")
    private VenueService venueService;

    @ActionAnnotation(name = "品牌馆入口",group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "品牌馆分页",group = "查询")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam venue = BeanUtil.wrapPageBean(request);
        PageResult pageResult = venueService.pageList(venue);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }

    @ActionAnnotation(name = "品牌馆详细",group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        Venue venue = venueService.query(request.getParameter("id"));
        model.put("venue",venue);
        return getView(request,model);
    }

    @ActionAnnotation(name = "品牌馆添加",group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "品牌馆添加保存",group = "添加",log = true)
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Venue venue = BeanUtil.wrapBean(Venue.class, request.getParameter("venue"));
        User user = getLoginUser(request);
        String result = venueService.create(venue, user);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "品牌馆修改",group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        Venue venue = venueService.query(request.getParameter("id"));
        model.put("venue",venue);
        return getView(request,model);
    }

    @ActionAnnotation(name = "品牌馆修改保存",group = "修改",log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Venue venue = BeanUtil.wrapBean(Venue.class, request.getParameter("venue"));
        User user = getLoginUser(request);
        String result = venueService.update(venue, user);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "品牌馆删除",group = "删除",log = true)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = venueService.delete(request.getParameter("id"));
        return responseText(response, result);
    }

    @ActionAnnotation(name = "品牌馆批量删除",group = "删除",log = true)
    public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] ids = BeanUtil.wrapArray(String.class, request.getParameter("ids"));
        String result = venueService.delete(ids);
        return responseText(response, result);
    }
    
    @ActionAnnotation(name = "品牌馆列表数据",group = "查询")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Venue> list = venueService.list(null);
        return responseText(response, BeanUtil.toJsonString(list));
    }
}
