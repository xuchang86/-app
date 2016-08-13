package com.rogrand.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.EasyuiUtil;
import com.rogrand.sys.domain.Sysconfig;
import com.rogrand.sys.service.SysconfigService;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2013-12-17 <br/>
 * 描述：系统配置 Controller
 */
@Controller("SysconfigController")
@RequestMapping("/sys/sysconfig_*.do")
public class SysconfigController extends BaseController{

    @Autowired
    @Qualifier("SysconfigService")
    private SysconfigService sysconfigService;

    @ActionAnnotation(name = "系统配置入口",group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "系统配置分页",group = "查询")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam sysconfig = BeanUtil.wrapPageBean(request);
        PageResult pageResult = sysconfigService.pageList(sysconfig);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }

    @ActionAnnotation(name = "系统配置详细",group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        Sysconfig sysconfig = sysconfigService.query(request.getParameter("configid"));
        model.put("sysconfig",sysconfig);
        return getView(request,model);
    }

    @ActionAnnotation(name = "系统配置添加",group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "系统配置添加保存",group = "添加",log = true)
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Sysconfig sysconfig = BeanUtil.wrapBean(Sysconfig.class, request.getParameter("sysconfig"));
        sysconfig.setConfigaddtime(new Date());
        String result = sysconfigService.create(sysconfig);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "系统配置修改",group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        Sysconfig sysconfig = sysconfigService.query(request.getParameter("configid"));
        model.put("sysconfig",sysconfig);
        return getView(request,model);
    }

    @ActionAnnotation(name = "系统配置修改保存",group = "修改",log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Sysconfig sysconfig = BeanUtil.wrapBean(Sysconfig.class, request.getParameter("sysconfig"));
        String result = sysconfigService.update(sysconfig);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "系统配置删除",group = "删除",log = true)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = sysconfigService.delete(request.getParameter("configid"));
        return responseText(response, result);
    }

    @ActionAnnotation(name = "系统配置批量删除",group = "删除",log = true)
    public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] configids = BeanUtil.wrapArray(String.class, request.getParameter("configids"));
        String result = sysconfigService.delete(configids);
        return responseText(response, result);
    }
}
