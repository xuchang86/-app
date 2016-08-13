package com.group.lab.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group.core.commons.SysCode;
import com.group.core.controller.BaseController;
import com.group.lab.model.ActivityDetailOne;
import com.group.lab.model.ActivityDetailThree;
import com.group.lab.model.ActivityDetailTwo;
import com.group.lab.model.Brand;
import com.group.lab.model.BuyPools;
import com.group.lab.model.BuynewActivity;
import com.group.lab.model.Fashion;
import com.group.lab.model.FashionClassify;
import com.group.lab.model.FashionTaobao;
import com.group.lab.model.Fashions;
import com.group.lab.model.HotLable;
import com.group.lab.model.Venue;
import com.group.lab.service.AlibaichuanService;
import com.group.lab.service.BuyNewService;
import com.taobao.api.internal.toplink.LinkException;

/**
 * 买新模块
 * @author dailing
 * 2016-07-16 9:45
 */
@Controller
@RequestMapping("buynew/V1")
public class BuyNewController extends BaseController{

	@Autowired
	private BuyNewService buyNewService;
	
	@Autowired
	private AlibaichuanService alibaichuanService; 
	
	/**
	 * 获取首页轮播图活动
	 * @param request
	 * @param response
	 */
	@RequestMapping("getActivitys")
	public void getActivitys(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {};
        String method = "getActivitys";
        parseParams(request, method, paramKey);
        
        //获取商城首页轮播图活动
        List<BuynewActivity> result = buyNewService.getActivitys();
		Map<Class<?>, String[]> includes = new HashMap<Class<?>, String[]>() {
            private static final long serialVersionUID = -5349178483472578926L;
            {
                put(BuynewActivity.class, new String[] {"id","title","image","type"});
            }
        };
        renderJson(request, response, SysCode.SUCCESS, result, includes);
	}
	
	/**
	 * 获取活动详情
	 * @param request
	 * @param response
	 */
	@RequestMapping("getActivityDetail")
	public void getActivityDetail(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {"activityId","type"};
        String method = "getActivityDetail";
        Map<String, String> params = parseParams(request, method, paramKey);
        
        String activityId = params.get("activityId");//活动ID
        String type = params.get("type");
        if(StringUtils.isEmpty(activityId) || StringUtils.isEmpty(type)){
        	renderJson(request, response, SysCode.PARAM_IS_ERROR, null);
        	return;
        }
		
        if("1".equals(type)){
        	List<ActivityDetailOne> result = buyNewService.getActivityDetailOne(Integer.parseInt(activityId));
        	renderJson(request, response, SysCode.SUCCESS, result);
        }
        if("2".equals(type)){
        	List<ActivityDetailTwo> result = buyNewService.getActivityDetailTwo(Integer.parseInt(activityId));
        	Map<Class<?>, String[]> includes = new HashMap<Class<?>, String[]>() {
                private static final long serialVersionUID = -5349178483472578926L;
                {
                    put(FashionTaobao.class, new String[] {"cid","mall","nick","num","openIid","picUrl","postFee","priceWap","priceWapEndTime","priceStartTime","promotedService","reservePrice","shopName","title"});
                }
            };
        	renderJson(request, response, SysCode.SUCCESS, result, includes);
        }
        if("3".equals(type)){
        	List<ActivityDetailThree> result = buyNewService.getActivityDetailThree(Integer.parseInt(activityId));
        	Map<Class<?>, String[]> includes = new HashMap<Class<?>, String[]>() {
                private static final long serialVersionUID = -5349178483472578926L;
                {
                    put(FashionTaobao.class, new String[] {"cid","mall","nick","num","openIid","picUrl","postFee","priceWap","priceWapEndTime","priceStartTime","promotedService","reservePrice","shopName","title"});
                }
            };
            renderJson(request, response, SysCode.SUCCESS, result, includes);
        }
	}
	
