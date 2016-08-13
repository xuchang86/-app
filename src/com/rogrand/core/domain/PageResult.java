package com.rogrand.core.domain;


import java.util.HashMap;
import java.util.List;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：翻页查询返回结果对象
 */
public class PageResult extends HashMap<String,Object> {

	private static final long serialVersionUID = -6161271783519259501L;

	public void setPageParam(PageParam pageParam) {
        put("pageParam", pageParam);
    }

    public PageParam getPageParam() {
        return (PageParam)get("pageParam");
    }

    @SuppressWarnings("rawtypes")
    public void setPageList(List list) {
        put("pageList", list);
    }
    
    @SuppressWarnings("rawtypes")
    public List getPageList() {
        return (List)get("pageList");
    }
}
