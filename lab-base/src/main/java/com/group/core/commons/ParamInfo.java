package com.group.core.commons;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.group.core.commons.pages.Pagination;




/**
 * 版权：魔法衣橱 <br/>
 * 作者：dailing <br/>
 * 生成日期：2013-12-2 <br/>
 * 描述：请求参数解析信息
 */

public class ParamInfo {

    protected static Logger logger = LoggerFactory.getLogger(ParamInfo.class);

    private SysCode state;

    private Map<String, String> params;

    private ResponseJSON response;

    private RequestJSON requestJSON;

    private String appId;

    private String userId;

    private String channelId;

    private int pageNo;
    private int pageSize;

    public ParamInfo() {
        params = new HashMap<String, String>();
    }

    public String toString() {
        return "state:" + state + ",response:" + response + ",requestJSON" + requestJSON;
    }

    public String getParam(String key) {
        if (params != null && params.size() > 0) {
            return params.get(key);
        }
        return null;
    }

    public Integer getTerminalState() {
        if (requestJSON != null) {
            return requestJSON.getHead().getTerminalstate();
        }
        return null;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public ResponseJSON getResponse() {
        return response;
    }

    public ResponseJSON getResponse(SysCode code, Object result) {
        if (response != null) {
            response.getBody().setMessage(code.getMessage());
            response.getBody().setCode(code.getCode());
            response.getBody().setResult(result);
        }
        //logger.info("the response json is :" + response);
        return response;
    }
    
    public ResponseJSON getResponse(String code,String message, Object result) {
        if (response != null) {
            response.getBody().setMessage(message);
            response.getBody().setCode(code);
            response.getBody().setResult(result);
        }
        logger.info("the response json is :" + response);
        return response;
    }

    public void setResponse(ResponseJSON response) {
        this.response = response;
    }

    public RequestJSON getRequestJSON() {
        return requestJSON;
    }

    public void setRequestJSON(RequestJSON requestJSON) {
        this.requestJSON = requestJSON;
    }

    public String getAppId() {
        if (appId == null) {
            appId = requestJSON.getBody().getString("appid") == null ? "" : requestJSON.getBody().getString("appid");
        }
        logger.info(">>>>>>appId=" + requestJSON.getBody().getString("appid"));
        return appId;
    }

    public String getUserId() {
        if (userId == null) {
            userId = requestJSON.getBody().getString("userid") == null ? "" : requestJSON.getBody().getString("userid");
        }
        logger.info(">>>>>>userId=" + requestJSON.getBody().getString("userid"));
        return userId;
    }

    public String getChannelId() {
        if (channelId == null) {
            channelId = requestJSON.getBody().getString("channelid") == null ? "" : requestJSON.getBody().getString(
                    "channelid");
        }
        logger.info(">>>>>>channelId=" + requestJSON.getBody().getString("channelid"));
        return channelId;
    }

    private int getPageNo() {
        if (pageNo == 0) {
            String pn = requestJSON.getBody().getString("pageNo");
            if (org.apache.commons.lang.StringUtils.isEmpty(pn) || !StringUtils.isNumeric(pn)) {
                pageNo = 1;
            } else {
                pageNo = Integer.parseInt(pn);
            }
        }
        logger.info(">>>>>>pageNo=" + requestJSON.getBody().getString("pageNo"));
        return pageNo;
    }
    
    private int getPageSize() {
        if (pageSize == 0) {
            String ps = requestJSON.getBody().getString("pageSize");
            if (StringUtils.isEmpty(ps) || !StringUtils.isNumeric(ps)) {
                pageSize = Pagination.DEF_COUNT;
            } else {
                pageSize = Integer.parseInt(ps);
            }
        }
        logger.info(">>>>>>pageSize=" + requestJSON.getBody().getString("pageSize"));
        return pageSize;
    }
    
    public <T> Pagination<T> getPagination() {
        return new Pagination<T>(getPageNo(), getPageSize());
    }

    public SysCode getState() {
        return state;
    }

    public void setState(SysCode state) {
        this.state = state;
    }
}
