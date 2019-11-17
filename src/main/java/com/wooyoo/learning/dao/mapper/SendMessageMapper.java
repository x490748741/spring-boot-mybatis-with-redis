package com.wooyoo.learning.dao.mapper;

import com.wooyoo.learning.dao.domain.Message;
import com.wooyoo.learning.dao.domain.SendMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SendMessageMapper {
    Long insert(@Param("sendMessage") SendMessage sendMessage);

    List<Map> selectNoSendAll();

    List<Map> selectByIsSend(@Param("isSend") Integer isSend,@Param("userName") String userName);

    Long updateById(@Param("id")Integer id);
}
