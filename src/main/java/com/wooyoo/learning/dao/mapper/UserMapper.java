package com.wooyoo.learning.dao.mapper;

import com.wooyoo.learning.dao.domain.Product;
import com.wooyoo.learning.dao.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    User selectByLogin(@Param("userName")String userName, @Param("pwd")String pwd);
    User selectByUserName(@Param("userName")String userName);
    List<Map> selectAll();
    void updateBalance(@Param("userName")String userName,@Param("balance")Double balance);
}
