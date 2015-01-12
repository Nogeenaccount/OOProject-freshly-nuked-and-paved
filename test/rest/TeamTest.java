package rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import rest.League;
import rest.Player;
import rest.Team;

public class TeamTest {

    League Barclays = new League("Barclays", 36, "", "");
    Team a = new Team("Chelsea", "Arena", 10000);
    Team b = new Team("Arsenal", "Arena", 10000);
	// public Player(String name, int number, int price, int end, int off, int
    // def, String pos, int cc, int injured){

    Player p1 = new Player("p1", 1, 500, 99, 99, 99, "gk", 0, 0);
    Player p2 = new Player("p2", 2, 500, 99, 99, 99, "gk", 0, 0);
    Player p3 = new Player("p3", 3, 500, 99, 99, 99, "gk", 0, 0);
    Player p4 = new Player("p4", 4, 500, 99, 99, 99, "gk", 0, 0);
    Player p5 = new Player("p5", 5, 500, 99, 99, 99, "gk", 0, 0);
    Player p6 = new Player("p6", 1, 500, 99, 99, 99, "gk", 0, 0);
    Player p7 = new Player("p7", 2, 500, 99, 99, 99, "gk", 0, 0);
    Player p8 = new Player("p8", 3, 500, 99, 99, 99, "gk", 0, 0);
    Player p9 = new Player("p9", 4, 500, 99, 99, 99, "gk", 0, 0);
    Player p0 = new Player("p0", 5, 50000, 99, 99, 99, "gk", 0, 0);

    /**
     * First test, tests if numbers are 1 . 2 . 3 . ?
     */
    @Test
    public void shirtnumberTest() {
	//First test, tests if numbers are 1 . 2 . 3 . ?
	a.add(p1);
	a.add(p2);
	a.add(p3);
	a.add(p4);
	assertFalse(a.availableShirtnumber() == 4);
	assertTrue(a.availableShirtnumber() == 5);
	
	//second test, test if there is a gap : 1. 3 .4
	a.add(p1);
	a.add(p2);
	a.add(p3);
	a.add(p4);
	a.getPlayers().remove(2);
	assertFalse(a.availableShirtnumber() == 5);
	assertTrue(a.availableShirtnumber() == 3);
	
	//Test if shirtnumbers free is working
	a.add(p1);
	a.add(p2);
	a.add(p3);
	a.add(p4);

	assertTrue(a.shirtnumberFree(p1) == false);
	assertTrue(a.shirtnumberFree(p5) == true);
	
	//Test if shirtnumber is taken
	a.add(p1);
	assertTrue(a.shirtnumberTaken(1) == true);
	assertTrue(a.shirtnumberTaken(15) == false);
    }

    /**
     * first test, is the budget upgraded and the player added?
     */
    @Test
    public void buyPlayerTest() {
	//first test, is the budget upgraded and the player added?
	a.buyPlayer(p1); // budget is 10000 - 500

	assertTrue(a.getBudget() == 9500);
	assertTrue(a.getPlayers().get(0).equals(p1));

	//second test, is the shirt number changed?
	a.add(p1);
	a.add(p2);
	a.buyPlayer(p6);
	a.buyPlayer(p7);
	assertTrue(p6.getShirtNumber() == 3);
	assertTrue(p7.getShirtNumber() == 4);
	
	assertEquals(a.getPlayers().size(), 4);
	a.buyPlayer(p0);
	assertEquals(a.getPlayers().size(), 4);
    }

    /**
     * test 1, is the budget upgraded?
     */
    @Test
    public void sellPlayerTest() {
	//test 1, is the budget upgraded?
	a.add(p1);
	a.sellPlayer(p1);
	assertTrue(a.getBudget() == 10500);

	//test 2, is the player removed and the list shifted to the left?
	a.add(p1);
	a.add(p2);
	a.sellPlayer(p1);
	assertTrue(a.getPlayers().get(0).equals(p2));
	
    }
    
    @Test
    public void setterTest() {
	a.setTeamName("Ajax");
	assertEquals(a.getTeamName(),"Ajax");
	
	a.setStadiumName("ArenA");
	assertEquals(a.getStadiumName(),"ArenA");
	
	a.setWinStreak(1);
	assertEquals(a.getWinStreak(),1);
	
	a.setPlayed(2);
	assertEquals(a.getPlayed(),2);
	
	a.setWins(3);
	assertEquals(a.getWins(),3);
	
	a.setDraws(4);
	assertEquals(a.getDraws(),4);
	
	a.setLosses(5);
	assertEquals(a.getLosses(),5);
	
	a.setGoalsMade(6);
	assertEquals(a.getGoalsMade(),6);
	
	a.setGoalsAgainst(7);
	assertEquals(a.getGoalsAgainst(),7);
	assertEquals(a.getGoalDifference(),-1);
    }

}
