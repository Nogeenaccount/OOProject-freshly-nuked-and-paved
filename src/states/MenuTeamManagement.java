package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
//WORKING WITH FILLER
public class MenuTeamManagement extends State {

    public MenuTeamManagement() {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void createGUI() {
	layout = new GridBagLayout();
	this.setLayout(layout);
	c = new GridBagConstraints();

	//Initialise Images
	String userDir = System.getProperty("user.home");
	String buttonBackImage = userDir + "\\Desktop\\GUIFiles\\buttonBack4.png";
	String promptFImage = userDir + "\\Desktop\\GUIFiles\\PromptF.png";
	String promptMImage = userDir + "\\Desktop\\GUIFiles\\PromptM.png";
	String promptDImage = userDir + "\\Desktop\\GUIFiles\\PromptD.png";
	String promptGImage = userDir + "\\Desktop\\GUIFiles\\PromptG.png";
	String panelPanelImage = userDir + "\\Desktop\\GUIFiles\\FootbalStadiumSize.png";

	//Initialise JComboBoxes
	String[] playerNames = null;
	for (int i = 0; i < StateManager.getLeague().getTeams().size(); i++) {
	    if (StateManager.getLeague().getChosenTeam().equals(StateManager.getLeague().getTeams().get(i).getTeamName())) {
		playerNames = new String[StateManager.getLeague().getTeams().get(i).getPlayers().size()];
		for (int j = 0; j < StateManager.getLeague().getTeams().get(i).getPlayers().size(); j++) {
		    playerNames[j] = StateManager.getLeague().getTeams().get(i).getPlayers().get(j).getPlayerName();
		}
	    }
	}
	JComboBox position1 = new JComboBox(playerNames);
	JComboBox position2 = new JComboBox(playerNames);
	JComboBox position3 = new JComboBox(playerNames);
	JComboBox position4 = new JComboBox(playerNames);
	JComboBox position5 = new JComboBox(playerNames);
	JComboBox position6 = new JComboBox(playerNames);
	JComboBox position7 = new JComboBox(playerNames);
	JComboBox position8 = new JComboBox(playerNames);
	JComboBox position9 = new JComboBox(playerNames);
	JComboBox position10 = new JComboBox(playerNames);
	JComboBox position11 = new JComboBox(playerNames);
	JButton buttonBack = new JButton(new ImageIcon(buttonBackImage));

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

	//Initialise JComboBox style
	position1.setBackground(Color.decode("#525151"));
	position1.setForeground(Color.white);
	position1.setMinimumSize(new Dimension(200, 24));
	position1.setFont(new Font("Arial", Font.PLAIN, 14));

	position2.setBackground(Color.decode("#525151"));
	position2.setForeground(Color.white);
	position2.setMinimumSize(new Dimension(200, 24));
	position2.setFont(new Font("Arial", Font.PLAIN, 14));

	position3.setBackground(Color.decode("#525151"));
	position3.setForeground(Color.white);
	position3.setMinimumSize(new Dimension(200, 24));
	position3.setFont(new Font("Arial", Font.PLAIN, 14));

	position4.setBackground(Color.decode("#525151"));
	position4.setForeground(Color.white);
	position4.setMinimumSize(new Dimension(200, 24));
	position4.setFont(new Font("Arial", Font.PLAIN, 14));

	position5.setBackground(Color.decode("#525151"));
	position5.setForeground(Color.white);
	position5.setMinimumSize(new Dimension(200, 24));
	position5.setFont(new Font("Arial", Font.PLAIN, 14));

	position6.setBackground(Color.decode("#525151"));
	position6.setForeground(Color.white);
	position6.setMinimumSize(new Dimension(200, 24));
	position6.setFont(new Font("Arial", Font.PLAIN, 14));

	position7.setBackground(Color.decode("#525151"));
	position7.setForeground(Color.white);
	position7.setMinimumSize(new Dimension(200, 24));
	position7.setFont(new Font("Arial", Font.PLAIN, 14));

	position8.setBackground(Color.decode("#525151"));
	position8.setForeground(Color.white);
	position8.setMinimumSize(new Dimension(200, 24));
	position8.setFont(new Font("Arial", Font.PLAIN, 14));

	position9.setBackground(Color.decode("#525151"));
	position9.setForeground(Color.white);
	position9.setMinimumSize(new Dimension(200, 24));
	position9.setFont(new Font("Arial", Font.PLAIN, 14));

	position10.setBackground(Color.decode("#525151"));
	position10.setForeground(Color.white);
	position10.setMinimumSize(new Dimension(200, 24));
	position10.setFont(new Font("Arial", Font.PLAIN, 14));

	position11.setBackground(Color.decode("#525151"));
	position11.setForeground(Color.white);
	position11.setMinimumSize(new Dimension(200, 24));
	position11.setFont(new Font("Arial", Font.PLAIN, 14));

	//Position 1 JComboBox
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 3;
	layout.setConstraints(position1, c);
	position1.setPreferredSize(new Dimension(200, 20));
	this.add(position1);
	position1.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		MenuTeamManagement.backButton(position1, position2, position3, position4, position5, position6, position7, position8, position9, position10, position11, buttonBack);
	    }
	});
	//Position 2 JComboBox
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 4;
	layout.setConstraints(position2, c);
	position2.setPreferredSize(new Dimension(200, 20));
	this.add(position2);
	position2.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		MenuTeamManagement.backButton(position1, position2, position3, position4, position5, position6, position7, position8, position9, position10, position11, buttonBack);
	    }
	});

	//Position 3 JComboBox
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 5;
	layout.setConstraints(position3, c);
	this.add(position3);
	position3.setPreferredSize(new Dimension(200, 20));
	position3.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		MenuTeamManagement.backButton(position1, position2, position3, position4, position5, position6, position7, position8, position9, position10, position11, buttonBack);
	    }
	});

	//Position 4 JComboBox
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 3;
	layout.setConstraints(position4, c);
	this.add(position4);
	position4.setPreferredSize(new Dimension(200, 20));
	position4.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		MenuTeamManagement.backButton(position1, position2, position3, position4, position5, position6, position7, position8, position9, position10, position11, buttonBack);
	    }
	});

	//Position 5 JComboBox
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 4;
	layout.setConstraints(position5, c);
	this.add(position5);
	position5.setPreferredSize(new Dimension(200, 20));
	position5.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		MenuTeamManagement.backButton(position1, position2, position3, position4, position5, position6, position7, position8, position9, position10, position11, buttonBack);
	    }
	});

	//Position 6 JComboBox
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 5;
	layout.setConstraints(position6, c);
	this.add(position6);
	position6.setPreferredSize(new Dimension(200, 20));
	position6.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		MenuTeamManagement.backButton(position1, position2, position3, position4, position5, position6, position7, position8, position9, position10, position11, buttonBack);
	    }
	});

	//Position 7 JComboBox
	c.weightx = 0.5;
	c.gridx = 3;
	c.gridy = 3;
	layout.setConstraints(position7, c);
	this.add(position7);
	position7.setPreferredSize(new Dimension(200, 20));
	position7.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		MenuTeamManagement.backButton(position1, position2, position3, position4, position5, position6, position7, position8, position9, position10, position11, buttonBack);
	    }
	});

	//Position 8 JComboBox
	c.weightx = 0.5;
	c.gridx = 3;
	c.gridy = 4;
	layout.setConstraints(position8, c);
	this.add(position8);
	position8.setPreferredSize(new Dimension(200, 20));
	position8.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		MenuTeamManagement.backButton(position1, position2, position3, position4, position5, position6, position7, position8, position9, position10, position11, buttonBack);
	    }
	});

	//Position 9 JComboBox
	c.weightx = 0.5;
	c.gridx = 3;
	c.gridy = 5;
	layout.setConstraints(position9, c);
	this.add(position9);
	position9.setPreferredSize(new Dimension(200, 20));
	position9.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		MenuTeamManagement.backButton(position1, position2, position3, position4, position5, position6, position7, position8, position9, position10, position11, buttonBack);
	    }
	});

	//Position 10 JComboBox
	c.weightx = 0.5;
	c.gridx = 3;
	c.gridy = 6;
	layout.setConstraints(position10, c);
	this.add(position10);
	position10.setPreferredSize(new Dimension(200, 20));
	position10.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		MenuTeamManagement.backButton(position1, position2, position3, position4, position5, position6, position7, position8, position9, position10, position11, buttonBack);
	    }
	});

	//Position 11 JComboBox
	c.weightx = 0.5;
	c.gridx = 4;
	c.gridy = 3;
	layout.setConstraints(position11, c);
	this.add(position11);
	position11.setPreferredSize(new Dimension(200, 20));
	position11.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		MenuTeamManagement.backButton(position1, position2, position3, position4, position5, position6, position7, position8, position9, position10, position11, buttonBack);
	    }
	});

	//Prompt position 1
	JLabel prompt1 = new JLabel(new ImageIcon(promptFImage));
	prompt1.setOpaque(true);
	prompt1.setPreferredSize(new Dimension(200, 35));
	prompt1.setMinimumSize(new Dimension(200, 35));
