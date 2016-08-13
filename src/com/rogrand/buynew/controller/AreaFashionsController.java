package com.rogrand.buynew.controller;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.common.util.StringUtils;
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
import com.rogrand.buynew.domain.AreaFashions;
import com.rogrand.buynew.service.AreaFashionsService;

/**
 * 版权：LAB <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-05 <br/>
 * 描述：服装区下的服装 Controller
 */
@Controller("AreaFashionsController")
@RequestMapping("/buynew/areaFashions_*.do")
public class AreaFashionsController extends BaseController{

    @Autowired
    @Qualifier("AreaFashionsService")
    private AreaFashionsService areaFashionsService;

    @ActionAnnotation(name = "服装区下的服装入口",group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "服装区下的服装分页",group = "查询")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam areaFashions = BeanUtil.wrapPageBean(request);
        PageResult pageResult = areaFashionsService.pageList(areaFashions);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }

    @ActionAnnotation(name = "服装区下的服装详细",group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        AreaFashions areaFashions = areaFashionsService.query(request.getParameter("id"));
        model.put("areaFashions",areaFashions);
        return getView(request,model);
    }

    @ActionAnnotation(name = "服装区下的服装添加",group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "服装区下的服装添加保存",group = "添加",log = true)
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] fashions = BeanUtil.wrapArray(String.class, request.getParameter("fashions"));
        String areaId = request.getParameter("area_id");
        String areaType = request.getParameter("area_type");
        if(StringUtils.isEmpty(areaId) || StringUtils.isEmpty(areaType) || fashions==null || fashions.length==0){
        	return responseText(response, "参数错误");
        }
        String result = areaFashionsService.create(fashions, areaId, areaType);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装区下的服装修改",group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        AreaFashions areaFashions = areaFashionsService.query(request.getParameter("id"));
        model.put("areaFashions",areaFashions);
        return getView(request,model);
    }

    @ActionAnnotation(name = "服装区下的服装修改保存",group = "修改",log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AreaFashions areaFashions = BeanUtil.wrapBean(AreaFashions.class, request.getParameter("areaFashions"));
        String result = areaFashionsService.update(areaFashions);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装区下的服装删除",group = "删除",log = true)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = areaFashionsService.delete(request.getParameter("id"));
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装区下的服装批量删除",group = "删除",log = true)
    public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] ids = BeanUtil.wrapArray(String.class, request.getParameter("ids"));
        String result = areaFashionsService.delete(ids);
        return responseText(response, result);
    }
    
    @ActionAnnotation(name = "服装区下的服装删除",group = "删除",log = true)
    public ModelAndView deleteFashions(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String num_iid = request.getParameter("num_iid");
    	String area_id = request.getParameter("area_id");
    	String area_type = request.getParameter("area_type");
    	if(StringUtils.isEmpty(num_iid) || StringUtils.isEmpty(area_id) || StringUtils.isEmpty(area_type)){
    		return responseText(response, "参数错误");
    	}
    		
        String result = areaFashionsService.deleteFashions(area_id, area_type, num_iid);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "服装区下的服装批量删除",group = "删除",log = true)
    public ModelAndView deleteBatchFashions(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String area_id = request.getParameter("area_id");
    	String area_type = request.getParameter("area_type");
        String[] num_iids = BeanUtil.wrapArray(String.class, request.getParameter("num_iids"));
        if(num_iids==null || num_iids.length==0 || StringUtils.isEmpty(area_id) || StringUtils.isEmpty(area_type)){
    		return responseText(response, "参数错误");
    	}
        
        String result = areaFashionsService.deleteFashions(area_id, area_type, num_iids);
        return responseText(response, result);
    }
}
