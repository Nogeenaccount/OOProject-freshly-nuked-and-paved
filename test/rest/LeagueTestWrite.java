//package rest;
//
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.Test;
//import rest.League;
//
//
//
//public class LeagueTestWrite{
//
//    String inputFilePath;
//    String outputFilePath;
//    League testleague;
//    League afterwrite;
//
//    @Before
//    public void before(){
//    inputFilePath = "src/testInput.xml";
//    testleague = League.readResources(inputFilePath);
//    outputFilePath = "src/testOutput.xml";
//    testleague.writeToXML(outputFilePath);
//    afterwrite = League.readResources(outputFilePath);
//
//    }
//    
//    @Test
//    public void leagueNameTest() {
//   	 assertEquals(afterwrite.getLeagueName(), "Barclays Premier League");
//    }
//    
//    @Test
//    public void leagueTeamstest(){
//   	 assertEquals(afterwrite.getRounds(), 38);
//    }
//    
//    @Test
//    public void numberOfTeamsTest(){
//   	 assertEquals(afterwrite.getTeams().size(), 20);
//    }
//    
//    @Test
//    public void numberOfPlayersTest(){
//   	 assertEquals(afterwrite.getTeams().get(5).getPlayers().size(), 22);
//    }
//    
//    @Test
//    public void deRobinVanPersieTest(){
//   	 assertEquals(afterwrite.getTeams().get(10).getPlayers().get(16).getPlayerName(), "Robin van Persie");
//    }
//    
//    @Test
//    public void getEnduranceTest(){
//   	 assertEquals(afterwrite.getTeams().get(8).getPlayers().get(8).getEndurance(), 91);
//    }
//    
//    @Test
//    public void doekoeTest(){
//   	 assertEquals(afterwrite.getTeams().get(8).getBudget(), 37166000);
//    }
//    
//    @Test
//    public void stadiumTest(){
//   	 assertEquals(afterwrite.getTeams().get(11).getStadiumName(), "St. James' Park");
//    }
//    
//    @Test
//    public void christianEriksen(){
//   	 assertEquals(afterwrite.getTeams().get(17).getPlayers().get(21).getPlayerName(), "Christian Eriksen");
//   	 assertEquals(afterwrite.getTeams().get(17).getPlayers().get(21).getShirtNumber(), 22);
//    }
//    
//    @Test
//    public void priceTest(){
//   	 assertEquals(afterwrite.getTeams().get(14).getPlayers().get(5).getPrice(), 10400000);
//    }
//
//}
//
//
