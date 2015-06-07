package calendar;

 import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.Vector;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.Arrays;



import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;



 public class Calendar extends JFrame implements ActionListener, MouseListener{

	 final JButton[] janCells = new JButton[31];
	 final JButton[] febCells = new JButton[28];
	 final JButton[] marCells = new JButton[31];
	 final JButton[] aprCells = new JButton[30];
	 final JButton[] mayCells = new JButton[31];
	 final JButton[] junCells = new JButton[30];
	 final JButton[] julCells = new JButton[31];
	 final JButton[] augCells = new JButton[31];
	 final JButton[] sepCells = new JButton[30];
	 final JButton[] octCells = new JButton[31];
	 final JButton[] novCells = new JButton[30];
	 final JButton[] decCells = new JButton[31];

	 
	 final JButton[] janfCells = new JButton[3]; // cells to be disabled from beginning
	 final JButton[] febfCells = new JButton[6];
	 final JButton[] marfCells = new JButton[6];
	 final JButton[] aprfCells = new JButton[2];
	 final JButton[] mayfCells = new JButton[4];
	 final JButton[] julfCells = new JButton[2];
	 final JButton[] augfCells = new JButton[5];
	 final JButton[] sepfCells = new JButton[1];
	 final JButton[] octfCells = new JButton[3];
	 final JButton[] novfCells = new JButton[6];
	 final JButton[] decfCells = new JButton[1];
	 
	 JLabel[] dayLabel = new JLabel[7];
	 String[] date = {"Sun","Mon","Tues","Wed","Thu","Fri","Sat"};
	 String[] monthString = {"January 2014","February 2014","March 2014","April 2014","May 2014","June 2014","July 2014","August 2014","September 2014", "October 2014","November 2014","December 2014" };
	 JPanel[] monthPanel = new JPanel[12];
	 String[] monWithoutYear ={"January","February","March","April","May","June","July","August","September", "October","November","December" };
	 

	 JPanel mainPanel = new JPanel();
	 Vector<LEvents> ev = new Vector<LEvents>();
	 JPanel lowestPanel = new JPanel();

	


	 
	 
	public int findDate(MouseEvent e){
		for(int i =0;i<janCells.length;i++){if(janCells[i]== ((JButton)e.getSource())){return i+1;}}
		for(int i =0;i<febCells.length;i++){if(febCells[i]== ((JButton)e.getSource())){return i+1;}}
		for(int i =0;i<marCells.length;i++){if(marCells[i]== ((JButton)e.getSource())){return i+1;}}
		for(int i =0;i<aprCells.length;i++){if(aprCells[i]== ((JButton)e.getSource())){return i+1;}}
		for(int i =0;i<mayCells.length;i++){if(mayCells[i]== ((JButton)e.getSource())){return i+1;}}
		for(int i =0;i<junCells.length;i++){if(junCells[i]== ((JButton)e.getSource())){return i+1;}}
		for(int i =0;i<julCells.length;i++){if(julCells[i]== ((JButton)e.getSource())){return i+1;}}
		for(int i =0;i<augCells.length;i++){if(augCells[i]== ((JButton)e.getSource())){return i+1;}}
		for(int i =0;i<sepCells.length;i++){if(sepCells[i]== ((JButton)e.getSource())){return i+1;}}
		for(int i =0;i<octCells.length;i++){if(octCells[i]== ((JButton)e.getSource())){return i+1;}}
		for(int i =0;i<novCells.length;i++){if(novCells[i]== ((JButton)e.getSource())){return i+1;}}
		for(int i =0;i<decCells.length;i++){if(decCells[i]== ((JButton)e.getSource())){return i+1;}}
		return 0;
	}
	public int findMonth(MouseEvent e){
		for(int i =0;i<janCells.length;i++){if(janCells[i]== ((JButton)e.getSource())){return 1;}}
		for(int i =0;i<febCells.length;i++){if(febCells[i]== ((JButton)e.getSource())){return 2;}}
		for(int i =0;i<marCells.length;i++){if(marCells[i]== ((JButton)e.getSource())){return 3;}}
		for(int i =0;i<aprCells.length;i++){if(aprCells[i]== ((JButton)e.getSource())){return 4;}}
		for(int i =0;i<mayCells.length;i++){if(mayCells[i]== ((JButton)e.getSource())){return 5;}}
		for(int i =0;i<junCells.length;i++){if(junCells[i]== ((JButton)e.getSource())){return 6;}}
		for(int i =0;i<julCells.length;i++){if(julCells[i]== ((JButton)e.getSource())){return 7;}}
		for(int i =0;i<augCells.length;i++){if(augCells[i]== ((JButton)e.getSource())){return 8;}}
		for(int i =0;i<sepCells.length;i++){if(sepCells[i]== ((JButton)e.getSource())){return 9;}}
		for(int i =0;i<octCells.length;i++){if(octCells[i]== ((JButton)e.getSource())){return 10;}}
		for(int i =0;i<novCells.length;i++){if(novCells[i]== ((JButton)e.getSource())){return 11;}}
		for(int i =0;i<decCells.length;i++){if(decCells[i]== ((JButton)e.getSource())){return 12;}}
		return 0;
		
	}


	 
	 public Calendar() throws IOException {
		 super("Calendar 2014");
		 setSize(470, 500);
		 setLocation(200, 200);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 setResizable(true);
		
		 
		 mainPanel.setLayout(new CardLayout());
		
		 for(int j=0;j<12;j++){ // create new panels for each month
				 monthPanel[j] = new JPanel();
				 monthPanel[j].setLayout(new BoxLayout(monthPanel[j], BoxLayout.Y_AXIS));
				 
				 JMenuBar menuBar = new JMenuBar();
				 JButton b1 = new JButton("Event Manager");
				 JButton b2 = new JButton("Export");
				 JButton b3 = new JButton("About");
				 b1.addActionListener( new ActionListener() {
					public void actionPerformed(ActionEvent e) {ManagerFrame();}});
				 b2.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent e){
						
						try {
							FileWriter fw = new FileWriter("database.csv");
							PrintWriter writer = new PrintWriter(fw);
							for(int i=0;i<ev.size();i++){
								// 0=h, 2=min;5=ampm
								String start = ev.get(i).startTime;
								String end = ev.get(i).endTime;
								String[] splitStart = start.split("\\s+");
								String[] splitEnd = end.split("\\s+");


								int sh = Integer.parseInt(splitStart[0]);
								int sm = Integer.parseInt(splitStart[2]);
								String sap = (splitStart[5]);
								
								int eh = Integer.parseInt(splitEnd[0]);
								int em = Integer.parseInt(splitEnd[2]);
								String eap = (splitEnd[5]);
								
								writer.print(ev.get(i).year+
										","+monWithoutYear[ev.get(i).Month-1]+
										","+ev.get(i).day+","+ev.get(i).Title+
										","+ev.get(i).Location+",");
										//","+sh+":"+sm+":"+"00"+sap+","+eh+":"+em+":"+"00"+eap);
								writer.format("%02d", sh);
								writer.print(":");
								writer.format("%02d", sm);
								writer.print(": 00");
								writer.print(sap);
								writer.print(",");
								writer.format("%02d", eh);
								writer.print(":");
								writer.format("%02d", em);
								writer.print(": 00");
								writer.print(eap);
								writer.println();
							}
							
							writer.flush();
							writer.close();
							JOptionPane.showMessageDialog(null,"Events exported Successfully","Event Export",JOptionPane.WARNING_MESSAGE);
						} catch (IOException e1) {
							System.out.println("Export failed");
							e1.printStackTrace();
						}
					 }
				 });
				 b3.addActionListener(new ActionListener(){
					 public void actionPerformed(ActionEvent e){
						try {
							AboutFrame();
						} catch (IOException e1) {
							System.out.println("Open About failed");
							e1.printStackTrace();
						}
					 }
				 });
				 menuBar.add(b1);
				 menuBar.add(b2);
				 menuBar.add(b3);
				 
