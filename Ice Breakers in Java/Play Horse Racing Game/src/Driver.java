import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;


public class Driver {
	public static void main(String [] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
	      
		ReentrantLock lock = new ReentrantLock();
		for (int i=0; i < 8; i++) {
			Thread t = new Thread(new AddHorseCommand(lock));
			t.start();
		}
		
//		for (int i=0; i < 8; i++) {
			Thread t1 = new Thread(new RaceCommand(lock));
			t1.start();
//		}
	}

}
