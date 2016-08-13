package com.rogrand.core.service;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.rogrand.core.dao.SqlDao;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：基础对象类
 */
public class BaseService implements ServletContextAware{

    protected ServletContext servletContext;

    @Autowired
    @Qualifier("sqlDao")
    protected SqlDao sqlDao;


    @Autowired
    @Qualifier("pageService")
    protected AbstractPageService pageService;
    protected Log logger = LogFactory.getLog(this.getClass());

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public WebApplicationContext getWebApplicationContext(){
        return WebApplicationContextUtils.getWebApplicationContext(this.servletContext);
    }

    public SqlDao getSqlDao() {
        return sqlDao;
    }

    public void setSqlDao(SqlDao sqlDao){
        this.sqlDao = sqlDao;
    }

    public AbstractPageService getPageService() {
        return pageService;
    }

    public void setPageService(AbstractPageService pageService) {
        this.pageService = pageService;
    }

    @SuppressWarnings("unchecked")
	public <T> T getBean(String id) {
        return (T)getWebApplicationContext().getBean(id);
    }

}