//	prompt1.setEditable(false);
	prompt1.setText("");
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 2;
	layout.setConstraints(prompt1, c);
	this.add(prompt1);

	//Prompt position 4
	JLabel prompt4 = new JLabel(new ImageIcon(promptMImage));
	prompt4.setOpaque(true);
	prompt4.setPreferredSize(new Dimension(200, 35));
	prompt4.setMinimumSize(new Dimension(200, 35));
//	prompt4.setEditable(false);
	prompt4.setText("");
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 2;
	layout.setConstraints(prompt4, c);
	this.add(prompt4);

	//Prompt position 7
	JLabel prompt7 = new JLabel(new ImageIcon(promptDImage));
	prompt7.setOpaque(true);
	prompt7.setPreferredSize(new Dimension(200, 35));
	prompt7.setMinimumSize(new Dimension(200, 35));
//	prompt7.setEditable(false);
	prompt7.setText("");
	c.weightx = 0.5;
	c.gridx = 3;
	c.gridy = 2;
	layout.setConstraints(prompt7, c);
	this.add(prompt7);

	//Prompt position 11
	JLabel prompt11 = new JLabel(new ImageIcon(promptGImage));
	prompt11.setOpaque(true);
	prompt11.setPreferredSize(new Dimension(200, 35));
	prompt11.setMinimumSize(new Dimension(200, 35));
