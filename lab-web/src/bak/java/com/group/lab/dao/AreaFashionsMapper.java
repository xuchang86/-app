package com.group.lab.dao;

import com.group.lab.model.AreaFashions;
import com.group.lab.model.AreaFashionsExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AreaFashionsMapper {
    int countByExample(AreaFashionsExample example);

    int deleteByExample(AreaFashionsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AreaFashions record);

    int insertSelective(AreaFashions record);

    List<AreaFashions> selectByExample(AreaFashionsExample example);

    AreaFashions selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AreaFashions record, @Param("example") AreaFashionsExample example);

    int updateByExample(@Param("record") AreaFashions record, @Param("example") AreaFashionsExample example);

    int updateByPrimaryKeySelective(AreaFashions record);

    int updateByPrimaryKey(AreaFashions record);
    
    
    /**
     * 查询品牌馆下的服装
     * @param venue
     * @return
     */
    List<String> selectVenueFashions(@Param("venueId") Integer venueId, @Param("start") int start, @Param("limit") int limit);
    
    /**
     * 查询品牌以及下面的首件服装
     * @param brandIds
     * @return
     */
    List<Map<String, Object>> selectBrandFirstFashion(@Param("venueId") Integer venueId, @Param("start") int start, @Param("limit") int limit);
}