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
import com.rogrand.buynew.domain.HotClass;

/**
 * 版权：小月科技 <br/>
 * 作者：dailing <br/>
 * 生成日期：2016-08-02 <br/>
 * 描述：热门品类 Service
 */
@Service("HotClassService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class HotClassService extends BaseService {
    /**
     * 查询热门品类对象
     * @param id 字符串型主键
     * @return HotClass
     * @throws Exception 异常
     */
    public HotClass query(String id) throws Exception{
    	PageParam param = new PageParam();
    	param.put("id", id);
        return sqlDao.query("lab_hot_class.pageList", param);
    }

    /**
     * 查询热门品类对象集合
     * @param param 查询条件
     * @return List<HotClass>
     * @throws Exception 异常
     */
    public List<HotClass> list(PageParam param) throws Exception {
    	return sqlDao.list("lab_hot_class.pageList",param);
    }
    
    /**
     * 热门品类翻页查询
     * @param 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
    public PageResult pageList(PageParam param) throws Exception {
    	param.setCountSql("lab_hot_class.pageCount");
    	param.setRecordSql("lab_hot_class.pageList");
        return pageService.pageQuery(param);
    }

    /**
     * 插入热门品类记录
     * @param hotClass 热门品类对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(HotClass hotClass, User user) throws Exception  {
    	hotClass.setCreate_user(user.getSu_code());
    	hotClass.setCreate_time(new Date());
    	hotClass.setStick_time(new Date());
        sqlDao.create("lab_hot_class.create",hotClass);
        return "1";
    }

    /**
     * 更新热门品类记录
     * @param hotClass 热门品类对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(HotClass hotClass, User user) throws Exception {
    	hotClass.setUpdate_user(user.getSu_code());
    	hotClass.setUpdate_time(new Date());
        sqlDao.update("lab_hot_class.update", hotClass);
        return "1";
    }

    /**
     * 删除热门品类记录
     * @param hotClass 热门品类对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(HotClass hotClass) throws Exception {
        sqlDao.delete("lab_hot_class.delete", hotClass);
        return "1";
    }

    /**
     * 删除热门品类记录
     * @param id 字符串型主键
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String id) throws Exception {
        return delete(new HotClass(id));
    }

    /**
     * 删除热门品类记录
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