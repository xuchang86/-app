package com.rogrand.core.domain;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2013-10-24 <br/>
 * 描述：翻页参数对象
 */
public class PageParam extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 2940669879993201874L;
	
	public List<String> propertyList;

    public List<String> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<String> propertyList) {
        this.propertyList = propertyList;
    }

    public String getCountSql() {
        return (String) get("countSql");
    }

    public void setCountSql(String countSql) {
        put("countSql", countSql);
    }

    public String getRecordSql() {
        return (String) get("recordSql");
    }

    public void setRecordSql(String recordSql) {
        put("recordSql", recordSql);
    }

    public String getDefaultSort() {
        return (String) get("defaultSort");
    }

    public void setDefaultSort(String defaultSort) {
        put("defaultSort", defaultSort);
    }

    public String getSort() {
        return (String) get("sort");
    }

    public void setSort(String sort) {
        put("sort", sort);
    }

    public String getOrder() {
        return (String) get("order");
    }

    public void setOrder(String order) {
        put("order", order);
    }

    public String getHead() {
        return (String) get("head");
    }

    public void setHead(String head) {
        put("head", head);
    }

    public String getFoot() {
        return (String) get("foot");
    }

    public void setFoot(String foot) {
        put("foot", foot);
    }

    public String getSortOrder() {
        return (String) get("sortOrder");
    }

    public void setSortOrder(String sortOrder) {
        put("sortOrder", sortOrder);
    }

    public String getRefresh() {
        return (String) get("refresh");
    }

    public void setRefresh(String refresh) {
        put("refresh", refresh);
    }

    public void setTime(String time) {
        put("time", time);
    }

    public String getTime() {
        return (String) get("time");
    }

    public Long getQueryTime() {
        Object queryTime = get("queryTime");
        if (queryTime == null) return (long) 0;
        try {
            return Long.parseLong(queryTime.toString());
        } catch (NumberFormatException e) {
            return (long) 0;
        }
    }

    public void setQueryTime(Long queryTime) {
        put("queryTime", queryTime);
    }

    public Long getPage() {
        Object page = get("page");
        Long intPage = 0l;
        if (page != null) {
            try {
                intPage = Long.parseLong(page.toString());
            } catch (NumberFormatException e) {
                intPage = 0l;
            }
        }
        Long totalPage = getTotalPage();
        intPage = totalPage <= 0 ? 0 : intPage < 1 ? 1 : intPage > totalPage ? totalPage : intPage;
        return intPage;
    }

    public void setPage(Long page) {
        put("page", page);
    }

    public Integer getRows() {
        Object rows = get("rows");
        if (rows == null) return 0;
        try {
            return Integer.parseInt(rows.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setRows(Integer rows) {
        put("rows", rows);
    }

    public Long getRecordCount() {
        Object count = get("recordCount");
        if (count == null) return 0l;
        try {
            return Long.parseLong(count.toString());
        } catch (NumberFormatException e) {
            return 0l;
        }
    }

    public void setRecordCount(Long recordCount) {
        put("recordCount", recordCount);
    }

    public Long getTotalPage() {
        Long count = this.getRecordCount();
        Integer rows = this.getRows();
        return count > 0 && rows > 0 ? (int) Math.ceil((double) count / (double) rows) : 0l;
    }

    public void setTotalPage(Long totalPage) {
        put("totalPage", totalPage);
    }

    public Long getBegin() {
        if (this.getRecordCount() == 0) return 0l;
        return 1 + (this.getPage() - 1) * this.getRows();
    }

    public void setBegin(Long begin) {
        put("begin", begin);
    }

    public Long getEnd() {
        if (this.getRecordCount() == 0) return 0l;
        Long end = this.getPage() * this.getRows();
        return (end > this.getRecordCount()) ? this.getRecordCount() : end;
    }

    public void setEnd(Long end) {
        put("end", end);
    }

    public void reset() {
        setRows(getRows());
        setPage(getPage());
        setRecordCount(getRecordCount());
        setTotalPage(getTotalPage());
        setBegin(getBegin());
        setEnd(getEnd());
        setQueryTime(getQueryTime());

        if (getSort() != null && getSort().length() > 0) {
            if (getOrder() == null || !getOrder().equals("asc") && !getOrder().equals("desc")) {
                setOrder("asc");
            }
        }

    }

    public String[] searchContents;

    public String[] getSearchContents() {
        return searchContents;
    }

    public void setSearchContents(String[] searchContents) {
        put("searchContents", searchContents);
        this.searchContents = searchContents;
    }

    public int getTotalPageIndex() {
        return getRecordCount() > 0 && getRows() > 0 ? (int) Math.ceil((double) getRecordCount() / (double) getRows()) : 0;
    }

    public void setTotalPageIndex(Integer totalPageIndex) {
        put("totalPageIndex", totalPageIndex);
    }

    public Long getStartPage() {
        if (getRecordCount() == 0) return 0l;
        if (getPage() - 10 > 0) return getPage() - 10;
        else return 1l;
    }

    public void setStartPage(Long startPage) {
        put("startPage", startPage);
    }

    public Long getEndPage() {
        if (getRecordCount() == 0) return 0l;
        if (getPage() + 9 > getTotalPage()) return getTotalPage();
        else return getPage() + 9;
    }

    public void setEndPage(Long endPage) {
        put("endPage", endPage);
    }

}
