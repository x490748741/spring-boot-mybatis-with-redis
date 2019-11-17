package com.wooyoo.learning.dao.mapper;

import com.wooyoo.learning.dao.domain.Aproject;
import com.wooyoo.learning.dao.domain.AprojectDesign;
import com.wooyoo.learning.dao.domain.Send;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AprojectDesignMapper {
    List<Map> selectByPid(String aprojectId);

    void insertBatch(@Param("aprojectDesigns") List<AprojectDesign> aprojectDesigns);

    void truncateTableByPid(Long aprojectId);
}
