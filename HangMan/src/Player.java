import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	
	private int guesses;
	private int score;
	private String name;
	
	Scanner in = new Scanner(System.in);
	
	public Player(String name)
	{
		this.score = 0;
		this.guesses = 0;
		this.name = name;
	}
	
	public int getGuesses()
	{
		return guesses;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void guess(Game game)
	{
		String word = game.getWord().toLowerCase();
		
		System.out.println(this.name + ", enter your guess (Whole word or letter)");
		String guess = in.nextLine().toLowerCase();
		
		if (guess.length() > 1)
		{
			if (guess.equals(word))
			{
				this.score += 5;
				game.updateWord(word);
				return;
			}
			else
			{
				System.out.println("Incorrect! You're killing him!!");
				guesses += 2;
				return;
			}
		}
		else if (guess.length() == 1) {
			if (game.getGuessed().contains(guess)) {
				System.out.println("Someone already guessed that! FOOL!!! One stick closer to death..");
				guesses += 1;
				return;
			}
			
			if (word.contains(guess))
			{
				System.out.println("Correct!");
				game.addGuessed(guess);
				game.updateWord(findLetters(word, guess));
				this.score += 1;
				return;
			}else {
				System.out.println("Incorrect, one stick closer to death..");
				guesses += 1;
				return;
			}
		}
	}
	
	public ArrayList<Integer> findLetters(String word, String letter)
	{
		ArrayList<Integer> locations = new ArrayList<Integer>();
		int index = (word).indexOf(letter);
		while (index >= 0) {  // indexOf returns -1 if no match found
			locations.add(index);
			index = word.indexOf(letter, index + 1);
		}
		//System.out.println(locations.toString());
		return locations;
	}
	

}
