package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestListAllAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        TestDao testDao = new TestDao();
        List<Test> tests = testDao.findAll();

        req.setAttribute("tests", tests);
        req.getRequestDispatcher("test_list_all.jsp").forward(req, res);
    }
}
