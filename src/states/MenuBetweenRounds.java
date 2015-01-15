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

    public MenuBetweenRounds() {

    }

    public void createGUI() {
	layout = new GridBagLayout();
	this.setLayout(layout);
	c = new GridBagConstraints();

	//Initialise Images
	String userDir = System.getProperty("user.home");
	String buttonNextMatchImage = "GUIFiles\\buttonNextMatch.png";
	String buttonTransfersImage = "GUIFiles\\buttonTransfers.png";
	String buttonTeamManagementImage = "GUIFiles\\buttonTeamManagement.png";
	String buttonTourneyOverviewImage = "GUIFiles\\buttonTourneyOverview.png";
	String buttonSaveImage = "GUIFiles\\buttonSave.png";
	String buttonHomeScreenImage = "GUIFiles\\buttonHomeScreen.png";
	String panelPanelImage = "GUIFiles\\FootbalStadiumSize.png";

	JTextArea invisi2 = new JTextArea();
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 0;
	layout.setConstraints(invisi2, c);
	invisi2.setPreferredSize(new Dimension(200, 200));
	invisi2.setOpaque(false);
	invisi2.setEditable(false);
	invisi2.setMargin(new Insets(200, 0, 0, 0));
	this.add(invisi2);

	JTextArea invisi3 = new JTextArea();
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 1;
	layout.setConstraints(invisi3, c);
	invisi3.setPreferredSize(new Dimension(200, 200));
	invisi3.setOpaque(false);
	invisi3.setEditable(false);
	invisi3.setMargin(new Insets(0, 200, 0, 0));
	this.add(invisi3);

	JTextArea invisi4 = new JTextArea();
	c.weightx = 0.5;
	c.gridx = 3;
	c.gridy = 1;
	layout.setConstraints(invisi4, c);
	invisi4.setPreferredSize(new Dimension(200, 200));
	invisi4.setOpaque(false);
	invisi4.setEditable(false);
	invisi4.setMargin(new Insets(0, 0, 0, 200));
	this.add(invisi4);

	JButton buttonNextMatch = new JButton(new ImageIcon(buttonNextMatchImage));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 2;
	buttonNextMatch.setMargin(new Insets(0, 0, 0, 0));
	buttonNextMatch.setMaximumSize(new Dimension(400, 100));
	createButton(buttonNextMatch, "", c, layout);
	attachStateChanger(buttonNextMatch, new MenuNextMatch());

	JButton buttonTransfers = new JButton(new ImageIcon(buttonTransfersImage));
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 2;
	buttonTransfers.setMargin(new Insets(0, 0, 0, 0));
	buttonTransfers.setMaximumSize(new Dimension(400, 100));
	createButton(buttonTransfers, "", c, layout);
	attachStateChanger(buttonTransfers, new MenuTransfers());

	JButton buttonTeamManagement = new JButton(new ImageIcon(buttonTeamManagementImage));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 3;
	buttonTeamManagement.setMargin(new Insets(0, 0, 0, 0));
	buttonTeamManagement.setMaximumSize(new Dimension(400, 100));
	createButton(buttonTeamManagement, "", c, layout);
	attachStateChanger(buttonTeamManagement, new MenuTeamManagement());

	JButton buttonTourneyOverview = new JButton(new ImageIcon(buttonTourneyOverviewImage));
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 3;
	//Reference to non-existent state
	//StateManager.States.TRANSFERS
	buttonTourneyOverview.setMargin(new Insets(0, 0, 0, 0));
	buttonTourneyOverview.setMaximumSize(new Dimension(400, 100));
	createButton(buttonTourneyOverview, "", c, layout);
	attachStateChanger(buttonTourneyOverview, new MenuTourneyOverview());

	JButton buttonSave = new JButton(new ImageIcon(buttonSaveImage));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 4;
	buttonSave.setMargin(new Insets(0, 0, 0, 0));
	buttonSave.setMaximumSize(new Dimension(400, 100));
	createButton(buttonSave, "", c, layout);
	//attachStateChanger(buttonSave, StateManager.States.BETWEEN_ROUNDS);
	buttonSave.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		StateManager.getLeague().writeToXML("SaveGame.xml");
	    }
	});

	JButton buttonHomeScreen = new JButton(new ImageIcon(buttonHomeScreenImage));
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 4;
	buttonHomeScreen.setMargin(new Insets(0, 0, 0, 0));
	buttonHomeScreen.setMaximumSize(new Dimension(400, 100));
	createButton(buttonHomeScreen, "", c, layout);
	attachStateChanger(buttonHomeScreen, new MenuMain());

	JTextArea textFieldBudget = new JTextArea();
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 1;
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

	System.out.println("Assuming default opstelling");

	c.weightx = 0.5;
	c.gridheight = 6;
	c.gridwidth = 4;
	c.gridx = 0;
	c.gridy = 0;
	ImagePanel panel = new ImagePanel(new ImageIcon(panelPanelImage).getImage(), c, layout);
	this.add(panel);
    }
}
