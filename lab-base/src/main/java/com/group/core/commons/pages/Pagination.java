package com.group.core.commons.pages;

import java.util.List;

/**
 * 列表分页。包含list属性。
 */
public class Pagination<T> extends SimplePage implements java.io.Serializable, Paginable {

    private static final long serialVersionUID = 8785781442054258462L;

    public Pagination() {
    }
    
    /**
     * 构造器
     * 
     * @param pageNo 页码
     * @param pageSize 每页几条数据
     */
    public Pagination(int pageNo, int pageSize) {
        super(pageNo, pageSize);
    }

    /**
     * 构造器
     * 
     * @param pageNo 页码
     * @param pageSize 每页几条数据
     * @param totalCount 总共几条数据
     */
    public Pagination(int pageNo, int pageSize, int totalCount) {
        super(pageNo, pageSize, totalCount);
    }

    /**
     * 构造器
     * 
     * @param pageNo 页码
     * @param pageSize 每页几条数据
     * @param totalCount 总共几条数据
     * @param list 分页内容
     */
    public Pagination(int pageNo, int pageSize, int totalCount, List<T> list) {
        super(pageNo, pageSize, totalCount);
        this.list = list;
    }

    /**
     * 第一条数据位置
     * 
     * @return
     */
    public int getFirstResult() {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 当前页的数据
     */
    private List<T> list;

    /**
     * 获得分页内容
     * 
     * @return
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 设置分页内容
     * 
     * @param list
     */
    public void setList(List<T> list) {
        this.list = list;
    }
}
