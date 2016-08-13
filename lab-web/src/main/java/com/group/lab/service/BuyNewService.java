package com.group.lab.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.core.service.ImageService;
import com.group.lab.dao.ActivityDetailMapper;
import com.group.lab.dao.AreaFashionsMapper;
import com.group.lab.dao.BrandMapper;
import com.group.lab.dao.BuyPoolsMapper;
import com.group.lab.dao.BuynewActivityMapper;
import com.group.lab.dao.FashionClassifyMapper;
import com.group.lab.dao.FashionMapper;
import com.group.lab.dao.FashionsMapper;
import com.group.lab.dao.HotClassMapper;
import com.group.lab.dao.HotLableMapper;
import com.group.lab.dao.SearchMapper;
import com.group.lab.dao.VenueMapper;
import com.group.lab.model.ActivityDetail;
import com.group.lab.model.ActivityDetailExample;
import com.group.lab.model.ActivityDetailOne;
import com.group.lab.model.ActivityDetailThree;
import com.group.lab.model.ActivityDetailTwo;
import com.group.lab.model.AreaFashions;
import com.group.lab.model.AreaFashionsExample;
import com.group.lab.model.Brand;
import com.group.lab.model.BrandExample;
import com.group.lab.model.BuyPools;
import com.group.lab.model.BuyPoolsExample;
import com.group.lab.model.BuynewActivity;
import com.group.lab.model.Fashion;
import com.group.lab.model.FashionClassify;
import com.group.lab.model.FashionTaobao;
import com.group.lab.model.Fashions;
import com.group.lab.model.FashionsExample;
import com.group.lab.model.HotLable;
import com.group.lab.model.Search;
import com.group.lab.model.SearchExample;
import com.group.lab.model.Venue;
import com.group.utils.ParamsUtils;

@Service
public class BuyNewService {
	
	@Autowired
	private AlibaichuanService alibaichuanService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private FashionMapper fashionMapper;
	
	@Autowired
	private BrandMapper brandMapper;
	
	@Autowired
	private VenueMapper venueMapper;
	
	@Autowired
	private AreaFashionsMapper areaFashionsMapper;
	
	@Autowired
	private FashionClassifyMapper fashionClassifyMapper;
	
	@Autowired
	private HotLableMapper hotLableMapper;
	
	@Autowired
	private SearchMapper searchMapper;
	
	@Autowired
	private BuynewActivityMapper buynewActivityMapper;
	
	@Autowired
	private ActivityDetailMapper activityDetailMapper;
	
	@Autowired
	private BuyPoolsMapper buyPoolsMapper;
	
	@Autowired
	private HotClassMapper hotClassMapper;
	
	@Autowired
	private FashionsMapper fashionsMapper;
	
	/**
	 * 获取首页轮播图活动
	 * @return
	 */
	public List<BuynewActivity> getActivitys(){
		List<BuynewActivity> list = buynewActivityMapper.selectByExample(null);
		return list;
	}
	
