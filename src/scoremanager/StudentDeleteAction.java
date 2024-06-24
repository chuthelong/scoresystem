package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import tool.Action;

public class StudentDeleteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String no = req.getParameter("no");
        StudentDao studentDao = new StudentDao();
        studentDao.delete(no);
        res.sendRedirect("StudentList.action");
    }
}
