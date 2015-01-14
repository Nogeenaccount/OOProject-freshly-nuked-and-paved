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
import rest.League;
import rest.Round;

//WORKING WITH FILLER
@SuppressWarnings("serial")
public class MenuNextMatch extends State {

    public MenuNextMatch() {
    }

    public void createGUI() {
	layout = new GridBagLayout();
	this.setLayout(layout);
	c = new GridBagConstraints();

	//Initialise
	String[] array1 = new String[20];
	rest.League league1 = rest.League.readResources("SaveGame.xml");
	for (int i = 0; i < league1.getTeams().size(); i++) {
	    array1[i] = league1.getTeams().get(i).getTeamName();
	}
        
    
	//Initialise Images
	String userDir = System.getProperty("user.home");
	String buttonAdvanceImage = "GUIFiles\\buttonNMAdvance.png";
	String buttonBackImage = "GUIFiles\\buttonNMBack.png";
	String panelPanelImage = "GUIFiles\\FootbalStadiumSize.png";

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
	String[] columnNames = {"First team", "vs", "Second team"};

	//Next matches table
	JTable table = new JTable(data, columnNames);
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 1;
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

	//Advance
	JButton buttonAdvance = new JButton(new ImageIcon(buttonAdvanceImage));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 2;
	createButton(buttonAdvance, "", c, layout);
	buttonAdvance.setPreferredSize(new Dimension(400, 75));
	buttonAdvance.setMinimumSize(new Dimension(400, 75));
	buttonAdvance.setMargin(new Insets(0, 0, 0, 0));
	attachStateChanger(buttonAdvance, new MenuMatchScreen());

	//Go back
	JButton buttonBack = new JButton(new ImageIcon(buttonBackImage));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 3;
	createButton(buttonBack, "", c, layout);
	buttonBack.setPreferredSize(new Dimension(400, 75));
	buttonBack.setMinimumSize(new Dimension(400, 75));
	buttonBack.setMargin(new Insets(0, 0, 0, 0));
	attachStateChanger(buttonBack, new MenuBetweenRounds());

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
	c.gridheight = 6;
	c.gridwidth = 3;
	c.gridx = 0;
	c.gridy = 0;
	ImagePanel panel = new ImagePanel(new ImageIcon(panelPanelImage).getImage(), c, layout);
	this.add(panel);
    }
    
        public static String[] teamsToShow(){
            String[] teamList = new String[20];
            String ht = "";
            String at = "";
            League l = states.StateManager.getLeague();
            Round r = l.nextRound("Speelschema.xml", (38-l.getRounds()));
            for(int i = 0; i < 10; i++){
            ht = r.getMatch(i).getHomeTeam().getTeamName();
            at = r.getMatch(i).getAwayTeam().getTeamName();
            teamList[2*i] = ht;
            teamList[2*i+1] = at;
            }
            
            return teamList;
        }
}
