package com.example.coolshark.mapper;

import com.example.coolshark.entity.User;
import org.apache.ibatis.annotations.Select;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

    @Select("select * from User where username = #{username}")
    User selectByUsername(String username);
}
