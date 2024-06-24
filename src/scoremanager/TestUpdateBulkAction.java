package scoremanager;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TestDao;
import tool.Action;

public class TestUpdateBulkAction extends Action {
    public void execute(
        HttpServletRequest req, HttpServletResponse res
    ) throws Exception {
        HttpSession session = req.getSession();
        Map<String, String[]> parameterMap = req.getParameterMap();
        TestDao testDao = new TestDao();

        for (String key : parameterMap.keySet()) {
            if (key.startsWith("points_")) {
                int testNo = Integer.parseInt(key.substring(7));
                int point = Integer.parseInt(req.getParameter(key));
                testDao.updateTestPoint(testNo, point);
            }
        }

        res.sendRedirect("TestList.action");
    }
}
