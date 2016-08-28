package com.xiaoyao.activity.dao;

import com.xiaoyao.activity.model.ActivityPerson;
import com.xiaoyao.activity.model.ActivityPersonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityPersonMapper {
    int countByExample(ActivityPersonExample example);

    int deleteByExample(ActivityPersonExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityPerson record);

    int insertSelective(ActivityPerson record);

    List<ActivityPerson> selectByExample(ActivityPersonExample example);

    ActivityPerson selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityPerson record, @Param("example") ActivityPersonExample example);

    int updateByExample(@Param("record") ActivityPerson record, @Param("example") ActivityPersonExample example);

    int updateByPrimaryKeySelective(ActivityPerson record);

    int updateByPrimaryKey(ActivityPerson record);
}