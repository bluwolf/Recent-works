import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class RaceCommand extends SQLCommand{

	public RaceCommand(ReentrantLock queryLock) {
		super(queryLock);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute() throws SQLException {
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/lab11", "root", "");
		String query = "SELECT MAX(race_number) FROM lab11.race_result";
		
		Statement st = (Statement) conn.createStatement();	       
	      // execute the query, and get a java resultset
	    ResultSet rs = st.executeQuery(query);
	    if (rs.next()) {
	    	  number = rs.getInt(1);
	    }	    
	    System.out.println(number);
	    
	    
	    String select = "SELECT horse_id FROM lab11.horse";
		Statement st1 = (Statement) conn.createStatement();	       
	      // execute the query, and get a java resultset
	    ResultSet rs1 = st.executeQuery(select);
    
	    Random rand = new Random();
//	    int r1 = rand.nextInt( 8 ); 
//	    int r2 = rand.nextInt( 8);
//	    int r3 = rand.nextInt( 8 ); 
//	    int r4 = rand.nextInt( 8); 
//	    int r5 = rand.nextInt( 8 ); 
//	    int r6 = rand.nextInt( 8); 
//	    int r7 = rand.nextInt( 8 ); 
//	    int r8 = rand.nextInt( 8); 

	      for(int i=0;i<8;i++){
		      String query1 = " insert into race_result (race_number, horse_id, place)"
		    	        + " values (?,?,?)";	
		      PreparedStatement prepared = (PreparedStatement) conn.prepareStatement(query1);
	//	      prepared.setInt(1,1);
		      prepared.setInt(1,number);
		      prepared.setInt(2,rand.nextInt( 8 ));
		      prepared.setInt(3,i+1);
		      prepared.execute();	
	      }

	    number++;
//	    System.out.println("One Race Done");
		return false;
	}

}
