package com.xiaoyao.pay.dao;

import com.xiaoyao.pay.model.TransferRecord;
import com.xiaoyao.pay.model.TransferRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransferRecordMapper {
    int countByExample(TransferRecordExample example);

    int deleteByExample(TransferRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TransferRecord record);

    int insertSelective(TransferRecord record);

    List<TransferRecord> selectByExample(TransferRecordExample example);

    TransferRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TransferRecord record, @Param("example") TransferRecordExample example);

    int updateByExample(@Param("record") TransferRecord record, @Param("example") TransferRecordExample example);

    int updateByPrimaryKeySelective(TransferRecord record);

    int updateByPrimaryKey(TransferRecord record);
}