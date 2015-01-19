package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
//FINISHED
public class MenuTourneyOverview extends State {

    //Initialise Images
    String userDir = System.getProperty("user.home");
    String buttonBackImage = "GUIFiles/buttonBack.png";
    String panelPanelImage = "GUIFiles/FootbalStadiumSize.png";
    
    JButton buttonBack = new JButton(new ImageIcon(buttonBackImage));
    JTable table;
    
    public MenuTourneyOverview() {
    }

    public void createGUI() {
	layout = new GridBagLayout();
	this.setLayout(layout);
	c = new GridBagConstraints();

	createSpace();
        
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
            System.out.println(Integer.toString(StateManager.getLeague().getTeams().get(i).getLeagueScore()));
	}
        
        //Initialize sort hasn't been tested yet
        Arrays.sort(data, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                final int points1 = Integer.parseInt(entry1[8]);
                final int points2 = Integer.parseInt(entry2[8]);
                Integer.compare(points1, points2);
                return Integer.compare(points1, points2) * -1;

            }
        });

	String[] columnNames = {"Team Name", "Played", "Wins", "Draws", "Losses",
	    "GS", "GA", "GD", "Points"};

	//Next matches table
	table = new JTable(data, columnNames);
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 4;
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
	c.gridx = 2;
	c.gridy = 3;
	layout.setConstraints(tableHeader, c);
	tableHeader.setEnabled(false);
	tableHeader.setForeground(Color.white);
	tableHeader.setBackground(Color.decode("#525151"));
	tableHeader.setMinimumSize(new Dimension(700, 20));
	tableHeader.setFont(new Font("Arial", Font.PLAIN, 18));
	this.add(tableHeader);

	//Go back
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 5;
	createButton(buttonBack, "", c, layout);
	attachStateChanger(buttonBack, new MenuBetweenRounds());

	setBackground(panelPanelImage);
    }

}
