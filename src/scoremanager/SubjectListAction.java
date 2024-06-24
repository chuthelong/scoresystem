package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjects = subjectDao.findAll();

        req.setAttribute("subjects", subjects);
        req.getRequestDispatcher("subject_list.jsp").forward(req, res);
    }
}
