import java.net.Socket;
import java.util.Random;
import javax.swing.Timer;
//import java.util.TimerTask;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ClientReceive extends JFrame{
	
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
     JLabel myHealth = new JLabel("10/10 Health");
	 JLabel otherHealth = new JLabel("Waiting for other player");
	 JLabel message = new JLabel();
	 JButton sb = new JButton("Sword");
	 JButton mb = new JButton("Magic");
	 int max = 5;
	 int min = 1;
    
	public ClientReceive( String hostname, int port ) {		
		 super("Player2");

		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		 myHealth = new JLabel("10/10 Health");
		 otherHealth = new JLabel("Waiting for other player");
		 sb = new JButton("Sword");
		 mb = new JButton("Magic");
		 sb.setEnabled(false);
		 mb.setEnabled(false);
		 myHealth.setAlignmentX(Component.CENTER_ALIGNMENT);
		 otherHealth.setAlignmentX(Component.CENTER_ALIGNMENT);
		 sb.setAlignmentX(Component.CENTER_ALIGNMENT);
		 mb.setAlignmentX(Component.CENTER_ALIGNMENT);
		 message.setAlignmentX(Component.CENTER_ALIGNMENT);
		 
		 

	     
		 sb.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 ((JButton) e.getSource()).setEnabled(false); 
            	 mb.setEnabled(false);
            	 MyListener listener = new MyListener(((JButton) e.getSource()), mb);
                new Timer(4000, listener).start();
            	 System.out.println("sword damage");
                 out.println("MOVE " + 2);}});
		 
		 mb.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 ((JButton) e.getSource()).setEnabled(false); 
            	 sb.setEnabled(false);
            	 MyListener listener = new MyListener(((JButton) e.getSource()),sb);
                 new Timer(4000, listener).start();
       		     Random random = new Random();  
    		    final int randomNumber = random.nextInt(max - min + 1) + min;
            	 System.out.println("random damage: " +randomNumber);
                 out.println("MOVE " + randomNumber);}});
		 
		 mainPanel.add(myHealth);
		 mainPanel.add(otherHealth);
		 mainPanel.add(sb);
		 mainPanel.add(mb);
		 mainPanel.add(message);
		 add(mainPanel);

		 setVisible(true);
		 
		 
		try {
			socket = new Socket (hostname, port );
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    out = new PrintWriter(socket.getOutputStream(), true);

		} catch (IOException ioe) {
			System.out.println( "IOExceptionin Client constructor: " + ioe.getMessage() );
		}		
		
	}
	public class MyListener implements ActionListener{

	    private JButton b1;
	    private JButton b2;
	    public MyListener(JButton b1,JButton b2){
	        this.b1 = b1;
	        this.b2 = b2;
	    }
	    public void actionPerformed(ActionEvent e) {
	    	b1.setEnabled(true);
	    	b2.setEnabled(true);
	    }
	}
	  public void play() throws Exception {
	        String response;
	        try {
//	            response = in.readLine();
	           
	            while (true) {
	                response = in.readLine();
	                if (response.startsWith("RESET")) {
		                otherHealth.setText("10/10");
		                myHealth.setText("10/10");
		                MyListener listener = new MyListener(mb,sb);
//		                MyListener listener1 = new MyListener(sb);
		                new Timer(4000, listener).start();
//		                new Timer(3000, listener1).start();
		                
		            }
	                if (response.startsWith("VALID_MOVE1")) {
	                	int n = Integer.parseInt(response.substring(12));
	                	System.out.println("Received Valid move"+n);
	                	otherHealth.setText(n+"/10 ");
	                    message.setText("Valid move");
//	                    currentSquare.setIcon(icon);
//	                    currentSquare.repaint();
	                } else if (response.startsWith("VALID_MOVE2")){
	                	int n = Integer.parseInt(response.substring(12));
	                	System.out.println("Received Valid move"+n);
	                	myHealth.setText(n+"/10 ");
	                    message.setText("Valid move");
	                	
	                }
	                else if (response.startsWith("OPPONENT_MOVED")) {
	                    int loc = Integer.parseInt(response.substring(15));
//	                    board[loc].setIcon(opponentIcon);
//	                    board[loc].repaint();
	                    message.setText("Opponent moved, your turn");
	                } else if (response.startsWith("VICTORY")) {
	                	message.setText("You win");
	                    break;
	                } else if (response.startsWith("DEFEAT")) {
	                	message.setText("You lose");
	                    break;
	                } else if (response.startsWith("TIE")) {
	                	message.setText("You tied");
	                    break;
	                } else if (response.startsWith("MESSAGE")) {
//	                	 otherHealth.setText(response.substring(8));
	                	message.setText(response.substring(8));
	                }
	            }
	            out.println("QUIT");
	        }
	        finally {
	            socket.close();
	        }
	    }
  private boolean wantsToPlayAgain() {
      int response = JOptionPane.showConfirmDialog(this,
          "Want to play again?",
          "This game is fun",
          JOptionPane.YES_NO_OPTION);
      this.dispose();
      return response == JOptionPane.YES_OPTION;
  }
	public static void main( String [] args ) throws Exception {
		if ( args.length!= 2 ) {
			System.out.println( "USAGE: java Client [hostname] [port]" );
			return;
		}
		while (true) {
			ClientReceive client= new ClientReceive( args[0], Integer.parseInt(args[1] ) );
            client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            client.setSize(300, 200);
            client.setVisible(true);
            client.setResizable(false);
 
            client.play();
            if (!client.wantsToPlayAgain()) {
                break;
            }
        }
	}
}
