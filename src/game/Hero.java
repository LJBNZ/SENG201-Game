/**
 * The Hero class contains methods and data for each individual
 * hero instantiated into the game environment
 * 
 * @author Logan Beard, James Macfarlane
 * @version 1.0, April 2018
 */

package game;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Hero {
	
	public String hero_name;
	public int hero_type;	
	public int current_health = 100;
	public int max_health = 100;
	public ArrayList<PowerUp> current_powerups = new ArrayList<PowerUp>();
	public boolean currently_healing = false;
	public ArrayList<Integer> healing_checkpoints = new ArrayList<Integer>();
	public Potion healing_potion;
	public boolean price_discount = false;
	public double damage_proportion = 1.0;
	public boolean team_heal_speed_doubled = false;
	
	/**
	 * Hero constructor which sets the hero name and type. Adds attributes to Hero base on type
	 * @param name		the String representing the hero name
	 * @param type		the int representing the hero type
	 */
	public Hero(String name, int type) {
		hero_name = name;
		hero_type = type;
		if (type == 1) { //Merchant 
			price_discount = true;
		} else if (type == 2) { //Cartographer
			PowerUp map = new PowerUp(0, 4);
			current_powerups.add(map);
		} else if (type == 3) { //Tank
			damage_proportion = 0.75;
		} else if (type == 4) { //Medic
			team_heal_speed_doubled = true;
		} else if (type == 5) { //Clairvoyant
			PowerUp clairvoyance = new PowerUp(0, 3);
			current_powerups.add(clairvoyance);
		} else if (type == 6) { //Psychic
			PowerUp psychic_powers = new PowerUp(0, 5);
			current_powerups.add(psychic_powers);
		}
	}
	
	/**
	 * Check to see if the hero has a price discount
	 * @return	true if price discount, false otherwise
	 */
	public boolean hasPriceDiscount() {
		return price_discount;
	}
	
	/**
	 * Check to see if the hero has increased healing speed
	 * @return	true if healing speed increased, false otherwise
	 */
	public boolean hasIncreasedHealSpeed() {
		return team_heal_speed_doubled;
	}

	/**
	 * Initates the healing process of a team member when given a Potion object
	 * to restore health from
	 * 
	 * @param potion	the Potion object to be consumed
	 * @param team		the Team object
	 * @return			true if potion is successfully consumed, false otherwise
	 */
	public boolean consumePotion(Potion potion, Team team) {
		if (current_health == 100) {
			return false;
		} else {
			healing_potion = potion;
			int heal_pct = potion.heal_percentage;
			int heal_increments = heal_pct / 25;
			//get the duration in seconds of each 25% heal incriment
			int increment_duration;
			if (team.hasDoubledHealSpeed()) {
				//duration is halved if team has a medic
				increment_duration = (potion.application_time / 2) / heal_increments;
			} else {
				increment_duration = potion.application_time / heal_increments;
			}
			
			//get current time
			LocalDateTime date = LocalDateTime.now();
			int current_time = date.toLocalTime().toSecondOfDay();
			
			//Calculate the intervals that the hero will gain 25% health at
			for (int i = 1; i <= heal_increments; i++) {
				int checkpoint = current_time + (i * increment_duration);
				healing_checkpoints.add(checkpoint);
			}
			//remove the potion from inventory
			team.inventory.remove(potion);
			currently_healing = true;
			return true;
		}
	}
	
	/**
	 * takes a PowerUp object from the inventory and adds it to the Hero's
	 * current powerups
	 * @param powerup	the PowerUp object to be consumed
	 * @param team		the Team object
	 */
	public void consumePowerup(PowerUp powerup, Team team) {
		this.current_powerups.add(powerup);
		team.inventory.remove(powerup);
	}

	/**
	 * Checks to see if hero health has been damaged below 0%
	 * @return	true if dead, false otherwise
	 */
	public boolean checkIfDead() {
		if (current_health <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/** 
	 * checks to see if the hero has a particular powerup in their current powerups
	 * @param powerup_name	the name of the powerup to search for
	 * @return				true if powerup is held, false otherwise
	 */
	public boolean hasPowerUp(String powerup_name) {
		for (int i = 0; i < current_powerups.size(); i++) {
			if (current_powerups.get(i).name == powerup_name) {
				return true;
			}
		}
		return false;
	}
}
