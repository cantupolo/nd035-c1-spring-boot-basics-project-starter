package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid=#{userId}")
    public List<Note> getNotes(Integer userId);

    @Insert("INSERT INTO NOTES (id, title, description, userid)" +
            "VALUES (#{id}, #{title}, #{description}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public Integer insert(Note note);

    @Update("UPDATE NOTES SET title=#{title}, description=#{description}, userid=#{userId} WHERE id=#{id}")
    public void update(Note note);

    @Delete("DELETE FROM NOTES WHERE id=#{noteId}")
    public void delete(Integer noteId);
}
