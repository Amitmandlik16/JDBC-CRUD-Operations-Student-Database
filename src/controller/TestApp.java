package controller;

import java.util.Scanner;

import dto.Student;
import service.IStudentService;
import servicefactory.StudentServiceFactory;

public class TestApp {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String args[]) {

		System.out.println(
				"choose the operation \n 1.Search Student \n 2.Add Student \n 3.Update Student \n 4.Delete Student");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			searchOperation();
			break;
		case 2:
			insertOperation();
			break;
		case 3:
			updateOperation();
			break;
		case 4:
			deleteOperation();
			break;

		default:
			System.out.println("choose valid option");
			break;
		}
		scanner.close();

	}

	public static void searchOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		System.out.println("Enter the student Id::");
		int sid = scanner.nextInt();

		Student std = studentService.searchStudent(sid);

		if (std != null) {
			System.out.println(std);
			System.out.println("sid\tsname\tsage\taddress");
			System.out.println(std.getSid() + "\t" + std.getSname() + "\t" + std.getSage() + "\t" + std.getAddress());
		} else {
			System.out.println("Record not found for the given id:" + sid);
		}
	}

	public static void updateOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		System.out.println("Enter the student Id which wants to update the data::");
		int sid = scanner.nextInt();

		Student std = studentService.searchStudent(sid);

		if (std != null) {
			System.out.println(sid);
			System.out.println("sid\tsname\tsage\taddress");
			System.out.println(std.getSid() + "\t" + std.getSname() + "\t" + std.getSage() + "\t" + std.getAddress());

			// logic to update the data related to sid
			System.out.println("Enter the student Name::");
			String sname = scanner.next();

			System.out.println("Enter the student Age::");
			int sage = scanner.nextInt();

			System.out.println("Enter the student Address::");
			String address = scanner.next();

			String msg = studentService.updateStudent(sid, sname, sage, address);

			if (msg.equalsIgnoreCase("success")) {
				System.out.println("record updated succesfully");
			} else {
				System.out.println("record updation failed");
			}

		} else {
			System.out.println("Record not found for the given id:" + sid);
		}
	}

	public static void insertOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		System.out.println("Enter the student Id::");
		int sid = scanner.nextInt();

		System.out.println("Enter the student Name::");
		String sname = scanner.next();

		System.out.println("Enter the student Age::");
		int sage = scanner.nextInt();

		System.out.println("Enter the student Address::");
		String address = scanner.next();

		String msg = studentService.addStudent(sid, sname, sage, address);

		if (msg.equalsIgnoreCase("success")) {
			System.out.println("record inserted succesfully");
		} else {
			System.out.println("record insertion failed");
		}

	}

	public static void deleteOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		System.out.println("Enter the student Id to be deleted::");
		int sid = scanner.nextInt();

		String msg = studentService.deleteStudent(sid);

		if (msg.equalsIgnoreCase("success")) {
			System.out.println("record deleted succesfully");
		} else if (msg.equalsIgnoreCase("not found")) {
			System.out.println("record not found in database");
		} else {
			System.out.println("record deletion failed");
		}
	}

}
