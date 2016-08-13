package com.rogrand.sys.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.service.BaseService;
import com.rogrand.core.system.SystemParameter;
import com.rogrand.sys.domain.Sysconfig;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xuan.zhou@rogrand.com <br/>
 * 生成日期：2013-12-17 <br/>
 * 描述：系统配置 Service
 */
@Service("SysconfigService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class SysconfigService extends BaseService {
    
    /**
     * 查询系统配置对象
     *
     * @param configid 字符串型主键
     * @return Sysconfig
     * @throws Exception 异常
     */
    public Sysconfig query(String configid) throws Exception {
        return query(new Sysconfig(configid));
    }
    
    /**
     * 查询系统配置对象
     *
     * @param sysconfig 系统配置对象
     * @return Sysconfig
     * @throws Exception 异常
     */
    public Sysconfig query(Sysconfig sysconfig) throws Exception {
        return sqlDao.query("sys_config.query", sysconfig);
    }
    
    /**
     * 查询系统配置对象集合
     *
     * @param sysconfig 系统配置对象
     * @return List
     * @throws Exception 异常
     */
    public List<Sysconfig> list(Sysconfig sysconfig) throws Exception {
        return sqlDao.list("sys_config.query", sysconfig);
    }
    
    /**
     * 查询系统配置对象集合
     *
     * @return List
     * @throws Exception 异常
     */
    public List<Sysconfig> list() throws Exception {
        return list(new Sysconfig());
    }
    
    /**
     * 系统配置翻页查询
     * @param sysconfig 分页条件对象
     * @return PageResult
     * @throws Exception 异常
     */
    public PageResult pageList(PageParam sysconfig) throws Exception {
        sysconfig.setCountSql("sys_config.pageCount");
        sysconfig.setRecordSql("sys_config.pageList");
        return pageService.pageQuery(sysconfig);
    }
    
    /**
     * 插入系统配置记录
     *
     * @param sysconfig 系统配置对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String create(Sysconfig sysconfig) throws Exception {
        sqlDao.create("sys_config.create", sysconfig);
        
        updateConfigCache(sysconfig);
        
        return "1";
    }
    
    /**
     * 更新系统配置记录
     *
     * @param sysconfig 系统配置对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String update(Sysconfig sysconfig) throws Exception {
        sqlDao.update("sys_config.update", sysconfig);
        
        updateConfigCache(sysconfig);
        
        return "1";
    }
    
    /**
     * 删除系统配置记录
     *
     * @param sysconfig 系统配置对象
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(Sysconfig sysconfig) throws Exception {
        removeConfigCache(sysconfig);
        
        sqlDao.delete("sys_config.delete", sysconfig);
        
        return "1";
    }
    
    /**
     * 删除系统配置记录
     *
     * @param configid 字符串型主键
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String configid) throws Exception {
        Sysconfig sysconfig = new Sysconfig(configid);
        
        removeConfigCache(sysconfig);
        
        return delete(sysconfig);
    }
    
    /**
     * 删除系统配置记录
     *
     * @param configids 字符串型主键数组
     * @return String
     * @throws Exception 异常
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String delete(String[] configids) throws Exception {
        for (String configid : configids) {
            delete(configid);
        }
        return "1";
    }
    
    /**
     * 通过可以查询系统配置对象
     *
     * @param sysconfig 系统配置对象
     * @return Sysconfig
     * @throws Exception 异常
     */
    public Sysconfig queryByKey(String configkey) throws Exception {
        return sqlDao.query("sys_config.queryByKey", configkey);
    }
    
    /**
     * 〈更新内存中的配置〉 <br/>
     * 
     * @param sysconfig
     * @throws Exception 
     */
    private void updateConfigCache(Sysconfig sysconfig) throws Exception {
        Long id = sysconfig.getConfigid();
        String key = sysconfig.getConfigkey();
        String value = sysconfig.getConfigvalue();
        if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
            SystemParameter.set(sysconfig.getConfigkey(), sysconfig.getConfigvalue());
        } else if (id != null) {
            Sysconfig record = query(String.valueOf(id));
            if (record != null) {
                SystemParameter.set(record.getConfigkey(), record.getConfigvalue());
            }
        }
    }
    
    /**
     * 〈移除内存中的配置〉 <br/>
     * 
     * @param sysconfig
     * @throws Exception 
     */
    private void removeConfigCache(Sysconfig sysconfig) throws Exception {
        Long id = sysconfig.getConfigid();
        String key = sysconfig.getConfigkey();
        if (StringUtils.isNotEmpty(key)) {
            SystemParameter.remove(sysconfig.getConfigkey());
        } else if (id != null) {
            Sysconfig record = query(String.valueOf(id));
            if (record != null) {
                SystemParameter.remove(record.getConfigkey());
            }
        }
    }
}
