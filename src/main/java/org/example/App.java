package org.example;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
       /* Connection connection =
                DriverManager.
                        getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123456");*/
        // Statement statement = connection.createStatement();
        // statement.execute("insert into users values (default,'abv','ABV','abv')");
        // PreparedStatement ps = connection.prepareStatement("insert into users values(default, ?,?,?)");
        //ps.setString(1,"rrr");
        //ps.setString(2,"RRR");
        //ps.setString(3,"TTT");
        //ps.execute();
        /*PreparedStatement ps = connection.prepareStatement("select * from users");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            int anInt = resultSet.getInt(1);
            String string1 = resultSet.getString(2);
            String string2 = resultSet.getString(3);
            String string3 = resultSet.getString(4);

            System.out.println("ID= " + anInt);
            System.out.println("name= " + string1);
            System.out.println("username= " + string2);
            System.out.println("password= " + string3);
        }*/

        JdbcUserStorage jdbcUserStorage = new JdbcUserStorage();
//        jdbcUserStorage.save(new User("TT","tt","tt "));
//        jdbcUserStorage.findAll().stream().forEach(System.out::println);
//        jdbcUserStorage.deleteById(2);
//        System.out.println();
//        jdbcUserStorage.findAll().stream().forEach(System.out::println);
//        System.out.println();
//        jdbcUserStorage.updateNameById(11,"LL");
//        jdbcUserStorage.updatePasswordById(11, "Password");
//        jdbcUserStorage.updateUsernameById(11,"ooooooo");
//        System.out.println(jdbcUserStorage.findByUserName("rr"));
        System.out.println(jdbcUserStorage.findById(9));
    }
}
