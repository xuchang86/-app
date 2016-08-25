package com.group.lab.dao;

import com.group.lab.model.Venue;
import com.group.lab.model.VenueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VenueMapper {
    int countByExample(VenueExample example);

    int deleteByExample(VenueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Venue record);

    int insertSelective(Venue record);

    List<Venue> selectByExample(VenueExample example);

    Venue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Venue record, @Param("example") VenueExample example);

    int updateByExample(@Param("record") Venue record, @Param("example") VenueExample example);

    int updateByPrimaryKeySelective(Venue record);

    int updateByPrimaryKey(Venue record);
}