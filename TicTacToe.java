import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
class TicTacToe{
	int[][] board = new int[3][3];
	int moves = 0; // for segfault checking
	
	public TicTacToe(){
		this.initialize_Board();
	}

	public void user_turn(int i, int j){
		if(this.board[i][j]  == 0){
			this.board[i][j] = 1; //1 is for user, 2 is for agent
			moves++;
			if(moves < 9 && (this.check_Winner() == 0)){
				this.agent_turn();				
			}
		}
	}

	public void reset(){
		this.moves = 0;
		this.initialize_Board();
	}

	public void agent_turn(){
		Gui.set_turn("Agent's turn");
		Scanner	 sc = new Scanner(System.in);
		ArrayList<State> array_moves = new ArrayList<State>();
		State rightMove = null;
		int max = -2;
		int nextX, nextY;
		
		moves++; // add moves counter
		
		// Traverse all the possible moves
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(this.board[i][j] == 0){ // free spots
					array_moves.add(new State(copyBoard(), i, j, 9-moves, 2, true)); // get the utility
				}
			}
		}
		for(State s: array_moves){
			// s.print_board();
			s.computeUtility();
            // sc.nextLine();
		}

		System.out.print("Utilities: ");
		// Get the state with highest utility
        for(int i=0; i<array_moves.size(); i++){
        	System.out.print(array_moves.get(i).getUtility()+" ");
			if(array_moves.get(i).getUtility() >= max){ // PROBLEM: WHAT IF EQUAL YUNG UTILITY
				max = array_moves.get(i).getUtility();
				rightMove = array_moves.get(i);
            }
		}

		System.out.println();
		// Get its coordinates
		nextX = rightMove.getX();
		nextY = rightMove.getY();
		

		// Set the board map by coordinates of the highest utility
		this.board[nextX][nextY] = 2; //1 is for user, 2 is for agent
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
			return this.board[0][2];
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

	public int[][] copyBoard(){
		int[][] copy = new int[3][3];
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				copy[i][j] = board[i][j];
			}
		}

		return copy;
	}
}