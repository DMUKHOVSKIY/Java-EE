package org.example;

import java.sql.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserStorage {
    private Connection setConnection() throws SQLException {
        return DriverManager.
                getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123456");
    }

    public void save(User user) throws SQLException {
        PreparedStatement ps = setConnection().prepareStatement("insert into users values (default, ?,?,?)");
        ps.setString(1, user.getName());
        ps.setString(2, user.getUserName());
        ps.setString(3, user.getPassword());
        ps.execute();
        setConnection().close();
    }

    public void updateNameById(int id, String name) throws SQLException {
        PreparedStatement ps = setConnection().prepareStatement("update users set name = ? where id = ?");
        ps.setString(1, name);
        ps.setInt(2, id);
        ps.execute();
        setConnection().close();
    }

    public void deleteById(int id) throws SQLException {
        PreparedStatement ps = setConnection().prepareStatement("delete from users where id =? ");
        ps.setInt(1, id);
        ps.execute();
        setConnection().close();
    }

    public List<User> findAll() throws SQLException {
        PreparedStatement ps = setConnection().prepareStatement("select * from users");
        ResultSet resultSet = ps.executeQuery();
        List<User> list = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setUserName(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
            list.add(user);
        }
        setConnection().close();
        return list;
    }

    public User findById(int id) throws SQLException {
        PreparedStatement ps = setConnection().prepareStatement("select * from users where id =?");
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        User user = new User();
        if (resultSet.next()) {//Если что-то есть то выполним
           //Изначально у resultSet(а) курсор висит в воздухе, а мы сдвигаем его на 1 вправо методом next()
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setUserName(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
            setConnection().close();
        }
        return user;
    }

    public void deleteByUserName(String name) throws SQLException {
        PreparedStatement ps = setConnection().prepareStatement("delete from users where userName = ? ");
        ps.setString(1, name);
        ps.execute();
        setConnection().close();
    }

    public User findByUserName(String userName) throws SQLException {
        PreparedStatement ps = setConnection().prepareStatement("select * from users where userName = ?");
        ps.setString(1, userName);
        ResultSet resultSet = ps.executeQuery();
        User user = new User();
        resultSet.next();
        user.setId(resultSet.getInt(1));
        user.setName(resultSet.getString(2));
        user.setUserName(resultSet.getString(3));
        user.setPassword(resultSet.getString(4));
        setConnection().close();
        return user;

    }

    public void updatePasswordById(int id, String password) throws SQLException {
        PreparedStatement ps = setConnection().prepareStatement("update users set password = ? where id=?");
        ps.setString(1, password);
        ps.setInt(2, id);
        ps.execute();
        setConnection().close();
    }

    public void updateUsernameById(int id, String userName) throws SQLException {
        PreparedStatement ps = setConnection().prepareStatement("update users set userName = ? where id = ?");
        ps.setString(1, userName);
        ps.setInt(2, id);
        ps.execute();
        setConnection().close();
    }

}
