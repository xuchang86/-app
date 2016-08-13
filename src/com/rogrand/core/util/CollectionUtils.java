package com.rogrand.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：集合工具类
 */
public class CollectionUtils {
	
	/**
	 * 判断集合是否为空
	 * 
	 * @param Collection<?> collection
	 * @return 为null或为空则返回true，否则返回false
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}
	
	/**
	 * 判断Map是否为空
	 * 
	 * @param map
	 * @return 为null或为空则返回true，否则返回false
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	/**
	 * 判断数组是否为空
	 * 
	 * @param set
	 * @return 为null或为空则返回true，否则返回false
	 */
	public static <T> boolean isEmpty(T[] array) {
		return array == null || array.length <= 0;
	}

	/**
	 * 从集合all中除去集合beside中的所有元素
	 * 
	 * @param all
	 *            待处理的集合
	 * @param beside
	 *            需要被去掉的元素组成的集合
	 * @return 返回all中除了beside中元素以外的其它元素的集合
	 */
	public static List<String> removeAllIgnoreCase(List<String> all,List<String> beside) {
		List<String> rst = new ArrayList<String>();
		if (all == null || all.isEmpty()) {
			return rst;
		}
		if (beside == null || beside.isEmpty()) {
			return all;
		}
		for (String desc : all) {
			boolean eq = false;
			for (String str : beside) {
				if ((desc + "").equalsIgnoreCase(str)) {
					eq = true;
					break;
				}
			}
			if (!eq) {
				rst.add(desc);
			}
		}
		return rst;
	}

	/**
     * 数组除重
     *
     * @param s 输入数组
     * @return 分隔后数组
     */
    public static String[] toDiffArray(String[] s) {
        Set<String> set = new LinkedHashSet<String>();
        for (String sa : s) {
            set.add(sa);
        }
        return set.toArray(new String[]{});
    }
    

    /**
     * 数组转成字符串
     *
     * @param String[] aa
     * @return String
     */
    public static String arrayToString(String[] aa) {
        String strs = "";
        for (String str : aa) {
            strs += "," + str;
        }
        return strs = strs.length() > 0 ? strs.substring(1) : "";
    }
    
    public static List<String> stringSetToStringList(Set<String> aa) {
        List<String> list = new ArrayList<String>();
        for (String str : aa) {
        	list.add(str);
        }
        return list;
    }
    
    /**
     * 字符串List转成以","分隔的字符串
     *
     * @param List<String> aa
     * @return String
     */
    public static String stringListToString(List<String> aa) {
        String strs = "";
        for (String str : aa) {
            strs += "," + str;
        }
        return strs = strs.length() > 0 ? strs.substring(1) : "";
    }
}
