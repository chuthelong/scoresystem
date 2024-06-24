package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestListAction extends Action {
    public void execute(
        HttpServletRequest req, HttpServletResponse res
    ) throws Exception {
        HttpSession session = req.getSession();

        String entYearStr = "";
        String classNum = "";
        String subjectCd = "";
        int entYear = 0;
        List<Test> tests = null;
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        TestDao testDao = new TestDao();
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();
        Map<String, String> errors = new HashMap<>();

        entYearStr = req.getParameter("f1");
        classNum = req.getParameter("f2");
        subjectCd = req.getParameter("f3");

        if (entYearStr != null && !entYearStr.isEmpty() && !entYearStr.equals("0")) {
            entYear = Integer.parseInt(entYearStr);
        }

        if (entYear != 0 || (classNum != null && !classNum.equals("0")) || (subjectCd != null && !subjectCd.equals("0"))) {
            tests = testDao.getFilteredTests(entYearStr, classNum, subjectCd);
        } else {
            tests = new ArrayList<>();
            if (entYear == 0 && (classNum != null && !classNum.equals("0")) || (subjectCd != null && !subjectCd.equals("0"))) {
                errors.put("f1", "クラスまたは科目を指定する場合は入学年度も指定してください");
            }
        }

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        List<String> classNumSet = cNumDao.filterAll();
        List<Subject> subjects = subjectDao.getAllSubjects();

        req.setAttribute("f1", entYearStr);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);
        req.setAttribute("tests", tests);
        req.setAttribute("class_num_set", classNumSet);
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("subjects", subjects);
        req.setAttribute("errors", errors);
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}
