package com.rogrand.auto.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rogrand.auto.domain.Column;
import com.rogrand.auto.domain.Database;
import com.rogrand.auto.domain.Table;
import com.rogrand.core.service.BaseService;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：自动生成控制器的service实现方法
 */
@Service("buildService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = Throwable.class)
public class BuildService extends BaseService {
    
    public Database getStructure() throws Exception {
        BasicDataSource dataSource = (BasicDataSource)sqlDao.getDataSource();
        if(dataSource.getDriverClassName().contains("mysql")){
            return getMysqlStructure(dataSource);
        }
        else {
            throw new RuntimeException("不支持"+dataSource.getDriverClassName()+"数据库");
        }
    }

    private Database getMysqlStructure(BasicDataSource dataSource) throws Exception{
        Database database = new Database();
        database.setDriverClassName(dataSource.getDriverClassName());
        database.setUrl(dataSource.getUrl());
        database.setUsername(dataSource.getUsername());
        database.setPassword(dataSource.getPassword());
        List<Table> tableList = sqlDao.list("mysql.getTables");
        Properties comments = getComments();
        for (Table table : tableList) {
            List<Column> columnList = sqlDao.list("mysql.getColumns", table.getName());
            table.setName(table.getName().toLowerCase());
            setSaveComments(comments,table);
            for (Column column : columnList) {
                column.setName(column.getName().toLowerCase());
                setSaveComments(comments,table,column);
            }
            table.setColumnList(columnList);
        }
        database.setTableList(tableList);
        return database;
    }

    private void setSaveComments(Properties comments,Table table) {
        if (comments.containsKey(table.getName() + ".annotation")) {
            table.setAnnotation(comments.getProperty(table.getName() + ".annotation"));
        }
        if (comments.containsKey(table.getName() + ".subjectmodulename")) {
            table.setSubjectModuleName(comments.getProperty(table.getName() + ".subjectmodulename"));
        }
        if (comments.containsKey(table.getName() + ".buildcurd") && comments.getProperty(table.getName() + ".buildcurd").equals("true")) {
            table.setBuildCURD(true);
        }
    }

    private void setSaveComments(Properties comments, Table table, Column column) {
        if (comments.containsKey(table.getName() + ".column." + column.getName())) {
            column.setAnnotation(comments.getProperty(table.getName() + ".column." + column.getName()));
        }
    }

    private Properties getComments() throws SQLException, IOException {
        Properties properties = new Properties();
        File file = new File(this.servletContext.getRealPath("/WEB-INF/view/auto/comments.properties"));
        InputStream inputStream = null;
        if (file.exists()) {
            try {
                inputStream = new FileInputStream(file);
                properties.load(inputStream);
            }
            catch (IOException e) {
                logger.error(e.getMessage());
            }
            finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    }
                    catch (IOException e) {
                        logger.error(e.getMessage());
                    }
                }
            }
        }
/*

        String sql = "select name as key,resvd5 as comment from SYSDBA.SYSTABLES " +
                     "where resvd5 is not null " +
                     "union " +
                     "select a.name||'.'||b.name as key,b.resvd5 as comment from SYSDBA.SYSCOLUMNS b " +
                     "inner join SYSDBA.SYSTABLES a on a.id=b.id " +
                     "where b.resvd5 is not null";

        Statement statement = null;
        ResultSet rs =  null;
        try{
            statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            rs=  statement.executeQuery(sql);
            while (rs.next()){
                String key = rs.getString(1).toLowerCase();
                String value = rs.getString(2);
                if(!properties.containsKey(key)|| StringUtil.isEmpty(properties.getProperty(key))){
                    properties.setProperty(key,value);
                }
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                }
                catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            }
        }
*/

        return properties;
    }


    public static void main(String[] args) {

    }
  
}
