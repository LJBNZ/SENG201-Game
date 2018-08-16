/**
 * The Team class contains methods and data for the team as well as a data
 * structure to house the individual Hero objects
 * 
 * @author Logan Beard, James Macfarlane
 * @version 1.0, April 2018
 */

package game;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Team {
	
	public String team_name;
	public ArrayList<Hero> heroes = new ArrayList<Hero>();
	
	public ArrayList<Item> inventory = new ArrayList<Item>();
	public int money = 50;
	public int current_city_num = 0;
	

	/**
	 * Asks user to input a team name, then stores the input in team_name variable
	 * @param 	teamname 	The String of the team name
	 */
	public void setTeamName(String teamname) {
		team_name = teamname;
	}

	/**
	 * Updates the health of the team members based on the current time
	 * by checking if the current time has surpassed healing increment 
	 * checkpoints
	 * 
	 *
	 */
	public void checkHealingStatus() {
		LocalDateTime date = LocalDateTime.now();
		int current_time = date.toLocalTime().toSecondOfDay();

		for (int i = 0; i < heroes.size(); i++) {
			Hero hero = heroes.get(i);
			//if hero is currently healing, update health value
			if (hero.currently_healing) {
				for (int j = 0; j < hero.healing_checkpoints.size(); j++) {
					if (hero.healing_checkpoints.get(j) <= current_time) {
						//if the current time exceeds a checkpoint, set hero health to the minimum of their current health + 25, or 100
						hero.current_health = Integer.min(hero.current_health + 25, 100);
						if (j == hero.healing_checkpoints.size() - 1 || hero.current_health == 100) {
							//If last healing checkpoint is exceeded, or health hits cap of 100% then stop healing
							hero.currently_healing = false;
							hero.healing_potion = null;
							hero.healing_checkpoints.clear();
						} else {
							hero.healing_checkpoints.remove(j);
						}
					}
				}
			}			
		}
	}

	/**
	 * Checks if the team has a map in one of team member's possession
	 * 
	 * @return true if a map is held, false otherwise
	 */
	public boolean hasMap() {
		for (int i = 0; i < heroes.size(); i++) {
			Hero hero = heroes.get(i);
			if (hero.hasPowerUp("Map")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if the team has clairvoyance in one of team member's possession
	 * 
	 * @return true if clairvoyance held, false otherwise
	 */
	public boolean hasClairvoyance() {
		for (int i = 0; i < heroes.size(); i++) {
			Hero hero = heroes.get(i);
			if (hero.hasPowerUp("Clairvoyance")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if the team has price discount in one of team member's attributes
	 * 
	 * @return true if a price discount is held, false otherwise
	 */
	public boolean hasPriceDiscount() {
		for (int i = 0; i < heroes.size(); i++) {
			Hero hero = heroes.get(i);
			if (hero.hasPriceDiscount()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if the team has increased healing speed in one of team member's attributes
	 * 
	 * @return true if healing increase is held, false otherwise
	 */
	public boolean hasDoubledHealSpeed() {
		for (int i = 0; i < heroes.size(); i++) {
			Hero hero = heroes.get(i);
			if (hero.hasIncreasedHealSpeed()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * removes a hero from the team
	 * @param hero the Hero object to be removed
	 */
	public void removeHero(Hero hero) {
		heroes.remove(hero);
	}
	

}
