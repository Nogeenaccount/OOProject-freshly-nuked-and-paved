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
    
    //Initialise images
    String gameNameImage = "GUIFiles/promptName1.png";
    String teamNameImage = "GUIFiles/promptTeam1.png";
    String buttonAdvanceImage = "GUIFiles/buttonAdvance2.png";
    String buttonBackImage = "GUIFiles/buttonBack2.png";
    String panelPanelImage = "GUIFiles/FootbalStadiumSize.png";
    
    JLabel gameName = new JLabel(new ImageIcon(gameNameImage));
    JTextField input = new JTextField();
    JLabel teamName = new JLabel(new ImageIcon(teamNameImage));
    JList teamList = new JList();
    JButton buttonAdvance = new JButton(new ImageIcon(buttonAdvanceImage));
    JButton buttonBack = new JButton(new ImageIcon(buttonBackImage));
    
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
        
        createSpace();
        
        //Prompt name
        c.gridx = 2;
        c.gridy = 3;
        createLabel(gameName,"",c,layout);
        
        //Enter your name
        c.gridx = 2;
        c.gridy = 4;
        createInput(input,c,layout);
        
        //Prompt team
        c.gridx = 2;
        c.gridy = 5;
        createLabel(teamName,"",c,layout);
        
        //Team list
        teamList = new JList(array1);
        teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        teamList.setVisibleRowCount(-1);
        teamList.setBackground(Color.decode("#525151"));
        teamList.setForeground(Color.white);
        JScrollPane teamScroller = new JScrollPane(teamList);
        teamScroller.setPreferredSize(new Dimension(400, 200));
        teamScroller.setMinimumSize(new Dimension(400, 200));
        c.gridx = 2;
        c.gridy = 6;
        layout.setConstraints(teamScroller, c);
        this.add(teamScroller);
        teamList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                enableButtons();
            }
        });
        
        //Advance
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 7;
        createButton(buttonAdvance, "", c, layout);
        attachStateChanger(buttonAdvance, new MenuBetweenRounds());
        advance();
        
        //Go back
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 8;
        createButton(buttonBack, "", c, layout);
        attachStateChanger(buttonBack, new MenuMain());
	
	setBackground(panelPanelImage);
    }
    
    public void enableButtons(){
        if(teamList.isSelectionEmpty() == false && input.getText().equals("") == false) {
            buttonAdvance.setEnabled(true);
        }
        else{
            buttonAdvance.setEnabled(false);
        }
    }
    
    public void advance(){
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
                
                for (int i=0; i<StateManager.getLeague().getTeams().size(); i++){
                    StateManager.getLeague().getTeams().get(i).setLineUp(StateManager.getLeague().getTeams().get(i).getDefaultLineUp());
                }
            }
        });
    }
}
