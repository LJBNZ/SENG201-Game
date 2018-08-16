package game;

public class Potion extends Item {
	
	public int heal_percentage;
	public int application_time;

	/**
	 * The Potion constructor which initiates the Potion cost, description and heal_percentage
	 * @param itemCost  the cost of the Potion
	 * @param heal		the amount of health the Potion heals
	 */
	public Potion(int itemCost, int heal) {
		super(itemCost);
		heal_percentage = heal;
		description = "Heals "+ heal_percentage + "% of a hero's health";
		
		//Initialise name and application time based on the amount the potion heals
		if (heal_percentage == 25) {
			name = "Small Potion";
			application_time = 30;
		} else if (heal_percentage == 50) {
			name = "Medium Potion";
			application_time = 60;
		} else {
			name = "Large Potion";
			application_time = 120;
		}
	}


}
