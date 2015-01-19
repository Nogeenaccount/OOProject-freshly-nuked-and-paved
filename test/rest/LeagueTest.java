/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author user
 */
public class LeagueTest {
    League testleague;
    
    @Before
    public void before(){
	testleague = League.readResources("resourceV6.xml");
//	assertTrue(League.readResources("resourceV6.xml").equals(testleague));
    }
    
    @Test
    public void readTest() {
	assertEquals(testleague.getLeagueName(), "Barclays Premier League");
	assertEquals(testleague.getRounds(), 38);
	assertEquals(testleague.getTeams().size(), 20);
	assertEquals(testleague.getTeams().get(5).getPlayers().size(), 22);
	assertEquals(testleague.getTeams().get(10).getPlayers().get(16).getPlayerName(), "Robin van Persie");
	assertEquals(testleague.getTeams().get(8).getPlayers().get(8).getEndurance(), 91);
	assertEquals(testleague.getTeams().get(8).getBudget(), 37166000);
	assertEquals(testleague.getTeams().get(11).getStadiumName(), "St. James' Park");
	assertEquals(testleague.getTeams().get(17).getPlayers().get(21).getPlayerName(), "Christian Eriksen");
   	assertEquals(testleague.getTeams().get(17).getPlayers().get(21).getShirtNumber(), 22);
	assertEquals(testleague.getTeams().get(14).getPlayers().get(5).getPrice(),10400000 );
    }
    
    @Test
    public void writeTest() {
	testleague.writeToXML("resourceV6.xml");
	readTest();
    }
    
    @Test
    public void toStringTest() {
	Team teama = testleague.getTeams().get(0);
	Team teamb = testleague.getTeams().get(1);
	testleague.setTeams(new ArrayList<Team>());
	testleague.add(teama);
	testleague.add(teamb);
	
	String str = "<League(" + testleague.getLeagueName() + ", " + testleague.getRounds() + ", " + teama + ", " + teamb + ")>";
	assertEquals(str, testleague.toString());
    }
    
    @Test
    public void addTeamTest() {
	assertEquals(testleague.getTeams().size(), 20);
	testleague.add(testleague.getTeams().get(3));
	assertEquals(testleague.getTeams().size(), 20);
    }
    
    @Test
    public void setterTest() {
	testleague.setGameName("test");
	assertEquals(testleague.getGameName(),"test");
	
	testleague.setChosenTeam("Arsenal");
	assertEquals(testleague.getChosenTeam(),"Arsenal");
	
	testleague.setLeagueName("Eredivisie");
	assertEquals(testleague.getLeagueName(),"Eredivisie");
	
	testleague.setRounds(34);
	assertEquals(testleague.getRounds(),34);
    }
}
