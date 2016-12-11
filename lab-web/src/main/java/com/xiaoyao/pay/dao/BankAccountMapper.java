package com.xiaoyao.pay.dao;

import com.xiaoyao.pay.model.BankAccount;
import com.xiaoyao.pay.model.BankAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BankAccountMapper {
    int countByExample(BankAccountExample example);

    int deleteByExample(BankAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BankAccount record);

    int insertSelective(BankAccount record);

    List<BankAccount> selectByExample(BankAccountExample example);

    BankAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BankAccount record, @Param("example") BankAccountExample example);

    int updateByExample(@Param("record") BankAccount record, @Param("example") BankAccountExample example);

    int updateByPrimaryKeySelective(BankAccount record);

    int updateByPrimaryKey(BankAccount record);
}