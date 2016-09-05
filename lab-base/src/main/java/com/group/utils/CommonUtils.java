package com.group.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class CommonUtils {
	
	/**
	 * 判断一个字符串是否在这个字符串数组中
	 * @param strs
	 * @param s
	 * @return
	 */
	public static boolean isHave(String[] strs, String s) {
		/*
		 * 此方法有两个参数，第一个是要查找的字符串数组，第二个是要查找的字符或字符串
		 */
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].indexOf(s) != -1) {// 循环查找字符串数组中的每个字符串中是否包含所有查找的内容
				return true;// 查找到了就返回真，不在继续查询
			}
		}
		return false;// 没找到返回false
	}
	
	/**
	 * 将集合转换成数组
	 * @param list
	 * @return
	 */
	public static int[] converListtoArray(List<Integer> list){
		if(list==null || list.size()==0)
			return new int[0];
		
		int[] array = new int[list.size()];
		for(int i=0;i<list.size();i++){
			array[i] = list.get(i);
		}
		return array;
	}
	
	/**
	  * 创建指定数量的随机字符串
	  * @param numberFlag 是否是数字
	  * @param length
	  * @return
	  */
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}
	
	/**
     * @description 将xml字符串转换成map
     * @param xml
     * @return Map
     */
	public static Map<String, String> readStringXmlOut(String xml) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节点
			Iterator<Element> iter = rootElt.elementIterator(); //获取跟节点下面所有的子节点

			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				String name = recordEle.getQualifiedName();
				String value = recordEle.getStringValue();
				map.put(name, value);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
     * @description 将xml字符串转换成map
     * @param xml
     * @param key
     * @return Map
     */
	public static String readStringXmlOut(String xml,String key) {
		String value = null;
		try {
			Document doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节点
			Iterator<Element> iter = rootElt.elementIterator(key); //获取跟节点下面所有的子节点

			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				value = recordEle.getStringValue();
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 转换成xml字符串
	 * @param params
	 * @return
	 */
	public static String toXml(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (int i = 0; i < params.size(); i++) {
			sb.append("<" + params.get(i).getName() + ">");
			sb.append("<![CDATA[").append(params.get(i).getValue()).append("]]>");
			sb.append("</" + params.get(i).getName() + ">");
		}
		sb.append("</xml>");
		return sb.toString();
	}
	
	
	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * @return String
	 */ 
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}
	
	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}
	
	/**
	 * List集合去重复
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> List<T> qcList(List<T> list) {  
	    List<T> tempList= new ArrayList<T>();  
	    for(T i:list){  
	        if(!tempList.contains(i)){  
	            tempList.add(i);  
	        }  
	    }  
	    return tempList;
	}  

}
