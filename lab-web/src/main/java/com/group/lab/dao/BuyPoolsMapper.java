package com.group.lab.dao;

import com.group.lab.model.BuyPools;
import com.group.lab.model.BuyPoolsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuyPoolsMapper {
    int countByExample(BuyPoolsExample example);

    int deleteByExample(BuyPoolsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BuyPools record);

    int insertSelective(BuyPools record);

    List<BuyPools> selectByExample(BuyPoolsExample example);

    BuyPools selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BuyPools record, @Param("example") BuyPoolsExample example);

    int updateByExample(@Param("record") BuyPools record, @Param("example") BuyPoolsExample example);

    int updateByPrimaryKeySelective(BuyPools record);

    int updateByPrimaryKey(BuyPools record);
}