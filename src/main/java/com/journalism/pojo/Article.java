package com.journalism.pojo;

import java.util.Date;

public class Article {
    private Integer id;

    private Integer anthologyId;

    private String title;

    private String subTitle;

    private Date publishTime;

    private Integer authorId;

    private String authorName;

    private String text;

    private String format;

    private String subImages;

    private Integer typeId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Article(Integer id, Integer anthologyId, String title, String subTitle, Date publishTime, Integer authorId, String authorName, String text, String format, String subImages, Integer typeId, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.anthologyId = anthologyId;
        this.title = title;
        this.subTitle = subTitle;
        this.publishTime = publishTime;
        this.authorId = authorId;
        this.authorName = authorName;
        this.text = text;
        this.format = format;
        this.subImages = subImages;
        this.typeId = typeId;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Article() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnthologyId() {
        return anthologyId;
    }

    public void setAnthologyId(Integer anthologyId) {
        this.anthologyId = anthologyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }

    public String getSubImages() {
        return subImages;
    }

    public void setSubImages(String subImages) {
        this.subImages = subImages == null ? null : subImages.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}