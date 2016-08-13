package com.rogrand.core.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rogrand.core.dao.SqlDao;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.util.NumberUtil;
import com.rogrand.core.util.SQLUtil;
import com.rogrand.core.util.StringUtil;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：mysql数据库分页
 */
@Service("mysqlPageService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class MySqlPageService extends AbstractPageService{
    @Autowired
    @Qualifier("sqlDao")
    protected SqlDao sqlDao;

    public void setSqlDao(SqlDao sqlDao) {
        this.sqlDao = sqlDao;
    }


    /**
     * 分页查询
     * @param pageParam 查询条件
     * @return PageResult
     * @throws Exception 异常
     */
    @Override
	public PageResult pageQuery(PageParam pageParam) throws Exception {
        return pageQuery(pageParam,null);
    }

    /**
     * 分页查询
     * @param pageParam 查询条件
     * @param sortAlias 排序字段转换
     * @return PageResult
     * @throws Exception 异常
     */
    @Override
	public PageResult pageQuery(PageParam pageParam, Map<String,String> sortAlias) throws Exception {
        Date dateStart = new Date();
        PageResult pageResult = new PageResult();
        String refresh = pageParam.getRefresh();
        Long recordCount;

        if (refresh != null && (refresh.equals("1") || refresh.equals("2"))) {
            recordCount = NumberUtil.parseLong(sqlDao.query(pageParam.getCountSql(), pageParam), 0);
            pageParam.setRecordCount(recordCount);
        } else {
            if (pageParam.containsKey("recordCount")) {
                recordCount = pageParam.getRecordCount();
            } else {
                recordCount = sqlDao.query(pageParam.getCountSql(), pageParam);
                pageParam.setRecordCount(recordCount);
            }
        }
        if (pageParam.getRows() == 0) pageParam.setRows(20);
        if (recordCount > 0) {
            if (pageParam.getPage() == 0) pageParam.setPage(1l);
            Long skipResults = (pageParam.getPage() - 1) * pageParam.getRows();
            String foot = "limit " + skipResults + "," + pageParam.getRows();
            pageParam.setFoot(foot);
            String sortOrder = "";
            String columnSort = "";
            String defaultSort = "";
            if (!StringUtil.isEmpty(pageParam.getSort())) {
                columnSort = getSortAlias(sortAlias, SQLUtil.escapeSql(pageParam.getSort()));
                sortOrder += "," + columnSort;
                if (!StringUtil.isEmpty(pageParam.getOrder()) &&
                        (pageParam.getOrder().equalsIgnoreCase("asc") || pageParam.getOrder().equalsIgnoreCase("desc"))) {
                    sortOrder += " " + pageParam.getOrder();
                }
            }

            else if (!StringUtil.isEmpty(pageParam.getDefaultSort())) {
                defaultSort = getSortAlias(sortAlias, SQLUtil.escapeSql(pageParam.getDefaultSort()));
                if (!repeatSort(columnSort, defaultSort)) {
                    sortOrder += "," + defaultSort;
                }
            }

            if (sortOrder.length() > 0) {
                sortOrder = "order by " + sortOrder.substring(1);
            }
            pageParam.setSortOrder(sortOrder);
            pageResult.setPageList(sqlDao.list(pageParam.getRecordSql(), pageParam));
        } else {
            pageParam.setPage(0l);
        }

        pageParam.remove("head");
        pageParam.remove("foot");
        pageParam.remove("refresh");
        pageParam.remove("sortOrder");
        Date dateEnd = new Date();
        pageParam.setQueryTime(dateEnd.getTime()-dateStart.getTime());
        pageParam.reset();
        pageResult.setPageParam(pageParam);
        logger.info("查询时间："+pageParam.getQueryTime()+"毫秒");
        return pageResult;

    }


}
