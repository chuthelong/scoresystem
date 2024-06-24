package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
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

public class StudentCreateExecuteAction extends Action {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
		HttpSession session = req.getSession();
		StudentDao sDao = new StudentDao();
		int entYear;
		String no ="";
		String name = "";
		String classNum = "";
		Student student = null;
		Map<String, String> errors = new HashMap<>();
		ClassNumDao cNumDao = new ClassNumDao();
		Teacher teacher = (Teacher) session.getAttribute("user");
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		List<Integer> entYearSet = new ArrayList<>();

		entYear = Integer.parseInt(req.getParameter("ent_year"));
		no = req.getParameter("no");
		name = req.getParameter("name");
		classNum = req.getParameter("class_num");

		student = sDao.get(no);
		List<String> list  = cNumDao.filter(teacher.getSchool());

		for (int i = year - 10; i < year + 10; i++){
			entYearSet.add(i);
		}
		if(entYear == 0){
			errors.put("ent_year","入学年度を選択してください");
		}else{
			if(student == null){
				student = new Student();
				student.setNo(no);
				student.setName(name);
				student.setEntYear(entYear);
				student.setClassNum(classNum);
				student.setAttend(true);
				student.setSchool(((Teacher)session.getAttribute("user")).getSchool());
				sDao.save(student);
			}else{
				errors.put("no", "学生番号が重複しています");
			}
		}
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);

		if(!errors.isEmpty()){
			req.setAttribute("errors", errors);
			req.setAttribute("ent_year", entYear);
			req.setAttribute("no", no);
			req.setAttribute("name", name);
			req.setAttribute("class_num", classNum);
			req.getRequestDispatcher("student_create.jsp").forward(req, res);
			return;
		}
		req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
	}

}