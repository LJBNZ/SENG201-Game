package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class City {

	public Map<Integer, Destination> locations = new HashMap<Integer, Destination>();
	public Map<Integer, Boolean> known_locations = new HashMap<Integer, Boolean>();
	public Map<Integer, String> direction_names = new HashMap<Integer, String>();
	public Villain villain;
	public int current_destination = 0;

	/**
	 * construct the City object and populate the direction_names ArrayList
	 */
	public City() {
		direction_names.put(1, "North");
		direction_names.put(2, "East");
		direction_names.put(3, "South");
		direction_names.put(4, "West");
	}
	
	
	/**
	 * Forms a map of the available movement options from the home base
	 * 
	 * @param has_map	 if a map is used then locations will be known
	 * @return a map of the strings which represent the options
	 */
	public Map<Integer, String> getMovementOptions(boolean has_map) {
		
		Map<Integer, String> options = new HashMap<Integer, String>();
		
		//If the destination name has been visited before or a Map is possessed 
		//the option name will reflect this, will display "???" otherwise
		for (int i = 1; i < 5; i++) {
			Destination dest = locations.get(i);
			String option_name = "???";
			
			if (has_map || known_locations.get(i)) {
				option_name = dest.getPlaceName();
			}
			options.put(i, option_name);
		}
		return options;
	}
	
	
	/**
	 * Attempts to move the team in the given direction, will return false if 
	 * input is not in range 0-4, or if you try to move the team to the location they
	 * are currently in
	 * 
	 * @param direction		the integer that represents to location to be moved to
	 * @param game			the Game object
	 * @return a boolean which represents whether the move was allowed and made, or not
	 */
	public boolean move(int direction, Game game) {
		boolean success = false;
		
		if (current_destination == 0) {
			if (direction >= 1 && direction <= 4) {
				current_destination = direction; 
				known_locations.put(direction, true);
				success = true;
			}
		} else {
			if (direction == 0) {
				game.eligible_for_random_event = true;
				current_destination = direction; 
				success = true;
			}
		}
		return success;
	}
	
	
	/**
	 * Initialise all the destination objects for this city, randomise the directions of each
	 * one from the home base and add direction, destination pairs to a Map so that we can
	 * 'look up' what destination will be reached when heading in a certain direction
	 */
	public void initDestinations() {
		
		// Add all possible directions to a list
		ArrayList<Integer> directions = new ArrayList<Integer>();
		directions.add(1);
		directions.add(2);
		directions.add(3);
		directions.add(4);
		// Shuffle these to simulate random placement of destinations
		Collections.shuffle(directions);		
		
		// Initialise the types of destinations (minus home base as that is always in same place)
		Hospital hospital = new Hospital();
		PowerUpDen den = new PowerUpDen();
		Shop shop = new Shop();
		Lair lair = new Lair();
		HomeBase homebase = new HomeBase();
		
		// Add the destinations to a list
		ArrayList<Destination> destinations = new ArrayList<Destination>();
		destinations.add(hospital);
		destinations.add(den);
		destinations.add(shop);
		destinations.add(lair);
		
		// For each random direction assign a destination, add these to a Map
		homebase.setDirection(0);
		locations.put(0, homebase);
		for (int i = 0; i < directions.size(); i++) {
			int direction = directions.get(i);
			Destination dest = destinations.get(i);
			dest.setDirection(direction);
			
			locations.put(direction, dest);
			known_locations.put(direction, false);
		}
	}
}
