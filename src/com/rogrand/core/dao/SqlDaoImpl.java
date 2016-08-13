package com.rogrand.core.dao;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.mapping.parameter.ParameterMap;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：数据访问对象的实现
 */
@SuppressWarnings({"unchecked"})
@Repository("sqlDao")
public class SqlDaoImpl extends SqlMapClientTemplate implements SqlDao {
    private Log logger = LogFactory.getLog(getClass());
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
	@Autowired
    public void setSqlMapClient(@Qualifier("sqlMapClient") SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

    @Override
	@Autowired
    public void setDataSource(@Qualifier("dataSource") DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    /**
     * 以Map的形式返回结果集
     *
     * @param sqlID       sql语句id
     * @param object      查询参数
     * @param keyProperty map的key值，对应于结果集中的字段名
     * @return Map
     * @throws org.springframework.dao.DataAccessException
     *
     */
    @SuppressWarnings("rawtypes")
    public Map query(String sqlID, Object object, String keyProperty) throws DataAccessException {
        printSql(sqlID, object);
        return super.queryForMap(sqlID, object, keyProperty);
    }

    /**
     * 以Map的形式返回结果集
     *
     * @param sqlID         sql语句id
     * @param object        查询参数
     * @param keyProperty   map的key值，对应于结果集中的字段名
     * @param valueProperty map的value值，对应于结果集中的字段名
     * @return Map
     * @throws org.springframework.dao.DataAccessException
     *
     */
    @SuppressWarnings("rawtypes")
    public Map query(String sqlID, Object object, String keyProperty, String valueProperty) throws DataAccessException {
        printSql(sqlID, object);
        return super.queryForMap(sqlID, object, keyProperty, valueProperty);
    }

    /**
     * 获得单个记录
     *
     * @param sqlID  sql语句id
     * @param object 查询参数
     * @return Object
     * @throws DataAccessException 数据库访问异常
     */
    public <T> T query(String sqlID, Object object) throws DataAccessException {
        printSql(sqlID, object);
        return (T) super.queryForObject(sqlID, object);
    }

    /**
     * 获得记录集
     *
     * @param sqlID  sql语句id
     * @param object 查询参数
     * @return List
     * @throws DataAccessException 数据库访问异常
     */
    public <T> List<T> list(String sqlID, Object object) throws DataAccessException {
        printSql(sqlID, object);
        return super.queryForList(sqlID, object);
    }


    /**
     * 获得记录集
     * @param sqlID sql语句id
     * @param object 查询参数
     * @param skipResults 跳过的记录数
     * @param maxResults 返回的记录数
     * @return list
     * @throws DataAccessException 数据库访问异常
     */
    public <T> List<T> list(String sqlID, Object object, int skipResults, int maxResults) throws DataAccessException {
        printSql(sqlID, object);
        return super.queryForList(sqlID, object, skipResults, maxResults);
    }

    /**
     * 获得单个记录（无参数）
     *
     * @param sqlID sql语句id
     * @return Object
     * @throws org.springframework.dao.DataAccessException
     *          数据库访问异常
     */
    public <T> T query(String sqlID) throws DataAccessException {
        return (T) query(sqlID, null);
    }

    /**
     * 获得记录集（无参数）
     *
     * @param sqlID sql语句id
     * @return List
     * @throws org.springframework.dao.DataAccessException
     *          数据库访问异常
     */
    public <T> List<T> list(String sqlID) throws DataAccessException {
        return list(sqlID, null);
    }

    /**
     * 插入记录
     *
     * @param sqlID  sql语句id
     * @param object 插入对象
     * @return object 返回自动生成的主键
     * @throws DataAccessException 数据库访问异常
     */
    public Object create(String sqlID, Object object) throws DataAccessException {
        printSql(sqlID, object);
        return super.insert(sqlID, object);
    }

    /**
     * 更新记录
     *
     *
     * @param sqlID  sql语句id
     * @param object 更新对象
     * @return int 返回更新记录数
     * @throws DataAccessException 数据库访问异常
     */
    @Override
	public int update(String sqlID, Object object) throws DataAccessException {
        printSql(sqlID, object);
        return super.update(sqlID, object);
    }

    /**
     * 删除记录
     *
     * @param sqlID  sql语句id
     * @param object 删除对象
     * @return int 删除影响的记录数
     * @throws DataAccessException 数据库访问异常
     */
    @Override
	public int delete(String sqlID, Object object) throws DataAccessException {
        printSql(sqlID, object);
        return super.delete(sqlID, object);
    }


    /**
     * 打印sql执行语句
     *
     * @param sqlID     sql语句id
     * @param parameter 参数对象
     */
    private void printSql(String sqlID, Object parameter) {

        if (logger.isInfoEnabled()) {
            SqlMapClientImpl sqlMapClient = (SqlMapClientImpl) super.getSqlMapClient();

            MappedStatement statement = sqlMapClient.getMappedStatement(sqlID);
            StatementScope scope = new StatementScope(new SessionScope());
            statement.initRequest(scope);
            Sql sql = statement.getSql();
            String strSql = sql.getSql(scope, parameter);
            ParameterMap parameterMap = sql.getParameterMap(scope, parameter);
            Object[] objects = parameterMap.getParameterObjectValues(scope, parameter);
            logger.info(sqlID + ":\n " + strSql);
            String param = "";
            String value = "";
            if (objects != null) {
                for (int i = 0; i < objects.length; i++) {
                    if (objects[i] == null) {
                        value = "null";
                    } else if (objects[i] instanceof java.util.Date) {
                        value = dateFormat.format(objects[i]);
                    } else {
                        value = objects[i].toString();
                    }
                    param += "参数" + (i + 1) + ":" + value + "\n";
                }
            }
            logger.info(param);
        }
    }

    public Connection getConnection() {
        return DataSourceUtils.getConnection(this.getDataSource());
    }

    public void releaseConnection(Connection connection) {
        DataSourceUtils.releaseConnection(connection, this.getDataSource());
    }


}
