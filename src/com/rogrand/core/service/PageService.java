package com.rogrand.core.service;


import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;


/**
 * 分页查询，根据不同的jdbc驱动调用不同的分页查询
 * SysUser: Administrator
 * Date: 2010-9-19
 * Time: 12:03:17
 */
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class PageService extends AbstractPageService implements ServletContextAware {
	
	@Autowired
	@Qualifier("mysqlPageService")
	AbstractPageService mysqlPageService;

    protected ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * 分页查询
     *
     * @param pageParam 查询条件
     * @return PageResult
     * @throws Exception 异常
     */
    @Override
    public PageResult pageQuery(PageParam pageParam) throws Exception {
        return mysqlPageService.pageQuery(pageParam);
    }

    /**
     * 分页查询
     *
     * @param pageParam 查询条件
     * @param sortAlias 排序字段转换
     * @return PageResult
     * @throws Exception 异常
     */
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public PageResult pageQuery(PageParam pageParam, Map sortAlias) throws Exception {
        return mysqlPageService.pageQuery(pageParam, sortAlias);
    }

    private String driverClassName;

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    /**
     * 得到分页bean
     * @return AbstractPageService
     */
    private AbstractPageService getService() {
        BeanFactory factory = WebApplicationContextUtils.getWebApplicationContext(this.servletContext);
        if (driverClassName.contains("mysql")) {
            return (AbstractPageService) factory.getBean("mysqlPageService");
        }
        return null;
    }


}
