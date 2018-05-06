import java.util.*;

public class State{
    private int[][] board;
    private int x, y;
    private int utility;
    private State[] array_moves;
    private int turn;
    private int remainingMoves;

    public State(int[][] board, int x, int y, int remainingMoves, int turn){
        this.x = x;
        this.y = y;
        this.board = board;
        this.array_moves  = new State[remainingMoves];
        this.remainingMoves = remainingMoves;
        this.turn = turn;        

        // Mark the current move
        markCurrentMove();

        // Check for winner
        if(check_Winner() == 1){
            this.utility = -1;
            // System.out.println("User");                                
        }else if(check_Winner() == 2){
            this.utility = 1;
            // System.out.println("Winner");                                            
        }else if(check_Winner() == 3){
            this.utility = 0;
            // System.out.println("Draw");                                            
        }else{
            // Compute utility
            computeUtility();
            
            if(this.turn == 1){ // user turn
                minimization(); // get the least of the utilities
            }else{ // agent turn
                maximization(); // get the max of the utilities
            }
        }

        
    }

    public void maximization(){
        int max = -2;
        for(int i=0; i<this.array_moves.length; i++){
			if(this.array_moves[i] != null && this.array_moves[i].getUtility() > max){
                max = this.array_moves[i].getUtility();
            }
        }

        this.utility = max;
    }

    public void minimization(){
        int min = 2;
        for(int i=0; i<this.array_moves.length; i++){
			if(this.array_moves[i] != null && this.array_moves[i].getUtility() < min){
                min = this.array_moves[i].getUtility();
            }
        }
        this.utility = min;
    }

    public void computeUtility(){
        int counter = 0;
        for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
                if(this.board[i][j] == 0 && counter<this.remainingMoves){ // free spots
					this.array_moves[counter] = new State(copyBoard(), i, j, this.remainingMoves-1,3-this.turn); // get the utility of next moves
                    counter++;
				}
			}
        }
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
		if(this.remainingMoves == 0){
			return 3;
		}

		return 0;
	}

    public void markCurrentMove(){
        this.board[this.x][this.y] = this.turn;
    }

    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }

    public int getUtility(){
        return this.utility;
    }

    public int[][] getBoard(){
        return this.board;
    }

    public void print_board(){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				System.out.print(this.board[i][j]+" ");
			}
			System.out.println();
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