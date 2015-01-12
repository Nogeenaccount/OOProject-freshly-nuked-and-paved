package rest;

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class PlayerTest {

    Player testPlayer;
    
    @Before
    public void before(){
	testPlayer = new Player("Harry Testema",12,1000000,80,70,60,"verdediger",5,0);
    }
    
    @Test
    public void constructorTest(){
	assertEquals(testPlayer.getPlayerName(), "Harry Testema");
	assertEquals(testPlayer.getShirtNumber(), 12);
	assertEquals(testPlayer.getPrice(), 1000000);
	assertEquals(testPlayer.getEndurance(), 80);
	assertEquals(testPlayer.getOffence(), 70);
	assertEquals(testPlayer.getDefence(), 60);
	assertEquals(testPlayer.getPosition(), "verdediger");
	assertEquals(testPlayer.getCardCount(), 5);
	assertEquals(testPlayer.getInjured(), 0);
    }
    
    public void setterTest(){
	testPlayer.setPlayerName("Barry Testema");
	testPlayer.setShirtNumber(11);
	testPlayer.setPrice(2000000);
	testPlayer.setEndurance(8);
	testPlayer.setOffence(7);
	testPlayer.setDefence(6);
	testPlayer.setPosition("aanvaller");
	testPlayer.setCardCount(4);
	testPlayer.setInjured(3);
	assertEquals(testPlayer.getPlayerName(), "Barry Testema");
	assertEquals(testPlayer.getShirtNumber(), 11);
	assertEquals(testPlayer.getPrice(), 2000000);
	assertEquals(testPlayer.getEndurance(), 8);
	assertEquals(testPlayer.getOffence(), 7);
	assertEquals(testPlayer.getDefence(), 6);
	assertEquals(testPlayer.getPosition(), "aanvaller");
	assertEquals(testPlayer.getCardCount(), 4);
	assertEquals(testPlayer.getInjured(), 3);
    }
}
