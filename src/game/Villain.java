package game;

public class Villain {
	
	public String name;
	public String taunt;
	public int game;
	public boolean game_changes;
	public int damage;
	public boolean super_villain;
	
	/**
	 * the constructor for the Villain class, initialises Villain attributes
	 * @param new_name			the String representing the villain name
	 * @param new_taunt			the String representing the villain's taunt
	 * @param new_game			the Game object
	 * @param is_super_villain	the boolean representing if the villain is the supervillain of the game or not
	 * @param dmg				the int representing the damage dealt by the villain if the hero loses the game
	 * @param game_change		the boolean representing id the villain will change games between battles or not
	 */
	Villain(String new_name, String new_taunt, int new_game, boolean is_super_villain, int dmg, boolean game_change) {
		name = new_name;
		taunt = new_taunt;
		game = new_game;
		super_villain = is_super_villain;
		damage = dmg;
		game_changes = game_change;
	}

}
