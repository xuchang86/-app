package com.rogrand.core.advice;

import java.lang.reflect.Method;

import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Service;

import com.rogrand.sys.domain.Log;
import com.rogrand.sys.service.LogService;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：通过spring切面，记录controller中的方法日志
 */
@Service("daoAdvice")
public class DaoAdvice implements AfterReturningAdvice {
    protected org.apache.commons.logging.Log logger = LogFactory.getLog(this.getClass());

    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        if (!RightAdvice.isLog()) return;
        Object obj = objects[1];
        if (obj == null) return;
        if (obj instanceof Log) return;     //不对日志表的操作写日志
        String action = "";
        if (method.getName().equals("create")) action = "插入";
        else if (method.getName().equals("update")) action = "更新";
        else if (method.getName().equals("delete")) action = "删除";
        String content = LogService.getString(obj);
        if (content != null&&content.length()>0) RightAdvice.appendContent(action  + content);
    }

}
