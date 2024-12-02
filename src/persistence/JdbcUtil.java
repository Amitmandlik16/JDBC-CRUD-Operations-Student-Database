package persistence;

import java.io.*;
import java.util.*;
import java.sql.*;
//Jdbc connection utility

//steps to communicate with the database;
//1.load and register the driver
//2.establish the connection with the database
//3.create statement object and execute the query
//4. process the result set
//5.handle the sql exception if gets the generated
//6.close the connection

public class JdbcUtil {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	public static Connection getJdbcConnection() throws SQLException, IOException {
		Connection connection = null;

		// not required to write this because from java 4.x auto loading feature is
		// enabled
		// 1.load and register the driver
		// Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("driver loaded and registered sucessfully");

		// 2.establish the connection with the database
		// String url = "jdbc:mysql://localhost:3306/amit";
		// username and password vary from user to user
		// String userName = "root";
		// String passWord = "root";

		// another alternative to store the url,userName and passWord in
		// application.properties file.it helps to not recompile the compiling the data
		// after changes in data
		// store application.properties file in the home directory of the project
		String location = "application.properties";
		FileInputStream fis = new FileInputStream(location);
		Properties properties = new Properties();
		properties.load(fis);
		String url = properties.getProperty("url");
		String userName = properties.getProperty("userName");
		String passWord = properties.getProperty("passWord");
		connection = DriverManager.getConnection(url, userName, passWord);
		System.out.println("\nConnection established sucesfully");
		System.out.println("The implementation classname is " + connection.getClass().getName());
		return connection;
	}

	public static void cleanup(Connection conn, Statement statement, ResultSet resultset) throws SQLException {
		if (conn != null) {
			conn.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (resultset != null) {
			resultset.close();
		}
		System.out.println("\nConnection closed sucessfully");
	}
}
