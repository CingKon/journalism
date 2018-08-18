package com.journalism.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.journalism.common.Const;
import com.journalism.common.ResponseCode;
import com.journalism.common.ServerResponse;
import com.journalism.dao.ArticleMapper;
import com.journalism.dao.TypeMapper;
import com.journalism.dao.UserMapper;
import com.journalism.pojo.Article;
import com.journalism.service.IArticleService;
import com.journalism.vo.ArticleItemVO;
import com.journalism.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iArticleService")
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TypeMapper typeMapper;

    public ServerResponse saveOrUpdateArticle(Article article){
        if (article != null){
            if (article.getId() != null){
                int rowCount = articleMapper.updateByPrimaryKeySelective(article);
                if (rowCount > 0)
                    return ServerResponse.createBySuccessMessage("文章更新成功");
                return ServerResponse.createByErrorMessage("文章更新失败");
            }else {
                int rowCount = articleMapper.insert(article);
                if (rowCount > 0)
                    return ServerResponse.createBySuccessMessage("新增文章成功");
                return ServerResponse.createByErrorMessage("新增文章失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新文章的参数错误！");
    }

    /**
     *
     * @param articleId
     * @param status
     * @return
     */
    public ServerResponse<String> setIssueStatus(Integer articleId,Integer status){
        if (articleId == null || status == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        Article article = new Article();
        article.setId(articleId);
        article.setStatus(status);
        if (status.intValue() == Const.ArticleStatus.PUBLISHED.getCode()) {
            int updateCount = articleMapper.updatePublishTime(articleId);
            if (updateCount > 0){
                int rowCount = articleMapper.updateByPrimaryKeySelective(article);
                if (rowCount > 0)
                    return ServerResponse.createBySuccessMessage("修改文章状态成功");
            }
        }
        return ServerResponse.createByErrorMessage("修改文章状态失败");
    }

    /**
     * 将已发布的文章进行撤回，设置为未发布状态
     * @param articleId
     * @param status
     * @return
     */
    public ServerResponse<String> reCall(Integer articleId,Integer status){
        if (articleId == null || status == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        Article article = new Article();
        article.setId(articleId);
        article.setStatus(Const.ArticleStatus.UNPUBLISHED.getCode());
        int rowCount = articleMapper.updateByPrimaryKeySelective(article);
        if (rowCount > 0)
            return ServerResponse.createBySuccessMessage("文章撤回成功");
        return ServerResponse.createByErrorMessage("文章撤回失败");
    }

    //游客浏览新闻能看到的新闻信息
    public ServerResponse<ArticleItemVO> getArticle(Integer articleId){
        if (articleId == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        Article article = articleMapper.selectByPrimaryKey(articleId);
        if (article == null)
            return ServerResponse.createByErrorMessage("文章已被删除！");
        ArticleItemVO articleItemVO = getArticleItemVO(article);
        return ServerResponse.createBySuccess(articleItemVO);
    }

    //作者以及经理能看见的详细信息
    public ServerResponse<ArticleVO> getArticleDetails(Integer articleId){
        if (articleId == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        Article article = articleMapper.selectByPrimaryKey(articleId);
        if (article == null)
            return ServerResponse.createByErrorMessage("文章已被删除！");
        ArticleVO articleVO = getArticleVO(article);
        return ServerResponse.createBySuccess(articleVO);
    }

    private ArticleItemVO getArticleItemVO(Article article){
        ArticleItemVO articleItemVO = new ArticleItemVO();
        articleItemVO.setArticleId(article.getId());
        articleItemVO.setTitle(article.getTitle());
        articleItemVO.setSubTitle(article.getSubTitle());
        articleItemVO.setPublishTime(article.getPublishTime());
        articleItemVO.setAuthorName(userMapper.selectByPrimaryKey(article.getAuthorId()).getName());
        articleItemVO.setText(article.getText());
        articleItemVO.setFormat(article.getFormat());
        articleItemVO.setSubImages(article.getSubImages());
        articleItemVO.setTypeName(typeMapper.selectByPrimaryKey(article.getTypeId()).getName());
        return articleItemVO;
    }
    
    private ArticleVO getArticleVO(Article article){
        ArticleVO ArticleVO = new ArticleVO();
        ArticleVO.setArticleId(article.getId());
        ArticleVO.setTitle(article.getTitle());
        ArticleVO.setSubTitle(article.getSubTitle());
        ArticleVO.setPublishTime(article.getPublishTime());
        ArticleVO.setAuthorName(userMapper.selectByPrimaryKey(article.getAuthorId()).getName());
        ArticleVO.setText(article.getText());
        ArticleVO.setFormat(article.getFormat());
        ArticleVO.setSubImages(article.getSubImages());
        ArticleVO.setTypeName(typeMapper.selectByPrimaryKey(article.getTypeId()).getName());
        ArticleVO.setStatus(article.getStatus());
        ArticleVO.setCreateTime(article.getCreateTime());
        return ArticleVO;
    }

    //查看个人文章，按文集
    public ServerResponse getArticleList(Integer authorId,Integer anthologyId,int pageNum,int pageSize){
        if (authorId == null || anthologyId == null)
            return ServerResponse.createByErrorMessage("未登录或未选中文集！");
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articleList = articleMapper.getArticleListByAuthorIdAndAnthologyId(authorId,anthologyId);
        List<ArticleVO> articleVOList = Lists.newArrayList();
        for (Article article : articleList){
            ArticleVO articleVO = this.getArticleVO(article);
            articleVOList.add(articleVO);
        }

        PageInfo pageInfo = new PageInfo(articleList);
        pageInfo.setList(articleVOList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    //查看个人文章，不按文集
    public ServerResponse getArticleList(Integer authorId,int pageNum,int pageSize){
        if (authorId == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articleList = articleMapper.getArticleListByAuthorId(authorId);
        List<ArticleVO> articleVOList = Lists.newArrayList();
        for (Article article : articleList){
            ArticleVO articleVO = this.getArticleVO(article);
            articleVOList.add(articleVO);
        }

        PageInfo pageInfo = new PageInfo(articleList);
        pageInfo.setList(articleVOList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    //查看发布的新闻
    public ServerResponse getArticleList(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articleList = articleMapper.getAllList(Const.ArticleStatus.PUBLISHED.getCode());
        List<ArticleVO> articleVOList = Lists.newArrayList();
        for (Article article : articleList){
            ArticleVO articleVO = this.getArticleVO(article);
            articleVOList.add(articleVO);
        }

        PageInfo pageInfo = new PageInfo(articleList);
        pageInfo.setList(articleVOList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    //查看选择分类的已发布的新闻
    public ServerResponse getArticleTypeList(Integer typeId,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articleList = articleMapper.getArticleByTypeId(typeId,Const.ArticleStatus.PUBLISHED.getCode());
        List<ArticleVO> articleVOList = Lists.newArrayList();
        for (Article article : articleList){
            ArticleVO articleVO = this.getArticleVO(article);
            articleVOList.add(articleVO);
        }

        PageInfo pageInfo = new PageInfo(articleList);
        pageInfo.setList(articleVOList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    //获取需要审核的文章列表
    public ServerResponse getApplyList(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articleList = articleMapper.getCheckList(Const.ArticleStatus.APPLICATION_FOR_PUBLISH.getCode());
        List<ArticleVO> articleVOList = Lists.newArrayList();
        for (Article article : articleList){
            ArticleVO articleVO = this.getArticleVO(article);
            articleVOList.add(articleVO);
        }

        PageInfo pageInfo = new PageInfo(articleList);
        pageInfo.setList(articleVOList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse deleteArticle(Integer authorId,Integer articleId){
        if (authorId == null)
            return ServerResponse.createByErrorMessage("请先登录");
        if (articleId == null)
            return ServerResponse.createByErrorMessage("请选择要删除的文章");
        int deleteCount = articleMapper.deleteByAuthorIdAndArticleId(authorId,articleId);
        if (deleteCount > 0)
            return ServerResponse.createBySuccessMessage("删除成功");
        return ServerResponse.createByErrorMessage("删除失败");

    }
}
