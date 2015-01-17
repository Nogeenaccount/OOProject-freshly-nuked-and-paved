package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

@SuppressWarnings("serial")
//WORKING WITH FILLER
public class MenuTeamManagement extends State {

    //Initialise Images
    String buttonBackImage = "GUIFiles/buttonBack.png";
    String promptFImage = "GUIFiles/PromptF.png";
    String promptMImage = "GUIFiles/PromptM.png";
    String promptDImage = "GUIFiles/PromptD.png";
    String promptGImage = "GUIFiles/PromptG.png";
    String panelPanelImage = "GUIFiles/FootbalStadiumSize.png";
    
    JLabel labelFront = new JLabel(new ImageIcon(promptFImage));
    JLabel labelMidfield = new JLabel(new ImageIcon(promptMImage));
    JLabel labelDefense = new JLabel(new ImageIcon(promptDImage));
    JLabel labelKeeper = new JLabel(new ImageIcon(promptGImage));
    JComboBox position1;
    JComboBox position2;
    JComboBox position3;
    JComboBox position4;
    JComboBox position5;
    JComboBox position6;
    JComboBox position7;
    JComboBox position8;
    JComboBox position9;
    JComboBox position10;
    JComboBox position11;
    JButton buttonBack = new JButton(new ImageIcon(buttonBackImage));
    
