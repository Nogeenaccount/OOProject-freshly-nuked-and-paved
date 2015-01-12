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
