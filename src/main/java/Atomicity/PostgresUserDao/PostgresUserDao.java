package Atomicity.PostgresUserDao;

import Atomicity.entity.Address;
import Atomicity.entity.Role;
import Atomicity.entity.User;

import java.sql.*;
import java.util.Optional;

public class PostgresUserDao implements UserDao {

    public static void main(String[] args) throws SQLException {
        UserDao userDao = new PostgresUserDao();
        userDao.save(new User.Builder().name("Tom").password("4245").username("Nagibator").address(new Address("Minsk")).role(Role.PROGRAMMER).build());
        System.out.println(userDao.findByUserName("Na").get());
    }

    //В БД не может находится Address_id(полноценный класс), может только id, который связывает БД
    //Следовательно, сначала мы сохраняем адрес и потом по его id сохраняем этот адресс в users
    @Override
    public void save(User user) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123456")) {
            connection.setAutoCommit(false); //Начало границы нашей транзакции (Это позволяет соеденить 2 наши операции(execute) воедино. Будет либо только 1 целая (атомарная)тарнзакция, либо ничего)
            //Этот connection на уровне метода, поэтому возращать его в .setAutoCommit(true) не нужно, но если connection на уровне класса, объекта и тд., то возвращать нужно
            PreparedStatement preparedStatement1 = connection.
                    prepareStatement("insert into address(street) values(?) returning id");//returning - оператор, который возвращает id адреса, который заносится в БД
            preparedStatement1.setString(1, user.getAddress().getStreet());
            ResultSet resultSet = preparedStatement1.executeQuery();
            long aLong = 0;

            if (resultSet.next()) {
                aLong = resultSet.getLong(1);
            }

         /*   if(user.getName().equals("Tom"))
                throw new RuntimeException();*/

            PreparedStatement preparedStatement = connection.
                    prepareStatement("insert into users (name, username, password, role, address_id) values (?,?,?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole().name());//Только так можно хранить enum в БД
            preparedStatement.setLong(5, aLong);
            preparedStatement.execute();

            connection.commit(); //Конец нашей транзакции
        } catch (SQLException e) {
            //connection.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByUserName(String username) throws SQLException {
        PreparedStatement preparedStatement = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123456").
                prepareStatement(" select u.name, u.username, u.password, u.role, a.id, a.street from users u join address a on u.address_id = a.id where u.username=?");
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User user = new User.Builder()
                    .name(resultSet.getString(1))
                    .username(resultSet.getString(2))
                    .password(resultSet.getString(3))
                    .role(Role.valueOf(resultSet.getString(4)))
                    .address(new Address(resultSet.getLong(5), resultSet.getString(6)))
                    .build();
            return Optional.of(user);
        }

        return Optional.empty();
    }
}
