package com.journalism.dao;

import com.journalism.pojo.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    int updatePublishTime(Integer articleId);

    List<Article> getArticleListByAuthorIdAndAnthologyId(@Param("authorId") Integer authorId,@Param("anthologyId") Integer anthologyId);

    List<Article> getArticleListByAuthorId(@Param("authorId")Integer authorId);

    List<Article> getAllList(@Param("status")Integer status);

    List<Article> getArticleByTypeId(@Param("typeId")Integer typeId,@Param("status")Integer status);

    List<Article> getCheckList(@Param("status")int status);

    int deleteByAuthorIdAndArticleId(@Param("authorId")Integer authorId,@Param("articleId")Integer articleId);
}