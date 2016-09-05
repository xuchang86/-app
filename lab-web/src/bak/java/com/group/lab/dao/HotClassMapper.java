package com.group.lab.dao;

import com.group.lab.model.HotClass;
import com.group.lab.model.HotClassExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface HotClassMapper {
    int countByExample(HotClassExample example);

    int deleteByExample(HotClassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HotClass record);

    int insertSelective(HotClass record);

    List<HotClass> selectByExample(HotClassExample example);

    HotClass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HotClass record, @Param("example") HotClassExample example);

    int updateByExample(@Param("record") HotClass record, @Param("example") HotClassExample example);

    int updateByPrimaryKeySelective(HotClass record);

    int updateByPrimaryKey(HotClass record);
    
    
    /**
     * 获取大分类下的热门品类
     * @param parentId
     * @return
     */
    List<Map<String, Object>> selectHotClass(@Param("parentName") String parentName);
}