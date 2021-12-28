package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE id=#{id}")
    public File getFile(Integer id);

    @Select("SELECT * FROM FILES WHERE userid=#{userId}")
    public List<File> getFiles(Integer userId);

    @Select("SELECT id, userid, filename, contenttype, filesize FROM FILES WHERE userid=#{userId}")
    public List<File> getFilesWithoutContent(Integer userId);

    @Select("SELECT filename FROM FILES WHERE userid=#{userId}")
    public List<String> getFileNames(Integer userId);

    @Insert("INSERT INTO FILES (id, filename, contenttype, filesize, userid, filedata)" +
            "VALUES (#{id}, #{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public Integer insert(File file);

//    @Update("UPDATE FILES SET title=#{title}, description=#{description}, userid=#{userId} WHERE id=#{id}")
//    public void update(File file);

    @Delete("DELETE FROM FILES WHERE id=#{fileId}")
    public void delete(Integer fileId);
}
