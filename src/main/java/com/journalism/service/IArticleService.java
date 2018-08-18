package com.journalism.service;

import com.journalism.common.ServerResponse;
import com.journalism.pojo.Article;
import com.journalism.vo.ArticleItemVO;
import com.journalism.vo.ArticleVO;

public interface IArticleService {

    ServerResponse saveOrUpdateArticle(Article article);

    ServerResponse<String> setIssueStatus(Integer articleId,Integer status);

    ServerResponse<String> reCall(Integer articleId,Integer status);

    ServerResponse<ArticleItemVO> getArticle(Integer articleId);

    ServerResponse<ArticleVO> getArticleDetails(Integer articleId);

    ServerResponse getArticleList(Integer authorId,Integer anthologyId,int pageNum,int pageSize);

    ServerResponse getArticleList(Integer authorId,int pageNum,int pageSize);

    ServerResponse getArticleList(int pageNum,int pageSize);

    ServerResponse getArticleTypeList(Integer typeId,int pageNum,int pageSize);

    ServerResponse getApplyList(int pageNum,int pageSize);

    ServerResponse deleteArticle(Integer authorId,Integer articleId);

}
