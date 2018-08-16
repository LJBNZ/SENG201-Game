package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	//create hero
	@Test
	public void create_hero() {
		Game test = new Game();
		Team team = new Team();
		test.team = team;
		test.createHero("hero1", 2);
		Hero hero1 = team.heroes.get(0);
		String name = hero1.hero_name;
		assertEquals("hero1", name);
	}
	
	//generate cities
	@Test
	public void generate_cities() {
		Game test = new Game();
		test.generateCities(6);
		int size = test.cities.size();
		assertEquals(6, size);
	}
}
