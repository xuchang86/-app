package com.rogrand.core.domain;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：数据库中的table数量，和table的名称
 */
public class TableCount {
    private String table_name;
    private Integer record_count;

    public TableCount() {
    }

    public TableCount(String table_name, Integer record_count) {
        this.table_name = table_name;
        this.record_count = record_count;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public Integer getRecord_count() {
        return record_count;
    }

    public void setRecord_count(Integer record_count) {
        this.record_count = record_count;
    }
}
