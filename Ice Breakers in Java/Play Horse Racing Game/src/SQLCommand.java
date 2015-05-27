

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

public abstract class SQLCommand implements Runnable
{
	public static final String DB_ADDRESS = "jdbc:mysql://localhost/";
	public static final String DB_NAME = "lab11";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String USER = "root";
	public static final String PASSWORD = "";
	protected ReentrantLock queryLock;
	int number=-11;
	int place =1;
	public SQLCommand(ReentrantLock queryLock)
	{
		this.queryLock = queryLock;
	}
	@Override
	public void run() {
		queryLock.lock();
		try{
		System.out.print("Executing... ");
		execute();
		System.out.println("Done");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			queryLock.unlock();
		}
	}
	public abstract boolean execute()throws SQLException;
}
