package com.rogrand.buynew.service;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.service.BaseService;
import com.rogrand.buynew.domain.BuyPools;

/**
 * 版权：LAB <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-08 <br/>
 * 描述：商品专区 Service
 */
@Service("BuyPoolsService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class BuyPoolsService extends BaseService {
    /**
     * 查询商品专区对象
     * @param id 字符串型主键
     * @return BuyPools
     * @throws Exception 异常
     */
    public BuyPools query(String id) throws Exception{
    	PageParam param = new PageParam();
    	param.put("id", id);
        return sqlDao.query("lab_buy_pools.pageList", param);
    }

    /**
     * 查询商品专区对象集合
     * @param param 查询条件
     * @return List<BuyPools>
     * @throws Exception 异常
     */
    public List<BuyPools> list(PageParam param) throws Exception {
    	return sqlDao.list("lab_buy_pools.pageList",param);
    }
    
    /**
     * 商品专区翻页查询
     * @param 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
    public PageResult pageList(PageParam param) throws Exception {
    	param.setCountSql("lab_buy_pools.pageCount");
    	param.setRecordSql("lab_buy_pools.pageList");
        return pageService.pageQuery(param);
    }

    /**
     * 插入商品专区记录
     * @param buyPools 商品专区对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(BuyPools buyPools) throws Exception  {
        sqlDao.create("lab_buy_pools.create",buyPools);
        return "1";
    }

    /**
     * 更新商品专区记录
     * @param buyPools 商品专区对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(BuyPools buyPools) throws Exception {
        sqlDao.update("lab_buy_pools.update", buyPools);
        return "1";
    }

    /**
     * 删除商品专区记录
     * @param buyPools 商品专区对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(BuyPools buyPools) throws Exception {
        sqlDao.delete("lab_buy_pools.delete", buyPools);
        return "1";
    }

    /**
     * 删除商品专区记录
     * @param id 字符串型主键
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String id) throws Exception {
        return delete(new BuyPools(id));
    }

    /**
     * 删除商品专区记录
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