//				 BlinkLabel b = new BlinkLabel("HELLO!!!!");
//				 menuBar.add(b);
				 

				 
				 JPanel upperPanel = new JPanel(); 
				 upperPanel.setLayout(new BorderLayout());
				 upperPanel.add(menuBar, BorderLayout.NORTH);
				 
				 JPanel lowerPanel = new JPanel();
				 lowerPanel.setLayout(new GridLayout(0,7));

				 BufferedImage pic = ImageIO.read(new File("arrow_sans_left-128.png"));

				 Image buffered = pic.getScaledInstance(20,20,Image.SCALE_SMOOTH);
				 ImageIcon image = new ImageIcon(buffered);
				 
				 JButton leftb = new JButton();
				 leftb.setIcon(image); // put image with icon
				 
				 BufferedImage pic1 = ImageIO.read(new File("arrow_sans_right_32.png"));
				 Image buffered1 = pic1.getScaledInstance(20,20,Image.SCALE_SMOOTH);
				 ImageIcon image1 = new ImageIcon(buffered1);	
				 
				 JButton rightb = new JButton();		 
				 rightb.setIcon(image1); 
				 
				 
				 JPanel laPanel = new JPanel(); // left create arrow panel and right arrow panel
				 JPanel raPanel = new JPanel();

				 
				 
				 JLabel month = new JLabel(monthString[j], JLabel.CENTER);
				 month.setFont (month.getFont ().deriveFont (20.0f));		 
	
				 
				 for (int i = 0; i < 7; i++){ // write date labels
					 dayLabel[i]=new JLabel(date[i]);
					 dayLabel[i].setHorizontalAlignment(JLabel.CENTER);
					 dayLabel[i].setVerticalAlignment(JLabel.BOTTOM);
					 dayLabel[i].setFont (dayLabel[i].getFont ().deriveFont (10.0f));
					 lowerPanel.add(dayLabel[i]);
				 }
				 switch(j){ // set up dates layout based on current month
				 
				 case 0:
						 for (int i = 0; i < 3; i++){ // set the nonusable dates
						 
					        janfCells[i] = new JButton();
					        janfCells[i].setText("");
					        janfCells[i].setPreferredSize(new Dimension(100, 100));
					        janfCells[i].setEnabled(false);
					        lowerPanel.add(janfCells[i]);		        	
						 }
						 for (int i = 0; i < janCells.length; i++){ //set out dates in number for Janu
						 
					        janCells[i] = new JButton();
					        janCells[i].setText(Integer.toString(i+1));
					        janCells[i].setPreferredSize(new Dimension(100, 100));
					        lowerPanel.add(janCells[i]);
					        janCells[i].addMouseListener(this);
					        janCells[i].addActionListener(this);		        	
						 }
						 
						 //set up greyed out buttons for next month
						 for(int i=0;i<1;i++){ 
							 JButton useless = new JButton(Integer.toString(i+1));
							 useless.setEnabled(false);
							 lowerPanel.add(useless);						 
						 }
						 
						 leftb.addActionListener(new arrowButtonClicked(monthString[11], mainPanel));
						 laPanel.add(leftb);
						 rightb.addActionListener(new arrowButtonClicked(monthString[j+1], mainPanel));
						 raPanel.add(rightb);
					 break;
					 
				 case 1:
					 for (int i = 0; i < febfCells.length; i++){ // set the nonusable dates
						 
					        febfCells[i] = new JButton();
					        febfCells[i].setText("");
					        febfCells[i].setPreferredSize(new Dimension(100, 100));
					        febfCells[i].setEnabled(false);
					        lowerPanel.add(febfCells[i]);		        	
						 }
						 for (int i = 0; i < febCells.length; i++){ //set out dates in number for Janu
						 
					        febCells[i] = new JButton();
					        febCells[i].setText(Integer.toString(i+1));
					        febCells[i].setPreferredSize(new Dimension(100, 100));
					        lowerPanel.add(febCells[i]);
					        febCells[i].addMouseListener(this);
					        febCells[i].addActionListener(this);        
						 }
						 for(int i=0;i<1;i++){ 
							 JButton useless = new JButton(Integer.toString(i+1));
							 useless.setEnabled(false);
							 lowerPanel.add(useless);						 
						 }
						 leftb.addActionListener(new arrowButtonClicked(monthString[j-1], mainPanel));
						 laPanel.add(leftb);
						 rightb.addActionListener(new arrowButtonClicked(monthString[j+1], mainPanel));
						 raPanel.add(rightb);
					 break;
					 
				 case 2:
					 for (int i = 0; i < marfCells.length; i++){ // set the nonusable dates
						 
						 	marfCells[i] = new JButton();
						 	marfCells[i].setText("");
						 	marfCells[i].setPreferredSize(new Dimension(100, 100));
						 	marfCells[i].setEnabled(false);
					        lowerPanel.add(marfCells[i]);		        	
						 }
						 for (int i = 0; i < marCells.length; i++){ //set out dates in number for Janu
						 
							marCells[i] = new JButton();
							marCells[i].setText(Integer.toString(i+1));
							marCells[i].setPreferredSize(new Dimension(100, 100));
					        lowerPanel.add(marCells[i]);
					        marCells[i].addMouseListener(this);
					        marCells[i].addActionListener(this);		        	
						 }
						 for(int i=0;i<5;i++){ 
							 JButton useless = new JButton(Integer.toString(i+1));
							 useless.setEnabled(false);
							 lowerPanel.add(useless);						 
						 }

						 leftb.addActionListener(new arrowButtonClicked(monthString[j-1], mainPanel));
						 laPanel.add(leftb);
						 rightb.addActionListener(new arrowButtonClicked(monthString[j+1], mainPanel));
						 raPanel.add(rightb);
					 break;
					 
				 case 3:
					 for (int i = 0; i < aprfCells.length; i++){ // set the nonusable dates
						 
						 	aprfCells[i] = new JButton();
						 	aprfCells[i].setText("");
						 	aprfCells[i].setPreferredSize(new Dimension(100, 100));
						 	aprfCells[i].setEnabled(false);
					        lowerPanel.add(aprfCells[i]);		        	
						 }
						 for (int i = 0; i < aprCells.length; i++){ //set out dates in number for Janu
						 
							aprCells[i] = new JButton();
							aprCells[i].setText(Integer.toString(i+1));
							aprCells[i].setPreferredSize(new Dimension(100, 100));
					        lowerPanel.add(aprCells[i]);
					        aprCells[i].addMouseListener(this);
					        aprCells[i].addActionListener(this);		        	
						 }
						 for(int i=0;i<3;i++){ 
							 JButton useless = new JButton(Integer.toString(i+1));
							 useless.setEnabled(false);
							 lowerPanel.add(useless);						 
						 }
						 leftb.addActionListener(new arrowButtonClicked(monthString[j-1], mainPanel));
						 laPanel.add(leftb);
						 rightb.addActionListener(new arrowButtonClicked(monthString[j+1], mainPanel));
						 raPanel.add(rightb);
					 break;
					 
				 case 4:
					 for (int i = 0; i <mayfCells.length; i++){ // set the nonusable dates
						 
						 	mayfCells[i] = new JButton();
						 	mayfCells[i].setText("");
						 	mayfCells[i].setPreferredSize(new Dimension(100, 100));
						 	mayfCells[i].setEnabled(false);
					        lowerPanel.add(mayfCells[i]);		        	
						 }
						 for (int i = 0; i < mayCells.length; i++){ //set out dates in number for Janu
						 
							mayCells[i] = new JButton();
							mayCells[i].setText(Integer.toString(i+1));
							mayCells[i].setPreferredSize(new Dimension(100, 100));
					        lowerPanel.add(mayCells[i]);
					        mayCells[i].addMouseListener(this);
					        mayCells[i].addActionListener(this);		        	
						 }

						 leftb.addActionListener(new arrowButtonClicked(monthString[j-1], mainPanel));
						 laPanel.add(leftb);
						 rightb.addActionListener(new arrowButtonClicked(monthString[j+1], mainPanel));
						 raPanel.add(rightb);
					 break;
					 
				 case 5:
					 
						 for (int i = 0; i < junCells.length; i++){ //set out dates in number for Janu
						 
							junCells[i] = new JButton();
							junCells[i].setText(Integer.toString(i+1));
							junCells[i].setPreferredSize(new Dimension(100, 100));
					        lowerPanel.add(junCells[i]);
					        junCells[i].addMouseListener(this);
					        junCells[i].addActionListener(this);		        	
						 }
						 for(int i=0;i<5;i++){ 
							 JButton useless = new JButton(Integer.toString(i+1));
							 useless.setEnabled(false);
							 lowerPanel.add(useless);						 
						 }
						 
						 leftb.addActionListener(new arrowButtonClicked(monthString[j-1], mainPanel));
						 laPanel.add(leftb);
						 rightb.addActionListener(new arrowButtonClicked(monthString[j+1], mainPanel));
						 raPanel.add(rightb);
					 break;
					 
				 case 6:
					 for (int i = 0; i < julfCells.length; i++){ // set the nonusable dates
						 
						 	julfCells[i] = new JButton();
						 	julfCells[i].setText("");
						 	julfCells[i].setPreferredSize(new Dimension(100, 100));
						 	julfCells[i].setEnabled(false);
					        lowerPanel.add(julfCells[i]);		        	
						 }
						 for (int i = 0; i < julCells.length; i++){ //set out dates in number for Janu
						 
							julCells[i] = new JButton();
							julCells[i].setText(Integer.toString(i+1));
							julCells[i].setPreferredSize(new Dimension(100, 100));
					        lowerPanel.add(julCells[i]);
					        julCells[i].addMouseListener(this);
					        julCells[i].addActionListener(this);		        	
						 }
						 for(int i=0;i<2;i++){ 
							 JButton useless = new JButton(Integer.toString(i+1));
							 useless.setEnabled(false);
							 lowerPanel.add(useless);						 
						 }
						 leftb.addActionListener(new arrowButtonClicked(monthString[j-1], mainPanel));
						 laPanel.add(leftb);
						 rightb.addActionListener(new arrowButtonClicked(monthString[j+1], mainPanel));
						 raPanel.add(rightb);
					 break;
					 
				 case 7:
					 for (int i = 0; i < augfCells.length; i++){ // set the nonusable dates
						 
					        augfCells[i] = new JButton();
					        augfCells[i].setText("");
					        augfCells[i].setPreferredSize(new Dimension(100, 100));
					        augfCells[i].setEnabled(false);
					        lowerPanel.add(augfCells[i]);		        	
						 }
						 for (int i = 0; i < augCells.length; i++){ //set out dates in number for Janu
						 
							augCells[i] = new JButton();
							augCells[i].setText(Integer.toString(i+1));
							augCells[i].setPreferredSize(new Dimension(100, 100));
					        lowerPanel.add(augCells[i]);
					        augCells[i].addMouseListener(this);
					        augCells[i].addActionListener(this);		        	
						 }
						 for(int i=0;i<6;i++){ 
							 JButton useless = new JButton(Integer.toString(i+1));
							 useless.setEnabled(false);
							 lowerPanel.add(useless);						 
						 }
						 leftb.addActionListener(new arrowButtonClicked(monthString[j-1], mainPanel));
						 laPanel.add(leftb);
						 rightb.addActionListener(new arrowButtonClicked(monthString[j+1], mainPanel));
						 raPanel.add(rightb);
					 break;
					 
				 case 8:
					 for (int i = 0; i < sepfCells.length; i++){ // set the nonusable dates
						 
					        sepfCells[i] = new JButton();
					        sepfCells[i].setText("");
					        sepfCells[i].setPreferredSize(new Dimension(100, 100));
					        sepfCells[i].setEnabled(false);
					        lowerPanel.add(sepfCells[i]);		        	
						 }
						 for (int i = 0; i < sepCells.length; i++){ //set out dates in number for Janu
						 
					        sepCells[i] = new JButton();
					        sepCells[i].setText(Integer.toString(i+1));
					        sepCells[i].setPreferredSize(new Dimension(100, 100));
					        lowerPanel.add(sepCells[i]);
					        sepCells[i].addMouseListener(this);
					        sepCells[i].addActionListener(this);		        	
						 }
						 for(int i=0;i<4;i++){ 
							 JButton useless = new JButton(Integer.toString(i+1));
							 useless.setEnabled(false);
							 lowerPanel.add(useless);						 
						 }
						 leftb.addActionListener(new arrowButtonClicked(monthString[j-1], mainPanel));
						 laPanel.add(leftb);
						 rightb.addActionListener(new arrowButtonClicked(monthString[j+1], mainPanel));
						 raPanel.add(rightb);
					 break;
					 
				 case 9:
					 for (int i = 0; i < octfCells.length; i++){ // set the nonusable dates
						 
					        octfCells[i] = new JButton();
					        octfCells[i].setText("");
					        octfCells[i].setPreferredSize(new Dimension(100, 100));
					        octfCells[i].setEnabled(false);
					        lowerPanel.add(octfCells[i]);		        	
						 }
						 for (int i = 0; i < octCells.length; i++){ //set out dates in number for Janu
						 
					        octCells[i] = new JButton();
					        octCells[i].setText(Integer.toString(i+1));
					        octCells[i].setPreferredSize(new Dimension(100, 100));
					        lowerPanel.add(octCells[i]);
					        octCells[i].addMouseListener(this);
					        octCells[i].addActionListener(this);		        	
						 }
						 for(int i=0;i<1;i++){ 
							 JButton useless = new JButton(Integer.toString(i+1));
							 useless.setEnabled(false);
							 lowerPanel.add(useless);						 
						 }
						 leftb.addActionListener(new arrowButtonClicked(monthString[j-1], mainPanel));
						 laPanel.add(leftb);
						 rightb.addActionListener(new arrowButtonClicked(monthString[j+1], mainPanel));
						 raPanel.add(rightb);
					 break;
					 
				 case 10:
					 for (int i = 0; i < novfCells.length; i++){ // set the nonusable dates
						 
						 	novfCells[i] = new JButton();
						 	novfCells[i].setText("");
						 	novfCells[i].setPreferredSize(new Dimension(100, 100));
						 	novfCells[i].setEnabled(false);
					        lowerPanel.add(novfCells[i]);		        	
						 }
						 for (int i = 0; i < novCells.length; i++){ //set out dates in number for Janu
						 
					        novCells[i] = new JButton();
					        novCells[i].setText(Integer.toString(i+1));
					        novCells[i].setPreferredSize(new Dimension(100, 100));
					        lowerPanel.add(novCells[i]);
					        novCells[i].addMouseListener(this);
					        novCells[i].addActionListener(this);		        	
						 }
						 for(int i=0;i<6;i++){ 
							 JButton useless = new JButton(Integer.toString(i+1));
							 useless.setEnabled(false);
							 lowerPanel.add(useless);						 
						 }
						 leftb.addActionListener(new arrowButtonClicked(monthString[j-1], mainPanel));
						 laPanel.add(leftb);
						 rightb.addActionListener(new arrowButtonClicked(monthString[j+1], mainPanel));
						 raPanel.add(rightb);
					 break;
					 
				 case 11:
					 for (int i = 0; i < decfCells.length; i++){ // set the nonusable dates
						 
					        decfCells[i] = new JButton();
					        decfCells[i].setText("");
					        decfCells[i].setPreferredSize(new Dimension(100, 100));
					        decfCells[i].setEnabled(false);
					        lowerPanel.add(decfCells[i]);		        	
						 }
						 for (int i = 0; i < decCells.length; i++){ //set out dates in number for Janu
						 
					        decCells[i] = new JButton();
					        decCells[i].setText(Integer.toString(i+1));
					        decCells[i].setPreferredSize(new Dimension(100, 100));
					        lowerPanel.add(decCells[i]);
					        decCells[i].addMouseListener(this);
					        decCells[i].addActionListener(this);		        	
						 }
						 for(int i=0;i<3;i++){ 
							 JButton useless = new JButton(Integer.toString(i+1));
							 useless.setEnabled(false);
							 lowerPanel.add(useless);						 
						 }
						 leftb.addActionListener(new arrowButtonClicked(monthString[j-1], mainPanel));
						 laPanel.add(leftb);
						 rightb.addActionListener(new arrowButtonClicked(monthString[0], mainPanel));
						 raPanel.add(rightb);
					 break;				 
					 
				 }
				 upperPanel.add(laPanel,  BorderLayout.WEST);
				 upperPanel.add(month, BorderLayout.CENTER);
				 upperPanel.add(raPanel,  BorderLayout.EAST);
				 upperPanel.setPreferredSize(new Dimension(400, 80));
				 
			
				 
				 monthPanel[j].add(upperPanel);
				 monthPanel[j].add(lowerPanel);
				// monthPanel[j].add(lowestPanel);
			 
	 		}
			 
	 				
		
		 for(int i=0;i<12;i++){// add every month to card layout
			 mainPanel.add(monthPanel[i],monthString[i]);
		 }		 
		 String[] splited =showCurrentMonth(mainPanel); //get current date	 
		 // highlight the date
		 hilightCurrentDate(splited);

		 

		//monthPanel[1].setVisible(true);
		 add(mainPanel);
		 setVisible(true);
	 }
	
	 class arrowButtonClicked implements ActionListener {
			  private String numberString;
			  private JPanel jp;
			  public arrowButtonClicked(String numberString, JPanel jp) {
				  this.numberString = numberString;
				  this.jp = jp;
			  }
		  public void actionPerformed(ActionEvent ae) {
			  CardLayout cl = (CardLayout)jp.getLayout();
			  cl.show(jp, numberString);
		  }
	}
	 javax.swing.Timer mouseTimer = null; // timer for double and single click
	 

	public  void mouseClicked(final MouseEvent e) {		
		if (e.getClickCount() == 1) {
			disselectOthers();
			//final javax.swing.Timer mouseTimer = null;
			  mouseTimer = new javax.swing.Timer(350, new ActionListener() {
				   public void actionPerformed(ActionEvent evt) {
					  // System.out.println("Single");   
					   mouseTimer.stop();
					   
				   }
			   });
			  ((JButton)e.getSource()).setBackground(Color.blue);
			  showEvents(e);
			  mouseTimer.restart();
		} 
		else if (e.getClickCount() == 2 && mouseTimer.isRunning()) {//please dont't double click too fast
			   mouseTimer.stop(); 
			  // System.out.println("Double");
			   int d = findDate(e); // returns date nd month by button clicked
			   int m = findMonth(e);
			   createEvent(d,m,e); // pass in date and month
		}

	 }
	
	public void showEvents(MouseEvent e){
		final String[] print =new String[30];
		
		int index =0;
		final int date = findDate(e);
		final int month = findMonth(e);
		for(int i=0;i<ev.size();i++){
			if((ev.get(i).day==date) && (ev.get(i).Month ==month)){ //0=title 2=locaiton 4 = starttime 6-endtime
				String start = ev.get(i).startTime;
				String end = ev.get(i).endTime;
				String[] splitStart = start.split("\\s+");
				String[] splitEnd = end.split("\\s+");


				int sh = Integer.parseInt(splitStart[0]);
				int sm = Integer.parseInt(splitStart[2]);
				String sap = (splitStart[5]);
				
				int eh = Integer.parseInt(splitEnd[0]);
				int em = Integer.parseInt(splitEnd[2]);
				String eap = (splitStart[5]);
				
				print[index]= ev.get(i).Title+" - "+ev.get(i).Location+" From "+String.format("%02d",sh)+":"+ String.format("%02d",sm)+":00" +sap+" - "+  String.format("%02d",eh)+":"+ String.format("%02d",em)+":00"+eap;
				index++;
			}
		}
		 lowestPanel.setLayout(new BorderLayout());
		 lowestPanel.removeAll();
		 //lowestPanel.setPreferredSize(new Dimension(400, 80));

		 JPanel p= monthPanel[month-1];
		 p.revalidate();
		 boolean empty = true;
		 for (int i=0; i<print.length; i++) {
		   if (print[i] != null) {
		     empty = false;
		     break;
		   }
		 }
		 if(!empty){
			 final JList eventList = new JList(print);
			 eventList.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					int idx = eventList.getSelectedIndex();
					if(idx!=-1){
						String whole = print[idx];
						String[] splited = whole.split("\\s+");
						String title = splited[0];
					
						Object[] options = {"Delete","Edit","Cancel"};
						int choice = JOptionPane.showOptionDialog(null,
						   "Would you like to edit or delete this event?",
						    "Select an Option",
						    JOptionPane.YES_NO_OPTION,
						    JOptionPane.QUESTION_MESSAGE,
						    null,     //do not use a custom Icon
						    options,  //the titles of buttons
						    options[2]); //default button title
						switch (choice){
						case 0:
							for(int i=0;i<ev.size();i++){
								if(ev.get(i).Title.equals(title)){
								ev.remove(i);
								deleteIcon();
								monthPanel[month-1].revalidate();
								monthPanel[month-1].repaint();
								
								}
							}
							break;
						case 1:
							editEvent(date,month, title);
							break;

						}
							
						
					}
				}
			    });
			 lowestPanel.add(eventList);
			 p.add(lowestPanel);
			 p.revalidate();
		 }
		

	}
	public void editEvent(final int d,final int mon, String t){
		final JFrame eFrame = new JFrame("Edit Event");
	    eFrame.setSize(470, 300);
	    eFrame.setLocation(200, 200);
	    //eFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel ePanel = new JPanel();
	    //ePanel.setLayout(new BorderLayout());
	    //ePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    ePanel.setLayout(new BoxLayout(ePanel,BoxLayout.Y_AXIS));
	    
	    JPanel titlePanel = new JPanel();    
	    JLabel title= new JLabel("Title: ");
	    final JTextField tfield = new JTextField();
	    tfield.setBackground(Color.WHITE);
	    tfield.setColumns(15);
	    titlePanel.add(title, BorderLayout.WEST);
	    titlePanel.add(tfield, BorderLayout.CENTER);
	    
	    JPanel locationPanel = new JPanel();    
	    JLabel location= new JLabel("Location: ");
	    final JTextField lfield = new JTextField();
	    lfield.setBackground(Color.WHITE);
	    lfield.setColumns(15);
	    locationPanel.add(location, BorderLayout.WEST);
	    locationPanel.add(lfield, BorderLayout.CENTER);
	    
	    String[] hours = new String[12];
	    String[] minutes = new String[60];
	    String[] ampm = {"AM","PM"};
	    for(int i=0;i<12;i++){
	    	hours[i] = Integer.toString(i+1);
	    }
	    for(int i=0;i<60;i++){
	    	minutes[i] = Integer.toString(i+1);
	    }
	    
	    JPanel startPanel = new JPanel();    
	    JLabel start= new JLabel("Start: ");
	    final JComboBox<String> h = new JComboBox<String>(hours);
	    final JComboBox<String> m = new JComboBox<String>(minutes);
	    final JComboBox<String> ap = new JComboBox<String>(ampm);
	    startPanel.add(start, BorderLayout.WEST);
	    startPanel.add(h, BorderLayout.CENTER);
	    startPanel.add(m, BorderLayout.CENTER);
	    startPanel.add(ap, BorderLayout.CENTER);
	    
	    JPanel endPanel = new JPanel();    
	    JLabel end= new JLabel("End: ");
	    final JComboBox<String> h1 = new JComboBox<String>(hours);
	    final JComboBox<String> m1 = new JComboBox<String>(minutes);
	    final JComboBox<String> ap1 = new JComboBox<String>(ampm);
	    endPanel.add(end, BorderLayout.WEST);
	    endPanel.add(h1, BorderLayout.CENTER);
	    endPanel.add(m1, BorderLayout.CENTER);
	    endPanel.add(ap1, BorderLayout.CENTER);
	    
	    LEvents temp = null;
	    for(int i=0;i<ev.size();i++){
//	    	System.out.println(d+" vs "+ev.get(i).day);
//	    	System.out.println(mon+" vs "+ev.get(i).Month);
//	    	System.out.println(t+" vs "+(ev.get(i).Title));
			if((ev.get(i).day==d) && (ev.get(i).Month ==mon)&&((ev.get(i).Title).equals(t))){
				temp = ev.get(i);
				break;
			}
		}
	    if(temp!=null){
		    tfield.setText(temp.Title);
		    lfield.setText(temp.Location);
		    String s = temp.startTime;
		    String e = temp.endTime;
		    String[] ssplitted = s.split("\\s+");
		    String[] esplitted = e.split("\\s+");
		    h.setSelectedIndex(Integer.parseInt(ssplitted[0])-1);
		    m.setSelectedIndex(Integer.parseInt(ssplitted[2])-1);
		    h1.setSelectedIndex(Integer.parseInt(esplitted[0])-1);
		    m1.setSelectedIndex(Integer.parseInt(esplitted[2])-1);
		    //h.SelectedIndex() =1;
		    ev.remove(temp); // remove the event
		    monthPanel[mon-1].revalidate();
	    }
	    
	    
	   // final LEvents t = temp;
	    
	    JButton EditBut = new JButton("Edit Event");
	    EditBut.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent evt) { 
				 int date =d;// = Integer.parseInt();
				 int month = mon;
				String Title= tfield.getText();
				String Location= lfield.getText(); // 0=h, 2=min;5=ampm
				String startTime = (String) h.getSelectedItem() +" : "+(String) m.getSelectedItem() +" : 00 "+(String) ap.getSelectedItem();
				String endTime  = (String) h1.getSelectedItem() +" : "+(String) m1.getSelectedItem() +" : 00 "+(String) ap1.getSelectedItem();
				if(!Title.equals("")){ // if title is not empty
					LEvents nEvent = new LEvents(2014,date, month, Title, Location, startTime, endTime); 
					
					ev.add(nEvent);
					JOptionPane.showMessageDialog(null,"Event edited successfully","Successful",JOptionPane.WARNING_MESSAGE);
					// create new event 
					
					// ((JButton)e.getSource()).setIcon(null); 
				}
				
				else{
					JOptionPane.showMessageDialog(null,"Event is not edited. Please Enter a Title.","Event Not Created",JOptionPane.WARNING_MESSAGE);		
				}
			   }
		});
	    JButton cancelBut = new JButton("Cancel");
	    cancelBut.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent evt) { 
				  eFrame.dispose();
			   }
		});
	    


	    ePanel.add(titlePanel);
	    ePanel.add(locationPanel);
	    ePanel.add(startPanel);
	    ePanel.add(endPanel);
	    ePanel.add(EditBut);
	    ePanel.add(cancelBut);
