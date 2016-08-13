package com.rogrand.core.spring;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesLoader {
	
	protected static final Logger logger = Logger.getLogger(PropertiesLoader.class);
	
	private static final String DEFAULT_FILENAME = "/select.properties";
	
	private static Properties properties = new Properties();
	
	static {
		InputStream in = PropertiesLoader.class.getResourceAsStream(DEFAULT_FILENAME);
		
		if (in == null) {
			logger.error(DEFAULT_FILENAME + " 没有找到！");
			throw new RuntimeException(DEFAULT_FILENAME + " 没有找到！");
		} else {
			if (!(in instanceof BufferedInputStream)) {
				in = new BufferedInputStream(in);
			}
			
			try {
				properties.load(in);
				in.close();
				logger.debug(DEFAULT_FILENAME + " 加载完成！");
			} catch (Exception e) {
				logger.error(DEFAULT_FILENAME + " 加载过程出错！");
				throw new RuntimeException(DEFAULT_FILENAME + " 加载过程出错！", e);
			}
		}
	}
	
	public static String getProperty(final String key) {
		return properties.getProperty(key);
	}
	
}
