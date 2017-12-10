
public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
		while (game.getGuesses() < 7 && game.isGameWon() == false){
			game.play();
		}
		
		if (game.getGuesses() >= 7) { 
			System.out.println("YOU KILLED HIM!!!!!");
		}else { 
			System.out.println("HE LIVES!!!!!");
		}
		game.printScores();
		}
	}
