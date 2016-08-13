package com.rogrand.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import com.rogrand.sys.domain.App;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：应用系统表管理逻辑
 */
@Service("sysAppService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class AppService extends BaseService {
    /**
     * 查询应用系统表对象
     *
     * @param pk 字符串型主键
     * @return App
     * @throws Exception 异常
     */
    public App query(String pk) throws Exception{
        return query(new App(pk));
    }

    /**
     * 查询应用系统表对象
     *
     * @param pk long型主键
     * @return App
     * @throws Exception 异常
     */
    public App query(Long pk) throws Exception{
        return query(new App(pk));
    }


    /**
     * 查询应用系统表对象
     *
     * @param param 应用系统表对象
     * @return App
     * @throws Exception 异常
     */
    public App query(App param) throws Exception{
        return sqlDao.query("sys_app.query",param);
    }

    /**
     * 查询应用系统表对象集合
     *
     * @param param 应用系统表对象
     * @return List
     * @throws Exception 异常
     */
    public List<App> list(App param) throws Exception {
        return sqlDao.list("sys_app.query",param);
    }


    /**
     * 应用系统表翻页查询
     * @param param 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
    public PageResult pageList(PageParam param) throws Exception {
        param.setCountSql("sys_app.pageCount");
        param.setRecordSql("sys_app.pageList");
        return pageService.pageQuery(param);
    }

    /**
     * 插入应用系统表记录
     *
     * @param param 应用系统表对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(App param) throws Exception  {
        sqlDao.create("sys_app.create",param);
        return "1";
    }

    /**
     * 更新应用系统表记录
     *
     * @param param 应用系统表对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(App param) throws Exception {
        sqlDao.update("sys_app.update", param);
        return "1";
    }

    /**
     * 删除应用系统表记录
     *
     * @param param 应用系统表对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(App param) throws Exception {
        sqlDao.delete("sys_app.delete", param);
        return "1";
    }


    /**
     * 删除应用系统表记录
     *
     * @param pk 字符串型主键
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String pk) throws Exception {
        return delete(new App(pk));
    }


    /**
     * 删除应用系统表记录
     *
     * @param pks 字符串型主键数组
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String[] pks) throws Exception {
        for(String pk:pks){
            delete(pk);
        }
        return "1";
    }


}