	/**
	 * 获取首页商品池的服装
	 * @param request
	 * @param response
	 */
	@RequestMapping("getSYFashionPoolData")
	public void getSYFashionPoolData(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {};
        String method = "getSYFashionPoolData";
        parseParams(request, method, paramKey);
        
        Map<String, Object> result = buyNewService.getSYFashionPoolData();
        Map<Class<?>, String[]> includes = new HashMap<Class<?>, String[]>() {
            private static final long serialVersionUID = -5349178483472578926L;
            {
                put(Fashions.class, new String[] {"numIid","name","mainImage","price","tbkShotUrl","tbkUrl"});
                put(BuyPools.class, new String[]{"id","name","instruction"});
            }
        };
        renderJson(request, response, SysCode.SUCCESS, result, includes);
	}
	
	/**
	 * 获取商品池服装
	 * @param request
	 * @param response
	 */
	@RequestMapping("getPoolFashions")
	public void getPoolFashions(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {"poolId","pageNo"};
        String method = "getPoolFashions";
        Map<String, String> params = parseParams(request, method, paramKey);
        
        String poolId = params.get("poolId");//商品池ID
        String pageNo = params.get("pageNo");//页号
        if(StringUtils.isEmpty(poolId) || StringUtils.isEmpty(pageNo)){
        	renderJson(request, response, SysCode.PARAM_IS_ERROR, null);
        	return;
        }
        
        List<Fashions> result = buyNewService.getPoolFashions(Integer.parseInt(poolId), Integer.parseInt(pageNo));
        Map<Class<?>, String[]> includes = new HashMap<Class<?>, String[]>() {
            private static final long serialVersionUID = -5349178483472578926L;
            {
            	put(Fashions.class, new String[] {"numIid","name","mainImage","price","tbkShotUrl","tbkUrl"});
            }
        };
        renderJson(request, response, SysCode.SUCCESS, result, includes);
	}
	
	/**
	 * 获取主推优选品牌数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("getMainBrandData")
	public void getMainBrandData(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {"pageNo"};
        String method = "getMainBrandData";
        Map<String, String> params = parseParams(request, method, paramKey);
        
        String pageNo = params.get("pageNo");//页号
        if(StringUtils.isEmpty(pageNo)){
        	renderJson(request, response, SysCode.PARAM_IS_ERROR, null);
        	return;
        }
        
        //获取首页主推品牌数据
		List<Map<String, Object>> result = buyNewService.getMainBrandData(Integer.parseInt(pageNo));
		Map<Class<?>, String[]> includes = new HashMap<Class<?>, String[]>() {
            private static final long serialVersionUID = -5349178483472578926L;
            {
            	put(Brand.class, new String[] {"brandId","name","enName","logo","country","flag","venueId"});
            	put(Fashions.class, new String[] {"numIid","name","mainImage","price","tbkShotUrl","tbkUrl"});
            }
        };
		renderJson(request, response, SysCode.SUCCESS, result, includes);
	}
	
	/**
	 * 获取首页次要优选品牌的数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("getBrandData")
	public void getBrandData(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {"pageNo"};
        String method = "getBrandData";
        Map<String, String> params = parseParams(request, method, paramKey);
        
        String pageNo = params.get("pageNo");//页号
        if(StringUtils.isEmpty(pageNo)){
        	renderJson(request, response, SysCode.PARAM_IS_ERROR, null);
        	return;
        }
        
        //获取首页次要品牌数据
        List<Map<String, Object>> result = buyNewService.getBrandData(Integer.parseInt(pageNo));
        renderJson(request, response, SysCode.SUCCESS, result);
	}
	
	/**
	 * 获取首页热门品类服装数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("getHotClassData")
	public void getHotClassData(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {};
        String method = "getHotClassData";
        parseParams(request, method, paramKey);
        
        Map<String, Object> result = buyNewService.getHotClassData();
        renderJson(request, response, SysCode.SUCCESS, result);
	}
	
	/**
	 * 获取首页品牌馆数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("getVenueData")
	public void getVenueData(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {};
        String method = "getVenueData";
        parseParams(request, method, paramKey);
		
        //获取首页品牌馆数据
        List<Map<String, Object>> result = buyNewService.getVenueData();
        Map<Class<?>, String[]> includes = new HashMap<Class<?>, String[]>() {
            private static final long serialVersionUID = -5349178483472578926L;
            {
            	put(Venue.class, new String[] {"id","name","image","price","tbkShotUrl","tbkUrl"});
            	put(Fashions.class, new String[] {"numIid","name","mainImage","price","tbkShotUrl","tbkUrl"});
            }
        };
        renderJson(request, response, SysCode.SUCCESS, result, includes);
	}
	
	/**
	 * 获取所有的品牌馆
	 * @param request
	 * @param response
	 */
	@RequestMapping("getVenues")
	public void getVenues(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {};
        String method = "getVenues";
        parseParams(request, method, paramKey);
        
        List<Venue> result = buyNewService.getVenues();
        Map<Class<?>, String[]> includes = new HashMap<Class<?>, String[]>() {
            private static final long serialVersionUID = -5349178483472578926L;
            {
                put(Venue.class, new String[] {"id","name","image"});
            }
        };
        renderJson(request, response, SysCode.SUCCESS, result, includes);
	}
	
