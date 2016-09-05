package com.group.lab.dao;

import com.group.lab.model.BuynewActivity;
import com.group.lab.model.BuynewActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuynewActivityMapper {
    int countByExample(BuynewActivityExample example);

    int deleteByExample(BuynewActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BuynewActivity record);

    int insertSelective(BuynewActivity record);

    List<BuynewActivity> selectByExample(BuynewActivityExample example);

    BuynewActivity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BuynewActivity record, @Param("example") BuynewActivityExample example);

    int updateByExample(@Param("record") BuynewActivity record, @Param("example") BuynewActivityExample example);

    int updateByPrimaryKeySelective(BuynewActivity record);

    int updateByPrimaryKey(BuynewActivity record);
}