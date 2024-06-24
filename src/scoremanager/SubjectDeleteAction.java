package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String schoolCd = req.getParameter("schoolCd");
        String cd = req.getParameter("cd");

        SubjectDao subjectDao = new SubjectDao();
        subjectDao.delete(schoolCd, cd);

        res.sendRedirect("SubjectList.action");
    }
}
