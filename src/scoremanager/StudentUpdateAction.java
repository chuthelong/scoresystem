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
//public class StudentUpdateAction extends Action {
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
//        studentDao.update(student);
//
//        res.sendRedirect("StudentList.action");
//    }
//}

package scoremanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
		StudentDao sDao = new StudentDao();
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		ClassNumDao cNumDao = new ClassNumDao();
		Map<String, String> errors = new HashMap<>();

		String no = req.getParameter("no");
		Student student = sDao.get(no);
		List<String> list = cNumDao.filter(teacher.getSchool());
		req.setAttribute("class_num_set", list);
		if (student != null){
			req.setAttribute("ent_year", student.getEntYear());
			req.setAttribute("no", student.getNo());
			req.setAttribute("name", student.getName());
			req.setAttribute("class_num", student.getClassNum());
			req.setAttribute("is_attend", student.isAttend());
		} else {
			errors.put("no", "学生が存在しません");
			req.setAttribute("errors", errors);
		}
		req.getRequestDispatcher("student_update.jsp").forward(req, res);
	}

}
