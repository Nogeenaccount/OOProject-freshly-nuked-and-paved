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
    public void sellPlayer(Player x) {
	for (int i = 0; i < this.players.size(); i++) {
	    if (this.players.get(i).getPlayerName()
		    .equalsIgnoreCase(x.getPlayerName())) {
		int newBudget = this.budget + this.players.get(i).getPrice();
		this.players.remove(i);

		this.setBudget(newBudget);
	    }

	}
    }

    /**
     * method to buy a player X constraint: your budget must be high enough to
     * buy the player Shirtnumber from player x is changed if shirtnumber is
     * already taken.
     *
     * @param x
     */
    public void buyPlayer(Player x) {
	if (this.getBudget() >= x.getPrice()) {
	    if (this.shirtnumberFree(x) != true) {
		x.setShirtNumber(this.availableShirtnumber());
	    }
	    this.add(x);
	    this.setBudget((this.getBudget() - x.getPrice()));

	}
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
	for (int i = 0; i < this.players.size(); i++) {
	    if (this.players.get(i).getShirtNumber() == number) {
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

	for (int i = 0; i < this.players.size(); i++) {
	    if (this.players.get(i).getShirtNumber() == shirtNumber) {
		return false;
	    }

	}
	return true;
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
				case "GK" : keepers.add(this.players.get(i)); break;
				case "MF" : middenvelders.add(this.players.get(i)); break;
				case "AT" : aanvallers.add(this.players.get(i)); break;
				case "DF" : verdedigers.add(this.players.get(i)); break; 
			}
		}
		
		
		// eliminatie van mindere keepers
		int toBeEliminated = 0;
		int lowestStats = 1000000;
		do {
			for (int p = 0; p<keepers.size(); p++){
			
				if (keepers.get(p).getOffence() + keepers.get(p).getDefence() + keepers.get(p).getEndurance() < lowestStats) {
					toBeEliminated = p;
					lowestStats = keepers.get(p).getOffence() + keepers.get(p).getDefence() + keepers.get(p).getEndurance();
				}
			}
			keepers.remove(toBeEliminated);
		} while(keepers.size()>1);
		
		// eliminatie van mindere verdedigers
		toBeEliminated = 0;
		lowestStats = 100000;
		do {
			for (int p = 0; p<verdedigers.size(); p++){
			
				if (verdedigers.get(p).getOffence() + verdedigers.get(p).getDefence() + verdedigers.get(p).getEndurance() < lowestStats) {
					toBeEliminated = p;
					lowestStats = verdedigers.get(p).getOffence() + verdedigers.get(p).getDefence() + verdedigers.get(p).getEndurance();
				}
			}
			verdedigers.remove(toBeEliminated);
		} while(verdedigers.size()>4);
		
		// eliminatie van mindere middenvelders
		toBeEliminated = 0;
		lowestStats = 100000;
		do {
			for (int p = 0; p<middenvelders.size(); p++){
			
				if (middenvelders.get(p).getOffence() + middenvelders.get(p).getDefence() + middenvelders.get(p).getEndurance() < lowestStats) {
					toBeEliminated = p;
					lowestStats = middenvelders.get(p).getOffence() + middenvelders.get(p).getDefence() + middenvelders.get(p).getEndurance();
				}
			}
			middenvelders.remove(toBeEliminated);
		} while(middenvelders.size()>3);

		// eliminatie van mindere aanvallers
		toBeEliminated = 0;
		lowestStats = 100000;
		do {
			for (int p = 0; p<aanvallers.size(); p++){
			
				if (aanvallers.get(p).getOffence() + aanvallers.get(p).getDefence() + aanvallers.get(p).getEndurance() < lowestStats) {
					toBeEliminated = p;
					lowestStats = aanvallers.get(p).getOffence() + aanvallers.get(p).getDefence() + aanvallers.get(p).getEndurance();
				}
			}
			aanvallers.remove(toBeEliminated);
		} while(aanvallers.size()>3);
		
		
		l.setAanvallers(aanvallers);
		l.setMiddenvelders(middenvelders);
		l.setVerdedigers(verdedigers);
		l.setKeeper(keepers.get(0));
		
		return l;
	}

	// transfers player x from team y to team this
    // getters/setters
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
}
