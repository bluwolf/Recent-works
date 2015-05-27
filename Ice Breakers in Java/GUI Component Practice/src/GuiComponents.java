
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;

 public class GuiComponents extends JFrame implements ActionListener {
	 
	 JButton oneclick = new JButton("Click Once");
	 JPanel mainPanel = new JPanel();
	 
	
	 public GuiComponents() throws IOException {
	 super("GUI Components");
	 setSize(500, 500);
	 setLocation(200, 200);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	 
	 
	 add(oneclick, BorderLayout.NORTH);
	 oneclick.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				((JComponent) e.getSource()).setVisible(false);
				mainPanel.remove(oneclick);
				oneclick =(new NewButton());
				add(oneclick, BorderLayout.NORTH);
			}
		 });
	 add(new NewPanel());

	 

	 setVisible(true);
	 }
	
	 public static void main(String [] args) throws IOException {
		 GuiComponents t = new GuiComponents();
	 }
	 
	 class NewButton extends JButton {
		 public NewButton(){
			 this.setText("Don't click me");	
			 this.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "WHY DID YOU CLICK ME AGAIN");
					System.exit(1);
					
				}
			 });
		 }		 
		 protected void paintComponent(Graphics g){
			super.paintComponent(g);
			 g.setColor(Color.RED);
			 g.drawLine(0,0,this.getWidth(),this.getHeight());
			 g.drawLine(this.getWidth(),0,0,this.getHeight());
		 }
	 }
	 
	 class NewPanel extends JPanel{
		 
		 protected void paintComponent(Graphics g){
			 super.paintComponent(g);
			 
			 Graphics2D g2d = (Graphics2D) g;
		  
			 int[] x = {10,20,25,25,20,10,5,5};
			 int[] y = {10,10,20,27,37,37,27,20};
			 Polygon poly = new Polygon(x,y,8);

			 AffineTransform scaleMatrix = new AffineTransform();
	         scaleMatrix.scale(4, 4);
	         g2d.setTransform(scaleMatrix);
	         g2d.setColor(Color.CYAN);
	         g2d.translate(45, 10);
	         g2d.fillPolygon(poly);
	        // this.setBorder(BorderFactory.createLineBorder(Color.black));
	         //g2d.setTransform(saveTransform);
		    	 
			 
		 }
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	 
 }