package com.codve.prospring.ch06.entry;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 * @date 2019/10/24 11:15
 */
public class User implements Serializable {
    private Long id;
    private String name;
    private Date birthday;
    private List<Article> articles;

    public boolean addArticle(Article article) {
        if (articles.contains(article)) {
            return false;
        }
        articles.add(article);
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        String birthdayStr = String.format("%tF %<tT", birthday);

        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthdayStr +
                '}';
    }
}