//	prompt11.setEditable(false);
	prompt11.setText("");
	c.weightx = 0.5;
	c.gridx = 4;
	c.gridy = 2;
	layout.setConstraints(prompt11, c);
	this.add(prompt11);

	//Go back
	c.weightx = 0.5;
	c.gridx = 4;
	c.gridy = 4;
	c.gridheight = 2;
	createButton(buttonBack, "", c, layout);
	buttonBack.setPreferredSize(new Dimension(200, 48));
	buttonBack.setMinimumSize(new Dimension(200, 48));
//	buttonBack.setEnabled(false);
	MenuTeamManagement.backButton(position1, position2, position3, position4, position5, position6, position7, position8, position9, position10, position11, buttonBack);
	attachStateChanger(buttonBack, new MenuBetweenRounds());

	//Padding
	JTextArea invisi2 = new JTextArea();
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 0;
	layout.setConstraints(invisi2, c);
	invisi2.setPreferredSize(new Dimension(200, 400));
	invisi2.setOpaque(false);
	invisi2.setEditable(false);
	invisi2.setMargin(new Insets(400, 0, 0, 0));
	this.add(invisi2);

	c.weightx = 0.5;
	c.gridheight = 8;
	c.gridwidth = 6;
	c.gridx = 0;
	c.gridy = 0;
	ImagePanel panel = new ImagePanel(new ImageIcon(panelPanelImage).getImage(), c, layout);
	this.add(panel);

//	for (j=0;j<zipcodeList.length;j++)
	//	  for (k=j+1;k<zipcodeList.length;k++)
//		    if (k!=j && zipcodeList[k] == zipcodeList[j])
//		      duplicates=true;
    }

    @SuppressWarnings("rawtypes")
    public static void backButton(JComboBox position1, JComboBox position2, JComboBox position3, JComboBox position4, JComboBox position5, JComboBox position6, JComboBox position7, JComboBox position8, JComboBox position9, JComboBox position10, JComboBox position11, JButton buttonBack) {
	String[] selectedPlayers = new String[11];
	selectedPlayers[0] = (String) position1.getSelectedItem();
	selectedPlayers[1] = (String) position2.getSelectedItem();
	selectedPlayers[2] = (String) position3.getSelectedItem();
	selectedPlayers[3] = (String) position4.getSelectedItem();
	selectedPlayers[4] = (String) position5.getSelectedItem();
	selectedPlayers[5] = (String) position6.getSelectedItem();
	selectedPlayers[6] = (String) position7.getSelectedItem();
	selectedPlayers[7] = (String) position8.getSelectedItem();
	selectedPlayers[8] = (String) position9.getSelectedItem();
	selectedPlayers[9] = (String) position10.getSelectedItem();
	selectedPlayers[10] = (String) position11.getSelectedItem();

	//Back Button Logic
	boolean duplicates = false;
	for (int i = 0; i < selectedPlayers.length; i++) {
	    for (int j = i + 1; j < selectedPlayers.length; j++) {
		if (j != i && selectedPlayers[i].equals(selectedPlayers[j])) {
		    duplicates = true;
		}
	    }
	}
	if (duplicates == false) {
	    System.out.println("You can go back");
	    buttonBack.setEnabled(true);
	}
	if (duplicates == true) {
	    System.out.println("You can't go back");
	    buttonBack.setEnabled(false);
	}
    }
}
