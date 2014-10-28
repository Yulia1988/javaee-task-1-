package com.academysmart.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.exception.ServletException;
import com.academysmart.model.Employee;

public class EmployeeRepositorySingleton {

	private static EmployeeRepositorySingleton instance;
	private static List<Employee> employees = new ArrayList<Employee>();
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/xe";
	static final String USER = "Yulia";
	static final String PASS = "12345";

	public static EmployeeRepositorySingleton getRepository() {
		// TODO implement method that returns repository instance
		if (instance == null) {
			instance = new EmployeeRepositorySingleton();

		}
		return instance;
	}

	public static void  getDataBase() {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql = "SELECT * FROM STUFF";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// int id = rs.getInt("ID");
				Employee employee = new Employee();
				employee.setName(rs.getString("NAME"));
				employee.setSurname(rs.getString("SURNAME"));
				employee.setEmail(rs.getString("EMAIL"));
				employees.add(employee);
			}
			rs.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

	// int id = 1;

	public void addEmployee(String fname, String lname, String email)
			throws IncorrectEmailException, ServletException, SQLException {
		// TODO implement method that adds an employee to repository
		// This method should check that email is not used by other employees
		Employee employee = new Employee();

		if (fname.equals("") || lname.equals("") || email.equals("")) {
			throw new ServletException("Fill in all the fields");
		}
		// employee.setId(String.valueOf(id));
		employee.setName(fname);
		employee.setSurname(lname);
		employee.setEmail(email);
		for (Employee i : employees) {
			if (i.getEmail().equals(employee.getEmail())) {
				throw new IncorrectEmailException("This email already exists");
			}
			employees.add(employee);
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				stmt = conn.prepareStatement("insert into STUFF "
						+ "values( ?, ?, ?)");
				// stmt.setInt(1, id);
				stmt.setString(1, fname);
				stmt.setString(2, lname);
				stmt.setString(3, email);
				stmt.executeUpdate();

			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {

				e.printStackTrace();
			} finally {

				try {
					if (stmt != null)
						conn.close();
				} catch (SQLException se) {
				}
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}

			}
			// id++;

		}

	}

	public List<Employee> getAllEmployees() {
	//getDataBase();
		return employees;
	}

}
