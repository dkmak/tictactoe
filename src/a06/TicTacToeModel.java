//Darryl Mak, 50693792
package a06;
import java.util.Arrays;

/**
 * The TicTacToe class simulates a board for TicTacToe. Methods support changes to the game board and keeps track of the status of the board, and supports
 * the AI.
 * @author darryl
 *
 */
public class TicTacToeModel {
	private int[][] gameboard = new int[3][3];
	private int turn;
	private int userturn;
	private int aiturn;
	private int status;
	private int movesMade;
	/**
	 * Constructor for TicTacToeModel
	 * @param user decision on whether to go first or second.
	 */
	public TicTacToeModel(int start){
		gameboard= new int [3][3];
		turn=start;
		status=0;
		movesMade=0;
		userturn=start;
		if (start==1) {
			aiturn=2;
		}else {
			aiturn=1;
		}
	}
	/**
	 * Returns the game board
	 * @return
	 */
	public int[][] returnBoard(){
		return gameboard;
	}
	
	/**
	 * returns turn
	 * @return
	 */
	public int returnTurn() {
		return turn;
	}
	
	/**
	 * returns status
	 * @return
	 */
	public int returnStatus() {
		return status;
	}
	/**
	 * return movesMade
	 * @return
	 */
	public int returnMovesMade() {
		return movesMade;
	}
	
	
	
