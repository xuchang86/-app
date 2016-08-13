package com.rogrand.util;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：yong.li@rogrand.com <br/>
 * 生成日期：2013-11-20 <br/>
 * 描述：通用工具类
 */
public class RoUtil {
	
	/**
	 * 
	 * 判断对象是否为空 <br/>
	 * 
	 * @param obj 目标对象 <br/>
	 * @return true 为空 false 不为空 <br/>
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj){
		if(obj==null){
			return true;
		}
		
		if(obj.toString().trim().isEmpty()){
			return true;
		}
		
		if(obj instanceof Collection){
			return ((Collection)obj).size()==0;
		}
		
		if(obj instanceof Map){
			return ((Map)obj).size()==0;
		}
		
		return false;
	}

}
