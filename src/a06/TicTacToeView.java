//Darryl Mak, 50693792
package a06;


/**
 * The TicTacToeUI provides methods to make a simple UI for the user.
 * @author darryl
 *
 */
public interface TicTacToeView {
	/**
	 * Draws a TicTacToe board for the UI.
	 * @param 2D array
	 */
	public static void drawBoard(int[][] board) {
		 System.out.println();
	        for (int i = 0; i < board.length; i++) {
	            for (int j = 0; j < board[i].length; j++) {
	                if (board[i][j] == 0) {
	                    System.out.print("_");
	                } else {
	                    if(board[i][j]==1) {
	                    	System.out.print('x');
	                    }else {
	                    	System.out.print('o');
	                    }
	                }
	                if (j < 2) {
	                    System.out.print("|");
	                } else {
	                    System.out.println();
	                } 
	             }
	         }
	}
	
	/**
	 * Announces the winner once the game is over.
	 * @param game
	 * @param user value depending on whether or not user decieded to go first
	 */
	public static void announceWinner(TicTacToeModel game, int user) {
		if (game.returnStatus()==0) {
			System.out.println("It's a tie!");
		}else if (game.returnStatus()==1) {
			System.out.println("User Wins!");
		}else {
			System.out.println("Computer wins!");
		}
		drawBoard(game.returnBoard());
	}
	/**
	 * Outputs the current status of the board
	 * @param game a TicTacToeModel object
	 */
	public static void outputStatus(TicTacToeModel game) {
		System.out.println("\nUser is X, Computer is O.");
		System.out.println("Current Turn: "+game.returnTurn());
		drawBoard(game.returnBoard());
	}
	
	
	
}
