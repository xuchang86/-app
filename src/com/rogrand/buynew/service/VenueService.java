package com.rogrand.buynew.service;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rogrand.core.domain.PageResult;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.service.BaseService;
import com.rogrand.sys.domain.User;
import com.rogrand.buynew.domain.Venue;

/**
 * 版权：LAB <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-02 <br/>
 * 描述：品牌馆 Service
 */
@Service("VenueService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class VenueService extends BaseService {
    /**
     * 查询品牌馆对象
     * @param id 字符串型主键
     * @return Venue
     * @throws Exception 异常
     */
    public Venue query(String id) throws Exception{
    	PageParam param = new PageParam();
    	param.put("id", id);
        return sqlDao.query("lab_venue.pageList", param);
    }

    /**
     * 查询品牌馆对象集合
     * @param param 查询条件
     * @return List<Venue>
     * @throws Exception 异常
     */
    public List<Venue> list(PageParam param) throws Exception {
    	return sqlDao.list("lab_venue.pageList",param);
    }
    
    /**
     * 品牌馆翻页查询
     * @param 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
    public PageResult pageList(PageParam param) throws Exception {
    	param.setCountSql("lab_venue.pageCount");
    	param.setRecordSql("lab_venue.pageList");
        return pageService.pageQuery(param);
    }

    /**
     * 插入品牌馆记录
     * @param venue 品牌馆对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(Venue venue, User user) throws Exception  {
    	venue.setCreate_user(user.getSu_code());
    	venue.setCreate_time(new Date());
        sqlDao.create("lab_venue.create",venue);
        return "1";
    }

    /**
     * 更新品牌馆记录
     * @param venue 品牌馆对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(Venue venue, User user) throws Exception {
    	venue.setUpdate_user(user.getSu_code());
    	venue.setUpdate_time(new Date());
        sqlDao.update("lab_venue.update", venue);
        return "1";
    }

    /**
     * 删除品牌馆记录
     * @param venue 品牌馆对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(Venue venue) throws Exception {
        sqlDao.delete("lab_venue.delete", venue);
        return "1";
    }

    /**
     * 删除品牌馆记录
     * @param id 字符串型主键
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String id) throws Exception {
        return delete(new Venue(id));
    }

    /**
     * 删除品牌馆记录
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
}