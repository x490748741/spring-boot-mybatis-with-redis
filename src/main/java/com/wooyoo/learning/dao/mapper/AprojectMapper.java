package com.wooyoo.learning.dao.mapper;

import com.wooyoo.learning.dao.domain.Aonetype;
import com.wooyoo.learning.dao.domain.Aproject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AprojectMapper {
    List<Map> selectAll();
    Long insert(@Param("aproject")Aproject aproject);
    Long update(@Param("aproject")Aproject aproject);
    void deleteByPid(Long id);
}
