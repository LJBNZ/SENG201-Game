package game;

public class Item {
	
	public int cost;
	public String name = "< name >";
	public String description = "< description >";

	/**
	 * the item constructor which initialises the item cost
	 * @param itemCost	the cost of the item in the store
	 */
	public Item(int itemCost) {
		cost = itemCost;
	}

}
