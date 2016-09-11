package com.xiaoyao.mall.dao;

import com.xiaoyao.mall.model.GoodsOrder;
import com.xiaoyao.mall.model.GoodsOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsOrderMapper {
    int countByExample(GoodsOrderExample example);

    int deleteByExample(GoodsOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsOrder record);

    int insertSelective(GoodsOrder record);

    List<GoodsOrder> selectByExample(GoodsOrderExample example);

    GoodsOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsOrder record, @Param("example") GoodsOrderExample example);

    int updateByExample(@Param("record") GoodsOrder record, @Param("example") GoodsOrderExample example);

    int updateByPrimaryKeySelective(GoodsOrder record);

    int updateByPrimaryKey(GoodsOrder record);
}