	/**
	 * Allows a player to mark the game board if the space is empty.
	 * @param row the desired row the current player wants to put their piece in.
	 * @param column the desired column the player wants to put their piece in.
	 * return status indicating whether or not change was successful.
	 */
	public boolean updateBoard(int row, int column) {
		if (gameboard[row][column]==0) {
			gameboard[row][column]= turn;
			checkWinner(row, column);
			
			if (turn ==1) {
				turn =2;
			}else {
				turn =1;
			}
			movesMade++;
			return true;
		}else {
			return false;}
			}
	/**
	 * Checks if the game is over, and there are no more moves left to be made.
	 * @return
	 */
	public boolean isGameOver() {
		if (status!=0) {
			return true;
		}
		if (movesMade >= 9 ) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if there are any moves remaining on the board
	 * @param board
	 * @return
	 */
	public boolean noMovesLeft(int[][]board) {
		for(int row=0; row<3;row++) {
			for(int col=0; col<3;col++) {
				if(board[row][col]==0) {
					return false;
				}
			}
		}	
		return true;
	}
	
	//AI
	
	/**
	 * Gives a heuristic value to the board, , depending on whether or not the board has a winning situation.
	 * @param board 2D array
	 * @return a value of the board 
	 */
	public int evalBoard(int[][] board) {
		
		if(checkRows(board)==10||checkRows(board)==-10) {
			//System.out.println("Rows");
			return checkRows(board);
		}
		else if(checkCols(board)==10||checkCols(board)==-10) {
			//System.out.println("Cols");
			return checkCols(board);
		}
		else if(checkDiag1(board)==10||checkDiag1(board)==-10) {
			//System.out.println("D1");
			return checkDiag1(board);
		}
		else if(checkDiag2(board)==10||checkDiag2(board)==-10) {
			//System.out.println("D2");
			return checkDiag2(board);
		}
		return 0;
	}
	/**
	 * Checks all the rows for a winning situation.
	 * @param board
	 * @return heuristic value if winning situation is found
	 */
	private int checkRows(int[][]board) {
		for (int row=0; row <3; row++) {
			if(board[row][0]==board[row][1]&&board[row][1]==board[row][2]) {
				if(board[row][0]==aiturn) {
					return +10;
				}else if(board[row][0]==userturn){
					return -10;
				}
			}
		}
		return 0;
	}
	/**
	 * Checks all columns for a winning situation.
	 * @param board
	 * @return heuristic value if winning situation is found
	 */
	private int checkCols(int[][]board) {
		for (int col=0; col <3; col++) {
			if(board[0][col]==board[1][col]&&board[1][col]==board[2][col]&&board[1][col]!=0) {
				if(board[0][col]==aiturn) {
					return +10;
				}else if(board[0][col]==userturn){
					return -10;
				}
			}
		}
		return 0;
	}
	/**
	 * Checks diagonal for a winning situation
	 * @param board
	 * @return heuristic value if winning situation is found
	 */
	private int checkDiag1(int[][]board) {
		if(board[0][0]==board[1][1]&&board[1][1]==board[2][2])
			if(board[0][0]==aiturn) {
				return +10;
			}else if(board[0][0]==userturn) {
				return -10;
				}

		return 0;
	}
	/**
	 * Checks other diagonal for a winning situation 
	 * @param board
	 * @return heuristic value if winning situation is found
	 */
	private int checkDiag2(int[][]board) {
		if(board[0][2]==board[1][1]&&board[1][1]==board[2][0]) {
			if(board[0][0]==aiturn) {
				return +10;
			}else if(board[0][0]==userturn) {
				return -10;
				}
		}
		return 0;
	}
	
	/**
	 * Determines the moves for the AI.
	 */
	public void aiMakeMove() {
		if(aiturn==1 && movesMade==0) {
			updateBoard(1,1);
			return;
		}
		int[] bestmove={-1, -1};
		int[] temp=canAIWin(gameboard);
		//System.out.println(Arrays.toString(temp));
		int[] temp2=canAIBlock(gameboard);
		//System.out.println(Arrays.toString(temp2));
		if(!Arrays.equals(temp,bestmove)) {
			bestmove=canAIWin(gameboard);
			//System.out.println("win");
			updateBoard(bestmove[0],bestmove[1]);
			return;
		}else if(!Arrays.equals(temp2, bestmove)){
			bestmove=canAIBlock(gameboard);
			//System.out.println("block");
			updateBoard(bestmove[0],bestmove[1]);
			return;
		}else {
			int bestvalue=-10000;
			for (int row=0; row<3; row++) {
				for(int col=0; col <3; col++) {
					if (gameboard[row][col]==0) {
						//make move
						gameboard[row][col]=aiturn;
						//evaluate move
						int currvalue=miniMax(gameboard,0,false);
						//undo the move
						gameboard[row][col]=0;
						//now you have heuristic score for current move, compare to best move
						if (currvalue > bestvalue) {
							bestvalue=currvalue;
							bestmove[0]=row;
							bestmove[1]=col;	
						}	
					}
				}
			}
		updateBoard(bestmove[0],bestmove[1]);
		}
	}
	
	/**
	 * The miniMax function utilizes is a common game theory algorithm that uses a scoring heuristic to determine the optimal move for a player. 
	 * @param board
	 * @param depth
	 * @param isMaxPlayer
	 */
	private int miniMax(int[][] board, int depth, boolean aiTurn) {
		//if gameboard has been won in its current state, return the value
		int score= evalBoard(board);
		if (score== +10) {
			return score-depth;
		}else if(score ==-10) {
			return score + depth;
		}
		//if the game is over and there are no more moves, return 0
		if(noMovesLeft(board)==true){
			return 0- depth;
		}
		//if player is the Maximizing Players
		if (aiTurn) {
			int bestVal=-1000000;
			for (int row=0; row <3; row++) {
				for (int col=0; col<3; col++) {
					if(board[row][col]==0) {
						board[row][col]=aiturn;
						int val=miniMax(board, depth+1, !aiTurn);
						bestVal=Math.max(val, bestVal);
						board[row][col]=0;
					}
				}
			}
			return bestVal;
		}else {
		//player is the Minimizing Player
			int bestVal= +100000000;
			for (int row=0; row<3; row++) {
				for (int col=0; col<3 ; col++) {
					if (board[row][col]==0) {
						board[row][col]=aiturn;
						int val=miniMax(board, depth+1, !aiTurn);
						bestVal=Math.min(val, bestVal);	
						board[row][col]=0;
					}
				}
			}
			return bestVal;
		}
	}
	/**
	 * Returns the state of all possible scenarios where the game might end.
	 * @param board
	 * @param turn
	 * @return
	 */
	public int[] aboutToEnd(int[][] board,int turn) {
		int[] bestMove = {-1, -1}; // row, column
		//
		if ( ((board[0][1]==turn && board[0][2]==turn) ||
			 (board[1][0]==turn && board[2][0]==turn)  ||
			 (board[1][1]==turn && board[2][2]==turn)  )  &&
			  board[0][0]==0) 
			{
				bestMove[0] = 0;
				bestMove[1] = 0;
			}
		else if ( ((board[0][0]==turn && board[0][2]==turn) ||
				  (board[1][1]==turn && board[2][1]==turn))  &&
				board[0][1]==0) 
			{
				bestMove[0] = 0;
				bestMove[1] = 1;
			}
		else if ( ((board[0][0]==turn && board[0][1]==turn) ||
				  (board[2][0]==turn && board[1][1]==turn) ||
				(board[1][2]==turn && board[2][2]==turn)) &&
				board[0][2]==0) 
			{
			bestMove[0] = 0;
			bestMove[1] = 2;
			}
		else if (  ((board[0][0]==turn && board[2][0]==turn) ||
				   (board[1][1]==turn && board[1][2]==turn) ) &&
				board[1][0]==0) 
			{
			bestMove[0] = 1;
			bestMove[1] = 0;
			}
		else if ( ((board[0][0]==turn && board[2][2]==turn) ||
				(board[0][2]==turn && board[2][0]==turn) ||
				(board[0][1]==turn && board[2][1]==turn) ||
				(board[1][0]==turn && board[1][2]==turn) ) &&
				board[1][1]==0) {
			bestMove[0] = 1;
			bestMove[1] = 1;
			}
		else if ( ((board[0][2]==turn && board[2][2]==turn) ||
				(board[1][0]==turn && board[1][1]==turn) ) &&
				board[1][2]==turn) 
			{
			bestMove[0] = 1;
			bestMove[1] = 2;
			}
		else if ( (	(board[0][0]==turn && board[1][0]==turn) ||
				(board[2][1]==turn && board[2][2]==turn) ||
				(board[1][1]==turn && board[0][2]==turn) ) &&
				board[2][0]==0) 
		{
			bestMove[0] = 2;
			bestMove[1] = 0;
		}
		else if ( (	(board[0][1]==turn && board[1][1]==turn) ||
				(board[2][0]==turn && board[2][2]==turn) ) &&
				board[2][1]==0) {
			bestMove[0] = 2;
			bestMove[1] = 1;
		}
		else if ( (	(board[0][0]==turn && board[1][1]==turn) ||
				(board[2][0]==turn && board[2][1]==turn) ||  
				(board[0][2]==turn && board[1][2]==turn) ) &&
				board[2][2]==0)
		{
			bestMove[0] = 2; bestMove[1] = 2;
		}	
		return bestMove;
	}
	
	/**
	 * Returns the coordinate of the space where AI can win.
	 * @param board 2D array
	 * @return an array with a move for the AI to win, {-1,-1} if not
	 */
	public int[] canAIWin(int[][]board) {
		return aboutToEnd(board, aiturn);
	}
	/**
	 * Returns the coordinates of the space where AI can block.
	 * @param board 2D array
	 * @return an array with a move for the AI to win, {-1,-1} if not
	 */
	public int[] canAIBlock(int[][]board) {
		return aboutToEnd(board,userturn);
	}
	/**
	 * Checks for winner by reading the game board after the most recent move. (Had trouble checking for winner using evaluate function.)
	 * @param row row of last move
	 * @param col col of last move
	 */
	public void checkWinner(int row, int col) {
		//turn, board is stored, can reference it directly
		//check row for 3 in a row
		//check column for 3 in a row
		//check diagonals
		
		checkRow(row);
		checkCol(col);
		checkDiag1();
		checkDiag2();
	}
	/**
	 * Checks a row for a winner.
	 * @param row row of last move
	 */
	private void checkRow(int row) {
		int checkRow=0;
		for (int j=0; j <3; j++) {
			if (gameboard[row][j]==turn) {
				checkRow++;
			}
		}
		 if(checkRow==3) {
			 status=turn;
		 }
	}
	/**
	 * Checks a column of last move;
	 * @param col
	 */
	private void checkCol(int col) {
		int checkCol=0;
		for (int i=0; i< 3; i++) {
			if(gameboard[i][col]==turn) {
				checkCol++;
			}
		}
		if(checkCol==3) {
			status=turn;
		}
		
	}
	/**
	 * Checks the diagonal for a winner
	 */
	private void checkDiag1() {
		int checkDiag1=0;
		int j1=0;
		for (int i1=0; i1 < 3; i1++) {
			if (gameboard[i1][j1]==turn) {
				checkDiag1++;
			}
			j1++;
		}
		if(checkDiag1==3) {
			status=turn;
		}
		
	}
	/**
	 * Checks the other diagonal for a winner. 
	 */
	private void checkDiag2() {
		int checkDiag2=0;
		int j2=2;
		for (int i2=0; i2 <3; i2++) {
			if (gameboard[i2][j2]==turn) {
				checkDiag2++;
			}
			j2--;
		}
		if(checkDiag2==3) {
			status=turn;
		}
	}

}


	

