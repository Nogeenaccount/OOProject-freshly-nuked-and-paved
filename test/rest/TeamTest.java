package rest;

import java.util.ArrayList;
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
	a.buyPlayer(p1,500); // budget is 10000 - 500

	assertTrue(a.getBudget() == 9500);
	assertTrue(a.getPlayers().get(0).equals(p1));

	//second test, is the shirt number changed?
	a.add(p1);
	a.add(p2);
	a.buyPlayer(p6,500);
	a.buyPlayer(p7,500);
	assertTrue(p6.getShirtNumber() == 3);
	assertTrue(p7.getShirtNumber() == 4);
	
	assertEquals(a.getPlayers().size(), 4);
	a.buyPlayer(p0,500);
	assertEquals(a.getPlayers().size(), 5);
    }

        /**
     * test eliminatWorstPlayer
     */
    @Test
    public void eliminatWorstPlayerTest(){
        ArrayList players = new ArrayList<Player>();
        
        Player p1 = new Player("p1", 1, 500, 20, 99, 99, "gk", 0, 0);
        Player p2 = new Player("p2", 2, 500, 10, 99, 99, "gk", 0, 0);
        Player p3 = new Player("p3", 3, 500, 50, 99, 99, "gk", 0, 0);
        Player p4 = new Player("p4", 4, 500, 40, 99, 99, "gk", 0, 0);
        Player p5 = new Player("p5", 5, 500, 30, 99, 99, "gk", 0, 0);
        
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        players.add(p5);
        
        assertTrue(players.contains(p1) &&
                    players.contains(p2) &&
                     players.contains(p3) &&
                      players.contains(p4) &&
                       players.contains(p5));
        
        players = Team.eliminateWorstPlayer(players);
        
        assertTrue(players.contains(p1) &&
                   !players.contains(p2) &&
                     players.contains(p3) &&
                      players.contains(p4) &&
                       players.contains(p5));

        players = Team.eliminateWorstPlayer(players);
        
        assertTrue(!players.contains(p1) &&
                    !players.contains(p2) &&
                      players.contains(p3) &&
                       players.contains(p4) &&
                        players.contains(p5));

        players = Team.eliminateWorstPlayer(players);
        
        assertTrue(!players.contains(p1) &&
                    !players.contains(p2) &&
                      players.contains(p3) &&
                       players.contains(p4) &&
                       !players.contains(p5));

        players = Team.eliminateWorstPlayer(players);
        
        assertTrue(!players.contains(p1) &&
                    !players.contains(p2) &&
                      players.contains(p3) &&
                       players.contains(p4) &&
                       !players.contains(p5));
        
        players = Team.eliminateWorstPlayer(players);
        
        assertTrue(!players.contains(p1) &&
                    !players.contains(p2) &&
                      players.contains(p3) &&
                       !players.contains(p4) &&
                        !players.contains(p5));

        players = Team.eliminateWorstPlayer(players);
        
        assertTrue(!players.contains(p1) &&
                    !players.contains(p2) &&
                     !players.contains(p3) &&
                      !players.contains(p4) &&
                       !players.contains(p5));
    }
    
    /**
     * test getDefaultLineUp
     */
    @Test
    public void getDefaultLineUpTest(){
        Team tempTeam = new Team("Chelsea", "Arena", 10000);

        Player p1  = new Player("p1",  1, 500, 10, 99, 99, "G", 0, 0);
        Player p2  = new Player("p2",  2, 500, 50, 99, 99, "G", 0, 0);
        Player p3  = new Player("p3",  3, 500, 60, 99, 99, "F", 0, 0);
        Player p4  = new Player("p4",  4, 500, 99, 55, 99, "M", 0, 0);
        Player p5  = new Player("p5",  5, 500, 99, 50, 99, "M", 0, 0);
        Player p6  = new Player("p6",  1, 500, 80, 99, 99, "M", 0, 0);
        Player p7  = new Player("p7",  2, 500, 99, 99, 80, "M", 0, 0);
        Player p8  = new Player("p8",  3, 500, 99, 30, 99, "M", 0, 0);
        Player p9  = new Player("p9",  4, 500, 30, 99, 99, "M", 0, 0);
        Player p10 = new Player("p10", 5, 500, 20, 20, 99, "D", 0, 0);
        Player p11 = new Player("p11", 1, 500, 70, 99, 99, "F", 0, 0);
        Player p12 = new Player("p12", 2, 500, 40, 99, 99, "D", 0, 0);
        Player p13 = new Player("p13", 3, 500, 99, 85, 99, "D", 0, 0);
        Player p14 = new Player("p14", 4, 500, 99, 80, 80, "D", 0, 0);
        Player p15 = new Player("p15", 5, 500, 90, 99, 99, "M", 0, 0);
        Player p16 = new Player("p16", 1, 500, 99, 40, 99, "M", 0, 0);
        Player p17 = new Player("p17", 2, 500, 30, 99, 99, "F", 0, 0);
        Player p18 = new Player("p18", 3, 500, 99, 90, 90, "F", 0, 0);
        Player p19 = new Player("p19", 4, 500, 80, 99, 99, "G", 0, 0);
        Player p20 = new Player("p20", 5, 500, 99, 99, 10, "F", 0, 0);
        Player p21 = new Player("p21", 4, 500, 99, 99, 90, "F", 0, 0);
        Player p22 = new Player("p22", 5, 500, 99, 90, 99, "D", 0, 0);

        tempTeam.add(p1);
        tempTeam.add(p2);
        tempTeam.add(p3);
        tempTeam.add(p4);
        tempTeam.add(p5);
        tempTeam.add(p6);
        tempTeam.add(p7);
        tempTeam.add(p8);
        tempTeam.add(p9);
        tempTeam.add(p10);
        tempTeam.add(p11);
        tempTeam.add(p12);
        tempTeam.add(p13);
        tempTeam.add(p14);
        tempTeam.add(p15);
        tempTeam.add(p16);
        tempTeam.add(p17);
        tempTeam.add(p18);
        tempTeam.add(p19);
        tempTeam.add(p20);
        tempTeam.add(p21);
        tempTeam.add(p22);
        
        LineUp tempLineUp = new LineUp();
        tempLineUp.setKeeper(p19);
        tempLineUp.addVerdediger(p22);
        tempLineUp.addVerdediger(p13);
        tempLineUp.addVerdediger(p14);
        tempLineUp.addMiddenvelder(p15);
        tempLineUp.addMiddenvelder(p7);
        tempLineUp.addMiddenvelder(p6);
        tempLineUp.addMiddenvelder(p4);
        tempLineUp.addAanvaller(p22);
        tempLineUp.addAanvaller(p13);
        tempLineUp.addAanvaller(p19);
        
        assertEquals(tempLineUp, tempTeam.getDefaultLineUp());
    }

    
    /**
     * test 1, is the budget upgraded?
     */
    @Test
    public void sellPlayerTest() {
	//test 1, is the budget upgraded?
	a.add(p1);
	a.sellPlayer(p1,1);
	assertTrue(a.getBudget() == 10500);

	//test 2, is the player removed and the list shifted to the left?
	a.add(p1);
	a.add(p2);
	a.sellPlayer(p1,1);
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