//	    ePanel.add(Box.createHorizontalGlue());


	    eFrame.add(ePanel);
	   // eFrame.setLocationByPlatform(true);
	    eFrame.setVisible(true);
	    eFrame.setResizable(true);
	}

	public void createEvent(int d, int month, MouseEvent e){
		EventFrame(d,month,e);
	}
	public void EventFrame(final int d,final int mon, final MouseEvent e){

	    final JFrame eFrame = new JFrame("Create Event");
	    eFrame.setSize(470, 300);
	    eFrame.setLocation(200, 200);
	    //eFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel ePanel = new JPanel();
	    //ePanel.setLayout(new BorderLayout());
	    //ePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    ePanel.setLayout(new BoxLayout(ePanel,BoxLayout.Y_AXIS));
	    
	    JPanel titlePanel = new JPanel();    
	    JLabel title= new JLabel("Title: ");
	    final JTextField tfield = new JTextField();
	    tfield.setBackground(Color.WHITE);
	    tfield.setColumns(15);
	    titlePanel.add(title, BorderLayout.WEST);
	    titlePanel.add(tfield, BorderLayout.CENTER);
	    
	    JPanel locationPanel = new JPanel();    
	    JLabel location= new JLabel("Location: ");
	    final JTextField lfield = new JTextField();
	    lfield.setBackground(Color.WHITE);
	    lfield.setColumns(15);
	    locationPanel.add(location, BorderLayout.WEST);
	    locationPanel.add(lfield, BorderLayout.CENTER);
	    
	    String[] hours = new String[12];
	    String[] minutes = new String[60];
	    String[] ampm = {"AM","PM"};
	    for(int i=0;i<12;i++){
	    	hours[i] = Integer.toString(i+1);
	    }
	    for(int i=0;i<60;i++){
	    	minutes[i] = Integer.toString(i+1);
	    }
	    
	    JPanel startPanel = new JPanel();    
	    JLabel start= new JLabel("Start: ");
	    final JComboBox<String> h = new JComboBox<String>(hours);
	    final JComboBox<String> m = new JComboBox<String>(minutes);
	    final JComboBox<String> ap = new JComboBox<String>(ampm);
	    startPanel.add(start, BorderLayout.WEST);
	    startPanel.add(h, BorderLayout.CENTER);
	    startPanel.add(m, BorderLayout.CENTER);
	    startPanel.add(ap, BorderLayout.CENTER);
	    
	    JPanel endPanel = new JPanel();    
	    JLabel end= new JLabel("End: ");
	    final JComboBox<String> h1 = new JComboBox<String>(hours);
	    final JComboBox<String> m1 = new JComboBox<String>(minutes);
	    final JComboBox<String> ap1 = new JComboBox<String>(ampm);
	    endPanel.add(end, BorderLayout.WEST);
	    endPanel.add(h1, BorderLayout.CENTER);
	    endPanel.add(m1, BorderLayout.CENTER);
	    endPanel.add(ap1, BorderLayout.CENTER);
	    
	    JButton createBut = new JButton("Create Event");
	    createBut.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent evt) { 
				 int date =d;// = Integer.parseInt();
				 int month = mon;
				String Title= tfield.getText();
				String Location= lfield.getText(); // 0=h 2=m
				String startTime = (String) h.getSelectedItem() +" : "+(String) m.getSelectedItem() +" : 00 "+(String) ap.getSelectedItem() ;
				String endTime  = (String) h1.getSelectedItem() +" : "+(String) m1.getSelectedItem() +" : 00 "+(String) ap1.getSelectedItem();
				
				int sh = Integer.parseInt((String) h.getSelectedItem());
				int sm = Integer.parseInt((String) m.getSelectedItem());
				int eh = Integer.parseInt((String) h1.getSelectedItem());
				int em = Integer.parseInt((String) m1.getSelectedItem());
				if(!Title.equals("")){ // if title is not empty
					LEvents nEvent = new LEvents(2014,date, month, Title, Location, startTime, endTime); 
					ev.add(nEvent);
					JOptionPane.showMessageDialog(null,"Event created successfully","Successful",JOptionPane.WARNING_MESSAGE);
					// create new event 
					BufferedImage pic1 = null;
					try {
						pic1 = ImageIO.read(new File("event_A-512.png"));
					} catch (IOException e) {
						System.out.println("Cannot find image icon file");
						e.printStackTrace();
					}
					 Image buffered1 = pic1.getScaledInstance(20,20,Image.SCALE_SMOOTH);
					 ImageIcon image1 = new ImageIcon(buffered1);	
					 	 
					 ((JButton)e.getSource()).setIcon(image1); 
				}
				
				else{
					JOptionPane.showMessageDialog(null,"Event is not created. Please Enter a Title. Or maybe your time isn't set up correctly.","Event Not Created",JOptionPane.WARNING_MESSAGE);		
				}
			   }
		});
	    JButton cancelBut = new JButton("Cancel");
	    cancelBut.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent evt) { 
				  eFrame.dispose();
			   }
		});
	    


	    ePanel.add(titlePanel);
	    ePanel.add(locationPanel);
	    ePanel.add(startPanel);
	    ePanel.add(endPanel);
	    ePanel.add(createBut);
	    ePanel.add(cancelBut);
