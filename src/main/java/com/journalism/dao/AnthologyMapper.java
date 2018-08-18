package com.journalism.dao;

import com.journalism.pojo.Anthology;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnthologyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Anthology record);

    int insertSelective(Anthology record);

    Anthology selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Anthology record);

    int updateByPrimaryKey(Anthology record);

    List<Anthology> getAnthologyList(@Param("authorId") Integer authorId);

    int deleteByAuthorIdAndAnthologyId(@Param("authorId") Integer authorId,@Param("anthologyId") Integer anthologyId);
}