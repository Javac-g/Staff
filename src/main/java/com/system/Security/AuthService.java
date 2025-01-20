package main.java.com.system.Security;
import java.sql.*;

public class AuthService {
    static final String DB_URL = "jdbc:mysql://localhost:3306/yourdb";
    static final String DB_USER = "root";
    static final String DB_PASS = "password";

    public static boolean authenticate(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "SELECT password FROM users WHERE username = ? AND active = TRUE";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                return PasswordUtil.verifyPassword(password, storedHash);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