	/**
	 * 获取活动详情（活动一）
	 * @param activityId
	 * @return
	 */
	public List<ActivityDetailOne> getActivityDetailOne(int activityId){
		ActivityDetailExample example = new ActivityDetailExample();
		example.or().andActivityIdEqualTo(activityId);
		List<ActivityDetail> activityDetails = activityDetailMapper.selectByExample(example);
		if(activityDetails!=null && activityDetails.size()>0){
			List<ActivityDetailOne> list = new ArrayList<ActivityDetailOne>();
			for(int i=0;i<activityDetails.size();i++){
				ActivityDetail activityDetail = activityDetails.get(0);
				ActivityDetailOne one = new ActivityDetailOne();
				BeanUtils.copyProperties(activityDetail, one);
				list.add(one);
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 获取活动详情（活动二）
	 * @param activityId
	 * @return
	 */
	public List<ActivityDetailTwo> getActivityDetailTwo(int activityId){
		ActivityDetailExample example = new ActivityDetailExample();
		example.or().andActivityIdEqualTo(activityId);
		List<ActivityDetail> activityDetails = activityDetailMapper.selectByExample(example);
		if(activityDetails!=null && activityDetails.size()>0){
			List<ActivityDetailTwo> list = new ArrayList<ActivityDetailTwo>();
			for(int i=0;i<activityDetails.size();i++){
				ActivityDetail activityDetail = activityDetails.get(i);
				ActivityDetailTwo two = new ActivityDetailTwo();
				BeanUtils.copyProperties(activityDetail, two);
				
				String[] fashionIds = StringUtils.split(activityDetail.getFashionIds(), ",");
				List<FashionTaobao> fashions = alibaichuanService.getFashionByNumIid(fashionIds);
				two.setFashions(fashions);
				list.add(two);
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 获取活动详情（活动三）
	 * @param activityId
	 * @return
	 */
	public List<ActivityDetailThree> getActivityDetailThree(int activityId){
		ActivityDetailExample example = new ActivityDetailExample();
		example.or().andActivityIdEqualTo(activityId);
		List<ActivityDetail> activityDetails = activityDetailMapper.selectByExample(example);
		if(activityDetails!=null && activityDetails.size()>0){
			List<ActivityDetailThree> list = new ArrayList<ActivityDetailThree>();
			for(int i=0;i<activityDetails.size();i++){
				ActivityDetail activityDetail = activityDetails.get(i);
				ActivityDetailThree three = new ActivityDetailThree();
				BeanUtils.copyProperties(activityDetail, three);
				
				String[] fashionIds = StringUtils.split(activityDetail.getFashionIds(), ",");
				List<FashionTaobao> fashions = alibaichuanService.getFashionByNumIid(fashionIds);
				three.setFashions(fashions);
				list.add(three);
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 获取首页服装池服装
	 * @param poolId
	 * @param pageNo
	 * @return
	 */
	public Map<String, Object> getSYFashionPoolData(){
		Map<String, Object> map = new HashMap<String, Object>();

		BuyPoolsExample exmaple = new BuyPoolsExample();
		exmaple.setOrderByClause("stick_time DESC");
		List<BuyPools> pools = buyPoolsMapper.selectByExample(exmaple);
		map.put("pools", pools);
		
		if(pools.size()>0){
			int poolId = pools.get(0).getId();
			AreaFashionsExample example2 = new AreaFashionsExample();
			example2.or().andAreaTypeEqualTo("pools").andAreaIdEqualTo(poolId);
			example2.setStart(0);
			example2.setLimit(10);
			example2.setOrderByClause("stick_time DESC");
			List<AreaFashions> list = areaFashionsMapper.selectByExample(example2);
			List<String> num_iids = new ArrayList<String>();
			for(int i=0;i<list.size();i++){
				num_iids.add(list.get(i).getNumIid());
			}
			
			FashionsExample example3 = new FashionsExample();
			example3.or().andNumIidIn(num_iids);
			List<Fashions> fashions = fashionsMapper.selectByExample(example3);
			map.put("fashions", fashions);
		}
		return map;
	}
	
	/**
	 * 获取商品池服装ID
	 * @param poolId
	 * @param pageNo
	 * @return
	 */
	public List<Fashions> getPoolFashions(int poolId, int pageNo){
		AreaFashionsExample example = new AreaFashionsExample();
		example.or().andAreaTypeEqualTo("pools").andAreaIdEqualTo(poolId);
		example.setStart((pageNo-1)*10);
		example.setLimit(10);
		example.setOrderByClause("stick_time DESC");
		List<AreaFashions> list = areaFashionsMapper.selectByExample(example);
		List<String> numIids = new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			numIids.add(list.get(i).getNumIid());
		}
		
		FashionsExample example2 = new FashionsExample();
		example2.or().andNumIidIn(numIids);
		List<Fashions> fashions = fashionsMapper.selectByExample(example2);
		return fashions;
	}
	
	/**
	 * 获取首页主推优选品牌数据
	 * @return
	 */
	public List<Map<String, Object>> getMainBrandData(int pageNo){
		List<Map<String, Object>> listdata = new ArrayList<Map<String,Object>>();
		
		BrandExample example = new BrandExample();
		example.or().andIsMainEqualTo("Y");
		example.setStart((pageNo-1)*5);
		example.setLimit(5);
		example.setOrderByClause("stick_time DESC");
		List<Brand> brands = brandMapper.selectByExample(example);
		
		if(brands.size()>0){
			for(int j=0;j<brands.size();j++){
				Map<String, Object> map = new HashMap<String, Object>();
				Brand brand = brands.get(j);
				brand.setLogo(imageService.convertImageUrl(brand.getLogo()));
				
				AreaFashionsExample example2 = new AreaFashionsExample();
				example2.or().andAreaTypeEqualTo("brand").andAreaIdEqualTo(brand.getBrandId());
				example2.setStart(0);
				example2.setLimit(10);
				List<AreaFashions> list = areaFashionsMapper.selectByExample(example2);
				List<String> numIids = new ArrayList<String>();
				for(int i=0;i<list.size();i++){
					numIids.add(list.get(i).getNumIid());
				}
				FashionsExample example3 = new FashionsExample();
				example3.or().andNumIidIn(numIids);
				List<Fashions> fashions = fashionsMapper.selectByExample(example3);
				
				map.put("brand", brand);
				map.put("brandFashions", fashions);
				listdata.add(map);
			}
		}
		return listdata;
	}
	
	/**
	 * 获取首页次要优选品牌的数据
	 * @param pageNo
	 * @return
	 */
	public List<Map<String, Object>> getBrandData(int pageNo){
		int pageSize = 8;
		int start = (pageNo-1)*pageSize;
		int limit = pageSize;
		List<Map<String, Object>> list = areaFashionsMapper.selectBrandFirstFashion(null, start, limit);
		for(int i=0;i<list.size();i++){
			Map<String, Object> map = list.get(i);
			String logo = (String) map.get("logo");
			map.put("logo", imageService.convertImageUrl(logo));
		}
		return list;
	}
	
	/**
	 * 获取首页热门品类数据
	 * @return
	 */
	public Map<String, Object> getHotClassData(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
		List<Map<String, Object>> list1 = hotClassMapper.selectHotClass("上装");//上装热门品类
		List<Map<String, Object>> list2 = hotClassMapper.selectHotClass("裙装");//裙装热门品类
		List<Map<String, Object>> list3 = hotClassMapper.selectHotClass("裤装");//裤装热门品类
		list.add(list1);
		list.add(list2);
		list.add(list3);
		result.put("hotClass", list);
		return result;
	}
	
	/**
	 * 获取首页品牌馆数据
	 * @return
	 */
	public List<Map<String, Object>> getVenueData(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//获取所有的品牌馆
		List<Venue> venues = venueMapper.selectByExample(null);
		for(int i=0;i<venues.size();i++){
			Venue venue = venues.get(i);
			venue.setImage(imageService.convertImageUrl(venue.getImage()));
			
			List<String> numIids = areaFashionsMapper.selectVenueFashions(venue.getId(), 0, 10);
			FashionsExample example = new FashionsExample();
			example.or().andNumIidIn(numIids);
			List<Fashions> fashions = fashionsMapper.selectByExample(example);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("venue", venue);
			map.put("fashions", fashions);
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 获取所有的品牌
	 * @return
	 */
	public List<Venue> getVenues(){
		List<Venue> venues = venueMapper.selectByExample(null);
		for(int i=0;i<venues.size();i++){
			venues.get(i).setImage(imageService.convertImageUrl(venues.get(i).getImage()));
		}
		return venues;
	}
	
	/**
	 * 获取品牌馆下品牌数据
	 * @param pageNo
	 * @return
	 */
	public List<Map<String, Object>> getVenueBrands(int venueId, int pageNo){
		int pageSize = 8;
		int start = (pageNo-1)*pageSize;
		int limit = pageSize;
		List<Map<String, Object>> list = areaFashionsMapper.selectBrandFirstFashion(venueId, start, limit);
		return list;
	}
	
	/**
	 * 获取品牌馆下的服装分页数据
	 * @param venueId
	 * @param pageNo
	 * @return
	 */
	public List<Fashions> getVenueFashions(int venueId, int pageNo){
		int pageSize = ParamsUtils.pageSize;
		int start = (pageNo-1)*pageSize;
		int limit = pageSize;
		List<String> numIids = areaFashionsMapper.selectVenueFashions(venueId, start, limit);
		
		FashionsExample example = new FashionsExample();
		example.or().andNumIidIn(numIids);
		List<Fashions> fashions = fashionsMapper.selectByExample(example);
		return fashions;
	}
	
	/**
	 * 获取品牌列表
	 * @param venueId
	 * @return
	 */
	public List<Brand> getBrands(String venueId){
		List<Brand> list = null;
		if(StringUtils.isEmpty(venueId)){
			brandMapper.selectByExample(null);
		}else{
			BrandExample example = new BrandExample();
			example.or().andVenueIdEqualTo(Integer.parseInt(venueId));
			brandMapper.selectByExample(example);
		}
		
		return list;
	}
	
	/**
	 * 获取品牌服装列表数据
	 * @param brandId
	 * @param pageNo
	 * @return
	 */
	public Map<String, Object> getBrandFashions(int brandId, int pageNo){
		Map<String, Object> map = new HashMap<String, Object>();
		
		int pageSize = ParamsUtils.pageSize;
		int start = (pageNo-1)*pageSize;
		int limit = pageSize;
		AreaFashionsExample example = new AreaFashionsExample();
		example.or().andAreaTypeEqualTo("brand").andAreaIdEqualTo(brandId);
		example.setStart(start);
		example.setLimit(limit);
		List<AreaFashions> list = areaFashionsMapper.selectByExample(example);
		
		List<String> numIids = new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			numIids.add(list.get(i).getNumIid());
		}
		FashionsExample example2 = new FashionsExample();
		example2.or().andNumIidIn(numIids);
		List<Fashions> fashions = fashionsMapper.selectByExample(example2);
		map.put("fashions", fashions);
		
		return map;
	}
	
	/**
	 * 获取服装分类数据
	 * @return
	 */
	public Map<String, Object> getFashionClassify(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<FashionClassify> sz = new ArrayList<FashionClassify>();
		List<FashionClassify> kz = new ArrayList<FashionClassify>();
		List<FashionClassify> qz = new ArrayList<FashionClassify>();
		List<FashionClassify> list = fashionClassifyMapper.selectByExample(null);
		for(int i=0;i<list.size();i++){
			String parent = list.get(i).getParentName();
			if("上装".equals(parent)){
				sz.add(list.get(i));
			}
			if("裤装".equals(parent)){
				kz.add(list.get(i));
			}
			if("裙装".equals(parent)){
				qz.add(list.get(i));
			}
		}
		map.put("上装", sz);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("裤装", kz);
		map2.put("裙装", qz);
		map.put("裤装/裙装", map2);
		return map;
	}
	
	/**
	 * 查询叶子节点服装分类数据（已废弃）
	 * @return
	 */
	public List<FashionClassify> getLeafClassify(){
		List<FashionClassify> list = fashionClassifyMapper.selectByExample(null);
		return list;
	}
	
	/**
	 * 获取热门标签数据
	 * @return
	 */
	public List<HotLable> getHotLabels(){
		List<HotLable> list = hotLableMapper.selectByExample(null);
		return list;
	}
	
	/**
	 * 搜索
	 * @param content
	 * @param type
	 * @return
	 */
	public void search(String content, String type){
		SearchExample example = new SearchExample();
		example.or().andContentLike(content);
		List<Search> list = searchMapper.selectByExample(example);
		for(int i=0;i<list.size();i++){
			Search search = new Search();
			search.setId(list.get(i).getId());
			search.setFrequency(list.get(i).getFrequency()==null?1:list.get(i).getFrequency()+1);
			searchMapper.updateByPrimaryKeySelective(search);
		}
	}
	
	/**
	 * 获取热门搜索
	 * @return
	 */
	public Map<String, Object> getHotSearch(){
		Map<String, Object> map = new HashMap<String, Object>();
		String[] types = new String[]{"buynew","renew","chic","laber"};
		for(int i=0;i<types.length;i++){
			SearchExample example = new SearchExample();
			example.or().andTypeEqualTo(types[i]);
			example.setOrderByClause("frequency DESC");
			example.setStart(0);
			example.setLimit(10);
			List<Search> list = searchMapper.selectByExample(example);
			map.put(types[i], list);
		}
		
		return map;
	}
	
	/**
	 * 获取服装详情
	 * @param fashionId
	 * @return
	 */
	public Fashion getFashionDetail(int fashionId){
		Fashion fashion = fashionMapper.selectByPrimaryKey(fashionId);
		
		return fashion;
	}
}
