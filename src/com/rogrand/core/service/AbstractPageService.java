package com.rogrand.core.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.StringUtil;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：翻页抽象类
 */
public abstract class AbstractPageService {


    protected Log logger = LogFactory.getLog(this.getClass());

    
    /**
     * 根据当前查询参数判断本次请求是否带有用户设置的查询参数
     * @param pageParam
     * @return 如果是取全表所有数据则返回true,否则返回false
     */
    public boolean isTableTotal(PageParam pageParam){
    	String refresh = pageParam.getRefresh();
    	if (refresh != null && (refresh.equals("1") || refresh.equals("2"))) {
    		// 要刷新时，才会重新求总数
    		Map<String, Object> params = new HashMap<String, Object>();
    		params.putAll(pageParam);
    		
    		return BeanUtil.isBlank(params);
    	}
    	
    	return false;
    }

    /**
     * 得到排序字段的别名
     *
     * @param sortAlias 排序字段Map
     * @param sort      页面提交的排序字段
     * @return String
     */
    @SuppressWarnings("rawtypes")
    protected String getSortAlias(Map sortAlias, String sort) {
        return sortAlias == null || !sortAlias.containsKey(sort) ? sort : (String) sortAlias.get(sort);
    }


    /**
     * 判断重复排序
     *
     * @param columnSort
     * @param defaultSort
     * @return
     */
    protected boolean repeatSort(String columnSort, String defaultSort) {
        return !StringUtil.isEmpty(columnSort) && !StringUtil.isEmpty(defaultSort) && defaultSort.indexOf(columnSort) != -1;
    }

    /**
     * 分页查询
     * @param pageParam 查询条件
     * @return PageResult
     * @throws Exception 异常
     */
    public abstract PageResult pageQuery(PageParam pageParam) throws Exception;

    /**
     * 分页查询
     * @param pageParam 查询条件
     * @param sortAlias 排序字段转换
     * @return PageResult
     * @throws Exception 异常
     */
    public abstract PageResult pageQuery(PageParam pageParam, Map<String,String> sortAlias) throws Exception;


}
