import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
	USER INTERFACE
*/
class Gui{
	static int[][] board; // copy of tictac board
	static JButton[][] button_board = new JButton[3][3]; // board made of buttons
	TicTacToe tictac = new TicTacToe();
	JFrame frame = new JFrame("Tic Tac Toe");

	public Gui(){
		JPanel board = new JPanel();
		board.setLayout(new  GridLayout(3,3));

		// GET THE BOARD FROM TICTAC
		this.get_Board();

		//INITIALIZES THE 3X3 SIZED BOARD
		this.initializa_Board(board);
		
		// BUILD FRAME
		frame.add(board);
		frame.setSize(600,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	void get_Board(){
		this.board = tictac.get_Board();
	}

	void update_Board(){
		this.get_Board();
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(this.board[i][j] == 1){
					this.button_board[i][j].setBackground(new Color(0,0,0));
				}else if(this.board[i][j] == 2){
					this.button_board[i][j].setBackground(new Color(100,0,0));
				}
			}
		}
	}

	void initializa_Board(JPanel board){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				JPanel cell = new JPanel();
				JButton button = new JButton();
				button.setName(i+","+j); //SETS THE NAME OF EACH BUTTON
				button.setPreferredSize(new Dimension(190,190));
				button.setBorderPainted(false);
				button.setFocusPainted(false);
				button.setBackground(new Color(175,206,255));
				cell.add(button);
				board.add(cell);

				//ADDS ACTION LISTENER
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
					// button.setBackground(new Color(0,0,0));
						String name = ((JComponent)e.getSource()).getName();
						String[] id = name.split(",");
						System.out.println("\nPressed "+id[0]+","+id[1]);
						int i = Integer.parseInt(id[0]);
						int j = Integer.parseInt(id[1]);
						tictac.user_turn(i,j);	//TURN NI USER. TIRA(UH! UH!)!
						tictac.print_board();
						check_Winner();						
						update_Board();
					}
				});

				// ADDS THE BUTTON TO THE BUTTON BOARD
				button_board[i][j] = button;

			}
		}
	}

	void check_Winner(){
		int winner = this.tictac.check_Winner();
		if(winner == 1){
			System.out.println("WINNER: USER");
			this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
		}else if(winner == 2){
			System.out.println("WINNER: AGENT");
			this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
		}else if(winner == 3){
			System.out.println("WINNER: NONE YOU WEAKSHITS");
			this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));			
		}
	}
}