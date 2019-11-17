package com.wooyoo.learning.dao.mapper;

import com.wooyoo.learning.dao.domain.Mvalidate;
import com.wooyoo.learning.dao.domain.Send;
import com.wooyoo.learning.dao.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MvalidateMapper {
    void insertBatch(@Param("mvalidates") List<Map<String, Object>> mvalidates);
    void insert(@Param("mvalidate") Map<String, Object> mvalidate);
    void updateByName(@Param("name") String name);
    Mvalidate selectValidate(@Param("name") String name, @Param("validateCode") String validateCode);
    List<Mvalidate> selectValidateList(@Param("name") String name, @Param("validateCode") String validateCode);
    void truncateTable();
    void updateSelectList(@Param("mvalidates") List<Mvalidate> mvalidates);
    List<Mvalidate> selectAllList();
}
