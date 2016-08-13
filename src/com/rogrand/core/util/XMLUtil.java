package com.rogrand.core.util;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.io.DOMReader;
import org.xml.sax.InputSource;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：XML操作工具类
 */
public class XMLUtil {

	/**
	 * 将w3c的Document转换为dom4j的Document
	 * @param doc w3c Document
	 * @return org.dom4j.Document
	 * @throws Exception
	 */
	public static org.dom4j.Document parseDom4j(org.w3c.dom.Document doc) throws Exception {
		if (doc == null) return (null);	
		DOMReader xmlReader = new DOMReader();
		return (xmlReader.read(doc));
	}

	/**
	 * 将dom4j的Document转换为w3c的Document
	 * @param doc dom4j Document
	 * @return org.w3c.dom.Document
	 * @throws Exception
	 */
	public static org.w3c.dom.Document parseW3c(org.dom4j.Document doc) throws Exception {
		if (doc == null) return (null);
		StringReader reader = new StringReader(doc.asXML());
		InputSource source = new InputSource(reader);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		return (documentBuilder.parse(source));
	}
}
