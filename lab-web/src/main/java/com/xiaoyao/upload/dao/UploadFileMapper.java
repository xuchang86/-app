package com.xiaoyao.upload.dao;

import com.xiaoyao.upload.model.UploadFile;
import com.xiaoyao.upload.model.UploadFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UploadFileMapper {
    int countByExample(UploadFileExample example);

    int deleteByExample(UploadFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UploadFile record);

    int insertSelective(UploadFile record);

    List<UploadFile> selectByExample(UploadFileExample example);

    UploadFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UploadFile record, @Param("example") UploadFileExample example);

    int updateByExample(@Param("record") UploadFile record, @Param("example") UploadFileExample example);

    int updateByPrimaryKeySelective(UploadFile record);

    int updateByPrimaryKey(UploadFile record);
}