package service;

import daofactory.StudentDaoFactory;
import dto.Student;
import persistence.IStudentDao;
import dto.Student;
import persistence.IStudentDao;

public class StudentServiceImpl implements IStudentService {
	private IStudentDao stdDao;

	@Override
	public String addStudent(Integer sid, String sname, Integer sage, String address) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.addStudent(sid, sname, sage, address);
	}

	@Override
	public Student searchStudent(Integer sid) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.searchStudent(sid);
	}

	@Override
	public String updateStudent(Integer sid, String sname, Integer sage, String saddress) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.updateStudent(sid, sname, sage, saddress);
	}

	@Override
	public String deleteStudent(Integer sid) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.deleteStudent(sid);
	}

}
