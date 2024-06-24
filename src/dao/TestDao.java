package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDao extends Dao {
    public void create(Test test) throws Exception {
        try (Connection con = getConnection()) {
            String sql = "INSERT INTO TEST (NO, STUDENT_NO, SUBJECT_CD, SCHOOL_CD, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, test.getNo());
            ps.setString(2, test.getStudentNo());
            ps.setString(3, test.getSubjectCd());
            ps.setString(4, test.getSchoolCd());
            ps.setInt(5, test.getPoint());
            ps.setString(6, test.getClassNum());
            ps.executeUpdate();
        }
    }

    public List<Test> findByStudentNoAndSubjectCd(String studentNo, String subjectCd) throws Exception {
        List<Test> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, studentNo);
            ps.setString(2, subjectCd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Test test = new Test();
                test.setNo(rs.getString("NO"));
                test.setStudentNo(rs.getString("STUDENT_NO"));
                test.setSubjectCd(rs.getString("SUBJECT_CD"));
                test.setSchoolCd(rs.getString("SCHOOL_CD"));
                test.setPoint(rs.getInt("POINT"));
                test.setClassNum(rs.getString("CLASS_NUM"));
                list.add(test);
            }
        }
        return list;
    }

    public void update(String originalNo, String originalStudentNo, String originalSubjectCd, String originalSchoolCd, Test test) throws Exception {
        try (Connection con = getConnection()) {
            String sql = "UPDATE TEST SET NO = ?, STUDENT_NO = ?, SUBJECT_CD = ?, SCHOOL_CD = ?, POINT = ?, CLASS_NUM = ? WHERE NO = ? AND STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, test.getNo());
            ps.setString(2, test.getStudentNo());
            ps.setString(3, test.getSubjectCd());
            ps.setString(4, test.getSchoolCd());
            ps.setInt(5, test.getPoint());
            ps.setString(6, test.getClassNum());
            ps.setString(7, originalNo);
            ps.setString(8, originalStudentNo);
            ps.setString(9, originalSubjectCd);
            ps.setString(10, originalSchoolCd);
            ps.executeUpdate();
        }
    }

    public void delete(String no, String studentNo, String subjectCd, String schoolCd) throws Exception {
        try (Connection con = getConnection()) {
            String sql = "DELETE FROM TEST WHERE NO = ? AND STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, no);
            ps.setString(2, studentNo);
            ps.setString(3, subjectCd);
            ps.setString(4, schoolCd);
            ps.executeUpdate();
        }
    }
    public List<Test> findAll() throws Exception {
        List<Test> list = new ArrayList<>();
        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM TEST";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Test test = new Test();
                test.setNo(rs.getString("NO"));
                test.setStudentNo(rs.getString("STUDENT_NO"));
                test.setSubjectCd(rs.getString("SUBJECT_CD"));
                test.setSchoolCd(rs.getString("SCHOOL_CD"));
                test.setPoint(rs.getInt("POINT"));
                test.setClassNum(rs.getString("CLASS_NUM"));
                list.add(test);
            }
        }
        return list;
    }
    public List<Test> getFilteredTests(String entYearStr, String classNum, String subjectCd) throws Exception {
        List<Test> tests = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT t.no, t.student_no, t.subject_cd, t.school_cd, t.point, t.class_num " +
                         "FROM test t " +
                         "JOIN student s ON t.student_no = s.no " +
                         "WHERE 1=1";

            if (entYearStr != null && !entYearStr.isEmpty() && !entYearStr.equals("0")) {
                sql += " AND s.ent_year = ?";
            }
            if (classNum != null && !classNum.equals("0")) {
                sql += " AND t.class_num = ?";
            }
            if (subjectCd != null && !subjectCd.equals("0")) {
                sql += " AND t.subject_cd = ?";
            }

            statement = connection.prepareStatement(sql);

            int paramIndex = 1;

            if (entYearStr != null && !entYearStr.isEmpty() && !entYearStr.equals("0")) {
                statement.setString(paramIndex++, entYearStr);
            }
            if (classNum != null && !classNum.equals("0")) {
                statement.setString(paramIndex++, classNum);
            }
            if (subjectCd != null && !subjectCd.equals("0")) {
                statement.setString(paramIndex++, subjectCd);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Test test = new Test();
                test.setNo(resultSet.getString("no"));
                test.setStudentNo(resultSet.getString("student_no"));
                test.setSubjectCd(resultSet.getString("subject_cd"));
                test.setSchoolCd(resultSet.getString("school_cd"));
                test.setPoint(resultSet.getInt("point"));
                test.setClassNum(resultSet.getString("class_num"));
                tests.add(test);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return tests;
    }
    public void updatePoint(Test test) throws Exception {
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
                "UPDATE test SET point = ? WHERE no = ? AND student_no = ? AND subject_cd = ? AND school_cd = ?");
        st.setInt(1, test.getPoint());
        st.setString(2, test.getNo());
        st.setString(3, test.getStudentNo());
        st.setString(4, test.getSubjectCd());
        st.setString(5, test.getSchoolCd());

        st.executeUpdate();

        st.close();
        con.close();
    }
    public void updateTestPoint(int testNo, int point) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE test SET point = ? WHERE no = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, point);
            statement.setInt(2, testNo);
            statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
