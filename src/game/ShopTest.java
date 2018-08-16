package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShopTest {

	//enough money
	@Test
	public void valid_money() {
		Shop test = new Shop();
		Team team = new Team();
		team.money = 20;
		boolean output = test.buyItem(1, team);
		assertTrue(output);
	}
	
	//not enough money
	@Test
	public void invalid_money() {
		Shop test = new Shop();
		Team team = new Team();
		team.money = 0;
		boolean output = test.buyItem(1, team);
		assertFalse(output);
	}
}
