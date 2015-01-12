/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SpringLayout.Constraints;

/**
 *
 * @author user
 */
public class TournamentOverview extends State {

    String[] columns = {"Team",
	"Points",
	"Won",
	"Draw",
	"Lost",
	"Goals"};

    public TournamentOverview() {

    }

    public void createGUI() {
	JTable table = new JTable(getTeamData(), columns);
	table.setSize(800, 300);
	System.out.println(table.getSize());

	/*JButton startButton = new JButton("Back");
	 layout = new GridBagLayout();
	 this.setLayout(layout);
	 c = new GridBagConstraints();
	 c.weightx = 0.5;
	 c.gridx = 0;
	 c.gridy = 0;
	 createButton(startButton, "back", c, layout);*/
	add(table);
    }

    public Object[][] getTeamData() {
	Object[][] teamData = {
	    {"Ajax", 12, 4, 0, 0, "7 (12-5)"},
	    {"SC Heerenveen", 10, 3, 1, 0, "5 (9-4)"},
	    {"PSV", 7, 2, 1, 1, "3 (4-1)"},
	    {"FC bal op het dak", 5, 1, 2, 1, "0 (5-5)"},
	    {"Feyenoord", -6, 0, 0, 6, "-12 (12-24)"}
	};

	return teamData;
    }
}
