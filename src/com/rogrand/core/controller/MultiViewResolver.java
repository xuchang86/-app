package com.rogrand.core.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.rogrand.core.util.StringUtil;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：支持多视图的解析器
 */
public class MultiViewResolver extends WebApplicationObjectSupport implements ViewResolver,Ordered {

    @SuppressWarnings("rawtypes")
	private Map[] viewResolvers;

    private int order=0;

    public void setOrder(int order){
        this.order=order;
    }

    public int getOrder() {
        return this.order;
    }

    @SuppressWarnings("rawtypes")
    public View resolveViewName(String s, Locale locale) throws Exception {
        //如果返回的是转发或重定向则不处理
        if(s.startsWith(UrlBasedViewResolver.FORWARD_URL_PREFIX)||s.startsWith(UrlBasedViewResolver.REDIRECT_URL_PREFIX)){
            return null;
        }

        int index = s.lastIndexOf('.');
        if(index==-1){   //返回的视图中没有文件后缀，自动查找
            String fileList="";
            for(Map resolver:viewResolvers){
                String prefix = (String)resolver.get("prefix");
                String suffix = (String)resolver.get("suffix");
                String filename = "";                
                if(StringUtil.isEmpty(prefix)){
                    filename = "/" +  trimChar(s, '/') + suffix;
                }
                else{
                    filename = "/" + trimChar(prefix, '/') + "/" + trimChar(s, '/') + suffix;
                }
                fileList+="\n"+filename;
                File viewFile = new File(getServletContext().getRealPath(filename));
                if( viewFile.exists()&&viewFile.isFile()){
                    return ((ViewResolver)resolver.get("viewResolver")).resolveViewName(s,locale);
                }
            }
            throw new FileNotFoundException("没有找到模板文件"+fileList+(viewResolvers.length>1?"\n其中之一":""));
        }
        else{ //返回的视图中有文件后缀，按指定的后缀名查找视图解析
            String viewSuffix = s.substring(index);
            for(Map resolver:viewResolvers){
                String suffix = (String)resolver.get("suffix");
                if(suffix.equals(viewSuffix)){
                   return ((ViewResolver)resolver.get("viewResolver")).resolveViewName(s.substring(0,index),locale);
                }
            }
            throw new FileNotFoundException("没有找到模板文件"+s);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
	protected void initApplicationContext(ApplicationContext context) {
        super.initApplicationContext(context);
        Map beans = BeanFactoryUtils.beansOfTypeIncludingAncestors(getWebApplicationContext(), UrlBasedViewResolver.class, true, false);
        if (!beans.isEmpty()) {
            List<UrlBasedViewResolver> resolverList = new ArrayList<UrlBasedViewResolver>(beans.values());
            viewResolvers = new HashMap[resolverList.size()];
            Collections.sort(resolverList, new OrderComparator());
            for (int i=0;i<resolverList.size();i++) {
                UrlBasedViewResolver item = resolverList.get(i);
                try {
                    Map<String, Object> map = new HashMap<String, Object>();
                    Method method =  BeanUtils.findMethod(item.getClass(),"getPrefix",null);
                    method.setAccessible(true);
                    map.put("prefix", method.invoke(item));
                    method =  BeanUtils.findMethod(item.getClass(),"getSuffix",null);
                    method.setAccessible(true);
                    map.put("suffix",method.invoke(item));
                    map.put("viewResolver", item);
                    viewResolvers[i]=map;
                } catch (Exception e) {
                    logger.error(e.getMessage());                 
                }
            }
        }
    }

    private static String trimChar(String s,char c){
        return StringUtils.trimTrailingCharacter(StringUtils.trimLeadingCharacter(s,c),c);
    }


}
