package TTT;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TTT extends JFrame implements ActionListener{
	int player =1;
	JLabel label1 = new JLabel("Current Player: " +1);
	final JButton[] cells = new JButton[9];
	int turns=0;
	JButton button2 = new JButton("Restart Game");
	JPanel gamePanel =  new JPanel();
	 

	public TTT(){
		 super("Tic Tac Toe Game");
		 setSize(500, 500);
		 setLocation(200, 200);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 JPanel mainPanel = new JPanel();

		 JPanel northPanel = new JPanel();
		 JPanel southPanel = new JPanel();

		 gamePanel.setLayout(new GridLayout(3,3));
		 mainPanel.setLayout(new BorderLayout());
		 button2.addActionListener(this);

		
		 
		 
		 button2.setMinimumSize(new Dimension(300,50));
		 
		 
		 northPanel.add(label1);
		 southPanel.add(button2);
		// gamePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		 mainPanel.add(northPanel,BorderLayout.NORTH);
		 mainPanel.add(gamePanel);
		 mainPanel.add(southPanel,BorderLayout.SOUTH);
		 
	
		
		 for (int i = 0; i < 9; i++){   
	        cells[i] = new JButton();
	        cells[i].setText("");
	        gamePanel.add(cells[i]);
	        cells[i].addActionListener(this);
	        	
	        }
	 
		 add(mainPanel);
		 setVisible(true);
	}
	

	public static void main(String [] args) throws IOException {
		
		 TTT t = new TTT();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(((JButton)ae.getSource())==(button2)){ // check if game should reset		
			 for (int j = 0; j < 9; j++){   
		        cells[j].setEnabled(true);
		        cells[j].setText("");
		        label1.setText("Current Player: " +1);
		        turns =0;
		        }
		}
		
		else{
			if(player ==1) {
				label1.setText("Current Player: " +2);
				player=2;
			}
			else {
				label1.setText("Current Player: " +1);
				player=1;
			}
			if(player==1){
				((JButton)ae.getSource()).setText("O");
				turns++;
				boolean flag = checkWinner();
				if(flag&&turns>4){
					label1.setText("Player 1 Wins");
					for (int i = 0; i < 9; i++){   
				        cells[i].setEnabled(false);    // disable everything after winner found     	
				    }
				}
				if(!flag&&turns>8){
					label1.setText("Tie");
				}
			}
			else{
				((JButton)ae.getSource()).setText("X");
				boolean flag = checkWinner();
				turns++;
				if(flag&&turns>4){
					label1.setText("Player 2 Wins");
					for (int i = 0; i < 9; i++){   
				        cells[i].setEnabled(false);    // disable everything after winner found     	
				    }
				}
				if(!flag&&turns>8){
					label1.setText("Tie");
				}
			}
			
			((JButton)ae.getSource()).setEnabled(false);
			
		}
		 //System.out.println(ae.getActionCommand());

		
		
	}
	
	
	public boolean checkWinner()
	{
		if(cells[0].getText().equals(cells[1].getText()) && cells[1].getText().equals(cells[2].getText()))
		{
			if(!(cells[0].getText().equals(""))){
				return true;
			}
		}
		if(cells[3].getText().equals(cells[4].getText()) && cells[4].getText().equals(cells[5].getText()))
		{
			if(!(cells[3].getText().equals(""))){
				return true;
			}
		}
		if(cells[6].getText().equals(cells[7].getText()) && cells[7].getText().equals(cells[8].getText()))
		{
			if(!(cells[6].getText().equals(""))){
				return true;
			}
		}
		if(cells[0].getText().equals(cells[3].getText()) && cells[3].getText().equals(cells[6].getText()))
		{
			if(!(cells[0].getText().equals(""))){
				return true;
			}
		}
		if(cells[1].getText().equals(cells[4].getText()) && cells[4].getText().equals(cells[7].getText()))
		{
			if(!(cells[1].getText().equals(""))){
				return true;
			}
		}
		if(cells[2].getText().equals(cells[5].getText()) && cells[5].getText().equals(cells[8].getText()))
		{
			if(!(cells[2].getText().equals(""))){
				return true;
			}
		}
		if(cells[0].getText().equals(cells[4].getText()) && cells[4].getText().equals(cells[8].getText()))
		{
			if(!(cells[0].getText().equals(""))){
				return true;
			}
		}
		if(cells[2].getText().equals(cells[4].getText()) && cells[4].getText().equals(cells[6].getText()))
		{
			if(!(cells[2].getText().equals(""))){
				return true;
			}
		}
		return false;
	}

	

}
