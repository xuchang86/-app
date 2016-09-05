package com.xiaoyao.pay.dao;

import com.xiaoyao.pay.model.CashPool;
import com.xiaoyao.pay.model.CashPoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CashPoolMapper {
    int countByExample(CashPoolExample example);

    int deleteByExample(CashPoolExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CashPool record);

    int insertSelective(CashPool record);

    List<CashPool> selectByExample(CashPoolExample example);

    CashPool selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CashPool record, @Param("example") CashPoolExample example);

    int updateByExample(@Param("record") CashPool record, @Param("example") CashPoolExample example);

    int updateByPrimaryKeySelective(CashPool record);

    int updateByPrimaryKey(CashPool record);
}