/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author user
 */
//FINISHED
@SuppressWarnings("serial")
public class MenuNew extends State {

    public MenuNew() {

    }

    public void createGUI() {
	layout = new GridBagLayout();
	this.setLayout(layout);
	c = new GridBagConstraints();

	//Initialise
	String[] array1 = new String[20];
	rest.League league1 = rest.League.readResources("ResourceV6.xml");
	for (int i = 0; i < league1.getTeams().size(); i++) {
	    array1[i] = league1.getTeams().get(i).getTeamName();
	}

	//Initialise images
	String userDir = System.getProperty("user.home");
	String gameNameImage = userDir + "\\Desktop\\GUIFiles\\promptName1.png";
	String teamNameImage = userDir + "\\Desktop\\GUIFiles\\promptTeam1.png";
	String buttonAdvanceImage = userDir + "\\Desktop\\GUIFiles\\buttonAdvance2.png";
	String buttonBackImage = userDir + "\\Desktop\\GUIFiles\\buttonBack2.png";
	String panelPanelImage = userDir + "\\Desktop\\GUIFiles\\FootbalStadiumSize.png";
//	System.out.println(gameNameImage);

	//Initialise components
	@SuppressWarnings({"unchecked", "rawtypes"})
	JList teamList = new JList(array1);
	JButton buttonAdvance = new JButton(new ImageIcon(buttonAdvanceImage));

	//Padding
	JTextArea invisi2 = new JTextArea();
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 0;
	layout.setConstraints(invisi2, c);
	invisi2.setPreferredSize(new Dimension(200, 100));
	invisi2.setOpaque(false);
	invisi2.setEditable(false);
	invisi2.setMargin(new Insets(100, 0, 0, 0));
	this.add(invisi2);

	//Prompt name	
	JLabel gameName = new JLabel(new ImageIcon(gameNameImage));
//	gameName.setOpaque(true);
//	gameName.setEditable(false);
	gameName.setPreferredSize(new Dimension(400, 40));
	gameName.setMinimumSize(new Dimension(400, 40));
//	gameName.setText("Please enter your name:");
	c.gridx = 1;
	c.gridy = 1;
	layout.setConstraints(gameName, c);
	this.add(gameName);

	//Enter your name
	JTextField input = new JTextField();
	input.setOpaque(true);
	input.setPreferredSize(new Dimension(400, 40));
	input.setMinimumSize(new Dimension(400, 40));
	input.setBackground(Color.decode("#525151"));
	input.setForeground(Color.white);
	input.setFont(new Font("Arial", Font.PLAIN, 20));
	c.gridx = 1;
	c.gridy = 2;
	layout.setConstraints(input, c);
	this.add(input);
	input.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (teamList.isSelectionEmpty() == false && input.getText().equals("") == false) {
		    buttonAdvance.setEnabled(true);
		}
		if (teamList.isSelectionEmpty() || input.getText().equals("")) {
		    buttonAdvance.setEnabled(false);
		}
	    }
	});

	//Prompt team
	JLabel teamName = new JLabel(new ImageIcon(teamNameImage));
//	teamName.setOpaque(true);
	teamName.setPreferredSize(new Dimension(400, 40));
	teamName.setMinimumSize(new Dimension(400, 40));
//	teamName.setEditable(false);
//	teamName.setText("Please choose your team:");
	c.gridx = 1;
	c.gridy = 3;
	layout.setConstraints(teamName, c);
	this.add(teamName);

	//Team list
	teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	teamList.setVisibleRowCount(-1);
	teamList.setBackground(Color.decode("#525151"));
	teamList.setForeground(Color.white);
	JScrollPane teamScroller = new JScrollPane(teamList);
	teamScroller.setPreferredSize(new Dimension(400, 200));
	teamScroller.setMinimumSize(new Dimension(400, 200));
	c.gridx = 1;
	c.gridy = 4;
	layout.setConstraints(teamScroller, c);
	this.add(teamScroller);
	teamList.addListSelectionListener(new ListSelectionListener() {
	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		if (teamList.isSelectionEmpty() == false && input.getText().equals("") == false) {
		    buttonAdvance.setEnabled(true);
		}
		if (teamList.isSelectionEmpty() || input.getText().equals("")) {
		    buttonAdvance.setEnabled(false);
		}
	    }
	});

	//Advance
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 5;
	createButton(buttonAdvance, "Advance", c, layout);
	attachStateChanger(buttonAdvance, new MenuBetweenRounds());
	buttonAdvance.setEnabled(false);
	buttonAdvance.setPreferredSize(new Dimension(400, 80));
	buttonAdvance.setMinimumSize(new Dimension(400, 80));
	buttonAdvance.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		PrintWriter writer;
		try {
		    writer = new PrintWriter("SaveGame.xml");
		    writer.print("");
		    writer.close();
		} catch (FileNotFoundException error) {
		    error.printStackTrace();
		}

		StateManager.setLeague(rest.League.readResources("ResourceV6.xml"));
		StateManager.getLeague().setGameName(input.getText());
		StateManager.getLeague().setChosenTeam(teamList.getSelectedValue().toString());
		StateManager.getLeague().writeToXML("SaveGame.xml");
	    }
	});

	//Go back
	JButton buttonBack = new JButton(new ImageIcon(buttonBackImage));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 6;
	createButton(buttonBack, "Back", c, layout);
	buttonBack.setPreferredSize(new Dimension(400, 80));
	buttonBack.setMinimumSize(new Dimension(400, 80));
	attachStateChanger(buttonBack, new MenuMain());

	//Padding
	JTextArea invisi3 = new JTextArea();
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 7;
	layout.setConstraints(invisi3, c);
	invisi3.setPreferredSize(new Dimension(200, 100));
	invisi3.setOpaque(false);
	invisi3.setEditable(false);
	invisi3.setMargin(new Insets(0, 0, 0, 200));
	this.add(invisi3);

	c.weightx = 0.5;
	c.gridheight = 8;
	c.gridwidth = 3;
	c.gridx = 0;
	c.gridy = 0;
	ImagePanel panel = new ImagePanel(new ImageIcon(panelPanelImage).getImage(), c, layout);
	this.add(panel);
    }
    
    
}
