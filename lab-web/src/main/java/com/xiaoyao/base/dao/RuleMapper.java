package com.xiaoyao.base.dao;

import com.xiaoyao.base.model.Rule;
import com.xiaoyao.base.model.RuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RuleMapper {
    int countByExample(RuleExample example);

    int deleteByExample(RuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Rule record);

    int insertSelective(Rule record);

    List<Rule> selectByExample(RuleExample example);

    Rule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Rule record, @Param("example") RuleExample example);

    int updateByExample(@Param("record") Rule record, @Param("example") RuleExample example);

    int updateByPrimaryKeySelective(Rule record);

    int updateByPrimaryKey(Rule record);
}