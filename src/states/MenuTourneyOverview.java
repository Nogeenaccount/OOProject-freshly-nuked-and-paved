package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
//FINISHED
public class MenuTourneyOverview extends State {

    public MenuTourneyOverview() {
    }

    public void createGUI() {
	layout = new GridBagLayout();
	this.setLayout(layout);
	c = new GridBagConstraints();

	//Initialise Images
	String userDir = System.getProperty("user.home");
	String buttonBackImage = "GUIFiles\\buttonBack.png";
	String panelPanelImage = "GUIFiles\\FootbalStadiumSize.png";

	//Padding
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
	c.gridx = 1;
	c.gridy = 4;
	layout.setConstraints(invisi3, c);
	invisi3.setPreferredSize(new Dimension(200, 200));
	invisi3.setOpaque(false);
	invisi3.setEditable(false);
	invisi3.setMargin(new Insets(0, 0, 200, 0));
	this.add(invisi3);

	//Next matches table Initialise
	String[][] data = new String[20][];
	int roundsPlayed = 38 - StateManager.getLeague().getRounds();
	for (int i = 0; i < 20; i++) {
	    data[i] = new String[]{StateManager.getLeague().getTeams().get(i).getTeamName(),
		Integer.toString(roundsPlayed),
		Integer.toString(StateManager.getLeague().getTeams().get(i).getWins()),
		Integer.toString(StateManager.getLeague().getTeams().get(i).getDraws()),
		Integer.toString(StateManager.getLeague().getTeams().get(i).getLosses()),
		Integer.toString(StateManager.getLeague().getTeams().get(i).getGoalsMade()),
		Integer.toString(StateManager.getLeague().getTeams().get(i).getGoalsAgainst()),
		Integer.toString(StateManager.getLeague().getTeams().get(i).getGoalDifference()),
		Integer.toString(StateManager.getLeague().getTeams().get(i).getLeagueScore())
	    };
	}

	String[] columnNames = {"Team Name", "Played", "Wins", "Draws", "Losses",
	    "GS", "GA", "GD", "Points"};

	//Next matches table
	JTable table = new JTable(data, columnNames);
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 2;
	layout.setConstraints(table, c);
	//table.setPreferredSize(new Dimension(400, 160));
	table.setEnabled(false);
	table.setForeground(Color.white);
	table.setBackground(Color.decode("#525151"));
	table.setMinimumSize(new Dimension(700, 320));
	table.setFont(new Font("Arial", Font.PLAIN, 14));
	this.add(table);

	//Column width
	TableColumn column = null;
	for (int i = 0; i < 9; i++) {
	    column = table.getColumnModel().getColumn(i);
	    if (i == 0) {
		column.setPreferredWidth(150); //third column is bigger
	    } else {
		column.setPreferredWidth(60);
	    }
	}

	//Table header
	JTableHeader tableHeader = table.getTableHeader();
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 1;
	layout.setConstraints(tableHeader, c);
	tableHeader.setEnabled(false);
	tableHeader.setForeground(Color.white);
	tableHeader.setBackground(Color.decode("#525151"));
	tableHeader.setMinimumSize(new Dimension(700, 20));
	tableHeader.setFont(new Font("Arial", Font.PLAIN, 18));
	this.add(tableHeader);

	//Go back
	JButton buttonBack = new JButton(new ImageIcon(buttonBackImage));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 3;
	buttonBack.setMargin(new Insets(0, 0, 0, 0));
	buttonBack.setMaximumSize(new Dimension(400, 100));
	createButton(buttonBack, "", c, layout);
	attachStateChanger(buttonBack, new MenuBetweenRounds());

	c.weightx = 0.5;
	c.gridheight = 5;
	c.gridwidth = 3;
	c.gridx = 0;
	c.gridy = 0;
	ImagePanel panel = new ImagePanel(new ImageIcon(panelPanelImage).getImage(), c, layout);
	this.add(panel);
    }

}
