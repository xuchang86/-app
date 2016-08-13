package com.group.alibaba.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group.core.commons.SysCode;
import com.group.core.controller.BaseController;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.ItemDetailData;
import com.taobao.api.domain.XItem;
import com.taobao.api.request.TaeItemDetailGetRequest;
import com.taobao.api.request.TaeItemsListRequest;
import com.taobao.api.response.TaeItemDetailGetResponse;
import com.taobao.api.response.TaeItemsListResponse;

/**
 * 阿里百川
 * @author dailing
 * 2016-07-12
 */
@Controller
@RequestMapping("baichuan")
public class AliBaichuanController extends BaseController {
	
	private final String appkey = "23403470";
	private final String secret = "39ebc3c36ce79b39c07d77e3d08806e3";
	private final String url = "http://gw.api.taobao.com/router/rest";
	
	/**
	 * 百川商品列表接口
	 * @param request
	 * @param response
	 * @throws ApiException 
	 */
	@RequestMapping("taobaoTaeItemsList")
	public void taobaoTaeItemsList(HttpServletRequest request, HttpServletResponse response) throws ApiException {
		String[] paramKey = {"numIids"};
        String method = "taobaoTaeItemsList";
        Map<String, String> params = parseParams(request, method, paramKey);
        
        String numIids = params.get("numIids");
        if(StringUtils.isEmpty(numIids)){
        	renderJson(request, response, SysCode.PARAM_IS_ERROR, null);
        	return;
        }
        
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TaeItemsListRequest req = new TaeItemsListRequest();
        req.setFields("num,title,price,promoted_service,nick,ju,location,post_fee,cid,pic_url,shop_name");
        req.setNumIids(numIids);
        TaeItemsListResponse rsp = client.execute(req);
        List<XItem> list = rsp.getItems();
        
        renderJson(request, response, list);
	}
	
	/**
	 * 商品详情数据
	 * @param request
	 * @param response
	 * @throws ApiException
	 */
	@RequestMapping("taobaoTaeItemDetailGet")
	public void taobaoTaeItemDetailGet(HttpServletRequest request, HttpServletResponse response) throws ApiException {
		String[] paramKey = {"open_iid","fields"};
        String method = "taobaoTaeItemDetailGet";
        Map<String, String> params = parseParams(request, method, paramKey);
        
        String open_iid = params.get("open_iid");//商品open_iid 
        String fields = params.get("fields");
        if(StringUtils.isEmpty(open_iid) || StringUtils.isEmpty(fields)){
        	renderJson(request, response, SysCode.PARAM_IS_ERROR, null);
        	return;
        }
        
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TaeItemDetailGetRequest req = new TaeItemDetailGetRequest();
        req.setFields(fields);
        req.setOpenIid(open_iid);
        TaeItemDetailGetResponse rsp = client.execute(req);
        ItemDetailData data = rsp.getData();
        
        renderJson(request, response, data);
	}
}
