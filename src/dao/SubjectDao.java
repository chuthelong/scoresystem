package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {
    public void create(Subject subject) throws Exception {
        try (Connection con = getConnection()) {
            String sql = "INSERT INTO SUBJECT (CD, NAME) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, subject.getCd());
            ps.setString(2, subject.getName());
            ps.executeUpdate();
        }
    }

    public List<Subject> findAll() throws Exception {
        List<Subject> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM SUBJECT";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSchoolCd(rs.getString("SCHOOL_CD"));
                subject.setCd(rs.getString("CD"));
                subject.setName(rs.getString("NAME"));
                list.add(subject);
            }
        }
        return list;
    }

    public void update(Subject subject) throws Exception {
        try (Connection con = getConnection()) {
            String sql = "UPDATE SUBJECT SET NAME = ? WHERE CD = ? AND SCHOOL_CD = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, subject.getName());
            ps.setString(2, subject.getCd());
            ps.setString(3, subject.getSchoolCd());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Database update error", e);
        }
    }

    public void delete(String schoolCd, String cd) throws Exception {
        try (Connection con = getConnection()) {
            String sql = "DELETE FROM SUBJECT WHERE SCHOOL_CD = ? AND CD = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, schoolCd);
            ps.setString(2, cd);
            ps.executeUpdate();
        }
    }
    public List<Subject> getAllSubjects() throws Exception {
        List<Subject> subjects = new ArrayList<>();
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("SELECT cd, name FROM subject ORDER BY cd");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Subject subject = new Subject();
            subject.setCd(rs.getString("cd"));
            subject.setName(rs.getString("name"));
            subjects.add(subject);
        }

        rs.close();
        st.close();
        con.close();

        return subjects;
    }
}
