import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;

 public class Test extends JFrame {

	
	 public Test() throws IOException {
	 super("CSCI 201 Window");
	 setSize(500, 500);
	 setLocation(200, 200);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	 JTabbedPane tabbedPane = new JTabbedPane();
	 //tabbedPane.setLayout(new GridLayout(0,1));;
	
//	 GridBagConstraints gbc = new GridBagConstraints();
//	 gbc.fill = GridBagConstraints.HORIZONTAL;
//	 gbc.ipadx=50;
//	 gbc.gridwidth=400;
	 
	 JPanel firstPanel = new JPanel();
	 JPanel insidePanel = new JPanel();
	 insidePanel.setLayout(new GridBagLayout());
	 firstPanel.setLayout(new BorderLayout());
	 
	 JButton button1 = new JButton("Button 1");
	 JButton button2 = new JButton("Button 2");
	 button2.setBackground(Color.red);
	 insidePanel.add(button1);
	 insidePanel.add(button2);	
	 //insidePanel.setPreferredSize(new Dimension(475,50));
	 
	 JPanel southPanel = new JPanel();
	 southPanel.setLayout(new BorderLayout());
	 
	 JPanel southWest = new JPanel();
	 JPanel southEast = new JPanel();
	// southPanel.setLayout( new BorderLayout());
	 JLabel lable1 = new JLabel("West");
	 JLabel lable2 = new JLabel("This is the South Quadrant");
	 lable2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	 JLabel lable3 = new JLabel("East");
	 lable2.setMinimumSize(new Dimension(300,50));
	 southWest.add(lable1);
	 southPanel.add(lable2);
	 southEast.add(lable3);
	 
	 southPanel.add(southWest, BorderLayout.WEST);
	 southPanel.add(southEast, BorderLayout.EAST);
	 firstPanel.add(insidePanel, BorderLayout.NORTH);
	 firstPanel.add(southPanel, BorderLayout.SOUTH);

	// firstPanel.add(insidePanel,BorderLayout.NORTH);
	 
	 insidePanel.setBorder(BorderFactory.createLineBorder(Color.black));
	 southPanel.setBorder(BorderFactory.createLineBorder(Color.red));
	 
	 JPanel secondPanel = new JPanel();
//	 BufferedImage pic = ImageIO.read(new File("C:/Users/Xiaohan/Desktop/wolf.jpg"));
//	
//	 Image buffered = pic.getScaledInstance(500,300,Image.SCALE_SMOOTH);
//	 JLabel picLabel = new JLabel(new ImageIcon(buffered));
//		        
//	 secondPanel.add(picLabel);
//	 secondPanel.add(button2);
	
	 tabbedPane.add("First", firstPanel);
	 tabbedPane.add("Doge", secondPanel);
	
	 add(tabbedPane);
	 setVisible(true);
	 }
	
	 public static void main(String [] args) throws IOException {
	 Test t = new Test();
	 }
 }