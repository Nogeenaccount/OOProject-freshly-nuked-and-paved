package rest;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String teamName;
    private List<Player> players = new ArrayList<Player>();
    private LineUp lineUp;
    private String stadiumName;
    private int winStreak;
    private int budget;

    private int played;
    private int wins;
    private int draws;
    private int losses;
    private int goalsMade;
    private int goalsAgainst;

    /**
     * Team: constructor
     *
     * @param name
     * @param stadium
     * @param budget
     */
    public Team(String name, String stadium, int budget) {
	teamName = name;
	stadiumName = stadium;
	this.budget = budget;

	players = new ArrayList<Player>();
	lineUp = new LineUp();
	winStreak = 0;
	played = 0;
	wins = 0;
	draws = 0;
	losses = 0;
	goalsMade = 0;
	goalsAgainst = 0;
    }

    @Override
    public boolean equals(Object other){
            if(other instanceof Team){
                Team that = (Team)other;
                if(teamName.equals(that.getTeamName())){
                    for(int n=0;n<players.size();n++){
                        if(!players.get(n).equals(that.getPlayers().get(n))){
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
    
    public Player getPlayerByName(String name) {
        Player player = null;
        for(int i = 0; i < this.getPlayers().size(); i++) {
           if (name.equals(this.getPlayers().get(i).getPlayerName())) {
               player = this.getPlayers().get(i);
           }
       }
       return player;
    }
    
    /**
     * add: adds player to the team
     *
     * @param player
     */
    public void add(Player player) {
	if (!players.contains(player)) {
	    players.add(player);
	}
    }
    /**
     * toString: turns Team into a printable String
     *
     * @return String
     */
    @Override
    public String toString() {
	String str = "<Team(";
	str += teamName + ", ";
	for (int i = 0; i < players.size(); i++) {
	    str += players.get(i).toString();
	    if (i < players.size() - 1) {
		str += ", ";
	    }

	}
	str += stadiumName + ", " + winStreak + ", " + budget + ", " + ", " + played + ", " + wins + ", " + draws
		+ ", " + losses + ", " + goalsMade + ", " + goalsAgainst + ", ";
	str += ")>";
	return str;
    }

    /**
     * method to sell a player X removes the player from the list and shifts the
     * list to the left upgrades the current budget
     *
     * @param x the player to be sold
     */
    public boolean sellPlayer(Player x, int bod) {
	for (int i = 0; i < this.players.size(); i++) {
	    if (this.players.get(i).getPlayerName().equalsIgnoreCase(x.getPlayerName())) {
		//int newBudget = this.budget + this.players.get(i).getPrice(); Don't think
		this.players.remove(i);
		this.setBudget(this.budget + bod);
                System.out.println("Sell player succes");
                return true;
	    }
	}
        System.out.println("Sell player failed - No such player");
        return false;
    }

    /**
     * method to buy a player X constraint: your budget must be high enough to
     * buy the player Shirtnumber from player x is changed if shirtnumber is
     * already taken.
     *
     * @param bod the amount of money you pay
     * @return 
     */
    public boolean buyPlayer(Player x, int bod) {
	//if (bod < this.getBudget()) {
	    if (this.shirtnumberFree(x) != true) {
		x.setShirtNumber(this.availableShirtnumber());
	    }
	    this.add(x);
	    this.setBudget((this.getBudget() - bod));
            System.out.println("Buy player succeeds");

            return true;
	//}
        //System.out.println("Buy player fails");
       // return false;
    }

    /**
     * method to determine the first available shirt number in your team.
     *
     * @return the first available shirtnumber
     */
    public int availableShirtnumber() {
	int counter = 1;
	while (this.shirtnumberTaken(counter) == true) {
	    counter++;

	}
               System.out.println("available shirtnumer " + counter);

	return counter;

    }
    /**
     * Returns a desired number of players that suits the lineup requirements
     * @return list of either attackers/ midfielder/ defenders/ a keeper
     */
      public ArrayList<Player> getPlayersAttackers() {
             ArrayList<Player> aanvallers = new ArrayList<Player>();
             for(int i = 0; i<players.size(); i++){
                 if(players.get(i).getPosition().equals("F")){
                     aanvallers.add(players.get(i));
                 }
             }
             while(aanvallers.size() != 3){
             aanvallers.remove(3);
             }
             return aanvallers;
	}
      
      /**
       * Returns a palyers when a shirt number is given
       * @param shn
       * @return Desired payer
       */
      public Player getByShirtNumber(int shn){
            Player p = null;
            Player q = null;
            for(int i =0; i < this.getPlayers().size(); i++){
            p = this.getPlayers().get(i);
            if(this.getPlayers().get(i).getShirtNumber() == shn){
            q = this.getPlayers().get(i);
                }
              }
            if(q instanceof Player){
            return q;
            }else{
                System.out.println("huh");
                return null;
            }
        }
      
      /**
       * Accepteert een String waarin de lineup een string representatie is van shirtnummers gescheiden door komma's
       * @param lu
       * @return Een volwaardige opstelling
       */  
            public LineUp convertToLineUp(String lineup){
            LineUp lup = new LineUp();
            Player tempKeeper = null;
            ArrayList<Player> tempVerdedigers = new ArrayList<Player>();
            ArrayList<Player> tempMiddenvelders = new ArrayList<Player>();
            ArrayList<Player> tempAanvallers = new ArrayList<Player>();
            String lineupTemp = lineup;
            String playerNumber = "";
                //System.out.println(lineupTemp.indexOf(", "));
            int p = 0;
            //Player tempPlayer = null;
           // lineupTemp.indexOf(", ");
            for(int i = 0; i<11; i++){
                if(i < 10){
                playerNumber = lineupTemp.substring(0, lineupTemp.indexOf(", "));
                lineup = lineup.substring(lineup.indexOf(", ")+2);
                lineupTemp = lineup;
                }else{                
                    playerNumber = lineupTemp;
                }
                p = Integer.parseInt(playerNumber);
            
                if(i == 0){
                tempKeeper = this.getByShirtNumber(p);
                }else if (i < 5){
                    tempVerdedigers.add(this.getByShirtNumber(p));
                }else if (i < 8){
                    tempMiddenvelders.add(this.getByShirtNumber(p));
                }else{
                    tempAanvallers.add(this.getByShirtNumber(p));
                }
            }
            lup.setKeeper(tempKeeper); lup.setVerdedigers(tempVerdedigers); lup.setMiddenvelders(tempMiddenvelders);lup.setAanvallers(tempAanvallers);
            return lup;
            }
            
           /**
     * Returns a desired number of players that suits the lineup requirements
     * @return list of either attackers/ midfielder/ defenders/ a keeper
     */
        public ArrayList<Player> getPlayersDefenders() {
	  ArrayList<Player> verdedigers = new ArrayList<Player>();
             for(int i = 0; i<players.size(); i++){
                 if(players.get(i).getPosition().equals("D")){
                     verdedigers.add(players.get(i));
                 }
             }
             
             while( verdedigers.size() != 4){
              verdedigers.remove(4);
             }
             return verdedigers;
	}
           /**
     * Returns a desired number of players that suits the lineup requirements
     * @return list of either attackers/ midfielder/ defenders/ a keeper
     */
        public ArrayList<Player> getPlayersMidfielders() {
		  ArrayList<Player> middenvelders = new ArrayList<Player>();
             for(int i = 0; i<players.size(); i++){
                 if(players.get(i).getPosition().equals("M")){
                     middenvelders.add(players.get(i));
                 }
             }
                 while( middenvelders.size() != 3){
              middenvelders.remove(3);
             }
             return middenvelders;
	}
           /**
     * Returns a desired number of players that suits the lineup requirements
     * @return list of either attackers/ midfielder/ defenders/ a keeper
     */
        public ArrayList<Player> getPlayersKeepers() {
		  ArrayList<Player> keepers = new ArrayList<Player>();
             for(int i = 0; i<players.size(); i++){
                 if(players.get(i).getPosition().equals("G")){
                     keepers.add(players.get(i));
                 }
             }
             while(keepers.size() != 1){
              keepers.remove(1);
             }
             return keepers;
	}

    /**
     * Find out where the specified shirtnumber is taken in your team
     *
     * @param number the specified shirtnumber
     * @return true if taken
     */
    public boolean shirtnumberTaken(int number) {
        for (Player player : this.players) {
            if (player.getShirtNumber() == number) {
                return true;
            }
        }
	return false;
    }

    /**
     * Find out whether the shirt number from player X is free in your team
     *
     * @param x Player
     * @return True if free
     */
    public boolean shirtnumberFree(Player x) {
	int shirtNumber = x.getShirtNumber();

        for (Player player : this.players) {
            if (player.getShirtNumber() == shirtNumber) {
                return false;
            }
        }
	return true;
    }


    public static ArrayList<Player> eliminateWorstPlayer(ArrayList<Player> List){
        ArrayList<Player> tempList = List;
        int toBeEliminated = 0;
	int lowestStats = 1000000;
	for (int p = 0; p<tempList.size(); p++){           
            if (tempList.get(p).getOffence() + tempList.get(p).getDefence() + tempList.get(p).getEndurance() < lowestStats) {
                toBeEliminated = p;
		lowestStats = tempList.get(p).getOffence() + tempList.get(p).getDefence() + tempList.get(p).getEndurance();
            }
	}
	tempList.remove(toBeEliminated);
        
        return tempList;
    }
    
    /**
    * getDefaultLineUp: returns default optimal lineUp from team this
    * @return LineUp
    */
    public LineUp getDefaultLineUp(){
        LineUp l = new LineUp();
	ArrayList<Player> aanvallers = new ArrayList<>();
	ArrayList<Player> middenvelders = new ArrayList<>();
	ArrayList<Player> verdedigers = new ArrayList<>();
	ArrayList<Player> keepers = new ArrayList<>();
	
	// "geblesseerde spelers niet in lineup" nog niet geimplementeerd
	for (int i =0; i<this.players.size(); i++){
		switch (this.players.get(i).getPosition()){
			case "G" : keepers.add(this.players.get(i)); break;
			case "M" : middenvelders.add(this.players.get(i)); break;
			case "F" : aanvallers.add(this.players.get(i)); break;
			case "D" : verdedigers.add(this.players.get(i)); break; 
		}
	}		

        // eliminatie van mindere keepers
        do {
            keepers = eliminateWorstPlayer(keepers);
        } while(keepers.size()>1);

        // eliminatie van mindere verdedigers
        do {
            verdedigers = eliminateWorstPlayer(verdedigers);
	} while(verdedigers.size()>4);

        // eliminatie van mindere middenvelders
        do {
            middenvelders = eliminateWorstPlayer(middenvelders);
        } while(middenvelders.size()>3);

        // eliminatie van mindere aanvallers
        do {
            aanvallers = eliminateWorstPlayer(aanvallers);
        } while(aanvallers.size()>3);
		
	l.setAanvallers(aanvallers);
	l.setMiddenvelders(middenvelders);
	l.setVerdedigers(verdedigers);
	l.setKeeper(keepers.get(0));
		
	return l;
    }
    // useless comment
    
        /**
         * Method to get a Player by name, String must be in Player format.
         * @param s The string containing the name.
         * @return 
         */
        public Player getPlayerByString(String s) {
            Player p;
        p = null;
        for (Player player : this.players) {
            if (s.equalsIgnoreCase(player.getPlayerName())) {
                p = player;
            }
        }
        return p;
        }
            
        
    public String getTeamName() {
	return teamName;
    }

    public void setTeamName(String teamName) {
	this.teamName = teamName;
    }

    public List<Player> getPlayers() {
	return players;
    }

    public void setPlayers(List<Player> players) {
	this.players = players;
    }

    public String getStadiumName() {
	return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
	this.stadiumName = stadiumName;
    }

    public int getWinStreak() {
	return winStreak;
    }

    public void setWinStreak(int winStreak) {
	this.winStreak = winStreak;
    }

    public int getBudget() {
	return budget;
    }

    public void setBudget(int budget) {
	this.budget = budget;
    }

    public int getLeagueScore() {
	return wins * 3 + draws * 1;
    }

    public int getPlayed() {
	return played;
    }

    public void setPlayed(int played) {
	this.played = played;
    }

    public int getWins() {
	return wins;
    }

    public void setWins(int wins) {
	this.wins = wins;
    }

    public int getDraws() {
	return draws;
    }

    public void setDraws(int draws) {
	this.draws = draws;
    }

    public int getLosses() {
	return losses;
    }

    public void setLosses(int losses) {
	this.losses = losses;
    }

    public int getGoalsMade() {
	return goalsMade;
    }

    public void setGoalsMade(int goalsMade) {
	this.goalsMade = goalsMade;
    }

    public int getGoalsAgainst() {
	return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
	this.goalsAgainst = goalsAgainst;
    }

    public int getGoalDifference() {
	return goalsMade - goalsAgainst;
    }

    public LineUp getLineUp() {
	return lineUp;
    }

    public void setLineUp(LineUp lineUp) {
	this.lineUp = lineUp;
    }
    
    public int getMoney(){
        return budget;
    }
}
