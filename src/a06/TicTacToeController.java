package a06;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The TicTacToeController controls the user input in this program.  
 * @author darryl
 *
 */

public class TicTacToeController {
	/**
	 * Choose turn takes user input to determine whether or not the user will go first.
	 * @param game
	 */
	public static int chooseTurn() {
		char userTurn= ' ';
		Scanner userIn= new Scanner(System.in);
		
		do{
			System.out.println("Would you like to go first? Enter either y (yes) or n (n)");
			userTurn=userIn.next().charAt(0);
			}while(userTurn!='y'&& userTurn!='n');
		if (userTurn=='y') {
			return 1;
		}else {
			return 2;
		}
		
	}
	/**
	 * Takes user input to make the user place a valid piece.
	 * @param game TicTacToeModel of a paticular game.
	 */
	public static void makeMove(TicTacToeModel game) {
		int row=-1;
		int column=-1;
		boolean valid=false;
		Scanner userIn= new Scanner(System.in);
		do {
			do {
				System.out.println("\nEnter which row and column you would like to mark on the board, by entering two numbers from 1-3."
						+ "\nPlace a single space between the two numbers. Invalid moves will be ignored.");
				System.out.println("(Note: The top row is 1, the bottom row is 3. The leftmost column is 1, the right most is 3.)");
				System.out.print("Enter numbers: ");
				try {
					row= userIn.nextInt() -1;
					column= userIn.nextInt() -1;
				}catch (InputMismatchException ex){
					userIn.nextLine();
				}
			}while(row < 0 || row > 2 || column < 0 || column >2);
			
			if(game.updateBoard(row, column)) {
				valid=true;
			}
			
		}while(!valid);
	}

}

