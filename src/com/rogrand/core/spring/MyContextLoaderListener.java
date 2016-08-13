package com.rogrand.core.spring;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.w3c.dom.Document;

import com.rogrand.core.jms.simplejms.JmsHelper;
import com.rogrand.core.util.FtpConfig;

/**
 * 版权：融贯资讯 <br/>
 * 作者：kai.gao@rogrand.com & xuan.zhou@rogrand.com <br/>
 * 生成日期：2013-12-24 <br/>
 * 描述：根据主机名自动需要加载的配置文件
 */
public class MyContextLoaderListener extends ContextLoaderListener {
	
	private static final Logger logger = Logger.getLogger(MyContextLoaderListener.class);
	
	// 正式服务器
	private static final String officialHostname = PropertiesLoader.getProperty("official.hostname");
	private static final String officialPushConfigFile = PropertiesLoader.getProperty("official.push.config.file");
	
	// 公网测试服务器
    private static final String test2Hostname = PropertiesLoader.getProperty("test2.hostname");
    private static final String test2ConfigFile = PropertiesLoader.getProperty("test2.config.file");
    private static final String test2FtpConfigFile = PropertiesLoader.getProperty("test2.ftp.config.file");
    private static final String test2PushConfigFile = PropertiesLoader.getProperty("test2.push.config.file");
	
	// 测试服务器
	private static final String testHostname = PropertiesLoader.getProperty("test.hostname");
	private static final String testConfigFile = PropertiesLoader.getProperty("test.config.file");
	private static final String testFtpConfigFile = PropertiesLoader.getProperty("test.ftp.config.file");
	private static final String testPushConfigFile = PropertiesLoader.getProperty("test.push.config.file");
	
	// 本地开发测试机
	private static final String localHostname = PropertiesLoader.getProperty("local.hostname");
	private static final String localConfigFile = PropertiesLoader.getProperty("local.config.file");
	private static final String localFtpConfigFile = PropertiesLoader.getProperty("local.ftp.config.file");
	private static final String localPushConfigFile = PropertiesLoader.getProperty("local.push.config.file");
	
	protected void customizeContext(ServletContext sc, ConfigurableWebApplicationContext wac) {
		// 获取主机名
		String hostname = getHostName();
		logger.info("hostname = " + hostname);
		// 当前tomcat位置
		String catalinaHome = System.getProperty("catalina.home");
		logger.info("catalina.home = " + catalinaHome);
		// tomcat配置文件位置
		String tomcatConfigXml = catalinaHome + "/conf/server.xml";
		// 获取当前tomcat端口号
		Integer port = getTomcatPortFromConfigXml(tomcatConfigXml);
		logger.info("tomcat.server.port = " + port);
		
		// 默认配置文件
		String configLocation = sc.getInitParameter(CONFIG_LOCATION_PARAM);
		
		if (officialHostname.equals(hostname)) {// 正式服务器
		    JmsHelper.loadPushConfig(officialPushConfigFile);
		} else if (testHostname.equals(hostname)) {// 开发测试服务器
			configLocation = testConfigFile;
			FtpConfig.reload(testFtpConfigFile);
			JmsHelper.loadPushConfig(testPushConfigFile);
		} else if (test2Hostname.equals(hostname)) {// 公网测试服务器
            configLocation = test2ConfigFile;
            FtpConfig.reload(test2FtpConfigFile);
            JmsHelper.loadPushConfig(test2PushConfigFile);
        } else if (localHostname.equals(hostname)) {// 本地开发测试机
			configLocation = localConfigFile;
			FtpConfig.reload(localFtpConfigFile);
			JmsHelper.loadPushConfig(localPushConfigFile);
		} else {// 其它名称均连接本地开发测试机
			configLocation = localConfigFile;
			FtpConfig.reload(localFtpConfigFile);
			JmsHelper.loadPushConfig(localPushConfigFile);
		}
		String[] xml = StringUtils.tokenizeToStringArray(configLocation, ConfigurableWebApplicationContext.CONFIG_LOCATION_DELIMITERS);
		wac.setConfigLocations(xml);
	}
	
	/**
	 * 获取主机名
	 * 
	 * @return
	 */
	private String getHostName() {
		String hostname = "";
		try {
			// Linux下获取主机名
			hostname = System.getenv("HOSTNAME");
			
			if (null == hostname || hostname.trim().length() == 0) {
				// Windows下获取主机名
				InetAddress netAddress = InetAddress.getLocalHost();
				
				if (null != netAddress) {
					hostname = netAddress.getHostName();
				}
			}
		} catch (UnknownHostException e) {
			logger.error("unknown host!", e);
		}
		return hostname;
	}
	
	/**
	 * 获取当前tomcat端口号
	 * 
	 * @return
	 */
	private Integer getTomcatPortFromConfigXml(String serverXml) {
		Integer port = 9999;// 默认设置为开发环境端口
		try {
			File tomcatConfigXmlFile = new File(serverXml);
			if (tomcatConfigXmlFile.exists()) {// 文件存在
				DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
				domFactory.setNamespaceAware(true); // never forget this!
				DocumentBuilder builder = domFactory.newDocumentBuilder();
				Document doc = builder.parse(tomcatConfigXmlFile);
				XPathFactory factory = XPathFactory.newInstance();
				XPath xpath = factory.newXPath();
				XPathExpression expr = xpath.compile("/Server/Service[@name='Catalina']/Connector[count(@scheme)=0]/@port[1]");
				String result = (String) expr.evaluate(doc, XPathConstants.STRING);
				if (result != null && result.length() > 0) {
					port = Integer.valueOf(result);
				}
			}
		} catch (Exception e) {
			logger.error("getTomcatPortFromConfigXml Exception!", e);
		}
		return port;
	}
	
}