//	    ePanel.add(Box.createHorizontalGlue());


	    eFrame.add(ePanel);
	   // eFrame.setLocationByPlatform(true);
	    eFrame.setVisible(true);
	    eFrame.setResizable(true);
	    
	    

	}
	public void AboutFrame() throws IOException{

	    final JFrame eFrame = new JFrame("About");
	    eFrame.setSize(470, 300);
	    eFrame.setLocation(300, 300);
	    //eFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel leftPanel = new JPanel(new BorderLayout());
	    
	    JLabel name = new JLabel("Xiaohan Chen");
	    BufferedImage pic = ImageIO.read(new File("IMG_20140524_173456.jpg"));
		Image buffered = pic.getScaledInstance(220,300,Image.SCALE_SMOOTH);
		JLabel picLabel = new JLabel(new ImageIcon(buffered));

			        
		leftPanel.add(picLabel, BorderLayout.WEST);
		leftPanel.add(name, BorderLayout.NORTH);
		
		JPanel rPanel = new JPanel ();
		rPanel.setLayout(new BoxLayout(rPanel, BoxLayout.Y_AXIS));
		JLabel Section = new JLabel("Section: MW 12:00pm");
		JLabel date = new JLabel("Date Completed: 10/4/14");
		JLabel comment = new JLabel("Cool Calendar");
		rPanel.add(Section);
		rPanel.add(date);
		rPanel.add(comment);
		leftPanel.add(rPanel, BorderLayout.CENTER);
    
	    eFrame.add(leftPanel);
	   // eFrame.add(rPanel);
		eFrame.setVisible(true);
		eFrame.setResizable(false);
	}
	public void ManagerFrame(){

	    final JFrame eFrame = new JFrame("Event Manager");
	    eFrame.setSize(470, 300);
	    eFrame.setLocation(200, 200);
	    //eFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel ePanel = new JPanel();
	    //ePanel.setLayout(new BorderLayout());
	    //ePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    ePanel.setLayout(new BoxLayout(ePanel,BoxLayout.Y_AXIS));
	    
	    JPanel titlePanel = new JPanel();    
	    JLabel title= new JLabel("Title: ");
	    final JTextField tfield = new JTextField();
	    tfield.setBackground(Color.WHITE);
	    tfield.setColumns(15);
	    titlePanel.add(title, BorderLayout.WEST);
	    titlePanel.add(tfield, BorderLayout.CENTER);
	    
	    JPanel locationPanel = new JPanel();    
	    JLabel location= new JLabel("Location: ");
	    final JTextField lfield = new JTextField();
	    lfield.setBackground(Color.WHITE);
	    lfield.setColumns(15);
	    locationPanel.add(location, BorderLayout.WEST);
	    locationPanel.add(lfield, BorderLayout.CENTER);
	    
	    String[] hours = new String[12];
	    String[] minutes = new String[60];
	    String[] ampm = {"AM","PM"};
	    String[] year = new String[50];
	    String[] day = new String[31];
	    String[] mon ={"1","2","3","4","5","6","7","8","9","10","11","12"};
	    for(int i=0;i<50;i++){
	    	year[i] = Integer.toString(2014+i);
	    }
	    for(int i=0;i<31;i++){
	    	day[i] = Integer.toString(i+1);
	    }
	    for(int i=0;i<12;i++){
	    	hours[i] = Integer.toString(i+1);
	    }
	    for(int i=0;i<60;i++){
	    	minutes[i] = Integer.toString(i+1);
	    }
	    
	    JPanel ymdPanel = new JPanel(); // year, minutes, day panel
	    JLabel yLabel= new JLabel("Year: ");
	    JLabel mLabel= new JLabel("Month: ");
	    JLabel dLabel= new JLabel("Day: ");
	    final JComboBox<String> y = new JComboBox<String>(year);
	    final JComboBox<String> mont = new JComboBox<String>(mon);
	    final JComboBox<String> d = new JComboBox<String>(day);
	    ymdPanel.add(yLabel, BorderLayout.WEST);
	    ymdPanel.add(y, BorderLayout.CENTER);
	    ymdPanel.add(mLabel, BorderLayout.WEST);
	    ymdPanel.add(mont, BorderLayout.CENTER);
	    ymdPanel.add(dLabel, BorderLayout.WEST);
	    ymdPanel.add(d, BorderLayout.CENTER);
	    
	    
	    JPanel startPanel = new JPanel();    
	    JLabel start= new JLabel("Start: ");
	    final JComboBox<String> h = new JComboBox<String>(hours);
	    final JComboBox<String> m = new JComboBox<String>(minutes);
	    final JComboBox<String> ap = new JComboBox<String>(ampm);
	    startPanel.add(start, BorderLayout.WEST);
	    startPanel.add(h, BorderLayout.CENTER);
	    startPanel.add(m, BorderLayout.CENTER);
	    startPanel.add(ap, BorderLayout.CENTER);
	    
	    JPanel endPanel = new JPanel();    
	    JLabel end= new JLabel("End: ");
	    final JComboBox<String> h1 = new JComboBox<String>(hours);
	    final JComboBox<String> m1 = new JComboBox<String>(minutes);
	    final JComboBox<String> ap1 = new JComboBox<String>(ampm);
	    endPanel.add(end, BorderLayout.WEST);
	    endPanel.add(h1, BorderLayout.CENTER);
	    endPanel.add(m1, BorderLayout.CENTER);
	    endPanel.add(ap1, BorderLayout.CENTER);
	    
	    JButton createBut = new JButton("Create Event");
	    createBut.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent evt) { 
				 int year = Integer.parseInt((String) y.getSelectedItem());
				 int date =Integer.parseInt((String) d.getSelectedItem());// = Integer.parseInt();
				 int month = Integer.parseInt((String) mont.getSelectedItem());
				String Title= tfield.getText();
				String Location= lfield.getText();
				String startTime = (String) h.getSelectedItem() +":"+(String) m.getSelectedItem() +":00 "+(String) ap.getSelectedItem() +" ";
				String endTime  = (String) h1.getSelectedItem() +":"+(String) m1.getSelectedItem() +":00 "+(String) ap1.getSelectedItem() +" ";
				if(!Title.equals("")){ // if title is not empty
					LEvents nEvent = new LEvents(year, date, month, Title, Location, startTime, endTime); // create new event 
					ev.add(nEvent);
					JOptionPane.showMessageDialog(null,"Event created successfully","Successful",JOptionPane.WARNING_MESSAGE);
				}
				
				else{
					JOptionPane.showMessageDialog(null,"Event is not created. Please Enter a Title.","Event Not Created",JOptionPane.WARNING_MESSAGE);		
				}
			   }
		});
	    JButton cancelBut = new JButton("Cancel");
	    cancelBut.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent evt) { 
				  eFrame.dispose();
			   }
		});
	    


	    ePanel.add(titlePanel);
	    ePanel.add(locationPanel);
	    ePanel.add(ymdPanel);
	    ePanel.add(startPanel);
	    ePanel.add(endPanel);
	    ePanel.add(createBut);
	    ePanel.add(cancelBut);
