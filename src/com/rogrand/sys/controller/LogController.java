package com.rogrand.sys.controller;

import java.util.Calendar;
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
import com.rogrand.sys.domain.Log;
import com.rogrand.sys.service.LogService;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：系统日志表管理控制器
 */
@Controller("sysLogController")
@RequestMapping("/sys/log_*.do")
public class LogController extends BaseController{

    @Autowired
    @Qualifier("sysLogService")
    private LogService logService;

    @ActionAnnotation(name = "系统日志表入口",group = "查询")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getView(request);
    }

    @ActionAnnotation(name = "系统日志表分页",group = "查询")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageParam param = BeanUtil.wrapPageBean(request);
        PageResult pageResult = logService.pageList(param);
        return responseText(response, EasyuiUtil.toDataGridData(pageResult));
    }

    @ActionAnnotation(name = "系统日志表详细",group = "查询")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        Log log = logService.query(request.getParameter("sl_id"));
        model.put("log",log);
        return getView(request,model);
    }


      @ActionAnnotation(name = "系统日志批量删除",group = "删除")
    public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] sl_ids = BeanUtil.wrapArray(String.class, request.getParameter("sl_ids"));
        String result = logService.delete(sl_ids);
        return responseText(response, result);
    }

    @ActionAnnotation(name = "删除一个月之前的日志",group = "删除")
    public ModelAndView deleteMonth(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-30);
        String result = logService.delete(calendar.getTime());
        return responseText(response, result);
    }

    @ActionAnnotation(name = "删除所有日志",group = "删除")
    public ModelAndView deleteAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = logService.delete();
        return responseText(response, result);
    }
}
