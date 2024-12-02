package servicefactory;

import service.IStudentService;
import service.StudentServiceImpl;

//Abstraction logic of implementation
public class StudentServiceFactory {
	// make constructor private to avoid object creation
	private StudentServiceFactory() {
	}

	private static IStudentService studentService = null;

	public static IStudentService getStudentService() {
		// singleton pattern code
		if (studentService == null) {
			studentService = new StudentServiceImpl();
			System.out.println("StudentserviceImpl is created");
		}
		return studentService;
	}
}