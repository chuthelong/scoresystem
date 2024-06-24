package scoremanager;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestUpdateListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Enumeration<String> parameterNames = req.getParameterNames();
        TestDao testDao = new TestDao();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            if (paramName.startsWith("point_")) {
                String[] parts = paramName.split("_");
                String no = parts[1];
                String studentNo = parts[2];
                String subjectCd = parts[3];
                String schoolCd = parts[4];
                int point = Integer.parseInt(req.getParameter(paramName));

                Test test = new Test(no, studentNo, subjectCd, schoolCd, point, null);
                testDao.updatePoint(test);
            }
        }

        res.sendRedirect("TestList.action");
    }
}
