package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Lair extends Destination {
	public Map<Integer, String> game_names = new HashMap<Integer, String>();
	public Map<Integer, String> options = new HashMap<Integer, String>();
	
	/**
	 * The Lair constructor which initiates the Destinations name and populates
	 * the game_names and options list
	 */
	public Lair () {
		super("Villain's lair");
		game_names.put(0, "Rock Paper Scissors");
		game_names.put(1, "Dice Rolls");
		game_names.put(2, "Guess the Number");
		
		options.put(1, "Go back to home base");
		options.put(2, "Battle villain");
	}
	
	/**
	 * gets the map of the options that can be chosen from at the destination
	 * @return Map of option int and corresponding option String
	 */
	public Map<Integer, String> getOptions() {
		return options;
	}	

	/**
	 * launches the appropriate screen based on the user selected option
	 * 
	 * @param choice 	the option chosen by the user
	 * @param game 		the Game object 
	 */
	public void parseChoice(int choice, Game game) {
		City current_city = game.cities.get(game.current_city);
		switch(choice) {
			case 1:
				current_city.move(0, game);
				game.launchOptionsScreen();
				break;
			case 2:
				game.launchBattleScreen();
				break;
		}
	}

	
}
