package com.codve.prospring.ch06.dao;

import com.codve.prospring.ch06.entry.User;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 * @date 2019/10/24 12:14
 * qZF08fDcidSRJ2CI
 */
public class PlainUserDao implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(PlainUserDao.class);

    // 检查驱动是否存在
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public List<User> listUsers() {
        List<User> result = new ArrayList<>();
        try (Connection conn = getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement("select * from `user`");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setName(resultSet.getString("user_name"));
                user.setBirthday(new Date(resultSet.getLong("user_birthday")));
                result.add(user);
            }
            statement.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public String getNameById(Long id) {
        throw new NotImplementedException("method not implement.");
    }

    @Override
    public List<User> listUsersByName(String name) {
        throw new NotImplementedException("method not implement.");
    }

    /**
     * 插入新数据, 同时返回主键
     * @param user 新用户
     */
    @Override
    public void insert(User user) {
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(
                    "insert into `user` (`user_name`, `user_birthday`) values(?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, user.getName());
            statement.setLong(2, user.getBirthday().getTime());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        throw new NotImplementedException("method not implement.");
    }

    @Override
    public void delete(Long id) {
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(
                    "delete from user where user_id =?"
            );
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public List<User> listUsersWithArticle() {
        throw new NotImplementedException("method not implement.");
    }

    @Override
    public void insertWithArticle(User user) {
        throw new NotImplementedException("method not implement.");
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/pro_spring?useSSL=false&useUnicode=true&characterEncoding=UTF-8",
                "spring",
                "qZF08fDcidSRJ2CI");
    }

    private void closeConnection(Connection connection) throws SQLException {
        if (connection == null) {
            return;
        }
        connection.close();
    }
}
