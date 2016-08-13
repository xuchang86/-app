package com.rogrand.core.jms.simplejms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2014-3-27 <br/>
 * 描述：〈推送帮助类〉
 */
public class JmsHelper {

    public static Map<Integer, WebNode> webNodes = new HashMap<Integer, WebNode>();

    public static void loadPushConfig(String configPath) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(JmsHelper.class.getResourceAsStream(configPath));
            List<?> elements = document.getRootElement().elements();

            webNodes.clear();
            for (Object e : elements) {
                if (e instanceof Element) {
                    Element et = (Element) e;
                    WebNode target = new WebNode();
                    target.setId(Integer.parseInt(et.attributeValue("id")));
                    target.setPort(Integer.parseInt(et.attributeValue("port")));
                    target.setProtocol(et.attributeValue("protocol"));
                    target.setHost(et.attributeValue("host"));
                    target.setApp(et.attributeValue("app"));
                    webNodes.put(target.getId(), target);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("加载消息节点配置文件出错:" + e.getMessage(), e);
        }
    }
}
