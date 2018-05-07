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
	JPanel board_panel;
	int fighter;
	JPanel choose;
	public Gui(){

		JPanel north_panel = new JPanel();
		
		

		
		choose_fighter();
		// BUILD FRAME
		frame.add(choose);
		frame.setResizable(false);
		frame.setSize(600,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	void choose_fighter(){
		choose = new JPanel();
		choose.setLayout(new FlowLayout());
		choose.setBackground(new Color(192,192,192));

		DropShadowPanel herme_panel = new DropShadowPanel(7);
		herme_panel.setLayout(new BorderLayout());
		JButton herme = new JButton("",new ImageIcon("herme.jpeg"));
		JLabel herme_label = new JLabel("Herme");
		herme_label.setFont(new Font("Courier New", Font.ITALIC, 30));
		herme_label.setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
		
		DropShadowPanel louie_panel = new DropShadowPanel(7);
		louie_panel.setLayout(new BorderLayout());

		JButton louie = new JButton("",new ImageIcon("louie.jpeg"));
		JLabel louie_label = new JLabel("Lowe");
		louie_label.setFont(new Font("Courier New", Font.ITALIC, 30));
		louie_label.setBorder(BorderFactory.createEmptyBorder(0,55,0,0));
	


		herme.setPreferredSize(new Dimension(190,190));
		herme.setBorderPainted(false);
		herme.setFocusPainted(false);
		herme.setBackground(new Color(89,89,89));

		louie.setPreferredSize(new Dimension(190,190));
		louie.setBorderPainted(false);
		louie.setFocusPainted(false);
		louie.setBackground(new Color(89,89,89));


		// herme_panel.setPreferredSize(new Dimension(200,200));
		// louie_panel.setPreferredSize(new Dimension(200,200));

		herme_panel.add(herme, BorderLayout.CENTER);
		herme_panel.add(herme_label, BorderLayout.SOUTH);
		louie_panel.add(louie, BorderLayout.CENTER);
		louie_panel.add(louie_label, BorderLayout.SOUTH);

		JLabel message = new JLabel("Choose your fighter!");
		message.setFont(new Font("Courier New", Font.BOLD, 40));
		message.setBorder(BorderFactory.createEmptyBorder(80,0,40,0));
		choose.add(message);
		choose.add(herme_panel);
		choose.add(louie_panel);

		herme.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					System.out.println("HERME IS YOUR FIGHTER!");
					fighter = 1;
					choose.setVisible(false);
					frame.remove(choose);
					initialize_board_panel();
					frame.add(board_panel);

			}
		});
		louie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					System.out.println("LOWE IS YOUR FIGHTER!");
					fighter = 2;
					choose.setVisible(false);
					frame.remove(choose);
					initialize_board_panel();
					frame.add(board_panel);

			}
		});




			
	}
	void initialize_board_panel(){
		board_panel = new JPanel();
		board_panel.setLayout(new  GridLayout(3,3));

		// GET THE BOARD FROM TICTAC
		this.get_Board();

		//INITIALIZES THE 3X3 SIZED BOARD
		this.initialize_Board(board_panel);
	}
	void get_Board(){
		this.board = tictac.get_Board();
	}

	void update_Board(){
		this.get_Board();
		ImageIcon user=null, agent=null;
		if(this.fighter == 1){
			user = new ImageIcon("herme.jpeg");
			agent = new ImageIcon("louie.jpeg");
		}else if(this.fighter == 2){
			user = new ImageIcon("louie.jpeg");
			agent = new ImageIcon("herme.jpeg");
		}
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(this.board[i][j] == 1){
					this.button_board[i][j].setIcon(user);
				}else if(this.board[i][j] == 2){
					this.button_board[i][j].setIcon(agent);
				}
			}
		}
	}

	void initialize_Board(JPanel board){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				DropShadowPanel cell = new DropShadowPanel(5);
				JButton button = new JButton();
				button.setName(i+","+j); //SETS THE NAME OF EACH BUTTON
				button.setPreferredSize(new Dimension(190,190));
				button.setBorderPainted(false);
				button.setFocusPainted(false);
				button.setBackground(new Color(89,89,89));
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