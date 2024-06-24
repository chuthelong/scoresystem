package scoremanager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String schoolCd = req.getParameter("schoolCd");
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        Map<String, String> errors = new HashMap<>();

        if (cd == null || cd.isEmpty()) {
            errors.put("cd", "科目コードを入力してください。");
        }

        if (name == null || name.isEmpty()) {
            errors.put("name", "科目名を入力してください。");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = new Subject(schoolCd, cd, name);

        subjectDao.create(subject);
        res.sendRedirect("SubjectList.action");
    }
}
