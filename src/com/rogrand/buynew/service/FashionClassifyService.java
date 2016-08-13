package com.rogrand.buynew.service;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.service.BaseService;
import com.rogrand.buynew.domain.FashionClassify;

/**
 * 版权：LAB <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-06 <br/>
 * 描述：服装分类 Service
 */
@Service("FashionClassifyService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class FashionClassifyService extends BaseService {
    /**
     * 查询服装分类对象
     * @param id 字符串型主键
     * @return FashionClassify
     * @throws Exception 异常
     */
    public FashionClassify query(String id) throws Exception{
    	PageParam param = new PageParam();
    	param.put("id", id);
        return sqlDao.query("lab_fashion_classify.pageList", param);
    }

    /**
     * 查询服装分类对象集合
     * @param param 查询条件
     * @return List<FashionClassify>
     * @throws Exception 异常
     */
    public List<FashionClassify> list(PageParam param) throws Exception {
    	return sqlDao.list("lab_fashion_classify.pageList",param);
    }
    
    /**
     * 服装分类翻页查询
     * @param 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
    public PageResult pageList(PageParam param) throws Exception {
    	param.setCountSql("lab_fashion_classify.pageCount");
    	param.setRecordSql("lab_fashion_classify.pageList");
        return pageService.pageQuery(param);
    }

    /**
     * 插入服装分类记录
     * @param fashionClassify 服装分类对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(FashionClassify fashionClassify) throws Exception  {
        sqlDao.create("lab_fashion_classify.create",fashionClassify);
        return "1";
    }

    /**
     * 更新服装分类记录
     * @param fashionClassify 服装分类对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(FashionClassify fashionClassify) throws Exception {
        sqlDao.update("lab_fashion_classify.update", fashionClassify);
        return "1";
    }

    /**
     * 删除服装分类记录
     * @param fashionClassify 服装分类对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(FashionClassify fashionClassify) throws Exception {
        sqlDao.delete("lab_fashion_classify.delete", fashionClassify);
        return "1";
    }

    /**
     * 删除服装分类记录
     * @param id 字符串型主键
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String id) throws Exception {
        return delete(new FashionClassify(id));
    }

    /**
     * 删除服装分类记录
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