package scoremanager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestCreateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String no = req.getParameter("no");
        String studentNo = req.getParameter("studentNo");
        String subjectCd = req.getParameter("subjectCd");
        String schoolCd = req.getParameter("schoolCd");
        String pointStr = req.getParameter("point");
        String classNum = req.getParameter("classNum");

        Map<String, String> errors = new HashMap<>();

        int point = 0;
        try {
            point = Integer.parseInt(pointStr);
        } catch (NumberFormatException e) {
            errors.put("point", "点数を整数で入力してください。");
        }

        if (studentNo == null || studentNo.isEmpty()) {
            errors.put("studentNo", "学生番号を入力してください。");
        }

        if (subjectCd == null || subjectCd.isEmpty()) {
            errors.put("subjectCd", "科目コードを入力してください。");
        }

        if (schoolCd == null || schoolCd.isEmpty()) {
            errors.put("schoolCd", "学校コードを入力してください。");
        }

        if (classNum == null || classNum.isEmpty()) {
            errors.put("classNum", "クラス番号を入力してください。");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("test_create.jsp").forward(req, res);
            return;
        }

        Test test = new Test(no, studentNo, subjectCd, schoolCd, point, classNum);
        TestDao dao = new TestDao();
        dao.create(test);

        res.sendRedirect("TestList.action");
    }
}
