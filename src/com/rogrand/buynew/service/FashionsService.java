package com.rogrand.buynew.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rogrand.core.domain.PageResult;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.service.BaseService;
import com.rogrand.buynew.domain.Fashions;

/**
 * 版权：小月科技 <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-01 <br/>
 * 描述：服装款式 Service
 */
@Service("FashionsService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class FashionsService extends BaseService {
    /**
     * 查询服装款式对象
     * @param num_iid 字符串型主键
     * @return Fashions
     * @throws Exception 异常
     */
    public Fashions query(String num_iid) throws Exception{
    	PageParam param = new PageParam();
    	param.put("num_iid", num_iid);
        return sqlDao.query("lab_fashions.pageList", param);
    }

    /**
     * 查询服装款式对象集合
     * @param param 查询条件
     * @return List<Fashions>
     * @throws Exception 异常
     */
    public List<Fashions> list(PageParam param) throws Exception {
    	return sqlDao.list("lab_fashions.pageList",param);
    }
    
    /**
     * 服装款式翻页查询
     * @param 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
    public PageResult pageList(PageParam param) throws Exception {
    	param.setCountSql("lab_fashions.pageCount");
    	param.setRecordSql("lab_fashions.pageList");
        return pageService.pageQuery(param);
    }
    
    /**
     * 热门品类翻页查询
     * @param 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
    public PageResult pageList2(PageParam param) throws Exception {
    	param.setCountSql("lab_fashions.selectAreaFashionsCount");
    	param.setRecordSql("lab_fashions.selectAreaFashions");
    	return pageService.pageQuery(param);
    }

    /**
     * 插入服装款式记录
     * @param fashions 服装款式对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(Fashions fashions) throws Exception  {
        sqlDao.create("lab_fashions.create",fashions);
        return "1";
    }

    /**
     * 更新服装款式记录
     * @param fashions 服装款式对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(Fashions fashions) throws Exception {
        sqlDao.update("lab_fashions.update", fashions);
        return "1";
    }

    /**
     * 删除服装款式记录
     * @param fashions 服装款式对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(Fashions fashions) throws Exception {
        sqlDao.delete("lab_fashions.delete", fashions);
        return "1";
    }

    /**
     * 删除服装款式记录
     * @param num_iid 字符串型主键
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String num_iid) throws Exception {
        return delete(new Fashions(num_iid));
    }

    /**
     * 删除服装款式记录
     * @param num_iids 字符串型主键数组
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String[] num_iids) throws Exception {
        for(String num_iid:num_iids){
            delete(num_iid);
        }
        return "1";
    }
}