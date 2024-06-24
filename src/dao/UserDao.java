//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//import bean.User;
//import tool.DbConnection;
//
//public class UserDao {
//
//    public User findByIdAndPassword(String id, String password) throws Exception {
//        String query = "SELECT * FROM users WHERE id=? AND password=?";
//        try (Connection con = DbConnection.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query)) {
//            stmt.setString(1, id);
//            stmt.setString(2, password);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    User user = new User();
//                    user.setId(rs.getString("id"));
//                    user.setPassword(rs.getString("password"));
//                    user.setName(rs.getString("name"));
//                    return user;
//                }
//            }
//        }
//        return null;
//    }
//    public User findById(String id) throws Exception {
//        Connection con = DbConnection.getConnection();
//        PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE id = ?");
//        stmt.setString(1, id);
//        ResultSet rs = stmt.executeQuery();
//        User user = null;
//        if (rs.next()) {
//            user = new User();
//            user.setId(rs.getString("id"));
//            user.setPassword(rs.getString("password"));
//            user.setName(rs.getString("name"));
//        }
//        con.close();
//        return user;
//    }
//
//    public void updateUser(User user) throws Exception {
//        String query = "UPDATE users SET password = ?, name = ?, role = ? WHERE id = ?";
//        try (Connection con = DbConnection.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query)) {
//            stmt.setString(1, user.getPassword());
//            stmt.setString(2, user.getName());
//            stmt.setString(4, user.getId());
//            stmt.executeUpdate();
//        }
//    }
////
////    private void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {
////        try {
////            if (resultSet != null) {
////                resultSet.close();
////            }
////            if (statement != null) {
////                statement.close();
////            }
////            if (connection != null) {
////                connection.close();
////            }
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////    }
//}
