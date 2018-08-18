package com.journalism.vo;

import java.util.Date;

public class ArticleVO {

    private Integer articleId;

    private String title;

    private String subTitle;

    private Date publishTime;

    private String authorName;

    private String text;

    private String format;

    private String subImages;

    private String typeName;

    private Integer status;

    private Date createTime;

    public ArticleVO() {
    }

    public ArticleVO(Integer articleId, String title, String subTitle, Date publishTime, String authorName, String text, String format, String subImages, String typeName, Integer status, Date createTime) {
        this.articleId = articleId;
        this.title = title;
        this.subTitle = subTitle;
        this.publishTime = publishTime;
        this.authorName = authorName;
        this.text = text;
        this.format = format;
        this.subImages = subImages;
        this.typeName = typeName;
        this.status = status;
        this.createTime = createTime;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSubImages() {
        return subImages;
    }

    public void setSubImages(String subImages) {
        this.subImages = subImages;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
