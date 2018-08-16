package game;

import java.util.HashMap;
import java.util.Map;

public class HomeBase extends Destination {
	
	public Map<Integer, String> options = new HashMap<Integer, String>();

	/**
	 * The HomeBase constructor which initialises the two options that can be selected at this location
	 */
	public HomeBase() {
		super("Home base");
		
		options.put(1, "Travel");
		options.put(2, "Check team status");
	}

	
	/**
	 * gets the map of the options that can be chosen from at the destination
	 * @return Map of option int and corresponding option String
	 */
	public Map<Integer, String> getOptions() {
		return options;
	}
	
	
	/**
	 * Launches the appropriate screen based on the user selected option
	 * 
	 * @param choice 	the option chosen by the user
	 * @param game 		the Game object
	 */
	public void parseChoice(int choice, Game game) {
		City current_city = game.cities.get(game.current_city);
		switch(choice) {
			case 1:
				game.launchTravelScreen();
				break;
			case 2:
				game.launchTeamStatusScreen();
				break;
		}
	}

}
