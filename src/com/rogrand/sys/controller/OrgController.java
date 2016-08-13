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
import com.rogrand.sys.domain.Org;
import com.rogrand.sys.service.OrgService;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：机构管理控制器
 */
@Controller("sysOrgController")
@RequestMapping("/sys/org_*")
public class OrgController extends BaseController {

    @Autowired
    @Qualifier("sysOrgService")
    private OrgService orgService;

    @ActionAnnotation(name = "机构入口", group = "查询" )
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "机构树", group = "查询")
    public ModelAndView tree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String tree = orgService.tree(new Org());
        return responseText(response, tree);
    }

    @ActionAnnotation(name = "机构分页查询", group = "查询")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam param= BeanUtil.wrapPageBean(request);
        PageResult pageResult = orgService.pageList(param);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }

    @ActionAnnotation(name = "机构详细查询", group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response)  throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Org org = orgService.query(request.getParameter("so_id"));
        map.put("org", org);
        return getView(request,map);
    }

    @ActionAnnotation(name = "机构添加", group = "添加")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Org org = BeanUtil.wrapBean(Org.class,request);
        String tree = orgService.tree();
        map.put("tree", tree);
        map.put("org", org);
        return getView(request, map);
    }

    @ActionAnnotation(name = "机构添加保存", group = "添加",log = true)
    public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response)   throws Exception{
        Org org = BeanUtil.wrapBean(Org.class,request.getParameter("org"));
        org.setSo_order(0);
        String result = orgService.create(org);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "机构修改", group = "修改")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Org org = orgService.query(request.getParameter("so_id"));
        Org param = new Org(org.getSo_id());
        param.setMode("treeForEdit");
        String tree = orgService.tree(param);
        map.put("org", org);
        map.put("tree", tree);
        return getView(request,map);
    }

    @ActionAnnotation(name = "机构修改保存", group = "修改",log = true)
    public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Org org = BeanUtil.wrapBean(Org.class,request.getParameter("org"));
        String result = orgService.update(org);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "机构删除", group = "删除",log = true)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = orgService.delete(request.getParameter("so_id"));
        return responseText(response, result);
    }

    @ActionAnnotation(name = "机构排序",group = "排序")
    public ModelAndView order(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "机构排序保存",group = "排序",log = true)
    public ModelAndView orderSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

}
