import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server {
	public Server(int port) {
		try {
			ServerSocket ss= new ServerSocket( port );
//			while(true) {
				Socket s1 = ss.accept();
				Socket s2 = ss.accept();
				ServerThread st= new ServerThread(s1,s2);
				st.start();
//				st = new ServerThread(s2);
//				st.start();
//			}
		} catch (IOException ioe) {
			System.out.println("IOExceptionin Server constructor: " + ioe.getMessage());
		}
	}
	public static void main(String [] args) {
		if (args.length!= 1) {
			System.out.println("USAGE: java Server [port]");
			return;
		}
		Server server= new Server(Integer.parseInt(args[0]));
	}
}
class ServerThread extends Thread {
	private Socket s1, s2;
	public ServerThread(Socket s1, Socket s2) {
		this.s1= s1;
		this.s2 = s2;
	}
	public void run() {
		try {
			BufferedReader br= new BufferedReader( new InputStreamReader(s1.getInputStream() ) );
			PrintWriter pw = new PrintWriter( s2.getOutputStream() );
			String line = br.readLine();
//			System.out.println( "Line received from client " + s.getInetAddress() + ": " + line );
			pw.println( "Line going to client" );
			pw.flush();
		} catch (IOException ioe) {
			System.out.println("IOExceptionin ServerThreadconstructor: " + ioe.getMessage());
		}
	}
}