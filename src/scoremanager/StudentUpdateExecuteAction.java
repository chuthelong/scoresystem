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

public class StudentUpdateExecuteAction extends Action{
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
		StudentDao sDao = new StudentDao();
		boolean isAttend = false;
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		ClassNumDao cNumDao = new ClassNumDao();
		Map<String, String> errors = new HashMap<>();

		String entYearStr = req.getParameter("ent_year");
		String no = req.getParameter("no");
		String name = req.getParameter("name");
		String classNum = req.getParameter("class_num");
		String isAttendStr = req.getParameter("is_attend");

		if(isAttendStr != null){
			isAttend = true;
		}

		Student student = sDao.get(no);
		List<String> list = cNumDao.filter(teacher.getSchool());

		if(student != null){
			student.setName(name);
			student.setClassNum(classNum);
			student.setAttend(isAttend);
			sDao.save(student);
		} else {
			errors.put("no", "学生番号が存在しません");

		}
		req.setAttribute("class_num_set", list);

		if(!errors.isEmpty()){
			req.setAttribute("ent_year", student.getEntYear());
			req.setAttribute("no", student.getNo());
			req.setAttribute("name", student.getName());
			req.setAttribute("class_num", student.getClassNum());
			req.setAttribute("is_attend", student.isAttend());
			req.getRequestDispatcher("student_update.jsp").forward(req, res);
		} else {
			errors.put("no", "学生番号が存在しません");
			req.setAttribute("errors", errors);
		}
		req.setAttribute("no", no);
		req.getRequestDispatcher("student_update_dones.jsp").forward(req, res);
 	}

}
