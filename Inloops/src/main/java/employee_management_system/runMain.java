package employee_management_system;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class runMain {
	public static void main(String[] args) {
		try { 
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Inloops?user=root&password=9497");
			Scanner sc = new Scanner(System.in);
			PreparedStatement ps1 = con.prepareStatement("INSERT INTO Employee_Details (Name, Email, Department, Salary) VALUES(?, ?, ?, ?)");
			PreparedStatement ps2 = con.prepareStatement("UPDATE Employee_Details "
					+ "SET Name = ?, Email = ?, Department = ?, Salary = ? "
					+ "WHERE ID = ?;");
			PreparedStatement ps3 = con.prepareStatement("UPDATE Employee_Details "
					+ "SET Status = 'INACTIVE' "
					+ "WHERE ID = ?");
			Statement state = con.createStatement();
			while (true) {
				System.out.print("1 -> Add Employee\n"
						+ "2 -> Fetch Employee by ID\n"
						+ "3 -> Fetch all Active Employees\n"
						+ "4 -> Update Employee\n"
						+ "5 -> Delete Employee"
						+ "\n6 -> Exit Application"
						+ "\nSelect the option number from above : ");
				int choice = sc.nextInt();
				sc.nextLine();
				if (choice == 6 ) {
					System.out.println("Until next time");
					break;
				}
				switch(choice) {
				case 1:
					System.out.print("Enter Employee Name : ");
					String name = sc.nextLine();
					System.out.print("Enter Email : ");
					String email = sc.next();
					System.out.print("Enter Department : ");
					String Department = sc.next();
					System.out.print("Enter Salary : ");
					double salary = sc.nextDouble();
					ps1.setString(1, name);
					ps1.setString(2, email);
					ps1.setString(3, Department);
					ps1.setDouble(4, salary);
					ps1.executeUpdate();
					System.out.print("Data Entered Successfully!");
					break;
				case 2:
					System.out.print("Enter ID of the Employee : ");
					int id2 = sc.nextInt();
					String query = "SELECT * FROM Employee_Details WHERE ID = " + id2;
					ResultSet rs = state.executeQuery(query);
					if (rs.next()) { 
	                    System.out.println("ID: " + rs.getInt("id"));
	                    System.out.println("Name: " + rs.getString("name"));
	                    System.out.println("Dept: " + rs.getString("department"));
	                    System.out.println("Salary: " + rs.getDouble("salary"));
	                    System.out.println("Status: " + rs.getString("status") + "\n");
	                } else {
	                    System.out.println("Employee not found!");
	                }
					break;
				case 3:
					System.out.println("All employee Details : \n");
					String query2 = "SELECT * FROM Employee_Details WHERE Status = 'ACTIVE'";
					ResultSet rsAll = state.executeQuery(query2);
					while(rsAll.next()) {
						System.out.println("ID : " + rsAll.getInt("id"));
				        System.out.println("Name : " + rsAll.getString("name"));
				        System.out.println("Email : " + rsAll.getString("email"));
				        System.out.println("Department : " + rsAll.getString("department"));
				        System.out.println("Salary : " + rsAll.getDouble("salary"));
				        System.out.println("Status : " + rsAll.getString("status"));
				        System.out.println("=====================\n");		
						}
					break;
				case 4:
					System.out.print("Enter ID of the Employee to be updated : ");
					int id3 = sc.nextInt();
					sc.nextLine();
					System.out.println("\nEnter the new details of Employee : \n");
					System.out.print("Enter Employee Name : ");
					String name1 = sc.nextLine();
					System.out.print("Enter Email : ");
					String email1 = sc.next();
					System.out.print("Enter Department : ");
					String Department1 = sc.next();
					System.out.print("Enter Salary : ");
					double salary1 = sc.nextDouble();
					ps2.setString(1, name1);
					ps2.setString(2, email1);
					ps2.setString(3, Department1);
					ps2.setDouble(4, salary1);
					ps2.setInt(5, id3);
					ps2.executeUpdate();
					System.out.println("Successfully Updated!");
					break;
				case 5:
					System.out.print("Enter ID of the Employee to be Deleted : ");
					int id4 = sc.nextInt();
					ps3.setInt(1, id4);
					ps3.executeUpdate();
					break;
				default:
					System.out.println("Invalid choice");
				}
			}
			con.close();
			state.close();
			sc.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
