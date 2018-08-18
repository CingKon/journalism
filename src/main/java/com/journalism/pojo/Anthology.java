package com.journalism.pojo;

import java.util.Date;

public class Anthology {
    private Integer id;

    private String name;

    private Integer authorId;

    private Date createTime;

    private Date updateTime;

    public Anthology(Integer id, String name, Integer authorId, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Anthology() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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