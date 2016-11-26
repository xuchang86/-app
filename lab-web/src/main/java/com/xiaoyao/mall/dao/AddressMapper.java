package com.xiaoyao.mall.dao;

import com.xiaoyao.base.dao.BaseMapper;
import com.xiaoyao.mall.model.Address;
import com.xiaoyao.mall.model.AddressExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AddressMapper extends BaseMapper{
    int countByExample(AddressExample example);

    int deleteByExample(AddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    List<Address> selectByExample(AddressExample example);

    Address selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByExample(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);
}