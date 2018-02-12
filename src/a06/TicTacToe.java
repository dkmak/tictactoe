package a06;
import java.util.Arrays;
/**
 * The TicTacToe class runs the fully implemented TicTacToe game. 
 * @author darryl
 *
 */
public class TicTacToe {
	
	public static void main(String[] args) {
		System.out.println("Welcome! Let's Tic Tac Toe, Bro (or Girl)!\n");
		int start=TicTacToeController.chooseTurn();
		
		TicTacToeModel newgame= new TicTacToeModel(start);
		boolean playing=true;
		
		while(playing) {
			if(start==2) {
				newgame.aiMakeMove();
				if(newgame.isGameOver()==true) {
					playing=false;
					break;
				}
				TicTacToeView.outputStatus(newgame);
				TicTacToeController.makeMove(newgame);
				if(newgame.isGameOver()==true) {
					playing=false;
				}
				
			}else {
				TicTacToeView.outputStatus(newgame);
				TicTacToeController.makeMove(newgame);
				if(newgame.isGameOver()==true) {
					playing=false;
					break;
				}
				newgame.aiMakeMove();
				if(newgame.isGameOver()==true) {
					playing=false;
				}
			}
		};
		TicTacToeView.announceWinner(newgame, start);
		//Finished
		 
		 
	}
}
