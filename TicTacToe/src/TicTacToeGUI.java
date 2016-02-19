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
	//GUIs have frames
	private JFrame frmTicTacToe;
	
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
		//Initializes the game
		initGame = initializeGame();
		//Initializes the frame
		initFrame = initializeFrame(initGame, FIELD_WIDTH, BAR_HEIGHT);
		//Initializes the panel
		initPanel = initializePanel(initGame, initFrame, FIELD_WIDTH, BAR_HEIGHT);
		//Initializes the labels
		initializeLabels(initGame, initPanel, FIELD_WIDTH, BAR_HEIGHT);
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
	private JFrame initializeFrame(TicTacToeGame initGame, int field_width, int bar_height) 
	{
		//Creates a new frame
		frmTicTacToe = new JFrame();
		frmTicTacToe.setTitle("Tic Tac Toe");
		frmTicTacToe.setBounds(field_width, field_width, field_width * 3, field_width * 3 + bar_height * 2);
		frmTicTacToe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTicTacToe.getContentPane().setLayout(null);
		return frmTicTacToe;
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
	private void initializeLabels(TicTacToeGame initGame, JPanel panel, int field_width, int bar_height) 
	{
		//Nine fields plus a statusbar
		JLabel[] lblFields = new JLabel[10];
		//Labelnumber
		int labelNumber = 0;
		//Creates status field number 0
		lblFields[0] = new JLabel();
		lblFields[0].setText(initGame.gameStatus.getField(0));
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
			public void mousePressed ( MouseEvent event )
			{
				//Reports the involved label index when the mouse was pressed
				String status = "You clicked on "+ event.getComponent().getClass().getName() + Integer.toString(index);								
				lblFields[0].setText(status);;
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
				lblFields[labelNumber].setText(initGame.gameStatus.getField(1));
				lblFields[labelNumber].setHorizontalAlignment(SwingConstants.CENTER);
				lblFields[labelNumber].setBounds(x * field_width, y * field_width, field_width, field_width);
				lblFields[labelNumber].addMouseListener(new MouseListenerWithIndex(labelNumber));
				panel.add(lblFields[labelNumber]);			
			}
		}
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
}
