package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT id, username, salt, password, firstname, lastname FROM USERS WHERE username=#{userName}")
    public User getUser(String userName);

    @Insert("INSERT INTO USERS (id, username, salt, password, firstname, lastname) " +
            "VALUES (#{id}, #{userName}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public Integer insert(User user);
}
