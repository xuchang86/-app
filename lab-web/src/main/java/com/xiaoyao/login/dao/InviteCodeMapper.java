package com.xiaoyao.login.dao;

import com.xiaoyao.login.model.InviteCode;
import com.xiaoyao.login.model.InviteCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InviteCodeMapper {
    int countByExample(InviteCodeExample example);

    int deleteByExample(InviteCodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InviteCode record);

    int insertSelective(InviteCode record);

    List<InviteCode> selectByExample(InviteCodeExample example);

    InviteCode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InviteCode record, @Param("example") InviteCodeExample example);

    int updateByExample(@Param("record") InviteCode record, @Param("example") InviteCodeExample example);

    int updateByPrimaryKeySelective(InviteCode record);

    int updateByPrimaryKey(InviteCode record);
}