    public MenuTeamManagement() {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void createGUI() {
	layout = new GridBagLayout();
	this.setLayout(layout);
	c = new GridBagConstraints();
	
	createSpace();

	//Initialise JComboBoxes
        rest.Team chosenTeam = StateManager.getLeague().getTeamByName(StateManager.getLeague().getChosenTeam());
	String[] playerNames = null;
	playerNames = new String[chosenTeam.getPlayers().size()];
	for (int j = 0; j < chosenTeam.getPlayers().size(); j++) {
	    playerNames[j] = chosenTeam.getPlayers().get(j).getPlayerName();
	}

	position1 = new JComboBox(playerNames);
	position2 = new JComboBox(playerNames);
	position3 = new JComboBox(playerNames);
	position4 = new JComboBox(playerNames);
	position5 = new JComboBox(playerNames);
	position6 = new JComboBox(playerNames);
	position7 = new JComboBox(playerNames);
	position8 = new JComboBox(playerNames);
	position9 = new JComboBox(playerNames);
	position10 = new JComboBox(playerNames);
	position11 = new JComboBox(playerNames);

	//Initialise JComboBox selected //filler data
        
	position1.setSelectedIndex(0);
	position2.setSelectedIndex(1);
	position3.setSelectedIndex(2);
	position4.setSelectedIndex(3);
	position5.setSelectedIndex(4);
	position6.setSelectedIndex(5);
	position7.setSelectedIndex(6);
	position8.setSelectedIndex(7);
	position9.setSelectedIndex(8);
	position10.setSelectedIndex(9);
	position11.setSelectedIndex(10);
        position1.setSelectedItem((String)chosenTeam.getLineUp().getAanvallers().get(0).getPlayerName());
        position2.setSelectedItem((String)chosenTeam.getLineUp().getAanvallers().get(1).getPlayerName());
        position3.setSelectedItem((String)chosenTeam.getLineUp().getAanvallers().get(2).getPlayerName());
        position4.setSelectedItem((String)chosenTeam.getLineUp().getMiddenvelders().get(0).getPlayerName());
        position5.setSelectedItem((String)chosenTeam.getLineUp().getMiddenvelders().get(1).getPlayerName());
        position6.setSelectedItem((String)chosenTeam.getLineUp().getMiddenvelders().get(2).getPlayerName());
        position7.setSelectedItem((String)chosenTeam.getLineUp().getVerdedigers().get(0).getPlayerName());
        position8.setSelectedItem((String)chosenTeam.getLineUp().getVerdedigers().get(1).getPlayerName());
        position9.setSelectedItem((String)chosenTeam.getLineUp().getVerdedigers().get(2).getPlayerName());
        position10.setSelectedItem((String)chosenTeam.getLineUp().getVerdedigers().get(3).getPlayerName());
        position11.setSelectedItem((String)chosenTeam.getLineUp().getKeeper().getPlayerName());
        
        
	//Position 1 JComboBox
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 5;
	createDropdown(position1,"",c,layout);
	
	//Position 2 JComboBox
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 6;
	createDropdown(position2,"",c,layout);

	//Position 3 JComboBox
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 7;
	createDropdown(position3,"",c,layout);

	//Position 4 JComboBox
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 5;
	createDropdown(position4,"",c,layout);

	//Position 5 JComboBox
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 6;
	createDropdown(position5,"",c,layout);

	//Position 6 JComboBox
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 7;
	createDropdown(position6,"",c,layout);

	//Position 7 JComboBox
	c.weightx = 0.5;
	c.gridx = 3;
	c.gridy = 5;
	createDropdown(position7,"",c,layout);

	//Position 8 JComboBox
	c.weightx = 0.5;
	c.gridx = 3;
	c.gridy = 6;
	createDropdown(position8,"",c,layout);

	//Position 9 JComboBox
	c.weightx = 0.5;
	c.gridx = 3;
	c.gridy = 7;
	createDropdown(position9,"",c,layout);

	//Position 10 JComboBox
	c.weightx = 0.5;
	c.gridx = 3;
	c.gridy = 8;
	createDropdown(position10,"",c,layout);

	//Position 11 JComboBox
	c.weightx = 0.5;
	c.gridx = 4;
	c.gridy = 5;
	createDropdown(position11,"",c,layout);

	//Prompt position 1
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 4;
	createSmallLabel(labelFront,"",c,layout);

	//Prompt position 4
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 4;
	createSmallLabel(labelMidfield,"",c,layout);

	//Prompt position 7
	c.weightx = 0.5;
	c.gridx = 3;
	c.gridy = 4;
	createSmallLabel(labelDefense,"",c,layout);

	//Prompt position 11
	c.weightx = 0.5;
	c.gridx = 4;
	c.gridy = 4;
	createSmallLabel(labelKeeper,"",c,layout);

	//Go back
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 8;
	c.gridheight = 2;
	c.gridwidth = 2;
	createButton(buttonBack, "", c, layout);
	attachStateChanger(buttonBack, new MenuBetweenRounds());

	setBackground(panelPanelImage);
    }
    
    protected void createDropdown(JComboBox combobox, String text,
                               GridBagConstraints constraints, GridBagLayout layout){
	combobox.setBackground(Color.decode("#525151"));
	combobox.setForeground(Color.white);
	combobox.setMinimumSize(new Dimension(200, 24));
	combobox.setFont(new Font("Arial", Font.PLAIN, 14));
        layout.setConstraints(combobox, constraints);
	combobox.setPreferredSize(new Dimension(200, 20));
	combobox.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		enableButtons();
                rest.Team chosenOne = StateManager.getLeague().getTeamByName(StateManager.getLeague().getChosenTeam());
                StateManager.getLeague().setCustomLineUp(chosenOne, playersSelected());
	    }
	});
        this.add(combobox);
    }
    
    public void enableButtons(){
        List<String> playerList = new ArrayList<>();
	playerList.add((String) position1.getSelectedItem());
	playerList.add((String) position2.getSelectedItem());
	playerList.add((String) position3.getSelectedItem());
	playerList.add((String) position4.getSelectedItem());
	playerList.add((String) position5.getSelectedItem());
	playerList.add((String) position6.getSelectedItem());
	playerList.add((String) position7.getSelectedItem());
	playerList.add((String) position8.getSelectedItem());
	playerList.add((String) position9.getSelectedItem());
	playerList.add((String) position10.getSelectedItem());
	playerList.add((String) position11.getSelectedItem());

	//Create a set of the players in order to see whether or not there are duplicates
	Set<String> playerSet = new HashSet<>(playerList);
	
	if (playerList.size() == playerSet.size()) {
	    buttonBack.setEnabled(true);
	} else {
	    buttonBack.setEnabled(false);
	}
    }
    
    public String[] playersSelected() {
        String selected[] = new String[] {
            (String) position1.getSelectedItem(),
            (String) position2.getSelectedItem(),
            (String) position3.getSelectedItem(),
            (String) position4.getSelectedItem(),
            (String) position5.getSelectedItem(),
            (String) position6.getSelectedItem(),
            (String) position7.getSelectedItem(),
            (String) position8.getSelectedItem(),
            (String) position9.getSelectedItem(),
            (String) position10.getSelectedItem(),
            (String) position11.getSelectedItem(),
        };
        return selected;
    }
}
