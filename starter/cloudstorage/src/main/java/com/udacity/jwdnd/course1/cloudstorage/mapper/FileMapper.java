package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE id=#{id}")
    public File getFile(Integer id);

    @Select("SELECT id FROM FILES WHERE filename=#{fileName}")
    public Integer getFileId(String fileName);

    @Select("SELECT id, userid, filename, contenttype, filesize FROM FILES WHERE userid=#{userId}")
    public List<File> getFilesWithoutContent(Integer userId);

    @Insert("INSERT INTO FILES (id, filename, contenttype, filesize, userid, filedata)" +
            "VALUES (#{id}, #{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public Integer insert(File file);

    @Delete("DELETE FROM FILES WHERE id=#{fileId}")
    public void delete(Integer fileId);
}
