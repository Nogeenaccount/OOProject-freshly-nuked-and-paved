package states;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import rest.Match;
import rest.MatchLogic;

//FINISHED
@SuppressWarnings("serial")
public class MenuBetweenRounds extends State {

    //Initialise Images
    String buttonNextMatchImage = "GUIFiles/buttonNextMatch.png";
    String buttonTransfersImage = "GUIFiles/buttonTransfers.png";
    String buttonTeamManagementImage = "GUIFiles/buttonTeamManagement.png";
    String buttonTourneyOverviewImage = "GUIFiles/buttonTourneyOverview.png";
    String buttonSaveImage = "GUIFiles/buttonSave.png";
    String buttonHomeScreenImage = "GUIFiles/buttonHomeScreen.png";
    String buttonPlayerCatalogImage = "GUIFiles/buttonPlayerCatalog.png";
    String panelPanelImage = "GUIFiles/FootbalStadiumSize.png";
    
    JTextArea textFieldBudget = new JTextArea();
    JButton buttonNextMatch = new JButton(new ImageIcon(buttonNextMatchImage));
    JButton buttonTransfers = new JButton(new ImageIcon(buttonTransfersImage));
    JButton buttonTeamManagement = new JButton(new ImageIcon(buttonTeamManagementImage));
    JButton buttonTourneyOverview = new JButton(new ImageIcon(buttonTourneyOverviewImage));
    JButton buttonSave = new JButton(new ImageIcon(buttonSaveImage));
    JButton buttonHomeScreen = new JButton(new ImageIcon(buttonHomeScreenImage));
    JButton buttonPlayerCatalog = new JButton(new ImageIcon(buttonPlayerCatalogImage));
    
    public MenuBetweenRounds() {

    }

    public void createGUI() {
	layout = new GridBagLayout();
	this.setLayout(layout);
	c = new GridBagConstraints();

	createSpace();
	
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 4;
	createButton(buttonNextMatch, "", c, layout);
	attachStateChanger(buttonNextMatch, new MenuNextMatch());

	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 4;
	createButton(buttonTransfers, "", c, layout);
	attachStateChanger(buttonTransfers, new MenuTransfers());

	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 5;
	createButton(buttonTeamManagement, "", c, layout);
	attachStateChanger(buttonTeamManagement, new MenuTeamManagement());

	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 5;
	//Reference to non-existent state
	//StateManager.States.TRANSFERS
	buttonTourneyOverview.setMargin(new Insets(0, 0, 0, 0));
	buttonTourneyOverview.setMaximumSize(new Dimension(400, 100));
	createButton(buttonTourneyOverview, "", c, layout);
	attachStateChanger(buttonTourneyOverview, new MenuTourneyOverview());

	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 6;
	createButton(buttonSave, "", c, layout);
	//attachStateChanger(buttonSave, StateManager.States.BETWEEN_ROUNDS);
	buttonSave.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		StateManager.getLeague().writeToXML("SaveGame.xml");
	    }
	});

	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 6;
	createButton(buttonHomeScreen, "", c, layout);
	attachStateChanger(buttonHomeScreen, new MenuMain());
        
        c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 7;
        c.gridwidth = 2;
        createButton(buttonPlayerCatalog, "", c, layout);
        attachStateChanger(buttonPlayerCatalog, new MenuPlayerCatalog());

	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 3;
	c.gridwidth = 2;
	textFieldBudget.setOpaque(true);
	textFieldBudget.setBackground(Color.decode("#525151"));
	textFieldBudget.setForeground(Color.white);
	textFieldBudget.setFont(new Font("Arial", Font.PLAIN, 14));

	int yourBudget = 0;
	String yourTeam = StateManager.getLeague().getChosenTeam();
	for (int i = 0; i < StateManager.getLeague().getTeams().size(); i++) {
	    if (StateManager.getLeague().getTeams().get(i).getTeamName().equals(yourTeam)) {
		yourBudget = StateManager.getLeague().getTeams().get(i).getBudget();
	    }
	}

        
        Match nextMatch = MatchLogic.findOwnMatch(StateManager.getLeague().getRounds()+1);
        String nextOpp = nextMatch.getHomeTeam().getTeamName() + " (Home)";
        if(!nextMatch.getHomeTeam().equals(StateManager.getLeague().getChosenTeam())){
            nextOpp = nextMatch.getAwayTeam().getTeamName() + "  (Away)";
        }
        String lastRes = states.StateManager.getLeague().getLastResult();
        if(lastRes == null){
            lastRes = "No matches played yet!";
        }
	//Get SomeResult and 
	String display = "Name: " + StateManager.getLeague().getGameName() + "\n" + "Your team: " + StateManager.getLeague().getChosenTeam() + "\n" + "Budget: " + yourBudget + "\n" + "Last Result: " + lastRes + "\n" + "Next Opponent: " + nextOpp;

	textFieldBudget.setText(display);
	textFieldBudget.setEditable(false);
	textFieldBudget.setMinimumSize(new Dimension(800, 105));
	this.add(textFieldBudget);
	layout.setConstraints(textFieldBudget, c);

	setBackground(panelPanelImage);
    }
}
