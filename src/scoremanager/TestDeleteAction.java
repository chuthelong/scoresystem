package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TestDao;
import tool.Action;

public class TestDeleteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String no = req.getParameter("no");
        String studentNo = req.getParameter("studentNo");
        String subjectCd = req.getParameter("subjectCd");
        String schoolCd = req.getParameter("schoolCd");

        TestDao testDao = new TestDao();
        testDao.delete(no, studentNo, subjectCd, schoolCd);

        res.sendRedirect("TestList.action");
    }
}
