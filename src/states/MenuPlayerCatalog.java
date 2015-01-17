package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

//FINISHED
@SuppressWarnings("serial")
public class MenuPlayerCatalog extends State {

    //Initialise Images
    String buttonBackImage = "GUIFiles/buttonBack.png";
    String panelPanelImage = "GUIFiles/FootbalStadiumSize.png";
    
    JButton buttonBack = new JButton(new ImageIcon(buttonBackImage));
    
    public MenuPlayerCatalog() {

    }

    public void createGUI() {
	layout = new GridBagLayout();
	this.setLayout(layout);
	c = new GridBagConstraints();

	createSpace();
        
        //Initialize table data

        //Number of players
        int nbr = 0;
        for(int a = 0; a < StateManager.getLeague().getTeams().size(); a++) {
            for(int b = 0; b < StateManager.getLeague().getTeams().get(a).getPlayers().size(); b++) {
                nbr = nbr + 1;
            }
        }
        
        //For each team and players
	String[][] data = new String[nbr][];
	int nthRow = 0;
	for (int i = 0; i < StateManager.getLeague().getTeams().size(); i++) {
	    for(int c = 0; c < StateManager.getLeague().getTeams().get(i).getPlayers().size(); c++) {
                data[nthRow] = new String [] {
                    StateManager.getLeague().getTeams().get(i).getTeamName(),
                    StateManager.getLeague().getTeams().get(i).getPlayers().get(c).getPlayerName(),
                    Integer.toString(StateManager.getLeague().getTeams().get(i).getPlayers().get(c).getShirtNumber()),
                    StateManager.getLeague().getTeams().get(i).getPlayers().get(c).getPosition(),
                    Integer.toString(StateManager.getLeague().getTeams().get(i).getPlayers().get(c).getOffence()),
                    Integer.toString(StateManager.getLeague().getTeams().get(i).getPlayers().get(c).getDefence()),
                    Integer.toString(StateManager.getLeague().getTeams().get(i).getPlayers().get(c).getEndurance()),
                    Integer.toString(StateManager.getLeague().getTeams().get(i).getPlayers().get(c).getPrice())
                };
                nthRow = nthRow + 1;
            }
	}
        String[] columnNames = {"Team", "Name","Number", "Position", "Offense", "Defense", "Endurance", "Price"};

	//Next matches table
	JTable table = new JTable(data, columnNames);
	table.setEnabled(false);
	table.setForeground(Color.white);
	table.setBackground(Color.decode("#525151"));
	table.setMinimumSize(new Dimension(800, 400));
	table.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane teamScroller = new JScrollPane(table);
	teamScroller.setPreferredSize(new Dimension(800, 400));
	teamScroller.setMinimumSize(new Dimension(800, 400));
        c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 3;
	layout.setConstraints(teamScroller, c);
	this.add(teamScroller);
        
	//Column width
	TableColumn column = null;
	for (int i = 0; i < 8; i++) {
	    column = table.getColumnModel().getColumn(i);
	    if (i == 0) {
		column.setPreferredWidth(130);
            }
            else if (i == 1) {
                column.setPreferredWidth(150);
            }
            else if (i == 7) {
                column.setPreferredWidth(80);
            }
            else {
		column.setPreferredWidth(60);
	    }
	}

	//Table header
	JTableHeader tableHeader = table.getTableHeader();
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 2;
	layout.setConstraints(tableHeader, c);
	tableHeader.setEnabled(false);
	tableHeader.setForeground(Color.white);
	tableHeader.setBackground(Color.decode("#525151"));
	tableHeader.setMinimumSize(new Dimension(700, 20));
	tableHeader.setFont(new Font("Arial", Font.PLAIN, 18));
	this.add(tableHeader);
        
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 4;
	createButton(buttonBack, "", c, layout);
        buttonBack.setMinimumSize(new Dimension(400, 100));
	attachStateChanger(buttonBack, new MenuBetweenRounds());
        
	setBackground(panelPanelImage);
    }
}
