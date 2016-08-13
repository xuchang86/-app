package com.rogrand.buynew.service;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.service.BaseService;
import com.rogrand.buynew.domain.FashionSales;

/**
 * 版权：小月科技 <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-07-13 <br/>
 * 描述：服装促销 Service
 */
@Service("FashionSalesService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class FashionSalesService extends BaseService {
    /**
     * 查询服装促销对象
     * @param fashion_sales_id 字符串型主键
     * @return FashionSales
     * @throws Exception 异常
     */
    public FashionSales query(String fashion_sales_id) throws Exception{
    	PageParam param = new PageParam();
    	param.put("fashion_sales_id", fashion_sales_id);
        return sqlDao.query("lab_fashion_sales.pageList", param);
    }

    /**
     * 查询服装促销对象集合
     * @param param 查询条件
     * @return List<FashionSales>
     * @throws Exception 异常
     */
    public List<FashionSales> list(PageParam param) throws Exception {
    	return sqlDao.list("lab_fashion_sales.pageList",param);
    }
    
    /**
     * 服装促销翻页查询
     * @param 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
    public PageResult pageList(PageParam param) throws Exception {
    	param.setCountSql("lab_fashion_sales.pageCount");
    	param.setRecordSql("lab_fashion_sales.pageList");
        return pageService.pageQuery(param);
    }

    /**
     * 插入服装促销记录
     * @param fashionSales 服装促销对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(FashionSales fashionSales) throws Exception  {
        sqlDao.create("lab_fashion_sales.create",fashionSales);
        return "1";
    }

    /**
     * 更新服装促销记录
     * @param fashionSales 服装促销对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(FashionSales fashionSales) throws Exception {
        sqlDao.update("lab_fashion_sales.update", fashionSales);
        return "1";
    }

    /**
     * 删除服装促销记录
     * @param fashionSales 服装促销对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(FashionSales fashionSales) throws Exception {
        sqlDao.delete("lab_fashion_sales.delete", fashionSales);
        return "1";
    }

    /**
     * 删除服装促销记录
     * @param fashion_sales_id 字符串型主键
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String fashion_sales_id) throws Exception {
        return delete(new FashionSales(fashion_sales_id));
    }

    /**
     * 删除服装促销记录
     * @param fashion_sales_ids 字符串型主键数组
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String[] fashion_sales_ids) throws Exception {
        for(String fashion_sales_id:fashion_sales_ids){
            delete(fashion_sales_id);
        }
        return "1";
    }
}