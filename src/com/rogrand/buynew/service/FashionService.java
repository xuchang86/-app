package com.rogrand.buynew.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.service.BaseService;
import com.rogrand.sys.domain.User;
import com.rogrand.util.HttpUtils;
import com.rogrand.buynew.domain.Fashion;
import com.rogrand.buynew.domain.FashionExt;

/**
 * 版权：小月科技 <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-07-13 <br/>
 * 描述：服装 Service
 */
@Service("FashionService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class FashionService extends BaseService {
	
	@Value("${alibaba.url}")
	private String alibabaurl;
	
	@Value("${alibaba.port}")
	private String alibabaport;
	
    /**
     * 查询服装对象
     * @param fashion_id 字符串型主键
     * @return Fashion
     * @throws Exception 异常
     */
    public Fashion query(String fashion_id) throws Exception{
    	PageParam param = new PageParam();
    	param.put("fashion_id", fashion_id);
        return sqlDao.query("lab_fashion.pageList", param);
    }

    /**
     * 查询服装对象集合
     * @param param 查询条件
     * @return List<Fashion>
     * @throws Exception 异常
     */
    public List<Fashion> list(PageParam param) throws Exception {
    	return sqlDao.list("lab_fashion.pageList",param);
    }
    
    /**
     * 服装翻页查询
     * @param 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
	public PageResult pageList(PageParam param) throws Exception {
    	param.setCountSql("lab_fashion.pageCount");
    	param.setRecordSql("lab_fashion.pageList");
    	PageResult pageResult = pageService.pageQuery(param);
    	/*List<Fashion> list = pageResult.getPageList();
    	if(list!=null && list.size()>0){
    		String num_iids = "";
        	for(int i=0;i<list.size();i++){
        		num_iids += list.get(i).getNum_iid() + ",";
        	}
        	
        	String result = HttpUtils.sendPost("http://"+alibabaurl+":"+alibabaport+"/lab-alibaba/baichuan/taobaoTaeItemsList.do", "numIids="+num_iids);
        	List<FashionExt> fashions = JSON.parseArray(result, FashionExt.class);
        	pageResult.setPageList(fashions);
    	}*/
    	
    	return pageResult;
    }

    /**
     * 插入服装记录
     * @param fashion 服装对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(Fashion fashion, User user, String type) throws Exception  {
    	String result = HttpUtils.sendPost("http://"+alibabaurl+":"+alibabaport+"/lab-alibaba/baichuan/taobaoTaeItemsList.do", "numIids="+fashion.getNum_iid());
    	if(StringUtils.isEmpty(result)) return "获取不到淘宝服装信息";
    	
    	List<FashionExt> fashions = JSON.parseArray(result, FashionExt.class);
    	if(fashions!=null && fashions.size()>0){
    		FashionExt fashionExt = fashions.get(0);
    		fashion.setOpen_iid(fashionExt.getOpenIid());
    		fashion.setTitle(fashionExt.getTitle());
    		fashion.setNick(fashionExt.getNick());
    		fashion.setShop_name(fashionExt.getShopName());
    	}
    	
    	fashion.setCreate_user(user.getSu_code());
    	fashion.setCreate_time(new Date());
        sqlDao.create("lab_fashion.create",fashion);
        return "1";
    }

    /**
     * 更新服装记录
     * @param fashion 服装对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(Fashion fashion, User user) throws Exception {
    	String result = HttpUtils.sendPost("http://"+alibabaurl+":"+alibabaport+"/lab-alibaba/baichuan/taobaoTaeItemsList.do", "numIids="+fashion.getNum_iid());
    	List<FashionExt> fashions = JSON.parseArray(result, FashionExt.class);
    	if(fashions!=null && fashions.size()>0){
    		FashionExt fashionExt = fashions.get(0);
    		fashion.setOpen_iid(fashionExt.getOpenIid());
    		fashion.setTitle(fashionExt.getTitle());
    		fashion.setNick(fashionExt.getNick());
    		fashion.setShop_name(fashionExt.getShopName());
    	}
    	
    	fashion.setUpdate_user(user.getSu_code());
    	fashion.setUpdate_time(new Date());
        sqlDao.update("lab_fashion.update", fashion);
        return "1";
    }

    /**
     * 删除服装记录
     * @param fashion 服装对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(Fashion fashion) throws Exception {
        sqlDao.delete("lab_fashion.delete", fashion);
        return "1";
    }

    /**
     * 删除服装记录
     * @param fashion_id 字符串型主键
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String fashion_id) throws Exception {
        return delete(new Fashion(fashion_id));
    }

    /**
     * 删除服装记录
     * @param fashion_ids 字符串型主键数组
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String[] fashion_ids) throws Exception {
        for(String fashion_id:fashion_ids){
            delete(fashion_id);
        }
        return "1";
    }
}