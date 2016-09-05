package com.group.lab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.group.lab.model.FashionTaobao;
import com.group.utils.HttpUtils;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.XItem;
import com.taobao.api.internal.tmc.*;
import com.taobao.api.internal.toplink.LinkException;
import com.taobao.api.request.TaeItemsListRequest;
import com.taobao.api.response.TaeItemsListResponse;

/**
 * 阿里百川Service
 * @author dailing
 * 2016-07-21
 */
@Service
public class AlibaichuanService {
	
	private final String appkey = "23403470";
	private final String secret = "39ebc3c36ce79b39c07d77e3d08806e3";
	private final String url = "http://gw.api.taobao.com/router/rest";
	
	@Value("${alibaba.url}")
	private String alibabaurl;
	
	@Value("${alibaba.port}")
	private String alibabaport;

	/**
	 * 获取淘宝服装列表数据
	 * @param numIids
	 * @return
	 */
	public List<FashionTaobao> getFashionByNumIid(List<String> numIids){
		if(numIids==null || numIids.size()==0)
			return null;
		
		String num_iids = "";
    	for(int i=0;i<numIids.size();i++){
    		num_iids += numIids.get(i) + ",";
    	}
    	
    	String result = HttpUtils.sendPost("http://"+alibabaurl+":"+alibabaport+"/lab-alibaba/baichuan/taobaoTaeItemsList.do", "numIids="+num_iids);
    	if(!StringUtils.isEmpty(result)){
    		List<FashionTaobao> fashions = JSON.parseArray(result, FashionTaobao.class);
        	return fashions;
    	}
    	return null;
	}
	
	/**
	 * 获取淘宝服装列表数据（直接获取）
	 * @param numIids
	 * @return
	 * @throws ApiException 
	 */
	public List<FashionTaobao> getFashionTaobaoByNumIid(List<String> numIids) throws ApiException{
		if(numIids==null || numIids.size()==0)
			return null;
		
		String num_iids = "";
    	for(int i=0;i<numIids.size();i++){
    		num_iids += numIids.get(i) + ",";
    	}
    	
    	TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TaeItemsListRequest req = new TaeItemsListRequest();
        req.setFields("num,title,price,promoted_service,nick,ju,location,post_fee,cid,pic_url,shop_name");
        req.setNumIids(num_iids);
        TaeItemsListResponse rsp = client.execute(req);
        List<XItem> list = rsp.getItems();
    	List<FashionTaobao> fashions = new ArrayList<FashionTaobao>();
    	BeanUtils.copyProperties(list, fashions);
    	return fashions;
	}
	
	/**
	 * 获取淘宝服装列表数据
	 * @param numIids
	 * @return
	 */
	public List<FashionTaobao> getFashionByNumIid(String[] numIids){
		if(numIids==null || numIids.length==0)
			return null;
		
		String num_iids = "";
    	for(int i=0;i<numIids.length;i++){
    		num_iids += numIids[i] + ",";
    	}
    	
    	String result = HttpUtils.sendPost("http://"+alibabaurl+":"+alibabaport+"/lab-alibaba/baichuan/taobaoTaeItemsList.do", "numIids="+num_iids);
    	if(!StringUtils.isEmpty(result)){
    		List<FashionTaobao> fashions = JSON.parseArray(result, FashionTaobao.class);
        	return fashions;
    	}
    	return null;
	}
	
	/**
	 * 获取淘宝服装列表数据（直接获取）
	 * @param numIids
	 * @return
	 * @throws ApiException 
	 */
	public List<FashionTaobao> getFashionTaobaoByNumIid(String[] numIids) throws ApiException{
		if(numIids==null || numIids.length==0)
			return null;
		
		String num_iids = "";
    	for(int i=0;i<numIids.length;i++){
    		num_iids += numIids[i] + ",";
    	}
    	
    	TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TaeItemsListRequest req = new TaeItemsListRequest();
        req.setFields("num,title,price,promoted_service,nick,ju,location,post_fee,cid,pic_url,shop_name");
        req.setNumIids(num_iids);
        TaeItemsListResponse rsp = client.execute(req);
        List<XItem> list = rsp.getItems();
    	List<FashionTaobao> fashions = new ArrayList<FashionTaobao>();
    	BeanUtils.copyProperties(list, fashions);
    	return fashions;
	}
	
	/**
	 * 获取单个淘宝服装数据
	 * @param numIids
	 * @return
	 */
	public FashionTaobao getFashionByNumIid(String numIid){
		if(StringUtils.isEmpty(numIid))
			return null;
		
		String result = HttpUtils.sendPost("http://"+alibabaurl+":"+alibabaport+"/lab-alibaba/baichuan/taobaoTaeItemsList.do", "numIids="+numIid);
    	if(!StringUtils.isEmpty(result)){
    		List<FashionTaobao> fashions = JSON.parseArray(result, FashionTaobao.class);
        	if(fashions!=null && fashions.size()>0){
        		return fashions.get(0);
        	}
    	}
    	return null;
	}
	
	/**
	 * 接收消息
	 * @throws LinkException 
	 */
	public void receiveMsg() throws LinkException{
		TmcClient client = new TmcClient(appkey, secret, "default"); // 关于default参考消息分组说明
		client.setMessageHandler(new MessageHandler() {
		    public void onMessage(Message message, MessageStatus status) {
		        try {
		            System.out.println(message.getContent());
		            System.out.println(message.getTopic());
		        } catch (Exception e) {
		            e.printStackTrace();
		            status.fail(); // 消息处理失败回滚，服务端需要重发
		          // 重试注意：不是所有的异常都需要系统重试。 
		          // 对于字段不全、主键冲突问题，导致写DB异常，不可重试，否则消息会一直重发
		          // 对于，由于网络问题，权限问题导致的失败，可重试。
		          // 重试时间 5分钟不等，不要滥用，否则会引起雪崩
		        }
		    }
		});
		client.connect("ws://mc.api.taobao.com"); // 沙箱环境消息环境地址：ws://mc.api.tbsandbox.com/
	}
}
