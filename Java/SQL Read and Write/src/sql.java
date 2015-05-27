import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.PreparedStatement;

import com.mysql.fabric.Server;



public class sql {
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");

//		Connection sc =DriverManager.getConnection("jdbc:mysql://localhost/newbieschema", "root", "");
		 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/newbieschema", "root", "");
			String firstname, lastname;
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter your fistname");
			firstname = sc.nextLine();
			System.out.println("Please enter your lastname");
			lastname = sc.nextLine();
		 //PreparedStatement stmt = null;
		  System.out.println("Creating statement...");


		String insert = "INSERT INTO user(first_name, last_name) VALUES (?, ?);";
		      
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO user(first_name, last_name) VALUES (?, ?);");
	      
	      
	      //Bind values into the parameters.
	      ((PreparedStatement) stmt).setString(1, firstname);  // This would set age
	      ((PreparedStatement) stmt).setString(2, lastname); // This would set ID
	      stmt.execute();
	      
//	      String sql = "UPDATE Employees set age=? WHERE id=?";	      
//	      int rows = stmt.executeUpdate(sql);
//	      System.out.println("Rows impacted : " + rows );
//
//	      // Let us select all the records and display them.

	      System.out.println("Inserting records into the table...");
	      Statement stmt2 = conn.createStatement();
	      String sql = "SELECT keyid, first_name, last_name FROM user";
	      ResultSet rs = stmt2.executeQuery(sql);
	      
//	      ((PreparedStatement) stmt).setString(1,"Ford");
//	      sql = "INSERT INTO Registration " +
//	                   "VALUES (101, 'Mahnaz', 'Fatma', 25)";
//	      stmt.executeUpdate(sql);
//	      sql = "INSERT INTO Registration " +
//	                   "VALUES (102, 'Zaid', 'Khan', 30)";
//	      stmt.executeUpdate(sql);
//	      sql = "INSERT INTO Registration " +
//	                   "VALUES(103, 'Sumit', 'Mittal', 28)";
//	      stmt.executeUpdate(sql);
	      System.out.println("Inserted records into the table...");
	      String haha =rs.getString(0);
	      System.out.println(haha);

//		PreparedStatement fn = (PreparedStatement) sc.prepareStatement("what is your first name?");
//		PreparedStatement ln = (PreparedStatement) sc.prepareStatement("what is your last name?");
//		INSERT INTO user (first_name,last_name);
//		VALUES (fn,ln);

		
	}

}
