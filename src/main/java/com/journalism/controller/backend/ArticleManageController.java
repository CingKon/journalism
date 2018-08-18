package com.journalism.controller.backend;

import com.journalism.common.Const;
import com.journalism.common.ResponseCode;
import com.journalism.common.ServerResponse;
import com.journalism.pojo.Article;
import com.journalism.pojo.User;
import com.journalism.service.IArticleService;
import com.journalism.service.IUserService;

import com.journalism.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("/manage/article")
public class ArticleManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IArticleService iArticleService;

    @RequestMapping("article_save.do")
    @ResponseBody
    public ServerResponse articleSave(HttpSession session, Article article){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        return iArticleService.saveOrUpdateArticle(article);
    }

    @RequestMapping("set_status.do")
    @ResponseBody
    public ServerResponse setIssueStatus(HttpSession session,Integer articleId,Integer status){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        if (status.intValue() == Const.ArticleStatus.APPLICATION_FOR_PUBLISH.getCode()){
            if (iUserService.checkManagerRole(user).isSuccess())
                return iArticleService.setIssueStatus(articleId, status);
            return ServerResponse.createByErrorMessage("需要经理权限！");
        }
        else if (status.intValue() == Const.ArticleStatus.PUBLISHED.getCode()){
            if (iUserService.checkManagerRole(user).isSuccess())
                return iArticleService.reCall(articleId,status);
            return ServerResponse.createByErrorMessage("需要经理权限！");
        }else
            return iArticleService.setIssueStatus(articleId,status);
    }

    @RequestMapping("article_detail.do")
    @ResponseBody
    public ServerResponse<ArticleVO> getDetail(HttpSession session, Integer articleId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        return iArticleService.getArticleDetails(articleId);
    }

    @RequestMapping("article_list.do")
    @ResponseBody
    public ServerResponse getArticleList(HttpSession session,Integer anthologyId,
                                  @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        if (anthologyId == null)
            return iArticleService.getArticleList(user.getId(),pageNum,pageSize);
        return iArticleService.getArticleList(user.getId(),anthologyId,pageNum,pageSize);
    }

    @RequestMapping("all_list.do")
    @ResponseBody
    public ServerResponse getAllList(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        return iArticleService.getArticleList(pageNum,pageSize);
    }

    @RequestMapping("type_article.do")
    @ResponseBody
    public ServerResponse searchTypeArticle(Integer typeId,
                                            @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        return iArticleService.getArticleTypeList(typeId,pageNum,pageSize);
    }

    @RequestMapping("check_article.do")
    @ResponseBody
    public ServerResponse checkArticlePublishList(HttpSession session,
                                                  @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                  @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        if (user.getRole().intValue() == Const.Role.ROLE_MANAGER)
            return iArticleService.getApplyList(pageNum,pageSize);
        return ServerResponse.createByErrorMessage("需要经理权限！");
    }

    @RequestMapping("delete.do")
    @ResponseBody
    public ServerResponse deleteArticle(HttpSession session,Integer articleId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        return iArticleService.deleteArticle(articleId,articleId);
    }
}
