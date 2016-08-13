package com.rogrand.auto.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.rogrand.auto.domain.Column;
import com.rogrand.auto.domain.Database;
import com.rogrand.auto.domain.Table;
import com.rogrand.auto.service.BuildService;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.StringUtil;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：自动生成控制器，用于生成ftl，xml，service，controller，javabean
 */
@Controller("buildController")
@RequestMapping("/auto/*.do")
public class BuildController extends BaseController {

    private String tempDir = "/WEB-INF/view/auto/template";
    private String outDir = "/autoBuild";

    @Autowired
    @Qualifier("freeMarkerConfigurer")
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    @Qualifier("buildService")
    private BuildService buildService;

    public BuildService getBuildService() {
        return buildService;
    }

    public void setBuildService(BuildService buildService) {
        this.buildService = buildService;
    }

    public FreeMarkerConfigurer getFreeMarkerConfigurer() {
        return freeMarkerConfigurer;
    }

    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    public ModelAndView build(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        Database database = buildService.getStructure();
        model.put("database", database);
        model.put("databaseJson", BeanUtil.toJsonString(database));
        return getView(request, model);
    }

    /**
     * 保存注释
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LinkedHashMap<String, String> map = BeanUtil.wrapBean(LinkedHashMap.class, request.getParameter("param"));
        File file = new File(getServletContext().getRealPath("/WEB-INF/view/auto/comments.properties"));
        PropertiesConfiguration configuration = new PropertiesConfiguration();
        FileOutputStream outputStream = new FileOutputStream(file);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            configuration.addProperty(entry.getKey(), entry.getValue());
        }
        configuration.save(outputStream, "utf-8");
        return responseText(response, "1");
    }

    /**
     * 生成代码
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Table> tableList = BeanUtil.wrapBeanList(Table.class, request.getParameter("tableList"));
        for (Table table : tableList) {
            createDomain(table);
            createSqlmap(table);
            if (table.getBuildCURD() != null && table.getBuildCURD()) {
                createController(table);
                createService(table);
                createView(table);
            }
        }
        return responseText(response, "1");
    }

    private void createSqlmap(Table table) throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<String, Object>();
        boolean hasBlob = false;
        for (Column column : table.getColumnList()) {
            if (column.getIbatisType().equals("BLOB")) {
                hasBlob = true;
                break;
            }
        }
        model.put("now", new Date());
        model.put("hasBlob", hasBlob);
        model.put("table", table);
        model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(tempDir + "/ibatis_sqlmap.ftl", "utf-8");
        String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        String dir = outDir + "/src/" + table.getPackageDomain().replaceAll("\\.", "/") + "/" + table.getClassDomain() + ".xml";
        File file = new File(getServletContext().getRealPath(dir));
        FileUtils.writeStringToFile(file, temp, "utf-8");
    }

    private void createController(Table table) throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("now", new Date());
        model.put("table", table);
        model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(tempDir + "/java_controller.ftl", "utf-8");
        String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        String dir = outDir + "/src/" + table.getPackageController().replaceAll("\\.", "/") + "/" + table.getClassController() + ".java";
        File file = new File(getServletContext().getRealPath(dir));
        FileUtils.writeStringToFile(file, temp, "utf-8");
    }

    private void createDomain(Table table) throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("now", new Date());
        model.put("table", table);
        model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(tempDir + "/java_domain.ftl", "utf-8");
        String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        String dir = outDir + "/src/" + table.getPackageDomain().replaceAll("\\.", "/") + "/" + table.getClassDomain() + ".java";
        File file = new File(getServletContext().getRealPath(dir));
        FileUtils.writeStringToFile(file, temp, "utf-8");
    }

    private void createService(Table table) throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<String, Object>();
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(tempDir + "/java_service.ftl", "utf-8");
        model.put("now", new Date());
        model.put("table", table);
        model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
        String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        String dir = outDir + "/src/" + table.getPackageService().replaceAll("\\.", "/") + "/" + table.getClassService() + ".java";
        File file = new File(getServletContext().getRealPath(dir));
        FileUtils.writeStringToFile(file, temp, "utf-8");
    }

    private void createView(Table table) throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("now", new Date());
        model.put("table", table);
        model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
        String dir = outDir + "/web/WEB-INF/view";
        if (!StringUtil.isEmpty(table.getModuleName())) dir += "/" + table.getModuleName();
        if (!StringUtil.isEmpty(table.getSubjectModuleName())) dir += "/" + table.getSubjectModuleName();
        String name = StringUtil.lowerFirstChar(table.getClassDomain());

        String view = dir + "/" + name + "_main.ftl";
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(tempDir + "/view_main.ftl", "utf-8");
        String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        File file = new File(getServletContext().getRealPath(view));
        FileUtils.writeStringToFile(file, temp, "utf-8");

        view = dir + "/" + name + "_add.ftl";
        template = freeMarkerConfigurer.getConfiguration().getTemplate(tempDir + "/view_add.ftl", "utf-8");
        temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        file = new File(getServletContext().getRealPath(view));
        FileUtils.writeStringToFile(file, temp, "utf-8");

        view = dir + "/" + name + "_edit.ftl";
        template = freeMarkerConfigurer.getConfiguration().getTemplate(tempDir + "/view_edit.ftl", "utf-8");
        temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        file = new File(getServletContext().getRealPath(view));
        FileUtils.writeStringToFile(file, temp, "utf-8");

        view = dir + "/" + name + "_view.ftl";
        template = freeMarkerConfigurer.getConfiguration().getTemplate(tempDir + "/view_view.ftl", "utf-8");
        temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        file = new File(getServletContext().getRealPath(view));
        FileUtils.writeStringToFile(file, temp, "utf-8");

    }

    public static void main(String[] args) {

    }
}
