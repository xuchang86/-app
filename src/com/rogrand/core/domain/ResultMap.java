package com.rogrand.core.domain;

import java.util.HashMap;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：使用hash返回的结果集映射统一使用该，将key值统一转换成小写。解决不同数据库查询返回字段名大小于不统一问题
 */
public class ResultMap extends HashMap<String, Object> {

	private static final long serialVersionUID = 7771824360352727313L;

	@Override
    public Object put(String key, Object value) {
        return super.put(key.toLowerCase(), value);
    }
}
