package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import tool.DbConnection;

public class StudentDao extends Dao {
    private String baseSql = "SELECT * FROM student WHERE school_cd=?";

    public Student get(String no) throws Exception {
        Student student = new Student();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM student WHERE no=?")) {
            statement.setString(1, no);
            try (ResultSet rSet = statement.executeQuery()) {
                SchoolDao schoolDao = new SchoolDao();
                if (rSet.next()) {
                    student.setNo(rSet.getString("no"));
                    student.setName(rSet.getString("name"));
                    student.setEntYear(rSet.getInt("ent_year"));
                    student.setClassNum(rSet.getString("class_num"));
                    student.setAttend(rSet.getBoolean("is_attend"));
                    student.setSchool(schoolDao.get(rSet.getString("school_cd")));
                } else {
                    student = null;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return student;
    }

    private List<Student> postFilter(ResultSet rSet, School school) throws SQLException {
        List<Student> list = new ArrayList<>();
        while (rSet.next()) {
            Student student = new Student();
            student.setNo(rSet.getString("no"));
            student.setName(rSet.getString("name"));
            student.setEntYear(rSet.getInt("ent_year"));
            student.setClassNum(rSet.getString("class_num"));
            student.setAttend(rSet.getBoolean("is_attend"));
            student.setSchool(school);
            list.add(student);
        }
        return list;
    }

    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        String condition = " AND ent_year=? AND class_num=?";
        String order = " ORDER BY no ASC";
        String conditionIsAttend = "";
        if (isAttend) {
        	conditionIsAttend ="AND is_attend = true";
        }

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order)) {
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            try (ResultSet rSet = statement.executeQuery()) {
                list = postFilter(rSet, school);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        String condition = " AND ent_year=?";
        String order = " ORDER BY no ASC";
        String conditionIsAttend = "";
        if (isAttend) {
        	conditionIsAttend ="AND is_attend = true";
        }
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order)) {
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            try (ResultSet rSet = statement.executeQuery()) {
                list = postFilter(rSet, school);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public List<Student> filter(School school, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        String order = " ORDER BY no ASC";
        String conditionIsAttend = "";
        if (isAttend) {
        	conditionIsAttend ="AND is_attend = true";
        }
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(baseSql + conditionIsAttend + order)) {
            statement.setString(1, school.getCd());
            try (ResultSet rSet = statement.executeQuery()) {
                list = postFilter(rSet, school);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public boolean save(Student student) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;
        try {
            Student old = get(student.getNo());
            if (old == null) {
                statement = connection.prepareStatement("INSERT INTO student(no, name, ent_year, class_num, is_attend, school_cd) VALUES(?, ?, ?, ?, ?, ?)");
                statement.setString(1, student.getNo());
                statement.setString(2, student.getName());
                statement.setInt(3, student.getEntYear());
                statement.setString(4, student.getClassNum());
                statement.setBoolean(5, student.isAttend());
                statement.setString(6, student.getSchool().getCd());
            } else {
                statement = connection.prepareStatement("UPDATE student SET name=?, ent_year=?, class_num=?, is_attend=? WHERE no=?");
                statement.setString(1, student.getName());
                statement.setInt(2, student.getEntYear());
                statement.setString(3, student.getClassNum());
                statement.setBoolean(4, student.isAttend());
                statement.setString(5, student.getNo());
            }
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return count > 0;
    }
    public Student findById(String no) throws Exception {
        String query = "SELECT * FROM student WHERE no = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, no);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Student student = new Student();
                    student.setNo(resultSet.getString("no"));
                    student.setName(resultSet.getString("name"));
                    student.setEntYear(resultSet.getInt("ent_year"));
                    student.setClassNum(resultSet.getString("class_num"));
                    student.setAttend(resultSet.getBoolean("is_attend"));
                    return student;
                }
            }
        }
        return null;
    }

    public void update(Student student) throws Exception {
        String query = "UPDATE student SET name = ?, ent_year = ?, class_num = ?, is_attend = ? WHERE no = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getEntYear());
            stmt.setString(3, student.getClassNum());
            stmt.setBoolean(4, student.isAttend());
            stmt.setString(5, student.getNo());
            stmt.executeUpdate();
        }
    }

    public void delete(String no) throws Exception {
        String query = "DELETE FROM student WHERE no = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, no);
            stmt.executeUpdate();
        }
    }

    public boolean create(Student student) throws Exception {
        String sql = "INSERT INTO student (no, name, ent_year, class_num, is_attend, school_cd) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, student.getNo());
            stmt.setString(2, student.getName());
            stmt.setInt(3, student.getEntYear());
            stmt.setString(4, student.getClassNum());
            stmt.setBoolean(5, student.isAttend());
            stmt.setString(6, student.getSchool().getCd());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new Exception("Failed to create student: " + e.getMessage());
        }
    }
}

