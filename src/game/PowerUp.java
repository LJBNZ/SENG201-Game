package game;

public class PowerUp extends Item {

	public int type;
	
	/**
	 * The PowerUp constructor which initialises the PowerUp cost, name, description and type
	 * @param itemCost		the cost of the powerUp
	 * @param power_up_type	the PowerUp type
	 */
	public PowerUp(int itemCost, int power_up_type) {
		super(itemCost);
		type = power_up_type;
		
		switch(power_up_type) {
			case 1:
				name = "Lucky Charm";
				description = "Grants hero better luck in battle";
				break;
			case 2:
				name = "Extra Dice";
				description = "Grants hero an extra dice in the dice roll battle";
				break;
			case 3:
				name = "Clairvoyance";
				description = "Allows hero to see which game the villain will play";
				break;
			case 4:
				name = "Map";
				description = "Reveals all locations of a city";
				break;
			case 5:
				name = "Psychic Powers";
				description = "Grants hero better luck in battle";
				break;
		}
	}

}
