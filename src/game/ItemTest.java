package game;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	//set item cost
	@Test
	public void item_cost() {
		Item test = new Item(20);
		int output = test.cost;
		assertEquals(20, output);
	}
}
