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
import com.rogrand.buynew.domain.Brand;

/**
 * 版权：小月科技 <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-07-16 <br/>
 * 描述：品牌 Service
 */
@Service("BrandService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class BrandService extends BaseService {
    /**
     * 查询品牌对象
     * @param brand_id 字符串型主键
     * @return Brand
     * @throws Exception 异常
     */
    public Brand query(String brand_id) throws Exception{
    	PageParam param = new PageParam();
    	param.put("brand_id", brand_id);
        return sqlDao.query("lab_brand.pageList", param);
    }

    /**
     * 查询品牌对象集合
     * @param param 查询条件
     * @return List<Brand>
     * @throws Exception 异常
     */
    public List<Brand> list(PageParam param) throws Exception {
    	return sqlDao.list("lab_brand.pageList",param);
    }
    
    /**
     * 品牌翻页查询
     * @param 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
    public PageResult pageList(PageParam param) throws Exception {
    	param.setCountSql("lab_brand.pageCount");
    	param.setRecordSql("lab_brand.pageList");
        return pageService.pageQuery(param);
    }

    /**
     * 插入品牌记录
     * @param brand 品牌对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(Brand brand, User user) throws Exception  {
    	brand.setCreate_time(new Date());
    	brand.setCreate_user(user.getSu_code());
        sqlDao.create("lab_brand.create",brand);
        return "1";
    }

    /**
     * 更新品牌记录
     * @param brand 品牌对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(Brand brand, User user) throws Exception {
    	brand.setUpdate_time(new Date());
    	brand.setUpdate_user(user.getSu_code());
        sqlDao.update("lab_brand.update", brand);
        return "1";
    }

    /**
     * 删除品牌记录
     * @param brand 品牌对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(Brand brand) throws Exception {
        sqlDao.delete("lab_brand.delete", brand);
        return "1";
    }

    /**
     * 删除品牌记录
     * @param brand_id 字符串型主键
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String brand_id) throws Exception {
        return delete(new Brand(brand_id));
    }

    /**
     * 删除品牌记录
     * @param brand_ids 字符串型主键数组
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String[] brand_ids) throws Exception {
        for(String brand_id:brand_ids){
            delete(brand_id);
        }
        return "1";
    }
}