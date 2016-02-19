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
		//Declares a new game
		TicTacToeGame initGame;
		//Initializes the game
		initGame = initializeGame();
		//Initializes the GUI itself
		initializeFrame(initGame);
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
	private void initializeFrame(TicTacToeGame initGame) 
	{
		//A few hardcoded sizes
		final int FIELD_WIDTH = 150;
		final int BAR_HEIGHT  = 25;
		//Nine fields plus a statusbar
		JLabel[] lblFields = new JLabel[10];
		//Labelnumber
		int labelNumber = 0;
		//Creates a new frame
		frmTicTacToe = new JFrame();
		frmTicTacToe.setTitle("Tic Tac Toe");
		frmTicTacToe.setBounds(100, 100, 450, 500);
		frmTicTacToe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTicTacToe.getContentPane().setLayout(null);
		//Creates a new panel within the frame
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, FIELD_WIDTH * 3, FIELD_WIDTH * 3 + BAR_HEIGHT);
		frmTicTacToe.getContentPane().add(panel);
		panel.setLayout(null);
		//Creates status field number 0
		lblFields[0] = new JLabel();
		lblFields[0].setText(initGame.gameStatus.getField(0));
		lblFields[0].setHorizontalAlignment(SwingConstants.CENTER);
		lblFields[0].setBounds(0, FIELD_WIDTH * 3, FIELD_WIDTH * 3, BAR_HEIGHT);
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
				lblFields[labelNumber].setBounds(x * FIELD_WIDTH, y * FIELD_WIDTH, FIELD_WIDTH, FIELD_WIDTH);
				lblFields[labelNumber].addMouseListener(new MouseListenerWithIndex(labelNumber));
				panel.add(lblFields[labelNumber]);			
			}
		}
		//Creates line 1
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 150, 450, 150);
		panel.add(separator_1);
		//Creates line 2
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 300, 450, 300);
		panel.add(separator_2);
		//Creates line 3
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(150, 0, 150, 450);
		panel.add(separator_3);
		//Creates line 4
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(300, 0, 300, 450);
		panel.add(separator_4);		
	}
}
