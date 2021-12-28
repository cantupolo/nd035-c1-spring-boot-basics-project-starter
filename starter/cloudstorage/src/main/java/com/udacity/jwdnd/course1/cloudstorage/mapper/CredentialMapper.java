package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE id=#{id}")
    public Credential getCredential(Integer id);

    @Select("SELECT * FROM CREDENTIALS WHERE userid=#{userId}")
    public List<Credential> getCredentials(Integer userId);

    @Insert("INSERT INTO CREDENTIALS (id, url, username, key, password, userid)" +
            "VALUES (#{id}, #{url}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public Integer insert(Credential credential);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{userName}, key=#{key}, password=#{password}, userid=#{userId} WHERE id=#{id}")
    public void update(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE id=#{credentialId}")
    public void delete(Integer credentialId);
}
