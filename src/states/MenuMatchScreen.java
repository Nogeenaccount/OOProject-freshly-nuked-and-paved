package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rest.ThreadTest;

@SuppressWarnings("serial")
//WORKING WITH FILLER
public class MenuMatchScreen extends State {

    private String ongoingMatchText;
    String labelImage = "GUIFiles/labelNR.png";
    String buttonNRAdvanceImage = "GUIFiles/buttonNRAdvance.png";
    String panelPanelImage = "GUIFiles/FootbalStadiumSize.png";
    
    JLabel progress = new JLabel(new ImageIcon(labelImage));
    JTextArea matchProgress = new JTextArea();
    JButton buttonAdvance = new JButton(new ImageIcon(buttonNRAdvanceImage));
    
    public MenuMatchScreen() {
    }

    public void createGUI() {
	layout = new GridBagLayout();
	this.setLayout(layout);
	c = new GridBagConstraints();

	createSpace();
	
	//Prompt Matches
	progress.setOpaque(true);
	progress.setPreferredSize(new Dimension(400, 50));
	progress.setMinimumSize(new Dimension(400, 50));
	progress.setMaximumSize(new Dimension(400, 50));
	progress.setText("");
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 2;
	layout.setConstraints(progress, c);
	this.add(progress);

	//Prompt Your Match progress
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 3;
	layout.setConstraints(matchProgress, c);
	matchProgress.setPreferredSize(new Dimension(400, 400));
	matchProgress.setEditable(false);
	String matchProgressTextPart1 = "Here is the progress of your match:\n\n";
	matchProgress.setText(matchProgressTextPart1);
	matchProgress.setBackground(Color.decode("#525151"));
	matchProgress.setForeground(Color.white);
	matchProgress.setMinimumSize(new Dimension(400, 400));
	matchProgress.setFont(new Font("Arial", Font.PLAIN, 14));
	this.add(matchProgress);

        //Advance
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 4;
	createButton(buttonAdvance, "", c, layout);
	buttonAdvance.setEnabled(false);
	attachStateChanger(buttonAdvance, new MenuAftermath());
        
	Thread t = new Thread(new ThreadTest(buttonAdvance));
	rest.ThreadTest.setWorkSpace(matchProgress);
	t.start();
	System.out.println("Tried: empty alreadyTried, Problem: filler data");

	setBackground(panelPanelImage);
    }

    public String getOngoingMatchText() {
	return ongoingMatchText;
    }

    public void setOngoingMatchText(String ongoingMatchText) {
	this.ongoingMatchText = ongoingMatchText;
    }

}
