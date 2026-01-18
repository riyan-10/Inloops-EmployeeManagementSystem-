package employee_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class tableCreate {
	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Inloops?user=root&password=9497");
			Statement state = con.createStatement();
			String query = "CREATE TABLE Employee_Details "
					+ "(id INT PRIMARY KEY AUTO_INCREMENT,"
					+ " Name VARCHAR(50) NOT NULL,"
					+ " Email VARCHAR(50) UNIQUE NOT NULL,"
					+ " Department VARCHAR(50),"
					+ " Salary DECIMAL(10,2),"
					+ " Status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE'"
					+ ")";
			state.execute(query);
			state.close();
			con.close();
			System.out.println("Table Successfully Created");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
