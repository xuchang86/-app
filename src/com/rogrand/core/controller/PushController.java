package com.rogrand.core.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.annotation.ActionAnnotation.Type;
import com.rogrand.core.service.PushletService;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2014-8-20 <br/>
 * 描述：〈Pushlet消息推送 Controller〉
 */
@Controller("pushController")
@RequestMapping("/push/*")
public class PushController extends BaseController {

    protected static Log logger = LogFactory.getLog(PushController.class);

    @Autowired
    @Qualifier("pushletService")
    private PushletService pushletService;

    /**
     * 描述：〈推送WEB消息〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-21 <br/>
     * 
     * Demo: 
     * unicast: /push/pushWebMsg.do?pushType=unicast&eventName=listen_noresponse_service_notice&sessionId=5000 
     * multicast: /push/pushWebMsg.do?pushType=multicast&eventName=listen_noresponse_service_notice&pushletType=multicast 
     * broadcast: /push/pushWebMsg.do?pushType=broadcast&pushletType=broadcast
     * 
     * @param request 用户请求
     * @param response 用户响应
     * @throws Exception 异常信息
     */
    @ActionAnnotation(name = "Pushlet推送消息", group = "消息推送", log = true, check = Type.NO)
    public void pushWebMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Pushlet会话ID
        String sessionId = request.getParameter("sessionId");
        // Pushlet事件名称,默认:/kkmy_manager/push
        String eventName = StringUtils.defaultString(request.getParameter("eventName"), "/kkmy_manager/push");
        // Pushlet推送方式,默认:unicast
        String pushletType = StringUtils.defaultString(request.getParameter("pushletType"), "unicast");
        // 推送类型（转枚举）,默认:unknow
        String pushType = StringUtils.defaultString(request.getParameter("pushType"), "unknow");
        // 推送内容（json字符串）,默认:{}
        String pushContent = StringUtils.defaultString(request.getParameter("pushContent"), "{}");

        logger.info("Pushlet推送消息: sessionId=" + sessionId + ", eventName=" + eventName + ", pushletType=" + pushletType
                + ", pushType=" + pushType + ", pushContent=" + pushContent);

        // 组装事件参数
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("pushType", pushType);
        attributes.put("pushContent", pushContent);

        // 调用Pushlet消息推送接口方法
        if ("unicast".equalsIgnoreCase(pushletType)) {
            pushletService.unicast(sessionId, eventName, attributes);
        } else if ("broadcast".equalsIgnoreCase(pushletType)) {
            pushletService.broadcast(eventName, attributes);
        } else if ("multicast".equalsIgnoreCase(pushletType)) {
            pushletService.multicast(eventName, attributes);
        } else {
            throw new Exception("Undefined pushletType:" + pushletType);
        }

    }
}
