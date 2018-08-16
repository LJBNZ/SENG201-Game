package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PowerUpDen extends Destination {
	
	public Map<Integer, String> options = new HashMap<Integer, String>();

	/**
	 * The PowerUpDen constructor which initialised the Destination name and the options
	 */
	public PowerUpDen () {
		super("Power up den");

		options.put(1, "Go back to home base");
		options.put(2, "Consume power up item");
	}
	
	/**
	 * gets the map of the options that can be chosen from at the destination
	 * @return Map of option int and corresponding option String
	 */
	public Map<Integer, String> getOptions() {
		return options;
	}
	
	
	/**
	 * Launches the appropriate screen based on user option choice
	 * 
	 * @param choice 	the int representing the users option choice
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
				game.launchPowerUpDenScreen();
				break;
		}
	}

	

}