	/**
	 * 获取品牌馆下的优选品牌
	 * @param request
	 * @param response
	 */
	@RequestMapping("getVenueBrands")
	public void getVenueBrands(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {"venueId", "pageNo"};
        String method = "getVenueBrands";
        Map<String, String> params = parseParams(request, method, paramKey);
        
        String venueId = params.get("venueId");
        String pageNo = params.get("pageNo");//页号
        if(StringUtils.isEmpty(venueId) || StringUtils.isEmpty(pageNo)){
        	renderJson(request, response, SysCode.PARAM_IS_ERROR, null);
        	return;
        }
		
        //获取首页次要品牌数据
        List<Map<String, Object>> result = buyNewService.getVenueBrands(Integer.parseInt(venueId), Integer.parseInt(pageNo));
        renderJson(request, response, SysCode.SUCCESS, result);
	}
	
	/**
	 * 获取品牌馆下的服装数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("getVenueFashions")
	public void getVenueFashions(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {"venueId", "pageNo"};
        String method = "getVenueFashions";
        Map<String, String> params = parseParams(request, method, paramKey);
        
        String venueId = params.get("venueId");
        String pageNo = params.get("pageNo");//页号
        if(StringUtils.isEmpty(venueId) || StringUtils.isEmpty(pageNo)){
        	renderJson(request, response, SysCode.PARAM_IS_ERROR, null);
        	return;
        }
        
        //获取品牌馆下的服装分页数据
        List<Fashions> list = buyNewService.getVenueFashions(Integer.parseInt(venueId), Integer.parseInt(pageNo));
        Map<Class<?>, String[]> includes = new HashMap<Class<?>, String[]>() {
            private static final long serialVersionUID = -5349178483472578926L;
            {
            	put(Fashions.class, new String[] {"numIid","name","mainImage","price","tbkShotUrl","tbkUrl"});
            }
        };
        renderJson(request, response, SysCode.SUCCESS, list, includes);
	}
	
	/**
	 * 获取品牌列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("getBrands")
	public void getBrands(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {"venueId"};
        String method = "getBrands";
        Map<String, String> params = parseParams(request, method, paramKey);
        
        String venueId = params.get("venueId");
        List<Brand> result = buyNewService.getBrands(venueId);
        Map<Class<?>, String[]> includes = new HashMap<Class<?>, String[]>() {
            private static final long serialVersionUID = -5349178483472578926L;
            {
                put(Brand.class, new String[] {"brandId","name","logo"});
            }
        };
        renderJson(request, response, SysCode.SUCCESS, result, includes);
	}
	
	/**
	 * 获取品牌服装列表数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("getBrandFashions")
	public void getBrandFashions(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {"brandId","pageNo"};
        String method = "getBrandFashions";
        Map<String, String> params = parseParams(request, method, paramKey);
        
        String brandId = params.get("brandId");
        String pageNo = params.get("pageNo");//页号
        if(StringUtils.isEmpty(brandId) || StringUtils.isEmpty(pageNo)){
        	renderJson(request, response, SysCode.PARAM_IS_ERROR, null);
        	return;
        }
        
        //获取服装品牌列表数据
        Map<String, Object> result = buyNewService.getBrandFashions(Integer.parseInt(brandId), Integer.parseInt(pageNo));
        Map<Class<?>, String[]> includes = new HashMap<Class<?>, String[]>() {
            private static final long serialVersionUID = -5349178483472578926L;
            {
            	put(Fashions.class, new String[] {"numIid","name","mainImage","price","tbkShotUrl","tbkUrl"});
            }
        };
        renderJson(request, response, SysCode.SUCCESS, result, includes);
	}
	
	/**
	 * 获取服装分类数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("getFashionClassify")
	public void getFashionClassify(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {};
        String method = "getFashionClassify";
        parseParams(request, method, paramKey);
        
        //获取服装分类数据
        Map<String, Object> result = buyNewService.getFashionClassify();
        Map<Class<?>, String[]> includes = new HashMap<Class<?>, String[]>() {
            private static final long serialVersionUID = -5349178483472578926L;
            {
                put(FashionClassify.class, new String[] {"id","name","parentName"});
            }
        };
        renderJson(request, response, SysCode.SUCCESS, result, includes);
	}
	
	/**
	 * 查询叶子节点服装分类数据（已废弃）
	 * @param request
	 * @param response
	 */
	@RequestMapping("getLeafClassify")
	public void getLeafClassify(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {};
        String method = "getLeafClassify";
        parseParams(request, method, paramKey);
        
        List<FashionClassify> result = buyNewService.getLeafClassify();
        Map<Class<?>, String[]> includes = new HashMap<Class<?>, String[]>() {
            private static final long serialVersionUID = -5349178483472578926L;
            {
                put(FashionClassify.class, new String[] {"id","number","name"});
            }
        };
        renderJson(request, response, SysCode.SUCCESS, result, includes);
	}
	
