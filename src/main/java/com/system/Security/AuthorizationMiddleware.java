package main.java.com.system.Security;

import java.sql.*;

import static main.java.com.system.Security.AuthService.*;

public class AuthorizationMiddleware {
    public static boolean hasRole(HttpServletRequest request, String role) {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) return false;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "SELECT r.name FROM roles r " +
                    "JOIN user_roles ur ON r.id = ur.role_id " +
                    "JOIN users u ON ur.user_id = u.id " +
                    "WHERE u.username = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("name").equals(role)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
