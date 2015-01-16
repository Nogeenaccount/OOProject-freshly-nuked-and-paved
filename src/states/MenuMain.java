/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import rest.League;

/**
 *
 * @author user
 */
//FINISHED
public class MenuMain extends State {
    
    String buttonNewImage = "GUIFiles/NewGameButton.png";
    String buttonContinueImage = "GUIFiles/ContinueButton.png";
    String buttonExitImage = "GUIFiles/ExitButton.png";
    String panelPanelImage = "GUIFiles/FootbalStadiumSize.png";
    
    JButton buttonNew = new JButton(new ImageIcon(buttonNewImage));
    JButton buttonContinue = new JButton(new ImageIcon(buttonContinueImage));
    JButton buttonExit = new JButton(new ImageIcon(buttonExitImage));
    
    public MenuMain() {
        
    }
    
    public void createGUI() {
        layout = new GridBagLayout();
        this.setLayout(layout);
        c = new GridBagConstraints();
        
        createSpace();
        
        //c = new GridBagConstraints();
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 3;
        createButton(buttonNew, "", c, layout);
        attachStateChanger(buttonNew, new MenuNew());
        
        
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 4;
        createButton(buttonContinue, "", c, layout);
        attachStateChanger(buttonContinue, new MenuBetweenRounds());
        buttonContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StateManager.setLeague(League.readResources("SaveGame.xml"));;
            }
        });
        
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 5;
        createButton(buttonExit, "", c, layout);
        attachStateChanger(buttonExit, new Exit());
	
	setBackground(panelPanelImage);
    }
    
    public void enableButtons(){
        File savegame = new File("SaveGame.xml");
        buttonContinue.setEnabled(savegame.exists() && !savegame.isDirectory());
    }
    
}
