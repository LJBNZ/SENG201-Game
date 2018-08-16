package game;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HeroTest {
	
	//consume potion
	@Test 
	public void consume_potion() {
		Team team = new Team();
		Hero test = new Hero("James", 1);
		test.current_health = 75;
		Potion potion = new Potion(10, 25);
		boolean output = test.consumePotion(potion, team);
		test.consumePotion(potion, team);
		assertTrue(output);
	}
	
	//hero alive
	@Test 
	public void hero_alive() {
		Hero test = new Hero("Hero1", 1);
		boolean output = test.checkIfDead();
		assertFalse(output);
	}
	
	//hero dead
	@Test 
	public void hero_dead() {
		Hero test = new Hero("Hero1", 1);
		test.current_health = 0;
		boolean output = test.checkIfDead();
		assertTrue(output);
	}
	
	//has map powerup
	@Test 
	public void map_powerup() {
		Hero test = new Hero("Map", 2);
		boolean output = test.hasPowerUp("Map");
		assertTrue(output);
	}
	
	//has psychic powers
	@Test
	public void phychic_powerup() {	
		Hero test = new Hero("Psychic", 6);
		boolean output2 = test.hasPowerUp("Psychic Powers");
		assertTrue(output2);
	}
}
