package com.rogrand.core.system;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.rogrand.core.exception.StartupException;
import com.rogrand.core.util.SpringContextHolder;
import com.rogrand.sys.domain.Sysconfig;
import com.rogrand.sys.service.SysconfigService;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：系统启动servlet, 解析 WEB-INF/config/system.xml 系统参数文件， 初始化SystemParameter对象
 */
public class StartupServlet extends javax.servlet.http.HttpServlet {

    private static final long serialVersionUID = -8888709767612737448L;

    private Log logger = LogFactory.getLog(getClass());

    public StartupServlet() {
        super();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void init() throws ServletException {
        String configName = this.getServletContext().getRealPath(this.getInitParameter("configFile"));
        String systemName = "";
        try {
            File file = new File(configName);
            if (!file.exists())
                throw new StartupException("系统参数文件" + configName + "未找到");
            SAXReader reader = new SAXReader();
            Document doc = reader.read(file);
            Element root = doc.getRootElement();

            // 加载system.xml配置参数
            for (Iterator i = root.elementIterator(); i.hasNext();) {
                Element element = (Element) i.next();
                String key = element.element("key").getText();
                String value = element.element("value").getText();
                SystemParameter.set(key, value);
            }

            ServletContext application = getServletContext();
            systemName = SystemParameter.get("systemName");
            if (SystemParameter.get("subSystemName") != null)
                systemName = SystemParameter.get("subSystemName");

            // 加载数据库配置参数
            //loadSysConfigParams();
            
            //开启登陆验证码
            SystemParameter.set("checkRandomCode", SystemParameter.get("checkRandomCode"));
            SystemParameter.set("exportMaxRows", "10000");

            System.out.println(application.getRealPath("/"));
            System.out.println("**************************************************");
            System.out.println("             " + systemName + "初始化完成！");
            System.out.println("**************************************************");

        } catch (Exception e) {
            logger.error("exception class:" + e.getClass().getName() + "\nexception message:" + e.getMessage());
            throw new ServletException(e);
        }
    }

    /**
     * 〈加载系统配置参数〉 <br/>
     */
    private void loadSysConfigParams() throws Exception {
        SysconfigService service = SpringContextHolder.getBean("SysconfigService");

        List<Sysconfig> list = service.list();
        if (list != null) {
            for (Sysconfig config : list) {
                SystemParameter.set(config.getConfigkey(), config.getConfigvalue());
            }
        }
    }

    public static void main(String[] args) {
        /*
         * OutputFormat format = OutputFormat.createPrettyPrint();
         * format.setEncoding("UTF-8"); XMLWriter writer = new XMLWriter(new
         * FileOutputStream(file),format); writer.write(doc); writer.close();
         */
    }
}
