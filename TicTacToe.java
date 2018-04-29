import java.util.Random;

class TicTacToe{
	int[][] board = new int[3][3];
	int moves = 0; // for segfault checking
	
	public TicTacToe(){
		this.initialize_Board();
	}

	public void user_turn(int i, int j){
		if(this.board[i][j] != 1){
			this.board[i][j] = 1; //1 is for user, 2 is for agent
			moves++;
			if(moves<9 && (this.check_Winner() == 0 || this.check_Winner() == 2)){
				this.agent_turn();				
			}
		}
	}

	public void agent_turn(){
		Random ai = new Random();
		int x = 0, y = 0 ; // initial agent turn is (0,0)
		
		while(this.board[x][y] != 0){ // get a random free spot on the board
			x = ai.nextInt(3);
			y = ai.nextInt(3);
		}
		this.board[x][y] = 2; //1 is for user, 2 is for agent
		moves++;
	}

	public void print_board(){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}

	public int[][] get_Board(){
		return this.board;
	}

	public int check_Winner(){

		// ROW check
		if(this.board[0][0] == this.board[0][1] && this.board[0][1] == this.board[0][2] && this.board[0][2] != 0){ // row 0
			return this.board[0][0];
		}

		if(this.board[1][0] == this.board[1][1] && this.board[1][1] == this.board[1][2] && this.board[1][2] != 0){ // row 1
			return this.board[1][0];
		}

		if(this.board[2][0] == this.board[2][1] && this.board[2][1] == this.board[2][2] && this.board[2][2] != 0){ // row 2
			return this.board[2][0];
		}

		// COLUMN check

		if(this.board[0][0] == this.board[1][0] && this.board[1][0] == this.board[2][0] && this.board[2][0] != 0){ // col 0
			return this.board[0][0];
		}
		
		if(this.board[0][1] == this.board[1][1] && this.board[1][1] == this.board[2][1] && this.board[2][1] != 0){ // col 1
			return this.board[0][1];
		}

		if(this.board[0][2] == this.board[1][2] && this.board[1][2] == this.board[2][2] && this.board[2][2] != 0){ // col 2
			return this.board[0][2];
		}

		// DIAGONAL check

		if(this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2] && this.board[2][2] !=0){ // diag 0
			return this.board[0][0];
		}

		if(this.board[0][2] == this.board[1][1] && this.board[1][1] == this.board[2][0] && this.board[2][0] !=0){ // diag 1
			return this.board[0][0];
		}

		// End of Game
		if(moves == 9){
			return 3;
		}

		return 0;
	}

	public void initialize_Board(){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				board[i][j] = 0; //intializes the board
			}
		}
	}
}