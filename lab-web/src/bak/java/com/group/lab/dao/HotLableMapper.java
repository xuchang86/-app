package com.group.lab.dao;

import com.group.lab.model.HotLable;
import com.group.lab.model.HotLableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HotLableMapper {
    int countByExample(HotLableExample example);

    int deleteByExample(HotLableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HotLable record);

    int insertSelective(HotLable record);

    List<HotLable> selectByExample(HotLableExample example);

    HotLable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HotLable record, @Param("example") HotLableExample example);

    int updateByExample(@Param("record") HotLable record, @Param("example") HotLableExample example);

    int updateByPrimaryKeySelective(HotLable record);

    int updateByPrimaryKey(HotLable record);
}