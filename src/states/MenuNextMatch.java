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
import rest.League;
import rest.Round;

//WORKING WITH FILLER
@SuppressWarnings("serial")
public class MenuNextMatch extends State {

    //Initialise Images
    String buttonAdvanceImage = "GUIFiles/buttonNMAdvance.png";
    String buttonBackImage = "GUIFiles/buttonNMBack.png";
    String panelPanelImage = "GUIFiles/FootbalStadiumSize.png";
    
    JTable table;
    JButton buttonAdvance = new JButton(new ImageIcon(buttonAdvanceImage));
    JButton buttonBack = new JButton(new ImageIcon(buttonBackImage));
    
    public MenuNextMatch() {
    }

    public void createGUI() {
	layout = new GridBagLayout();
	this.setLayout(layout);
	c = new GridBagConstraints();

	createSpace();
	
	//Initialise
	String[] array1 = new String[20];
	rest.League league1 = rest.League.readResources("SaveGame.xml");
	for (int i = 0; i < league1.getTeams().size(); i++) {
	    array1[i] = league1.getTeams().get(i).getTeamName();
	}
        
	//Next matches table Initialise
	String[] array2 = new String[20];
	for (int i = 0; i < array1.length; i++) {
	    if (array1[i].equals(StateManager.getLeague().getChosenTeam()) == false) {
		array2[i] = array1[i];
	    } else {
		array2[i] = array1[i].toUpperCase();
	    }
	}
	System.out.println("Next round matches determined by: array2 //filler");
	String[] array3 = teamsToShow();
        String[][] data = new String[10][];
	for (int i = 0; i < 20; i = i + 2) {
	    data[i / 2] = new String[]{array3[i], "vs", array3[i + 1]};
	}
	String[] columnNames = {"Home team", "", "Away team"};

       
        
	//Next matches table
	table = new JTable(data, columnNames);
	c.weightx = 0.5;
<<<<<<< HEAD
	c.gridx = 1;
	c.gridy = 2;
=======
	c.gridx = 2;
	c.gridy = 3;
>>>>>>> 868086d8d1002a371587bd1647d794d475002129
	layout.setConstraints(table, c);
	table.setPreferredSize(new Dimension(400, 160));
	table.getColumnModel().getColumn(0).setPreferredWidth(170);
	table.getColumnModel().getColumn(1).setPreferredWidth(60);
	table.getColumnModel().getColumn(2).setPreferredWidth(170);
	table.setBackground(Color.decode("#525151"));
	table.setForeground(Color.white);
	table.setMinimumSize(new Dimension(400, 160));
	table.setFont(new Font("Arial", Font.PLAIN, 14));
	table.setEnabled(false);
	this.add(table);
        
        //Table header
        JTableHeader tableHeader = table.getTableHeader();
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 1;
	layout.setConstraints(tableHeader, c);
	tableHeader.setEnabled(false);
	tableHeader.setForeground(Color.white);
	tableHeader.setBackground(Color.decode("#525151"));
	tableHeader.setMinimumSize(new Dimension(400, 20));
	tableHeader.setFont(new Font("Arial", Font.PLAIN, 18));
	this.add(tableHeader);
        
	//Advance
	c.weightx = 0.5;
<<<<<<< HEAD
	c.gridx = 1;
	c.gridy = 3;
=======
	c.gridx = 2;
	c.gridy = 4;
>>>>>>> 868086d8d1002a371587bd1647d794d475002129
	createButton(buttonAdvance, "", c, layout);
	attachStateChanger(buttonAdvance, new MenuMatchScreen());

	//Go back
	c.weightx = 0.5;
<<<<<<< HEAD
	c.gridx = 1;
	c.gridy = 4;
=======
	c.gridx = 2;
	c.gridy = 5;
>>>>>>> 868086d8d1002a371587bd1647d794d475002129
	createButton(buttonBack, "", c, layout);
	attachStateChanger(buttonBack, new MenuBetweenRounds());

<<<<<<< HEAD
	//Padding
	JTextArea invisi2 = new JTextArea();
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 0;
	layout.setConstraints(invisi2, c);
	invisi2.setPreferredSize(new Dimension(200, 100));
	invisi2.setOpaque(false);
	invisi2.setEditable(false);
	invisi2.setMargin(new Insets(250, 0, 0, 0));
	this.add(invisi2);

	c.weightx = 0.5;
	c.gridheight = 7;
	c.gridwidth = 3;
	c.gridx = 0;
	c.gridy = 0;
	ImagePanel panel = new ImagePanel(new ImageIcon(panelPanelImage).getImage(), c, layout);
	this.add(panel);
=======
	setBackground(panelPanelImage);
>>>>>>> 868086d8d1002a371587bd1647d794d475002129
    }
    
        public static String[] teamsToShow(){
            String[] teamList = new String[20];
            String ht = "";
            String at = "";
            League l = states.StateManager.getLeague();
            Round r = l.nextRound("Speelschema.xml", (l.getRounds()));
            for(int i = 0; i < 10; i++){
		ht = r.getMatch(i).getHomeTeam().getTeamName();
		at = r.getMatch(i).getAwayTeam().getTeamName();
		teamList[2*i] = ht;
		teamList[2*i+1] = at;
            }
            
            return teamList;
        }
}