//	    ePanel.add(Box.createHorizontalGlue());


	    eFrame.add(ePanel);
	   // eFrame.setLocationByPlatform(true);
	    eFrame.setVisible(true);
	    eFrame.setResizable(true);
	    
	    

	}
	
	

	
	
	 public static void main(String [] args) throws IOException {
		 Calendar c = new Calendar();
	 }

	 
	 public String[] showCurrentMonth(JPanel mainPanel){
		 DateFormat currentDate = new SimpleDateFormat("MM dd");
		 Date date = new Date();
		 String c = currentDate.format(date);
		 String[] splited = c.split("\\s+");
		 CardLayout cl = (CardLayout)mainPanel.getLayout();
		 cl.show(mainPanel, monthString[Integer.parseInt(splited[0])-1]);
		 
		 return splited;
		 
	 }
	 
	 public void hilightCurrentDate(String[] splited){ // hightlight current date
		 switch(Integer.parseInt(splited[0])-1){
		 case 0:
			 janCells[Integer.parseInt(splited[1])-1].setBackground(Color.yellow);
			 break;
		 case 1:
			 febCells[Integer.parseInt(splited[1])-1].setBackground(Color.yellow);
			 break;
		 case 2:
			 marCells[Integer.parseInt(splited[1])-1].setBackground(Color.yellow);
			 break;
		 case 3:
			 aprCells[Integer.parseInt(splited[1])-1].setBackground(Color.yellow);
			 break;
		 case 4:
			 mayCells[Integer.parseInt(splited[1])-1].setBackground(Color.yellow);
			 break;
		 case 5:
			 junCells[Integer.parseInt(splited[1])-1].setBackground(Color.yellow);
			 break;
		 case 6:
			 julCells[Integer.parseInt(splited[1])-1].setBackground(Color.yellow);
			 break;
		 case 7:
			 augCells[Integer.parseInt(splited[1])-1].setBackground(Color.yellow);
			 break;
		 case 8:
			 sepCells[Integer.parseInt(splited[1])-1].setBackground(Color.yellow);
			 break;
		 case 9:
			 octCells[Integer.parseInt(splited[1])-1].setBackground(Color.yellow);
			 break;
		 case 10:
			 novCells[Integer.parseInt(splited[1])-1].setBackground(Color.yellow);
			 break;
		 case 11:
			 decCells[Integer.parseInt(splited[1])-1].setBackground(Color.yellow);
			 break;
		 }
	 }
	 
	 public void disselectOthers(){
		 for(int i =0;i<janCells.length;i++){if(janCells[i].getBackground()==Color.BLUE){ janCells[i].setBackground(null);}}
		 for(int i =0;i<febCells.length;i++){if(febCells[i].getBackground()==Color.BLUE){ febCells[i].setBackground(null);}}
		 for(int i =0;i<marCells.length;i++){if(marCells[i].getBackground()==Color.BLUE){ marCells[i].setBackground(null);}}
		 for(int i =0;i<aprCells.length;i++){if(aprCells[i].getBackground()==Color.BLUE){ aprCells[i].setBackground(null);}}
		 for(int i =0;i<mayCells.length;i++){if(mayCells[i].getBackground()==Color.BLUE){ mayCells[i].setBackground(null);}}
		 for(int i =0;i<junCells.length;i++){if(junCells[i].getBackground()==Color.BLUE){ junCells[i].setBackground(null);}}
		 for(int i =0;i<julCells.length;i++){if(julCells[i].getBackground()==Color.BLUE){ julCells[i].setBackground(null);}}
		 for(int i =0;i<augCells.length;i++){if(augCells[i].getBackground()==Color.BLUE){ augCells[i].setBackground(null);}}
		 for(int i =0;i<sepCells.length;i++){if(sepCells[i].getBackground()==Color.BLUE){ sepCells[i].setBackground(null);}}
		 for(int i =0;i<octCells.length;i++){if(octCells[i].getBackground()==Color.BLUE){ octCells[i].setBackground(null);}}
		 for(int i =0;i<novCells.length;i++){if(novCells[i].getBackground()==Color.BLUE){ novCells[i].setBackground(null);}}
		 for(int i =0;i<decCells.length;i++){if(decCells[i].getBackground()==Color.BLUE){ decCells[i].setBackground(null);}}

			
		}
	 
	 public boolean noEventOnDay(int m, int d){
		 for(int i=0;i<ev.size();i++){
			 if(ev.get(i).Month==m && ev.get(i).day == d) return false;
		 }
		 return true;
	 }
	 

	 
	 public void deleteIcon(){
		 
		 for(int i=0;i<janCells.length;i++){if(noEventOnDay(1,i+1)){ janCells[i].setIcon(null); }}
		 for(int i=0;i<febCells.length;i++){if(noEventOnDay(2,i+1)){ febCells[i].setIcon(null); }}
		 for(int i=0;i<marCells.length;i++){if(noEventOnDay(3,i+1)){ marCells[i].setIcon(null); }}
		 for(int i=0;i<aprCells.length;i++){if(noEventOnDay(4,i+1)){ aprCells[i].setIcon(null); }}
		 for(int i=0;i<mayCells.length;i++){if(noEventOnDay(5,i+1)){ mayCells[i].setIcon(null); }}
		 for(int i=0;i<junCells.length;i++){if(noEventOnDay(6,i+1)){ junCells[i].setIcon(null); }}
		 for(int i=0;i<julCells.length;i++){if(noEventOnDay(7,i+1)){ julCells[i].setIcon(null); }}
		 for(int i=0;i<augCells.length;i++){if(noEventOnDay(8,i+1)){ augCells[i].setIcon(null); }}
		 for(int i=0;i<sepCells.length;i++){if(noEventOnDay(9,i+1)){ sepCells[i].setIcon(null); }}
		 for(int i=0;i<octCells.length;i++){if(noEventOnDay(10,i+1)){ octCells[i].setIcon(null); }}
		 for(int i=0;i<novCells.length;i++){if(noEventOnDay(11,i+1)){ novCells[i].setIcon(null); }}
		 for(int i=0;i<decCells.length;i++){if(noEventOnDay(12,i+1)){ decCells[i].setIcon(null); }}
	 }
	


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stu	
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub		
	}
			
 }
