//Import AWT
import java.awt.*;
import java.awt.event.*;

//Import Swing
import javax.swing.*;

/**
 * The AWT/Swing GUI for the game TicTacToe
 */
public class TicTacToeGUI 
{
	//The game has two players
	public static final ImageIcon PICTURE_X = new ImageIcon("images/x.gif");
	public static final ImageIcon PICTURE_O = new ImageIcon("images/o.gif");
	public static final String HUMAN = "Human";
	public static final String COMPUTER = "Computer";
	//TicTacToeGUIs have frames
	private JFrame frmTicTacToe;
	//TicTacToeGUIs have menu titles
	private String menuPlayerO;
	private String menuPlayerX;
	
	/**
	 *  Menu
     */
	public String getMenuPlayerO()
	{
		//Returns the menu name
		return menuPlayerO;
	}
	public void setMenuPlayerO(String newValue)
	{
		//Sets the menu name
		menuPlayerO = newValue;
	}
	public String getMenuPlayerX()
	{
		//Returns the menu name
		return menuPlayerX;
	}
	public void setMenuPlayerX(String newValue)
	{
		//Sets the menu name
		menuPlayerX = newValue;
	}

	/**
	 * Launches the application
	 *
	 * @param args: Dummy string array
	 */
	public static void main(String[] args) 
	{
		//Sets up event handler
		EventQueue.invokeLater
		(
				new Runnable() 
				{
					/**
					 * Initializes the event handler
					 */
					//
					public void run() 
					{
						try 
						{
							//Creates a new window
							TicTacToeGUI window = new TicTacToeGUI();
							window.frmTicTacToe.setVisible(true);
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
				}
		);
	}

	/**
	 * A new GUI has been created
	 */
	public TicTacToeGUI() 
	{
		//A few hardcoded sizes
		final int FIELD_WIDTH = 150;
		final int BAR_HEIGHT  = 25;
		//Declares a new game
		TicTacToeGame initGame;
		//Declares a new frame
		JFrame initFrame;
		//Declares a new panel
		JPanel initPanel;
		//Declares new labels
		JLabel[] initFields;
		//Initializes the game
		initGame = initializeGame();
		//Initializes the frame
		initFrame = initializeFrame(FIELD_WIDTH, BAR_HEIGHT);
		//Initializes the panel
		initPanel = initializePanel(initGame, initFrame, FIELD_WIDTH, BAR_HEIGHT);
		//Initializes the labels
		initFields = initializeLabels(initGame, initPanel, FIELD_WIDTH, BAR_HEIGHT);
		//Initializes the menu
		initializeMenu(initFrame, initFields, initGame);
		//Initializes the lines
		initializeLines(initPanel, FIELD_WIDTH, BAR_HEIGHT);		
	}

	/**
	 * Initializes the game
	 */
	private TicTacToeGame initializeGame() 
	{
		//Creates a new game
		TicTacToeGame game = new TicTacToeGame();
		return game;
	}
	
	/**
	 * Initializes the frame
	 */
	private JFrame initializeFrame(int field_width, int bar_height) 
	{
		//Creates a new frame
		frmTicTacToe = new JFrame();
		frmTicTacToe.setTitle("Tic Tac Toe");
		frmTicTacToe.setBounds(field_width, field_width, field_width * 3, field_width * 3 + bar_height * 3);
		frmTicTacToe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTicTacToe.getContentPane().setLayout(null);
		return frmTicTacToe;
	}

	/**
	 * Initializes the menu
	 */
	private void initializeMenu(JFrame frmTicTacToe, JLabel[] lblFields, TicTacToeGame initGame) 
	{
		//Creates a menubar
		JMenuBar menuBar = new JMenuBar();
		frmTicTacToe.setJMenuBar(menuBar);
		//Adds the menu 'Game'
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		//Adds game 'TicTacToe'
		JRadioButtonMenuItem btnGameTictactoe = new JRadioButtonMenuItem("TicTacToe");
		btnGameTictactoe.setSelected(true);
		mnGame.add(btnGameTictactoe);
	    //Defines the possible choices for this menu
	    final ButtonGroup grpGame = new ButtonGroup();
	    grpGame.add(btnGameTictactoe);
		//Adds the menu 'Player X'
		JMenu mnPlayerX = new JMenu("Player X");
		menuBar.add(mnPlayerX);
		//Adds human player
		JRadioButtonMenuItem btnXHuman = new JRadioButtonMenuItem(HUMAN);
		mnPlayerX.add(btnXHuman);
		//Adds computer player
		JRadioButtonMenuItem btnXComputer = new JRadioButtonMenuItem(COMPUTER);
		mnPlayerX.add(btnXComputer);	    
		//Defines the possible choices for this menu
	    final ButtonGroup grpX = new ButtonGroup();
	    grpX.add(btnXHuman);
	    grpX.add(btnXComputer);
		//Adds the menu 'Player O'
		JMenu mnPlayerO = new JMenu("Player O");
		menuBar.add(mnPlayerO);
		//Adds human player
		JRadioButtonMenuItem btnOHuman = new JRadioButtonMenuItem(HUMAN);
		mnPlayerO.add(btnOHuman);
		//Adds computer player
		JRadioButtonMenuItem btnOComputer = new JRadioButtonMenuItem(COMPUTER);
		mnPlayerO.add(btnOComputer);
		//Defines the possible choices for this menu
	    final ButtonGroup grpO = new ButtonGroup();
	    grpO.add(btnOHuman);
	    grpO.add(btnOComputer);
		//Stores the menu name for players X and O
	    setMenuPlayerX(mnPlayerX.getActionCommand());
		setMenuPlayerO(mnPlayerO.getActionCommand());
	    //Adds item listeners
	    ItemListener iListen = new ItemListener()
	    { 
	    	@Override 
			public void itemStateChanged(ItemEvent event) 
			{
	    		//Retrieves the selected menu item
	    		JMenuItem menuItemSelected = (JMenuItem) event.getSource();
	    		//Retrieves the involved pop-up menu
	    		JPopupMenu popUpSelected   = (JPopupMenu) menuItemSelected.getParent();
	    		//Retrieves the involved menu
	    		JMenu menuSelected         = (JMenu) popUpSelected.getInvoker();
	    		//Determines if a selection has been made
	    		boolean selected           = (event.getStateChange() == ItemEvent.SELECTED);
				if(selected==true)
				{
					//Retrieves the involved player
					String player = menuSelected.getActionCommand();
					//Retrieves what sort of player it is (is it human or is it a computer?)
					String playerType = menuItemSelected.getActionCommand();
					//Retrieves the menu names
					String menuPlayerO = getMenuPlayerO();
					String menuPlayerX = getMenuPlayerX();
					if(player==menuPlayerO)
					{
						initGame.ticTacToeGameStatus.setPlayerTypeO(playerType);
					}
					else if(player==menuPlayerX)
					{
						initGame.ticTacToeGameStatus.setPlayerTypeX(playerType);
					}
					//Informs the user
					lblFields[0].setText(menuPlayerX+" ("+initGame.ticTacToeGameStatus.getPlayerTypeX()+") versus "+menuPlayerO+" ("+initGame.ticTacToeGameStatus.getPlayerTypeO()+") it is!");
				}
			}		
	    };
	    btnGameTictactoe.addItemListener(iListen);
	    btnXHuman.addItemListener(iListen);
	    btnXComputer.addItemListener(iListen);
	    btnOHuman.addItemListener(iListen);
	    btnOComputer.addItemListener(iListen);
		//Sets the players
	    btnXHuman.setSelected(true);
		btnOHuman.setSelected(true);
	}
	
	/**
	 * Initializes the panel
	 */
	private JPanel initializePanel(TicTacToeGame initGame, JFrame frmTicTacToe, int field_width, int bar_height) 
	{
		//Creates a new panel within the frame
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, field_width * 3, field_width * 3 + bar_height);
		frmTicTacToe.getContentPane().add(panel);
		panel.setLayout(null);
		return panel;
	}
	
	/**
	 * Initializes the labels
	 */
	private JLabel[] initializeLabels(TicTacToeGame initGame, JPanel panel, int field_width, int bar_height) 
	{
		//Nine fields plus a statusbar
		JLabel[] lblFields = new JLabel[10];
		//Labelnumber
		int labelNumber = 0;
		//Creates status field number 0
		lblFields[0] = new JLabel();
		lblFields[0].setText(initGame.ticTacToeGameStatus.getField(0));
		lblFields[0].setHorizontalAlignment(SwingConstants.CENTER);
		lblFields[0].setBounds(0, field_width * 3, field_width * 3, bar_height);
		panel.add(lblFields[0]);

		/**
		 * Overrides the Mouse Listener in order to retrieve the label index
		 */
		final class MouseListenerWithIndex extends MouseAdapter 
		{
		    //A final label index
			private final int index;
		    //Constructor
			public MouseListenerWithIndex(int index) 
		    {
		        //Sets index
				this.index = index;
		    }
			@Override
			public void mousePressed (MouseEvent event)
			{
				//Reports the involved label index when the mouse was pressed
				String status = "You clicked on "+ event.getComponent().getClass().getName() + Integer.toString(index);								
				lblFields[0].setText(status);
				//Places a marker
				placeMarker(initGame, lblFields, index); 				
			}
		}
		//Creates the fields number 1 to 9
		for(int y = 0;y < 3; y++)
		{
			for(int x = 0;x < 3; x++)
			{
				//We're creating a new label
				labelNumber = labelNumber + 1;
				//Creates a field 
				lblFields[labelNumber] = new JLabel();			
				lblFields[labelNumber].setText(initGame.ticTacToeGameStatus.getField(1));
				lblFields[labelNumber].setHorizontalAlignment(SwingConstants.CENTER);
				lblFields[labelNumber].setBounds(x * field_width, y * field_width, field_width, field_width);
				lblFields[labelNumber].addMouseListener(new MouseListenerWithIndex(labelNumber));
				panel.add(lblFields[labelNumber]);			
			}
		}
		return lblFields;
	}

	/**
	 * Initializes the lines
	 */
	private void initializeLines(JPanel panel, int field_width, int bar_height) 
	{
		//Creates line 1
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, field_width, field_width * 3, field_width);
		panel.add(separator_1);
		//Creates line 2
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, field_width * 2, field_width * 3, field_width * 2);
		panel.add(separator_2);
		//Creates line 3
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(field_width, 0, field_width, field_width * 3);
		panel.add(separator_3);
		//Creates line 4
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(field_width * 2, 0, field_width * 2, field_width * 3);
		panel.add(separator_4);		
	}

	/**
	 * Updates the GUI
	 */
	private void updateGUI(TicTacToeGame game, JLabel[] lblFields) 
	{
		//Loops through the menus
		int menuCountMax = frmTicTacToe.getJMenuBar().getMenuCount() - 1;
		for(int menuCount = 0;menuCount <= menuCountMax;menuCount++)
		{
			//Retrieves a menu
			JMenu menuPlayer = frmTicTacToe.getJMenuBar().getMenu(menuCount);
			//Loops through the menu items in this menu
			int menuItemCountMax = menuPlayer.getItemCount() - 1;
			for(int menuItemCount = 0;menuItemCount <= menuItemCountMax;menuItemCount++)
			{
				//Retrieves a menu item
				JMenuItem menuItem = menuPlayer.getItem(menuItemCount);
				if(menuPlayer.getActionCommand()==menuPlayerX && menuItem.getActionCommand()==game.ticTacToeGameStatus.getPlayerTypeX())
				{
					//Player X has this player type
					menuItem.setSelected(true);
					break;
				}
				if(menuPlayer.getActionCommand()==menuPlayerO && menuItem.getActionCommand()==game.ticTacToeGameStatus.getPlayerTypeO())
				{
					//Player O has this player type
					menuItem.setSelected(true);
					break;
				}
			}
		}
		String status = "";
		//Sets the fields
		for(int i = 1;i < 10;i++)
		{
			switch(game.ticTacToeGameStatus.getField(i))
			{
				case TicTacToeGame.PLAYER_X: lblFields[i].setIcon(PICTURE_X); break;
				case TicTacToeGame.PLAYER_O: lblFields[i].setIcon(PICTURE_O); break;
				default: lblFields[i].setIcon(null); break;
			}
		}
		//Checks whether the game is over or not
		if (game.ticTacToeGameStatus.getGameOver() == 1)
		{
			status = "The game is over! ";
			status += "And the winner is " + game.ticTacToeGameStatus.getWinner() + ".";
		}
		//Sets the status bar
		status = Strings.NullValue(status, game.ticTacToeGameStatus.getField(0));
		lblFields[0].setText(status);
	}	

	/**
	 * Places a marker
	 */
	private void placeMarker(TicTacToeGame game, JLabel[] lblFields, int index) 
	{
		//Places a marker and applies the rules
		game.ticTacToeGameStatus = game.placeMarker(index);
		//Updates the GUI
		updateGUI(game, lblFields);
	}	
}
