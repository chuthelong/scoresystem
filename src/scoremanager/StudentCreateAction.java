//package scoremanager;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import bean.Student;
//import bean.Teacher;
//import dao.StudentDao;
//import tool.Action;
//
//public class StudentCreateAction extends Action {
//    @Override
//    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
//        HttpSession session = req.getSession();
//        Teacher teacher = (Teacher) session.getAttribute("user");
//
//        String no = req.getParameter("no");
//        String name = req.getParameter("name");
//        int entYear = Integer.parseInt(req.getParameter("entYear"));
//        String classNum = req.getParameter("classNum");
//        boolean isAttend = Boolean.parseBoolean(req.getParameter("isAttend"));
//
//        Student student = new Student();
//        student.setNo(no);
//        student.setName(name);
//        student.setEntYear(entYear);
//        student.setClassNum(classNum);
//        student.setAttend(isAttend);
//        student.setSchool(teacher.getSchool());
//
//        StudentDao studentDao = new StudentDao();
//        studentDao.create(student);
//
//        res.sendRedirect("StudentList.action");
//    }
//}

package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(true);
		ClassNumDao cNumDao = new ClassNumDao();
		Teacher teacher = (Teacher) session.getAttribute("user");
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		List<Integer> entYearSet = new ArrayList<>();
		List<String> list = cNumDao.filter(teacher.getSchool());
		for (int i = year - 10; i < year + 10; i++){
			entYearSet.add(i);
		}
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);
		req.getRequestDispatcher("student_create.jsp").forward(req, res);
	}
}
