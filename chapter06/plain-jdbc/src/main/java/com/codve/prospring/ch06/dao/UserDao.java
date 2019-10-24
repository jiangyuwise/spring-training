package com.codve.prospring.ch06.dao;

import com.codve.prospring.ch06.entry.User;

import java.util.List;

/**
 * @author admin
 * @date 2019/10/24 11:38
 */
public interface UserDao {

    /**
     * 查找所有的用户
     * @return List<User>
     */
    List<User> listUsers();

    /**
     * 根据 user id 查找 user name
     * @param id 用户编号
     * @return String
     */
    String getNameById(Long id);

    /**
     * 根据用户名模糊查找用户
     *
     * @param name 用户名
     * @return List<user>
     */
    List<User> listUsersByName(String name);

    /**
     * 添加用户
     *
     * @param user 新用户
     */
    void insert(User user);

    /**
     * 更新用户
     *
     * @param user 更新用户
     */
    void update(User user);

    /**
     * 根据 user id 删除 user
     *
     * @param id user id
     */
    void delete(Long id);

    /**
     * 列出所有的用户, 包括用户的文章
     *
     * @return List<User>
     */
    List<User> listUsersWithArticle();

    /**
     * 插入新用户, 并且插入文章
     * @param user 新用户
     */
    void insertWithArticle(User user);

}
