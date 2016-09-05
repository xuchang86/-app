package com.group.lab.dao;

import com.group.lab.model.Fashion;
import com.group.lab.model.FashionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FashionMapper {
    int countByExample(FashionExample example);

    int deleteByExample(FashionExample example);

    int deleteByPrimaryKey(Integer fashionId);

    int insert(Fashion record);

    int insertSelective(Fashion record);

    List<Fashion> selectByExample(FashionExample example);

    Fashion selectByPrimaryKey(Integer fashionId);

    int updateByExampleSelective(@Param("record") Fashion record, @Param("example") FashionExample example);

    int updateByExample(@Param("record") Fashion record, @Param("example") FashionExample example);

    int updateByPrimaryKeySelective(Fashion record);

    int updateByPrimaryKey(Fashion record);
}