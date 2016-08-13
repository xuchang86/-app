package com.rogrand.sys.controller;

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
import com.rogrand.sys.domain.App;
import com.rogrand.sys.service.AppService;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：应用系统表管理控制器
 */
@Controller("sysAppController")
@RequestMapping("/sys/app_*.do")
public class AppController extends BaseController{

    @Autowired
    @Qualifier("sysAppService")
    private AppService appService;

    @ActionAnnotation(name = "应用系统表入口",group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "应用系统表分页",group = "查询")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam param = BeanUtil.wrapPageBean(request);
        PageResult pageResult = appService.pageList(param);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }

    @ActionAnnotation(name = "应用系统表详细",group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        App app = appService.query(request.getParameter("sap_id"));
        model.put("app",app);
        return getView(request,model);
    }

    @ActionAnnotation(name = "应用系统表添加",group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "应用系统表添加保存",group = "添加")
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        App app = BeanUtil.wrapBean(App.class, request.getParameter("app"));
        String result = appService.create(app);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "应用系统表修改",group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        App app = appService.query(request.getParameter("sap_id"));
        model.put("app",app);
        return getView(request,model);
    }

    @ActionAnnotation(name = "应用系统表修改保存",group = "修改")
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        App app = BeanUtil.wrapBean(App.class, request.getParameter("app"));
        String result = appService.update(app);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "应用系统表删除",group = "删除")
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = appService.delete(request.getParameter("sap_id"));
        return responseText(response, result);
    }

    @ActionAnnotation(name = "应用系统表批量删除",group = "删除")
    public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] sap_ids = BeanUtil.wrapArray(String.class, request.getParameter("sap_ids"));
        String result = appService.delete(sap_ids);
        return responseText(response, result);
    }
}
