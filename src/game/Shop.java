package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Shop extends Destination {
	
	public Map<Integer, String> options = new HashMap<Integer, String>();
	
	public ArrayList<Item> stock = new ArrayList<Item>();
	
	/**
	 * The Shop constructor which initialises the stock list and options
	 */
	public Shop () {
		super("Shop");
		populateStock();
		options.put(1, "Go back to home base");
		options.put(2, "Purchase items");
	}

	/**
	 * Generates potions, powerups and maps to be put into the store stock list to be sold
	 */
	private void populateStock() {
		Potion small_pot = new Potion(10, 25);
		stock.add(small_pot);
		Potion medium_pot = new Potion(20, 50);
		stock.add(medium_pot);
		Potion large_pot = new Potion(30, 100);
		stock.add(large_pot);
		
		PowerUp lucky_charm = new PowerUp(40, 1);
		stock.add(lucky_charm);
		PowerUp extra_dice = new PowerUp(30, 2);
		stock.add(extra_dice);
		PowerUp clairvoyance = new PowerUp(25, 3);
		stock.add(clairvoyance);
		PowerUp map = new PowerUp(25, 4);
		stock.add(map);
		
	}
	
	/**
	 * gets the map of the options that can be chosen from at the destination
	 * @return Map of option int and corresponding option String
	 */
	public Map<Integer, String> getOptions() {
		return options;
	}
	
	/**
	 * Launches the appropriate screen according to user choice
	 * 
	 * @param choice the int representing user choice
	 * @param game 	 the Game object
	 */
	public void parseChoice(int choice, Game game) {
		City current_city = game.cities.get(game.current_city);
		switch(choice) {
			case 1:
				current_city.move(0, game);
				game.launchOptionsScreen();
				break;
			case 2:
				game.launchShopScreen();
				break;
		}
	}

	
	/**
	 * Checks the choice, and current funds of the team - if both permit then the item
	 * will be bought and transferred to the team's inventory.
	 * 
	 * @param choice 	the number entered as a choice
	 * @param team		the team object
	 * @return true if the item was bought successfully, false otherwise
	 */
	public boolean buyItem(int choice, Team team) {
		if (choice >= 0 && choice < stock.size()) {
			Item item = stock.get(choice);
			double item_cost = item.cost;
			if (team.hasPriceDiscount()) {
				item_cost *= 0.7;
			}
			if (item_cost <= team.money) {
				team.money -= item_cost;
				stock.remove(item);
				team.inventory.add(item);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
}
