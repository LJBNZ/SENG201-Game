package game;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CityTest {

	//valid move
	@Test
	public void valid_move() {
		Game game = new Game();
		City test = new City();
		boolean output = test.move(1, game);
		assertTrue(output);
	}

	//move to same place
	@Test
	public void same_place() {
		Game game = new Game();
		City test = new City();
		test.current_destination = 1;
		boolean output = test.move(1, game);
		assertFalse(output);
	}
	
	//out of range
	@Test
	public void invalid_input() {
		Game game = new Game();
		City test = new City();
		boolean output = test.move(5, game);
		assertFalse(output);
	}
}

