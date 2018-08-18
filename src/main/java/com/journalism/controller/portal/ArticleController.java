package com.journalism.controller.portal;

import com.journalism.common.ServerResponse;
import com.journalism.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/article/")
public class ArticleController {

    @Autowired
    private IArticleService iArticleService;

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse detail(Integer articleId){
        return iArticleService.getArticleDetails(articleId);
    }

    public ServerResponse list(){
        return null;
    }
}
