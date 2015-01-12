package rest;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class League {

    private String leagueName;
    private int rounds;
    private ArrayList<Team> teams;
    //NEW Attributes
    private String gameName;
    private String chosenTeam;

    public League(String name, int rounds, String gameName, String chosenTeam) {
	leagueName = name;
	this.rounds = rounds;
	teams = new ArrayList<Team>();
	//NEW Constructing
	this.gameName = gameName;
	this.chosenTeam = chosenTeam;
    }

    public void add(Team team) {
	if (!teams.contains(team)) {
	    teams.add(team);
	}
    }

    /**
     * readResources: reads data out of resource XML file
     *
     * @param filename name of to-be-read file
     * @return League league
     */
    public static League readResources(String fileName) {
	try {
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    Document doc = db.parse(new File(fileName));
	    doc.getDocumentElement().normalize();
	    NodeList leagueList = doc.getElementsByTagName("league");
	    Node lNode = leagueList.item(0);
	    Element lElement = (Element) lNode;

	    //NEW Get attributes
	    String leagueName = lElement.getAttribute("name");
	    int rounds = Integer.parseInt(lElement.getElementsByTagName("matchesLeft").item(0).getTextContent());
	    String gameName = lElement.getElementsByTagName("gameName").item(0).getTextContent();
	    String chosenTeam = lElement.getElementsByTagName("chosenTeam").item(0).getTextContent();
	    League league = new League(leagueName, rounds, gameName, chosenTeam);

	    NodeList teamList = doc.getElementsByTagName("team");
	    NodeList playerlistxml = doc.getElementsByTagName("player");
	    // predefine variables
	    Node tNode;
	    Element tElement;
	    String teamName;
	    String stadiumName;
	    int budget;
	    Team team;
	    Node pNode;
	    Element pElement;
	    String playerName;
	    int number;
	    int price;
	    int end;
	    int off;
	    int def;
	    String pos;
	    Player player;
	    int cc;
	    int inj;
	    int c = 0;
	    for (int i = 0; i < teamList.getLength(); i++) {
		tNode = teamList.item(i);
		tElement = (Element) tNode;
		teamName = tElement.getAttribute("name");
		stadiumName = tElement.getElementsByTagName("stadiumName").item(0).getTextContent();
		budget = Integer.parseInt(tElement.getElementsByTagName("budget").item(0).getTextContent());
		team = new Team(teamName, stadiumName, budget);
		int j = tElement.getElementsByTagName("player").getLength();
		for (int p = c; p < (c + j); p++) {
		    pNode = playerlistxml.item(p);
		    pElement = (Element) pNode;
		    playerName = pElement.getAttribute("name");
		    number = Integer.parseInt(pElement.getElementsByTagName("shirtNumber").item(0).getTextContent());
		    price = Integer.parseInt(pElement.getElementsByTagName("price").item(0).getTextContent());
		    end = Integer.parseInt(pElement.getElementsByTagName("endurance").item(0).getTextContent());
		    off = Integer.parseInt(pElement.getElementsByTagName("offence").item(0).getTextContent());
		    def = Integer.parseInt(pElement.getElementsByTagName("defence").item(0).getTextContent());
		    pos = pElement.getElementsByTagName("pos").item(0).getTextContent();
		    cc = Integer.parseInt(pElement.getElementsByTagName("cardCount").item(0).getTextContent());
		    inj = Integer.parseInt(pElement.getElementsByTagName("injured").item(0).getTextContent());
		    player = new Player(playerName, number, price, end, off, def, pos, cc, inj);
		    team.add(player);
		}

		c += j;

		league.add(team);

	    }
	    System.out.println("Read file: " + fileName);
	    return league;
	} catch (Exception e) {
	    e.printStackTrace();

	}
	return new League("", 0, "", "");
    }

    /**
     * toString: turns League into a printable String
     *
     * @return String
     */
    public String toString() {
	//NOT Changed for newest attributes
	String str = "<League(";
	str += leagueName + ", " + rounds + ", ";
	for (int i = 0; i < teams.size(); i++) {
	    str += teams.get(i).toString();
	    if (i < teams.size() - 1) {
		str += ", ";
	    }
	}
	str += ")>";
	return str;

    }

    // getters/setters
    public String getLeagueName() {
	return leagueName;
    }

    public void setLeagueName(String leaguename) {
	this.leagueName = leaguename;
    }

    public int getRounds() {
	return rounds;
    }

    public void setRounds(int rounds) {
	this.rounds = rounds;
    }

    public ArrayList<Team> getTeams() {
	return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
	this.teams = teams;
    }

    //NEW Getters & setters
    public String getGameName() {
	return gameName;
    }

    public void setGameName(String gameName) {
	this.gameName = gameName;
    }

    public String getChosenTeam() {
	return chosenTeam;
    }

    public void setChosenTeam(String chosenTeam) {
	this.chosenTeam = chosenTeam;
    }

    public void writeToXML(String filePath) {

	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder;
	try {
	    docBuilder = docFactory.newDocumentBuilder();

	    //root element Barclays premier league
	    Document doc = docBuilder.newDocument();
	    Element rootElement = doc.createElement("league");
	    doc.appendChild(rootElement);
	    Attr attribute = doc.createAttribute("name");
	    attribute.setValue(this.getLeagueName());
	    rootElement.setAttributeNode(attribute);

	    //Element matchesLeft instead of rounds
	    Element rounds = doc.createElement("matchesLeft");
	    rounds.appendChild(doc.createTextNode(Integer.toString(this.getRounds())));
	    rootElement.appendChild(rounds);

	    //NEW Element gameName & chosenTeam
	    Element gameName = doc.createElement("gameName");
	    gameName.appendChild(doc.createTextNode(this.getGameName()));
	    rootElement.appendChild(gameName);

	    Element chosenTeam = doc.createElement("chosenTeam");
	    chosenTeam.appendChild(doc.createTextNode(this.getChosenTeam()));
	    rootElement.appendChild(chosenTeam);

	    //Element Teams
	    for (int i = 0; i < this.teams.size(); i++) {
		Element team = doc.createElement("team");
		Attr name = doc.createAttribute("name");
		name.setValue(teams.get(i).getTeamName());
		team.setAttributeNode(name);
		rootElement.appendChild(team);

		Element Stadium = doc.createElement("stadiumName");
		Stadium.appendChild(doc.createTextNode(teams.get(i).getStadiumName()));
		team.appendChild(Stadium);

		Element win = doc.createElement("winStreak");
		win.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getWinStreak())));
		team.appendChild(win);

		Element budget = doc.createElement("budget");
		budget.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getBudget())));
		team.appendChild(budget);

		Element leagueScore = doc.createElement("leagueScore");
		leagueScore.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getLeagueScore())));
		team.appendChild(leagueScore);

		Element played = doc.createElement("played");
		played.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getPlayed())));
		team.appendChild(played);

		Element wins = doc.createElement("wins");
		wins.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getWins())));
		team.appendChild(wins);

		Element draws = doc.createElement("draws");
		draws.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getDraws())));
		team.appendChild(draws);

		Element losses = doc.createElement("losses");
		losses.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getLosses())));
		team.appendChild(losses);

		Element goalsMade = doc.createElement("goalsMade");
		goalsMade.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getGoalsMade())));
		team.appendChild(goalsMade);

		Element goalsAgainst = doc.createElement("goalsAgainst");
		goalsAgainst.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getGoalsAgainst())));
		team.appendChild(goalsAgainst);

		Element goalDifference = doc.createElement("goalsDifference");
		goalDifference.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getGoalDifference())));
		team.appendChild(goalDifference);

		//Element players
		for (int j = 0; j < teams.get(i).getPlayers().size(); j++) {
		    Element player = doc.createElement("player");
		    Attr namee = doc.createAttribute("name");
		    namee.setValue(teams.get(i).getPlayers().get(j).getPlayerName());
		    player.setAttributeNode(namee);
		    team.appendChild(player);

		    // player attributes
		    Element position = doc.createElement("pos");
		    position.appendChild(doc.createTextNode(teams.get(i).getPlayers().get(j).getPosition()));
		    player.appendChild(position);

		    Element offence = doc.createElement("offence");
		    offence.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getPlayers().get(j).getOffence())));
		    player.appendChild(offence);

		    Element defence = doc.createElement("defence");
		    defence.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getPlayers().get(j).getDefence())));
		    player.appendChild(defence);

		    Element endurance = doc.createElement("endurance");
		    endurance.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getPlayers().get(j).getEndurance())));
		    player.appendChild(endurance);

		    Element cardCount = doc.createElement("cardCount");
		    cardCount.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getPlayers().get(j).getCardCount())));
		    player.appendChild(cardCount);

		    Element injured = doc.createElement("injured");
		    injured.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getPlayers().get(j).getInjured())));
		    player.appendChild(injured);

		    Element shirtNumber = doc.createElement("shirtNumber");
		    shirtNumber.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getPlayers().get(j).getShirtNumber())));
		    player.appendChild(shirtNumber);

		    Element price = doc.createElement("price");
		    price.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getPlayers().get(j).getPrice())));
		    player.appendChild(price);
		}
	    }
	    // write the content into xml file
	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    DOMSource source = new DOMSource(doc);
	    StreamResult result = new StreamResult(new File(filePath));

	    // Output to console for testing
	    // StreamResult result = new StreamResult(System.out);
	    transformer.transform(source, result);

	    System.out.println("Save file: " + filePath);

	} catch (ParserConfigurationException | TransformerException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
