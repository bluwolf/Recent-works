import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class AddHorseCommand extends SQLCommand{

	public AddHorseCommand(ReentrantLock queryLock) {
		super(queryLock);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/lab11", "root", "");
		String query = "SELECT word FROM lab11.words";
		Statement st = (Statement) conn.createStatement();	       
	      // execute the query, and get a java resultset
	    ResultSet rs = st.executeQuery(query);
    
	    Random rand = new Random();
	    int r1 = rand.nextInt( 100 ); 
	    int r2 = rand.nextInt( 100 ); 
	    int pick1=0;
	    int pick2 =0;
	    String FirstName =null;
	    String LastName=null;
	    
	    while (rs.next()){ 
	    	if(pick1 ==r1){  
		        FirstName = rs.getString("word");
	        }
	    	if(pick2 ==r2){  
		        LastName = rs.getString("word");
	        }  
	        pick1++;
	        pick2++;
	      }
	      st.close();
	      
	      String name = FirstName +" "+ LastName;
	      System.out.format("%s\n",name);
//	      String query1 = " insert into horse (horse_id, name)"
//	    	        + " values (?, ?)";	
	      String query1 = " insert into horse (name)"
	    	        + " values (?)";	
	      PreparedStatement prepared = (PreparedStatement) conn.prepareStatement(query1);
//	      prepared.setInt(1,1);
	      prepared.setString(1,name);
	      prepared.execute();	 

		return false;
	}


}
