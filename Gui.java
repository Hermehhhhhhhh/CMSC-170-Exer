import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
/*
	USER INTERFACE
*/
class Gui{
	static int[][] board; // copy of tictac board
	static JButton[][] button_board = new JButton[3][3]; // board made of buttons
	TicTacToe tictac = new TicTacToe();
	JFrame frame = new JFrame("Tic Tac Toe");
	static JLabel turn;
	JPanel board_panel;
	
	public Gui(){
		frame.setLayout(new BorderLayout());

		JPanel north_panel = new JPanel();
		JPanel reset_panel = new JPanel();
		JButton reset = new JButton("Reset");
		// reset.setContentAreaFilled(false);
		reset.setFocusPainted(false);
		reset.setBackground(new Color(18,131,183));
		reset.setForeground(Color.WHITE);
		reset_panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,430));
		reset_panel.add(reset);
		north_panel.add(reset_panel);

		turn = new JLabel("User turn");
		north_panel.add(turn);

		board_panel = new JPanel();
		board_panel.setLayout(new  GridLayout(3,3));

		// GET THE BOARD FROM TICTAC
		this.get_Board();

		//INITIALIZES THE 3X3 SIZED BOARD
		this.initialize_Board(board_panel);

		//ADDS ACTION LISTENER
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			// button.setBackground(new Color(0,0,0));
				System.out.println("RESET!");
				board_panel.removeAll();
				board_panel.revalidate();
				tictac.reset();;
				initialize_Board(board_panel);
			}
		});

		
		// BUILD FRAME
		frame.add(north_panel, BorderLayout.NORTH);
		frame.add(board_panel, BorderLayout.CENTER);
		frame.setSize(600,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	void get_Board(){
		this.board = tictac.get_Board();
	}

	static void set_turn(String s){
		Gui.turn.setText(s);
	}
	void update_Board(){
		this.get_Board();
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(this.board[i][j] == 1){
					this.button_board[i][j].setIcon(new ImageIcon(("rock.png")));
				}else if(this.board[i][j] == 2){
					this.button_board[i][j].setIcon(new ImageIcon(("angrybird.jpg")));
				}
			}
		}
	}

	void initialize_Board(JPanel board){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				JPanel cell = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
				JButton button = new JButton("",new ImageIcon(("walls.jpg")));
				button.setName(i+","+j); //SETS THE NAME OF EACH BUTTON
				button.setPreferredSize(new Dimension(190,190));
				button.setBorderPainted(false);
				button.setFocusPainted(false);
				// button.setBackground(new Color(175,206,255));

				cell.add(button);
				board_panel.add(cell);

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
						Gui.set_turn("User's turn");
						tictac.print_board();
						update_Board();
						check_Winner();						
					}
				});

				// ADDS THE BUTTON TO THE BUTTON BOARD
				button_board[i][j] = button;

			}
		}
	}
	void message_box(String wins){
		int dialogButton = JOptionPane.showConfirmDialog (frame, wins+" wins. Do you want to play again?");
		if(dialogButton == JOptionPane.YES_OPTION) {
		    board_panel.removeAll();
			board_panel.revalidate();
			tictac.reset();
			initialize_Board(board_panel);
		
		}else System.exit(0);
	}
	void check_Winner(){
		int winner = this.tictac.check_Winner(board);
		if(winner == 1){
			System.out.println("WINNER: USER");
			message_box("User");
		}else if(winner == 2){
			System.out.println("WINNER: AGENT");
			message_box("Agent");
		}else if(winner == 3){
			System.out.println("WINNER: NONE YOU WEAKSHITS");
			message_box("No one ");
		}
		
	}

	
	public void run(){}
}