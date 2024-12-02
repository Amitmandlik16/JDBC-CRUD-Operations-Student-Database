package service;

import dto.Student;

public interface IStudentService {
	// operations to be implemented
	public String addStudent(Integer sid, String sname, Integer sage, String address);

	public Student searchStudent(Integer sid);

	public String updateStudent(Integer sid, String sname, Integer sage, String saddress);

	public String deleteStudent(Integer sid);
}