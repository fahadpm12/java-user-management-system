package dao;

import model.User;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    /*
     * Insert user into database
     */
    public void insertUser(User user) {

        String sql = "INSERT INTO users (id, name, email, password, role) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection connection = DatabaseConnection.getConnection();

            // If connection failed, stop here
            if (connection == null) {
                System.out.println("Database connection failed.");
                return;
            }

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole());

            statement.executeUpdate();

            System.out.println("User saved to database.");

            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Fetch all users from database
     */
    public void fetchAllUsers() {

        String sql = "SELECT * FROM users";

        try {
            Connection connection = DatabaseConnection.getConnection();

            if (connection == null) {
                System.out.println("Database connection failed.");
                return;
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            boolean hasUsers = false;

            while (resultSet.next()) {
                hasUsers = true;

                System.out.println(
                        "ID: " + resultSet.getInt("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Email: " + resultSet.getString("email") +
                        ", Role: " + resultSet.getString("role")
                );
            }

            if (!hasUsers) {
                System.out.println("No users found.");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Find user by ID
     */
    public User findUserById(int id) {

        String sql = "SELECT * FROM users WHERE id = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();

            if (connection == null) {
                System.out.println("Database connection failed.");
                return null;
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));

                resultSet.close();
                statement.close();
                connection.close();

                return user;
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    /*
     * Update user
     */
    public void updateUser(User user) {

        String sql = "UPDATE users SET name = ?, email = ?, password = ?, role = ? WHERE id = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();

            if (connection == null) {
                System.out.println("Database connection failed.");
                return;
            }

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.setInt(5, user.getId());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("User not found.");
            }

            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Delete user
     */
    public void deleteUser(int id) {

        String sql = "DELETE FROM users WHERE id = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();

            if (connection == null) {
                System.out.println("Database connection failed.");
                return;
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User not found.");
            }

            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}