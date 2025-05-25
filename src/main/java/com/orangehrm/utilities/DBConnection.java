package com.orangehrm.utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DBConnection {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/orangehrm";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "";

	public static Connection getDBConnection() {

		try {

			System.out.println("Database Cennection Initiated ..!!");
			Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			System.out.println("Database Connection done successfully..!!");

			return con;

		} catch (SQLException e) {

			System.out.println("Error while Connecting to the Database..");
			e.printStackTrace();
			return null;
		}
	}

//	To get the employee details from DB and storing in Map Collection
	public static Map<String, String> getEmployeeDetails(String employee_id) {

		String sql_query = "SELECT emp_firstname, emp_middle_name, emp_lastname FROM hs_hr_empoyee WHERE employee_id = "
				+ employee_id;

		Map<String, String> employee_Details = new HashMap<>();

		try (Connection con = getDBConnection();
				Statement statmnt = con.createStatement();
				ResultSet result = statmnt.executeQuery(sql_query)) {

			System.out.println("Executing query: " + sql_query);

			if (result.next()) {
//				catch employee details
				String first_name = result.getString("emp_firstname");
				String middle_name = result.getString("emp_middle_name");
				String last_name = result.getString("emp_lastname");

//				Store in Map
				employee_Details.put("first_name", first_name);
				employee_Details.put("middle_name", middle_name != null ? middle_name : "");
				employee_Details.put("last_name", last_name);

				System.out.println("SQL query Executed Successfully..!");
				System.out.println("Employee Data Fetched Successfully : " + employee_Details);

			} else {
				System.out.println("Employee not Found..");
			}
		} catch (SQLException e) {
			System.out.println("Error while executing SQL query..!!");
			e.printStackTrace();

		}

		return employee_Details;
	}

}