	/**
	 * 获取热门标签数据
	 * @param request
	 * @param response
	 */
	@RequestMapping("getHotLabels")
	public void getHotLabels(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {};
        String method = "getHotLabels";
        parseParams(request, method, paramKey);
        
        List<HotLable> result = buyNewService.getHotLabels();
        renderJson(request, response, SysCode.SUCCESS, result);
	}
	
	/**
	 * 搜索
	 * @param request
	 * @param response
	 */
	@RequestMapping("search")
	public void search(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {"content","type"};
        String method = "search";
        Map<String, String> params = parseParams(request, method, paramKey);
        
        String content = params.get("content");
        String type = params.get("type");
        if(StringUtils.isEmpty(content) || StringUtils.isEmpty(type)){
        	renderJson(request, response, SysCode.PARAM_IS_ERROR, null);
        	return;
        }
        
        //搜索
        buyNewService.search(content, type);
        renderJson(request, response, SysCode.SUCCESS, null);
	}
	
	/**
	 * 获取热门搜索
	 * @param request
	 * @param response
	 */
	@RequestMapping("getHotSearch")
	public void getHotSearch(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {};
        String method = "getHotSearch";
        parseParams(request, method, paramKey);
        
        //获取热门搜索
		Map<String, Object> result = buyNewService.getHotSearch();
		renderJson(request, response, SysCode.SUCCESS, result);
	}
	
	/**
	 * 获取服装详情
	 * @param request
	 * @param response
	 */
	@RequestMapping("getFashionDetail")
	public void getFashionDetail(HttpServletRequest request, HttpServletResponse response){
		String[] paramKey = {"fashionId"};
        String method = "getFashionDetail";
        Map<String, String> params = parseParams(request, method, paramKey);
        
        String fashionId = params.get("fashionId");//获取服装详情
        if(StringUtils.isEmpty(fashionId)){
        	renderJson(request, response, SysCode.PARAM_IS_ERROR, null);
        	return;
        }
        
        Fashion fashion = buyNewService.getFashionDetail(Integer.parseInt(fashionId));
	}
	
	@RequestMapping("receiveMsg")
	public void receiveMsg(HttpServletRequest request, HttpServletResponse response) throws LinkException{
		alibaichuanService.receiveMsg();
	}
}
