package com.group.lab.dao;

import com.group.lab.model.FashionClassify;
import com.group.lab.model.FashionClassifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FashionClassifyMapper {
    int countByExample(FashionClassifyExample example);

    int deleteByExample(FashionClassifyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FashionClassify record);

    int insertSelective(FashionClassify record);

    List<FashionClassify> selectByExample(FashionClassifyExample example);

    FashionClassify selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FashionClassify record, @Param("example") FashionClassifyExample example);

    int updateByExample(@Param("record") FashionClassify record, @Param("example") FashionClassifyExample example);

    int updateByPrimaryKeySelective(FashionClassify record);

    int updateByPrimaryKey(FashionClassify record);
}