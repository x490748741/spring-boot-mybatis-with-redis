package com.wooyoo.learning.dao.mapper;

import com.wooyoo.learning.dao.domain.Send;
import com.wooyoo.learning.dao.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SendMapper {
    void insertBatch(@Param("sends") List<Send> sends);

    List<Map> selectCopy();
}
