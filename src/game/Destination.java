package game;

import java.util.Map;

public abstract class Destination {
	
	public int direction;
	public String place_name;
	
	/**
	 * The Destination object constructor which initiates the name
	 * @param name	the given Destination name
	 */
	public Destination(String name) {
		place_name = name;
	}
	
	/**
	 * the getter method which returns the Destination place_name
	 * @return		the place_name String
	 */
	public String getPlaceName() {
		return place_name;
	}
	
	/**
	 * Sets which direction this Destination is in the current city (relative to the home base)
	 * @param dir	the direction to be allocated to the Destination
	 */
	public void setDirection(int dir) {
		// North = 1, East = 2, South = 3, West = 4
		direction = dir;
	}
	
	/**
	 * the getter method to return the Destination object's direction
	 * @return	the direction of the Destination
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * gets the map of the options that can be chosen from at the destination
	 * @return Map of option int and corresponding option String
	 */
	public abstract Map<Integer, String> getOptions();
	
	/**
	 * Logic for handling the choice of options selected by the user, routes to correct window
	 * @param choice	the int representing the option number chosen
	 * @param game		the Game object
	 */
	public abstract void parseChoice(int choice, Game game);

}
