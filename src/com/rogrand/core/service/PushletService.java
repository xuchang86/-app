package com.rogrand.core.service;

import java.util.Map;

import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.SessionManager;

import org.springframework.stereotype.Service;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2014-8-20 <br/>
 * 描述：〈Pushlet消息推送 Service〉
 */
@Service("pushletService")
public class PushletService {

    /**
     * 描述：〈Send event to specific subscriber.〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-21 <br/>
     * 
     * @param sessionId 会话ID
     * @param eventName 事件名称
     * @param attributes 参数集合
     * @return
     */
    public boolean unicast(String sessionId, String eventName, Map<String, String> attributes) {
        if (SessionManager.getInstance().hasSession(sessionId)) {
            Event event = Event.createDataEvent(eventName, attributes);
            Dispatcher.getInstance().unicast(event, sessionId);
        }
        return true;
    }
    
    /**
     * 描述：〈Send event to subscribers matching Event subject.〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-21 <br/>
     * 
     * @param eventName 事件名称
     * @param attributes 参数集合
     * @return
     */
    public boolean multicast(String eventName, Map<String, String> attributes) {
        Event event = Event.createDataEvent(eventName, attributes);
        Dispatcher.getInstance().multicast(event);
        return true;
    }

    /**
     * 描述：〈Send event to all subscribers.〉 <br/>
     * 作者：xuan.zhou@rogrand.com <br/>
     * 生成日期：2014-8-21 <br/>
     * 
     * @param eventName 事件名称
     * @param attributes 参数集合
     * @return
     */
    public boolean broadcast(String eventName, Map<String, String> attributes) {
        Event event = Event.createDataEvent(eventName, attributes);
        Dispatcher.getInstance().broadcast(event);
        return true;
    }
}
