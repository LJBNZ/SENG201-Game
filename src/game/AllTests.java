package game;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CityTest.class, HeroTest.class, ItemTest.class, ShopTest.class, GameTest.class})
public class AllTests {

}
