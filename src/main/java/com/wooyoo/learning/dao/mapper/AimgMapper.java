package com.wooyoo.learning.dao.mapper;

import com.wooyoo.learning.dao.domain.Aimg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AimgMapper {
    Aimg selectByCode(@Param("code") String code);

    void insert(@Param("aimg") Aimg aimg);
}
