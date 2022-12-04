package repo;

import domain.User;
import domain.validators.Validator;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UtilizatorDBRepository implements Repository<Integer, User> {
    private String url;
    private String username;
    private String password;
    private Validator<User> validator;

    public UtilizatorDBRepository(String url, String username, String password, Validator<User> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }

    @Override
    public Iterable<User> findAll() {
        Set<User> users = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from users");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("nume");
                String lastName = resultSet.getString("prenume");
                String oras = resultSet.getString("oras");

                User utilizator = new User(id, firstName, lastName, oras);
                users.add(utilizator);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public User save(User entity) {
        String sql = "insert into users (first_name, last_name) values (?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(0, entity.getID());
            ps.setString(1, entity.getNume());
            ps.setString(2, entity.getPrenume());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User delete(Integer id) {
        return null;
    }

    @Override
    public User findOne(Integer integer) {
        return null;
    }

}
