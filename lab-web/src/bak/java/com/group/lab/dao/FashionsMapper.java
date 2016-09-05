package com.group.lab.dao;

import com.group.lab.model.Fashions;
import com.group.lab.model.FashionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FashionsMapper {
    int countByExample(FashionsExample example);

    int deleteByExample(FashionsExample example);

    int deleteByPrimaryKey(String numIid);

    int insert(Fashions record);

    int insertSelective(Fashions record);

    List<Fashions> selectByExample(FashionsExample example);

    Fashions selectByPrimaryKey(String numIid);

    int updateByExampleSelective(@Param("record") Fashions record, @Param("example") FashionsExample example);

    int updateByExample(@Param("record") Fashions record, @Param("example") FashionsExample example);

    int updateByPrimaryKeySelective(Fashions record);

    int updateByPrimaryKey(Fashions record);
}