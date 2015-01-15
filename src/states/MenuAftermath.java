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

import java.util.ArrayList;
import rest.Update;

@SuppressWarnings("serial")
//WORKING WITH FILLER
public class MenuAftermath extends State {

    String labelImage = "GUIFiles/labelNR2.png";
    String buttonNRAdvanceImage = "GUIFiles/buttonNRAdvance.png";
    String panelPanelImage = "GUIFiles/FootbalStadiumSize.png";
    
    JTextArea matchResults = new JTextArea();
    JButton buttonAdvance = new JButton(new ImageIcon(buttonNRAdvanceImage));
    
    public MenuAftermath() {
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

	//Prompt Matches
	JLabel progress = new JLabel(new ImageIcon(labelImage));
	progress.setOpaque(true);
	progress.setPreferredSize(new Dimension(400, 50));
	progress.setMinimumSize(new Dimension(400, 50));
	progress.setMaximumSize(new Dimension(400, 50));
	progress.setText("");
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 3;
	layout.setConstraints(progress, c);
	this.add(progress);

	//Prompt Round Results
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 4;
	layout.setConstraints(matchResults, c);
	matchResults.setPreferredSize(new Dimension(400, 500));
	matchResults.setMinimumSize(new Dimension(400, 500));
	matchResults.setBackground(Color.decode("#525151"));
	matchResults.setForeground(Color.white);
	matchResults.setFont(new Font("Arial", Font.PLAIN, 14));
	matchResults.setEditable(false);

        //Preparing strings
        String Bookings = "Kaarten:\n";
        String injuries = "Blessures:\n";
        
        ArrayList<Update> last = states.StateManager.getLeague().getLastResultDetailed();
        String result = states.StateManager.getLeague().getLastResult();
        for(int n=0;n<states.StateManager.getLeague().getLastResultDetailed().size();n++){
            Update temp = states.StateManager.getLeague().getLastResultDetailed().get(n);
            switch(temp.getType()){
                case 1: Bookings = Bookings + temp.getMinuut() + "' " + temp.getSpeler().getPlayerName() + ": Gele kaart\n"; break;
                case 2: Bookings = Bookings + temp.getMinuut() + "' " + temp.getSpeler().getPlayerName() + ": Rode kaart\n";break;
                case 3: injuries = injuries + temp.getMinuut() + "' " + temp.getSpeler().getPlayerName() + ": " + rest.MatchLogic.randomInjury() + "\n";break;
                default: break;
            }
        }

	String yourRoundResult = states.StateManager.getLeague().getLastResult();

	String roundResults
		= "Here is the result of your match:" + "\n"
		+ yourRoundResult + "\n" + "\n"
		+ Bookings + "\n" + "\n"
		+ injuries + "\n" + "\n"
		+ "And here are the results of the other matches:" + "\n";
		//+ otherMatches;
	;
        matchResults.setText(roundResults);
	this.add(matchResults);

	//Advance
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 6;
	createButton(buttonAdvance, "", c, layout);
	attachStateChanger(buttonAdvance, new MenuBetweenRounds());

	setBackground(panelPanelImage);
    }
}
