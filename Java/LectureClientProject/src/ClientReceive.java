import java.net.Socket;
import java.io.*;
public class ClientReceive {
	public ClientReceive( String hostname, int port ) {
		try {
			Socket s = new Socket (hostname, port );
			BufferedReader br= new BufferedReader( new InputStreamReader( s.getInputStream() ) );
//			PrintWriter pw = new PrintWriter( s.getOutputStream() );
//			pw.println( "Here is a line from a client" );
//			pw.flush();
			System.out.println("Waiting for message from server");
			String line = br.readLine();
			System.out.println( "Here is the line received from the server: " + line );
//			pw.close();
			br.close();
			s.close();
		} catch (IOException ioe) {
			System.out.println( "IOExceptionin Client constructor: " + ioe.getMessage() );
		}
	}
	public static void main( String [] args ) {
		if ( args.length!= 2 ) {
			System.out.println( "USAGE: java Client [hostname] [port]" );
			return;
		}
		ClientReceive client= new ClientReceive( args[0], Integer.parseInt(args[1] ) );
	}
}
