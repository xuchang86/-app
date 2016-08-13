package com.rogrand.buynew.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rogrand.buynew.domain.AreaFashions;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;

/**
 * 版权：LAB <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-05 <br/>
 * 描述：服装区下的服装 Service
 */
@Service("AreaFashionsService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class AreaFashionsService extends BaseService {
    /**
     * 查询服装区下的服装对象
     * @param id 字符串型主键
     * @return AreaFashions
     * @throws Exception 异常
     */
    public AreaFashions query(String id) throws Exception{
    	PageParam param = new PageParam();
    	param.put("id", id);
        return sqlDao.query("lab_area_fashions.pageList", param);
    }

    /**
     * 查询服装区下的服装对象集合
     * @param param 查询条件
     * @return List<AreaFashions>
     * @throws Exception 异常
     */
    public List<AreaFashions> list(PageParam param) throws Exception {
    	return sqlDao.list("lab_area_fashions.pageList",param);
    }
    
    /**
     * 服装区下的服装翻页查询
     * @param 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
    public PageResult pageList(PageParam param) throws Exception {
    	param.setCountSql("lab_area_fashions.pageCount");
    	param.setRecordSql("lab_area_fashions.pageList");
        return pageService.pageQuery(param);
    }

    /**
     * 插入服装区下的服装记录
     * @param areaFashions 服装区下的服装对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(AreaFashions areaFashions) throws Exception  {
        sqlDao.create("lab_area_fashions.create",areaFashions);
        return "1";
    }
    
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(String[] fashions, String areaId, String areaType) throws Exception  {
    	List<AreaFashions> list = new ArrayList<AreaFashions>();
    	for(int i=0;i<fashions.length;i++){
    		AreaFashions areaFashions = new AreaFashions();
    		areaFashions.setArea_id(Long.parseLong(areaId));
    		areaFashions.setArea_type(areaType);
    		areaFashions.setNum_iid(fashions[i]);
    		areaFashions.setIs_sale("1");
    		areaFashions.setSale_time(new Date());
    		list.add(areaFashions);
    	}
    	sqlDao.create("lab_area_fashions.createBatch",list);
        return "1";
    }

    /**
     * 更新服装区下的服装记录
     * @param areaFashions 服装区下的服装对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(AreaFashions areaFashions) throws Exception {
        sqlDao.update("lab_area_fashions.update", areaFashions);
        return "1";
    }

    /**
     * 删除服装区下的服装记录
     * @param areaFashions 服装区下的服装对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(AreaFashions areaFashions) throws Exception {
        sqlDao.delete("lab_area_fashions.delete", areaFashions);
        return "1";
    }

    /**
     * 删除服装区下的服装记录
     * @param id 字符串型主键
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String id) throws Exception {
        return delete(new AreaFashions(id));
    }

    /**
     * 删除服装区下的服装记录
     * @param ids 字符串型主键数组
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String[] ids) throws Exception {
        for(String id:ids){
            delete(id);
        }
        return "1";
    }
    
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String deleteFashions(String area_id, String area_type, String num_iid) throws Exception {
    	AreaFashions areaFashions = new AreaFashions();
    	areaFashions.setArea_id(Long.parseLong(area_id));
    	areaFashions.setArea_type(area_type);
    	areaFashions.setNum_iid(num_iid);
    	sqlDao.delete("lab_area_fashions.delete2", areaFashions);
        return "1";
    }
    
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String deleteFashions(String area_id, String area_type, String[] num_iids) throws Exception {
        for(String num_iid:num_iids){
        	AreaFashions areaFashions = new AreaFashions();
        	areaFashions.setArea_id(Long.parseLong(area_id));
        	areaFashions.setArea_type(area_type);
        	areaFashions.setNum_iid(num_iid);
        	sqlDao.delete("lab_area_fashions.delete2", areaFashions);
        }
        return "1";
    }
}