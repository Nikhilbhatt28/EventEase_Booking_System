package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DBConnection;
import model.User;
public class UserDAO {
    // Register User
    public boolean registerUser(User user) {

        try {

            if (isUsernameExists(user.getUsername())) {
                return false;
            }

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO users(username, email, password) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }
    }
    // Login User
    public User loginUser(String username, String password) {

    try {

        Connection con = DBConnection.getConnection();

        String query = "SELECT * FROM users WHERE username=? AND password=?";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){

            User user = new User();

            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));

            return user;

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return null;
}
    // Check Username
    public boolean isUsernameExists(String username) {

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM users WHERE username=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }
    }
}