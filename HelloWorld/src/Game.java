import java.util.Scanner;
import java.util.ArrayList;

public class Game {
	
	Scanner in = new Scanner(System.in);
	private int guesses;
	private String word;
	private StringBuilder dispWord = new StringBuilder();
	private boolean gameWon = false;
	private StringBuilder alreadyGuessed = new StringBuilder();

	Player[] players;
		
	public void start(){
		System.out.println("Enter the word to guess:");
		this.word = in.nextLine();
		createWordMask(word);
		
		System.out.println("How many players?");
		int numOfPlayers = in.nextInt();
		players = new Player[numOfPlayers];
		
		for (int i = 0; i < numOfPlayers; i++) {
			System.out.println("Enter name for Player " + (i+1) + ":");
			String name = in.next();
			players[i] = new Player(name);
		}
	}
	
	public void play(){
		for (int i = 0; i < players.length; i++) {
			players[i].guess(this);
			guesses = getGuesses();
			printWord();
			printMan();
			
			if (word.equalsIgnoreCase(dispWord.toString()) || wordComplete()) {
				gameWon = true;
				return;
			}
		}
	}
	
	public boolean wordComplete(){
	    for (int i = 0; i < word.length(); i++) {
	        if (alreadyGuessed.toString().toLowerCase().indexOf(word.toLowerCase().charAt(i)) == -1) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public void createWordMask(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != ' ')
				dispWord.append("_.");
				else
					dispWord.append("  ");
		}
	}
	
	public void updateWord(String word)
	{
		dispWord = new StringBuilder(word);
	}
	
	public void updateWord(ArrayList<Integer> locations)
	{
		for (int i = 0; i < locations.size(); i++) {
			dispWord.setCharAt(locations.get(i)*2, word.charAt(locations.get(i)));
		}
	}
	
	public String getWord(){
		return word;
	}
	
	public String getGuessed(){
		return alreadyGuessed.toString().toLowerCase();
	}
	
	public void addGuessed(String guess){
		alreadyGuessed.append(guess);
	}
	
	public boolean isGameWon() {
		return gameWon;
	}
	
	public void printWord() {
		System.out.println("---------");
		System.out.println(dispWord);
		System.out.println("---------");
	}
	
	public void printMan() {
		switch (guesses) {
		case 0:
			System.out.println(
					" _ _\r\n" + 
					"|   |______\r\n" + 
					"|          |\r\n" + 
					"|__________|");	
			break;
		case 1:
			System.out.println( 
					"  |  \r\n" + 
					"  |  \r\n" + 
					"  |  \r\n" + 
					"  |  \r\n" + 
					"  |  \r\n" + 
					" _|_\r\n" + 
					"|   |______\r\n" + 
					"|          |\r\n" + 
					"|__________|");
			break;
		case 2:
			System.out.println(
					"  ____\r\n" + 
					"  |        \r\n" + 
					"  |        \r\n" + 
					"  |       \r\n" + 
					"  |   \r\n" + 
					"  |   \r\n" + 
					" _|_\r\n" + 
					"|   |______\r\n" + 
					"|          |\r\n" + 
					"|__________|");
			break;
		case 3:
			System.out.println(
					"  ____\r\n" + 
					"  |    |      \r\n" + 
					"  |         \r\n" + 
					"  |       \r\n" + 
					"  |    \r\n" + 
					"  |   \r\n" + 
					" _|_\r\n" + 
					"|   |______\r\n" + 
					"|          |\r\n" + 
					"|__________|");
			break;
		case 4:
			System.out.println(
					"  ____\r\n" + 
					"  |    |      \r\n" + 
					"  |    o      \r\n" + 
					"  |        \r\n" + 
					"  |    \r\n" + 
					"  |   \r\n" + 
					" _|_\r\n" + 
					"|   |______\r\n" + 
					"|          |\r\n" + 
					"|__________|");
			break;
		case 5:
			System.out.println(
					"  ____\r\n" + 
					"  |    |      \r\n" + 
					"  |    o      \r\n" + 
					"  |    |     \r\n" + 
					"  |    |\r\n" + 
					"  |   \r\n" + 
					" _|_\r\n" + 
					"|   |______\r\n" + 
					"|          |\r\n" + 
					"|__________|");
			break;
		case 6:
			System.out.println(
					"  ____\r\n" + 
					"  |    |      \r\n" + 
					"  |    o      \r\n" + 
					"  |   /|\\     \r\n" + 
					"  |    |\r\n" + 
					"  |    \r\n" + 
					" _|_\r\n" + 
					"|   |______\r\n" + 
					"|          |\r\n" + 
					"|__________|");
			break;
		case 7:
			System.out.println(
					"  ____\r\n" + 
					"  |    |      \r\n" + 
					"  |    o      \r\n" + 
					"  |   /|\\     \r\n" + 
					"  |    |\r\n" + 
					"  |   / \\\r\n" + 
					" _|_\r\n" + 
					"|   |______\r\n" + 
					"|          |\r\n" + 
					"|__________|");

		default:
			break;
		}
	}
	
	public int getGuesses(){
		guesses = 0;
		for (int i = 0; i < players.length; i++) {
			guesses += players[i].getGuesses();
		}
		return guesses;
	}
	
	public void printScores(){
		int maxScore = 0;
		int winner = -1;
		for (int i = 0; i < players.length; i++) {
			int score = players[i].getScore();
			if (score > maxScore) {
				maxScore = score;
				winner = i;
			}
			System.out.println(players[i].getName() + " scored " + players[i].getScore() + " points");
		}
		System.out.println(players[winner].getName() + " wins the game!!!");
	}
	
}
