package scoremanager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String originalNo = req.getParameter("originalNo");
        String originalStudentNo = req.getParameter("originalStudentNo");
        String originalSubjectCd = req.getParameter("originalSubjectCd");
        String originalSchoolCd = req.getParameter("originalSchoolCd");

        String no = req.getParameter("no");
        String studentNo = req.getParameter("studentNo");
        String subjectCd = req.getParameter("subjectCd");
        String schoolCd = req.getParameter("schoolCd");
        String pointStr = req.getParameter("point");
        String classNum = req.getParameter("classNum");

        Map<String, String> errors = new HashMap<>();

        int point;
        try {
            point = Integer.parseInt(pointStr);
        } catch (NumberFormatException e) {
            errors.put("point", "点数を整数で入力してください。");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("test_update.jsp").forward(req, res);
            return;
        }

        if (errors.isEmpty()) {
            TestDao testDao = new TestDao();
            Test test = new Test(no, studentNo, subjectCd, schoolCd, point, classNum);
            testDao.update(originalNo, originalStudentNo, originalSubjectCd, originalSchoolCd, test);
            res.sendRedirect("TestList.action");
        } else {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("test_update.jsp").forward(req, res);
        }
    }
}
