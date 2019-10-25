package com.codve.prospring.ch06.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * @author admin
 * @date 2019/10/24 11:16
 */
public class Article implements Serializable {
    private Long id;
    private Long userId;
    private String title;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        String createTimeStr = String.format("%tF %<tT", createTime);
        return "Article{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", createTime=" + createTimeStr +
                '}';
    }
}
