package com.wooyoo.learning.dao.mapper;

import com.wooyoo.learning.dao.domain.Aonetype;
import com.wooyoo.learning.dao.domain.Atwotype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AtwotypeMapper {
    List<Map> selectByOcode(@Param("ocode") String ocode);
}
