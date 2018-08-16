/**
 * The main game class which incorporates other classes to control
 * the game environment.
 * 
 * @author Logan Beard, James Macfarlane
 * @version 1.0, April 2018
 */

package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game {
	public Map<Integer, String> game_names = new HashMap<Integer, String>();
	public Map<Integer, String> types = new HashMap<Integer, String>();
	public Map<Integer, String> type_attributes = new HashMap<Integer, String>();
	public ArrayList<Integer> existing_types = new ArrayList<Integer>();
	public ArrayList<City> cities = new ArrayList<City>();
	public int current_city = 0;
	public int num_heroes;
	public int num_cities;
	public Team team;
	public int score = 0;
	public boolean eligible_for_random_event = false;
	
	/**
	 * the Game constructor which populates the lists used in Game methods
	 */
	public Game() {
		types.put(1, "Merchant");
		types.put(2, "Cartographer");
		types.put(3, "Tank");
		types.put(4, "Medic");
		types.put(5, "Clairvoyant");
		types.put(6, "Psychic");
		
		type_attributes.put(1, "Gets 30% cheaper prices at stores");
		type_attributes.put(2, "Always knows their way around cities");
		type_attributes.put(3, "Takes 25% less damage in battle");
		type_attributes.put(4, "2x team member potion consumption speed");
		type_attributes.put(5, "Knows which game the villain will play");
		type_attributes.put(6, "Better luck in battle");
		
		game_names.put(0, "Rock Paper Scissors");
		game_names.put(1, "Dice Rolls");
		game_names.put(2, "Guess the Number");
	}	
	
	/**
	 * Instantiates a Hero object and adds it to the Team
	 * @param hero_name		The user inputted name of the Hero
	 * @param hero_type		The int corresponding to the Hero type
	 */
	public void createHero(String hero_name, int hero_type) {
		this.existing_types.add(hero_type);
		Hero hero = new Hero(hero_name, hero_type);
		this.team.heroes.add(hero);
	}

	
	
	/**
	 * Generates as many cities as specified in the input
	 * 
	 * @param	num_cities	The number of cities to be generated
	 */
	public void generateCities(int num_cities) {		
		for (int i = 0; i < num_cities; i++) {
			City new_city = new City();
			new_city.initDestinations();
			cities.add(new_city);
		}
	}
	
	
	/**
	 * Generates 6 pre-set villains in random order and randomises the games that some will play in battle
	 * @param num_cities
	 */
	private void generateVillains(int num_cities) {
		//Random list of games to pick from
		ArrayList<Integer> random_games = new ArrayList<Integer>();
		random_games.add(0);
		random_games.add(1);
		random_games.add(2);
		Collections.shuffle(random_games);
		
		//Create 6 villain objects
		Villain joker = new Villain("Joker", "Wanna know how I got these scars?", random_games.get(0), true, 100, true);
		Villain two_face = new Villain("Two Face", "You either die a hero, or you live long enough to see yourself become the villain.", 0, false, 40, false);
		Villain bane = new Villain("Bane", "You merely adopted the dark, I was born in it.", random_games.get(1), false, 60, true);
		Villain penguin = new Villain("Penguin", "Come on, now. I've seen school girls with more grit!", 1, false, 35, false);
		Villain poison_ivy = new Villain("Poison Ivy", "Nature always wins.", random_games.get(2), false, 45, true);
		Villain riddler = new Villain("Riddler", "So you decided to finally show up, did you?", 2, false, 50, false);
		
		//Add them all to an ArrayList (except the Joker who is the supervillain and always appears in the last city
		ArrayList<Villain> villains = new ArrayList<Villain>();
		villains.add(two_face);
		villains.add(bane);
		villains.add(penguin);
		villains.add(poison_ivy);
		villains.add(riddler);
		
		Collections.shuffle(villains);
		
		//Assign the villains to their city, assign the Joker to the last city
		for (int i = 0; i < num_cities; i++) {
			City city = this.cities.get(i);
			if (i + 1 != num_cities) {
				city.villain = villains.get(i);
			} else {
				city.villain = joker;
			}
		}
		
	}
	
	/**
	 * Generates a list of Items and returns one at random to be added to the team's inventory
	 * in the case of a random event
	 * @return 	the Item to be added
	 */
	public Item getRandomItem() {
		ArrayList<Item> items = new ArrayList<Item>();
		Potion small_pot = new Potion(10, 25);
		items.add(small_pot);
		Potion medium_pot = new Potion(20, 50);
		items.add(medium_pot);
		Potion large_pot = new Potion(30, 100);
		items.add(large_pot);
		PowerUp lucky_charm = new PowerUp(40, 1);
		items.add(lucky_charm);
		PowerUp extra_dice = new PowerUp(30, 2);
		items.add(extra_dice);
		PowerUp clairvoyance = new PowerUp(25, 3);
		items.add(clairvoyance);
		PowerUp map = new PowerUp(25, 4);
		items.add(map);
		
		Random random_item = new Random();
		int item_index = random_item.nextInt(items.size());
    	Item item = items.get(item_index);
    	
    	return item;
	}

	/**
	 * Test to see if a random event is triggered, there is a 1/10 chance it will be
	 * @return 	boolean as true if event triggered, false otherwise
	 */
	public boolean randomEventTriggered() {
		Random event_chance = new Random();
	    int probability = event_chance.nextInt(10) + 1;
	    if (probability == 1) {
	    	return true;
	    }
		return false;
	}
	
	/**
	 * Launch the game set up screen
	 */
	public void launchSetupScreen() {
		SetupScreen setupWindow = new SetupScreen(this);
		
	}
	/**
	 * Close the game set up screen
	 * @param setupScreen	the window instance to be closed
	 */
	public void closeSetupScreen(SetupScreen setupScreen) {
		setupScreen.closeWindow();
		this.generateCities(this.num_cities);
		this.generateVillains(this.num_cities);
		//Launch the hero select screen after close
		launchHeroSelectScreen();
		
	}
	/**
	 * Launch the hero select screen
	 */
	public void launchHeroSelectScreen() {
		HeroSelectScreen heroSelectWindow = new HeroSelectScreen(this);
		
	}
	/**
	 * Close the hero select screen
	 * @param heroSelectWindow	the window instance to be closed
	 */
	public void closeHeroSelectScreen(HeroSelectScreen heroSelectWindow) {
		heroSelectWindow.closeWindow();
		if (this.team.heroes.size() < this.num_heroes) {
			//Launch again if more heroes to be selected
			launchHeroSelectScreen();
		} else {
			//If not, launch the options screen
			launchOptionsScreen();
		}	
	}
	
	/**
	 * Launch the options screen
	 */
	public void launchOptionsScreen() {
		OptionsScreen optionsWindow = new OptionsScreen(this);
	}
	/**
	 * Close the options screen
	 * @param optionsWindow	the window instance to be closed
	 */
	public void closeOptionsScreen(OptionsScreen optionsWindow) {
		optionsWindow.closeWindow();		
	}
	
	/**
	 * Launch the travel screen
	 */
	public void launchTravelScreen() {
		TravelScreen travelWindow = new TravelScreen(this);
	}
	/**
	 * Close the travel screen
	 * @param travelWindow	the window instance to be closed
	 */
	public void closeTravelScreen(TravelScreen travelWindow) {
		travelWindow.closeWindow();		
		launchOptionsScreen();
	}
	
	/**
	 * Launch the team status screen
	 */
	public void launchTeamStatusScreen() {
		TeamStatusScreen teamStatusWindow = new TeamStatusScreen(this);
	}
	/**
	 * Close the team status screen
	 * @param teamStatusWindow	the window instance to be closed
	 */
	public void closeTeamStatusScreen(TeamStatusScreen teamStatusWindow) {
		teamStatusWindow.closeWindow();		
		launchOptionsScreen();
	}
	
	/**
	 * Launch the powerup den screen
	 */
	public void launchPowerUpDenScreen() {
		PowerUpDenScreen powerUpDenWindow = new PowerUpDenScreen(this);
	}
	/**
	 * Close the powerup den screen
	 * @param powerUpDenWindow	the window instance to be closed
	 */
	public void closePowerUpDenScreen(PowerUpDenScreen powerUpDenWindow) {
		powerUpDenWindow.closeWindow();		
		launchOptionsScreen();
	}
	
	/**
	 * Launch the shop screen
	 */
	public void launchShopScreen() {
		ShopScreen shopWindow = new ShopScreen(this);
	}
	/**
	 * Close the shop screen
	 * @param shopWindow	the window instance to be closed
	 */
	public void closeShopScreen(ShopScreen shopWindow) {
		shopWindow.closeWindow();		
		launchOptionsScreen();
	}
	
	/**
	 * Launch the use potion screen
	 */
	public void launchUsePotionScreen() {
		UsePotionScreen usePotionWindow = new UsePotionScreen(this);
	}
	/**
	 * Close the use potion screen
	 * @param usePotionWindow	the window instance to be closed
	 */
	public void closeUsePotionScreen(UsePotionScreen usePotionWindow) {
		usePotionWindow.closeWindow();		
		launchOptionsScreen();
	}
	
	/**
	 * Launch the check healing status screen
	 */
	public void launchCheckHealingScreen() {
		CheckHealingScreen checkHealingWindow = new CheckHealingScreen(this);
	}
	/**
	 * Close the check healing status screen 
	 * @param checkHealingWindow	the window instance to be closed
	 */
	public void closeCheckHealingScreen(CheckHealingScreen checkHealingWindow) {
		checkHealingWindow.closeWindow();		
		launchOptionsScreen();
	}
	
	/**
	 * Launch the rock paper scissors game screen
	 * @param hero	the Hero chosen to battle with
	 */
	public void launchRockPaperScissorsScreen(Hero hero) {
		RockPaperScissorsScreen RPSWindow = new RockPaperScissorsScreen(this, hero);
	}
	/**
	 * Close the rock paper scissors game screen
	 * @param RPSWindow		the window instance to be closed
	 * @param game_won		the boolean representing the game outcome; true = win, false = loss
	 */
	public void closeRockPaperScissorsScreen(RockPaperScissorsScreen RPSWindow, boolean game_won) {
		RPSWindow.closeWindow();	
		checkGameConditions(game_won);
	}
	
	/**
	 * Launch the dice rolls game screen
	 * @param hero	the Hero chosen to battle with
	 */
	public void launchDiceRollScreen(Hero hero) {
		DiceRollScreen diceRollWindow = new DiceRollScreen(this, hero);
	}
	/**
	 * Close the dice rolls game screen
	 * @param diceRollWindow	the window instance to be closed
	 * @param game_won			the boolean representing the game outcome; true = win, false = loss
	 */
	public void closeDiceRollScreen(DiceRollScreen diceRollWindow, boolean game_won) {
		diceRollWindow.closeWindow();	
		checkGameConditions(game_won);
		
	}
	
	/**
	 * Launch the guess the number game screen
	 * @param hero the Hero chosen to battle with
	 */
	public void launchGuessTheNumberScreen(Hero hero) {
		GuessTheNumberScreen guessTheNumberWindow = new GuessTheNumberScreen(this, hero);
	}
	/**
	 * Close the guess the number game screen
	 * @param guessTheNumberWindow	the window instance to be closed
	 * @param game_won				the boolean representing the game outcome; true = win, false = loss
	 */
	public void closeGuessTheNumberScreen(GuessTheNumberScreen guessTheNumberWindow, boolean game_won) {
		guessTheNumberWindow.closeWindow();	
		checkGameConditions(game_won);
		
	}
	
	/**
	 * Launch the battle screen
	 */
	public void launchBattleScreen() {
		BattleScreen battleWindow = new BattleScreen(this);
	}
	/**
	 * Close the battle screen
	 * @param battleWindow	the window instance to be closed
	 * @param selection		the int representing the hero chosen to battle with
	 */
	public void closeBattleScreen(BattleScreen battleWindow, int selection) {
		battleWindow.closeWindow();	
		enterGame(selection);
	}
	
	/**
	 * Launch the game over screen
	 */
	public void launchGameOverScreen() {
		GameOverScreen gameOverWindow = new GameOverScreen(this);
	}
	/**
	 * Close the game over screen
	 * @param gameOverWindow	the window instance to be closed
	 */
	public void closeGameOverScreen(GameOverScreen gameOverWindow) {
		gameOverWindow.closeWindow();	
	}
	
	/**
	 * Launch the victory screen
	 */
	public void launchVictoryScreen() {
		VictoryScreen victoryWindow = new VictoryScreen(this);
	}
	/**
	 * Close the victory screen
	 * @param victoryWindow		the window instance to be closed
	 */
	public void closeVictoryScreen(VictoryScreen victoryWindow) {
		victoryWindow.closeWindow();	
		launchOptionsScreen();
	}
	
	/**
	 * Launch the win game screen
	 */
	public void launchWinGameScreen() {
		WinGameScreen winGameWindow = new WinGameScreen(this);
	}
	/**
	 * Close the win game screen
	 * @param winGameWindow		the window instance to be closed
	 */
	public void closeWinGameScreen(WinGameScreen winGameWindow) {
		winGameWindow.closeWindow();	
	}
	
	/**
	 * Launch the random event screen
	 */
	public void launchRandomEventScreen() {
		RandomEventScreen randomEventWindow = new RandomEventScreen(this);
	}
	/**
	 * Close the random event screen
	 * @param randomEventWindow		the window instance to be closed
	 */
	public void closeRandomEventScreen(RandomEventScreen randomEventWindow) {
		randomEventWindow.closeWindow();	
	}
	
	
	/**
	 * Routes to the correct screen based on the outcome of a battle and the game conditions
	 * @param game_won	the boolean reprsenting the outcome of the game; true = win, false = loss
	 */
	public void checkGameConditions(boolean game_won) {
		Villain villain = cities.get(team.current_city_num).villain;
		//if the villain likes to change games, get a new random game to play
		if (villain.game_changes) {
			Random random_game = new Random();
		    int game = random_game.nextInt(3);
		    villain.game = game;
		}
		//if the game was won by the hero
		if (game_won) {
			score++;
			//if score is high enough to advance
			if (score == 3) {
				score = 0;
				current_city++;
				//completed all cities
				if (current_city == cities.size()) {
					launchWinGameScreen();
				//award money, go to the next city
				} else {
					team.money += 50;
					team.current_city_num = current_city;
					launchVictoryScreen();
				}
			//not enough score to advance, relaunch battle screen
			} else {
				launchBattleScreen();
			}
		//else hero lost the game
		} else {
			//check to see if there is hero still alive to battle
			if (team.heroes.size() > 0) {
				//if there is, launch battle screen
				launchBattleScreen();
			} else {
				//game over
				launchGameOverScreen();
			}
		}
	}
	
	/**
	 * Checks the villain's game and launches the appropriate game window
	 * @param hero_selection	the Hero chosen to battle with
	 */
	public void enterGame(int hero_selection) {
		Villain villain = cities.get(team.current_city_num).villain;
		Hero hero = team.heroes.get(hero_selection);
		
		switch(villain.game) {
			case 0: //Rock paper scissors
				launchRockPaperScissorsScreen(hero);
				break;
			case 1: //Dice rolls
				launchDiceRollScreen(hero);
				break;
			case 2: //Guess the number
				launchGuessTheNumberScreen(hero);
				break;
		}	
	}

	/**
	 * The Main method to be run on startup, initiates Game and Team objects
	 * @param args	startup arguments, unused in our case
	 */
	public static void main(String[] args) {
		Game game = new Game();
		
		Team team = new Team();
		game.team = team;
		
		game.launchSetupScreen();
		
	}

}
