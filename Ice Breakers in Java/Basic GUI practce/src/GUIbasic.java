import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Panels and menus are new. Needs ActionListeners

public class GUIbasic extends JFrame {
	JPanel basePanel;
	JPanel menuPanel;
	JPanel componentPanel;

	JMenuBar menuBar;
	JMenu menu;
	JButton buttons[];
	JTextField textfields[];
	JMenuItem menuItems[]; // important add

	public GUIbasic() {
		basePanel = new JPanel();
		basePanel.setLayout( new GridLayout(2,1) );

		menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(2,1));

		componentPanel = new JPanel();
		componentPanel.setLayout( new GridLayout( 4, 3) );

		addMenu( menuPanel );
		addComponents( componentPanel);

		basePanel.add( menuPanel );
		basePanel.add( componentPanel );

		add( basePanel );
	}

	private void addMenu( JPanel panel ) {
		//Adds the menu and menu items to panel
		menuBar = new JMenuBar();
		menuItems = new JMenuItem[8]; // important add

		MenuListener ml = new MenuListener(); // important add
		//Menu 1
		menu = new JMenu( "My menu");
		
		for(int i=0;i<8;i++){ // importnat add. to consol
			menuItems[i] = new JMenuItem("Item 1-" +i);
			menu.add(menuItems[i]);
			menuItems[i].addActionListener(ml);
		}

		//Add items to the menu
		JMenuItem j = new JMenuItem( "Item 1-1" );

		menu.add( j );
		j.addActionListener(ml); // important add
		
		menu.add( new JMenuItem( "Item 1-2" ) );
		menu.add( new JMenuItem( "Item 1-3" ) );
		menu.add( new JMenuItem( "Item 1-4" ) );
		menuBar.add( menu );

		//Let's add another menu to this bar - Menu 2
		menu = new JMenu( "Second menu");

		//Add items to the menu
		menu.add( new JMenuItem( "Item 2-1" ) );
		menu.add( new JMenuItem( "Item 2-2" ) );
		menu.add( new JMenuItem( "Item 2-3" ) );
		menu.add( new JMenuItem( "Item 2-4" ) );
		menuBar.add( menu );

		panel.add( menuBar,BorderLayout.NORTH);
		//panel.add(menuBar);

		return;
	}

	private void addComponents( JPanel panel ) {
		//Create the components
		buttons = new JButton[4];
		textfields = new JTextField[4];

		ButtonListener bl = new ButtonListener(); // must have instance of ButtonListener
		
		for (int i=0; i<4; i++ ) {
			textfields[i] = new JTextField( 20 );
			buttons[i] = new JButton("Click Me" + (i+1));
			buttons[i].addActionListener(bl); //important add
		}

		//Adds all the components to panel

		//Row 1 in the grid
		panel.add( new JLabel( "Enter your name" ) );
		panel.add( textfields[0] );
		panel.add( buttons[0] );

		//Row 2 in the grid
		panel.add( new JLabel( "Enter your age" ) );
		panel.add( textfields[1]  );
		panel.add( buttons[1] );

		//Row 3 in the grid
		panel.add( new JLabel( "Enter your name" ) );
		panel.add( textfields[2]  );
		panel.add( buttons[2] );

		//Row 4 in the grid
		panel.add( new JLabel( "Enter your age 2" ) );
		panel.add( textfields[3]  );
		panel.add( buttons[3] );

		return;
	}

	public static void main (String[] args ) {
		GUIbasic gui = new GUIbasic();
		gui.setSize(600,450);
		gui.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		gui.setVisible( true );
	}
	
	class ButtonListener implements ActionListener{ // put text in associated text field
		public void actionPerformed(ActionEvent ae){
			for(int i=0;i<4;i++){
				if(ae.getSource()==buttons[i]){
					textfields[i].setText("You clicked a button");
				}
			}
		}
	}
	
	class MenuListener implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			System.out.println("You clicked on menu item");
		}
	}

}
