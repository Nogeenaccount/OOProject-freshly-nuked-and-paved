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

    public MenuMatchScreen() {
    }

    public void createGUI() {
	layout = new GridBagLayout();
	this.setLayout(layout);
	c = new GridBagConstraints();

	//Initialise Images
	String userDir = System.getProperty("user.home");
	String labelImage = userDir + "\\Desktop\\GUIFiles\\labelNR.png";
	String buttonNRAdvanceImage = userDir + "\\Desktop\\GUIFiles\\buttonNRAdvance.png";
	String panelPanelImage = userDir + "\\Desktop\\GUIFiles\\FootbalStadiumSize.png";

	//Prompt Matches
	JLabel progress = new JLabel(new ImageIcon(labelImage));
	progress.setOpaque(true);
	progress.setPreferredSize(new Dimension(400, 50));
	progress.setMinimumSize(new Dimension(400, 50));
	progress.setMaximumSize(new Dimension(400, 50));
//	progress.setEditable(false);
	progress.setText("");
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 1;
	layout.setConstraints(progress, c);
	this.add(progress);

	//Prompt Your Match progress
	JTextArea matchProgress = new JTextArea();
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 2;
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

	Thread t = new Thread(new ThreadTest());
	rest.ThreadTest.setWorkSpace(matchProgress);
	t.start();
	System.out.println("Tried: empty alreadyTried, Problem: filler data");

	//Advance
	JButton buttonAdvance = new JButton(new ImageIcon(buttonNRAdvanceImage));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 3;
	createButton(buttonAdvance, "", c, layout);
	buttonAdvance.setEnabled(false);
	buttonAdvance.setMinimumSize(new Dimension(400, 100));
	attachStateChanger(buttonAdvance, new MenuAftermath());

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

	c.weightx = 0.5;
	c.gridheight = 6;
	c.gridwidth = 3;
	c.gridx = 0;
	c.gridy = 0;
	ImagePanel panel = new ImagePanel(new ImageIcon(panelPanelImage).getImage(), c, layout);
	this.add(panel);

	//Advance button logic
	while (t.isAlive()) {
	    System.out.println("waiting...");
	}
	buttonAdvance.setEnabled(true);
	System.out.println("Thread has finished, advance button has been enabled");
    }

    public String getOngoingMatchText() {
	return ongoingMatchText;
    }

    public void setOngoingMatchText(String ongoingMatchText) {
	this.ongoingMatchText = ongoingMatchText;
    }

}
