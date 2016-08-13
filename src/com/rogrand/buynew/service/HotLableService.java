package com.rogrand.buynew.service;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.service.BaseService;
import com.rogrand.buynew.domain.HotLable;

/**
 * 版权：LAB <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-06 <br/>
 * 描述：热门标签 Service
 */
@Service("HotLableService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class HotLableService extends BaseService {
    /**
     * 查询热门标签对象
     * @param id 字符串型主键
     * @return HotLable
     * @throws Exception 异常
     */
    public HotLable query(String id) throws Exception{
    	PageParam param = new PageParam();
    	param.put("id", id);
        return sqlDao.query("lab_hot_lable.pageList", param);
    }

    /**
     * 查询热门标签对象集合
     * @param param 查询条件
     * @return List<HotLable>
     * @throws Exception 异常
     */
    public List<HotLable> list(PageParam param) throws Exception {
    	return sqlDao.list("lab_hot_lable.pageList",param);
    }
    
    /**
     * 热门标签翻页查询
     * @param 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
    public PageResult pageList(PageParam param) throws Exception {
    	param.setCountSql("lab_hot_lable.pageCount");
    	param.setRecordSql("lab_hot_lable.pageList");
        return pageService.pageQuery(param);
    }

    /**
     * 插入热门标签记录
     * @param hotLable 热门标签对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(HotLable hotLable) throws Exception  {
        sqlDao.create("lab_hot_lable.create",hotLable);
        return "1";
    }

    /**
     * 更新热门标签记录
     * @param hotLable 热门标签对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(HotLable hotLable) throws Exception {
        sqlDao.update("lab_hot_lable.update", hotLable);
        return "1";
    }

    /**
     * 删除热门标签记录
     * @param hotLable 热门标签对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(HotLable hotLable) throws Exception {
        sqlDao.delete("lab_hot_lable.delete", hotLable);
        return "1";
    }

    /**
     * 删除热门标签记录
     * @param id 字符串型主键
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String id) throws Exception {
        return delete(new HotLable(id));
    }

    /**
     * 删除热门标签记录
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