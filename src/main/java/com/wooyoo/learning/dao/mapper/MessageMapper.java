package com.wooyoo.learning.dao.mapper;

import com.wooyoo.learning.dao.domain.Message;
import com.wooyoo.learning.dao.domain.Send;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {
    Long insert(@Param("message") Message message);
}
