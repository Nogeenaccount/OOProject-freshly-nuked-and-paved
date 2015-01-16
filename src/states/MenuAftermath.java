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
import rest.Match;
import rest.MatchLogic;
import rest.Update;
import rest.Round;
import rest.Team;
import rest.MatchResult;

@SuppressWarnings("serial")
//WORKING WITH FILLER
public class MenuAftermath extends State {

    public MenuAftermath() {
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
	String labelImage = "GUIFiles\\labelNR2.png";
	String buttonNRAdvanceImage = "GUIFiles\\buttonNRAdvance.png";
	String panelPanelImage = "GUIFiles\\FootbalStadiumSize.png";

	//Prompt Matches
	JLabel progress = new JLabel(new ImageIcon(labelImage));
	progress.setOpaque(true);
	progress.setPreferredSize(new Dimension(400, 50));
	progress.setMinimumSize(new Dimension(400, 50));
	progress.setMaximumSize(new Dimension(400, 50));
//  progress.setEditable(false);
	progress.setText("");
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 1;
	layout.setConstraints(progress, c);
	this.add(progress);

	//Prompt Round Results
	JTextArea matchResults = new JTextArea();
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 2;
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
            String name = "";
            if(n%2 == 0){
                name = MatchLogic.findOwnMatch(states.StateManager.getLeague().getRounds()).getHomeTeam().getTeamName();
            }
            else{
                name = MatchLogic.findOwnMatch(states.StateManager.getLeague().getRounds()).getAwayTeam().getTeamName();
            }
            
            
            switch(temp.getType()){
                case 1: Bookings = Bookings + temp.getMinuut() + "' " + temp.getSpeler().getPlayerName() +" (" + name +")"+ ": Gele kaart\n"; break;
                case 2: Bookings = Bookings + temp.getMinuut() + "' " + temp.getSpeler().getPlayerName() +" (" + name +")"+ ": Rode kaart\n";break;
                case 3: injuries = injuries + temp.getMinuut() + "' " + temp.getSpeler().getPlayerName() + ": " + rest.MatchLogic.randomInjury() + "\n";break;
                default: break;
            }
        }
        
        
	String yourRoundResult = states.StateManager.getLeague().getLastResult();

        //Find other results
        String othermatches = "";
        Round thisRound = states.StateManager.getLeague().nextRound("Speelschema.xml",states.StateManager.getLeague().getRounds());
        for(int n=0;n<10;n++){
            Match temp = thisRound.getMatch(n);
            if(!temp.equals(MatchLogic.findOwnMatch(states.StateManager.getLeague().getRounds()))){
                Team home = temp.getHomeTeam();
                Team away = temp.getAwayTeam();
                temp = MatchResult.getResult(home,away,15);
                states.StateManager.getLeague().processResult(temp.getHomeTeam(), temp.getAwayTeam(), temp.getHomeScore(), temp.getAwayScore());
            }
            
        }
        
        
        
        
        
        
	
	String roundResults
		= "Here is the result of your match:" + "\n"
		+ yourRoundResult + "\n" + "\n"
		+ Bookings + "\n" + "\n"
		+ injuries + "\n" + "\n"
		+ "And here are the results of the other matches:" + "\n";
		//+ otherMatches;
	
        int round = states.StateManager.getLeague().getRounds();
        Match weddie = MatchLogic.findOwnMatch(round);
	states.StateManager.getLeague().processResult(weddie.getHomeTeam(),weddie.getAwayTeam(),weddie.getHomeScore(),weddie.getAwayScore());
        
        states.StateManager.getLeague().setRounds(states.StateManager.getLeague().getRounds()-1);
        
        matchResults.setText(roundResults);
	this.add(matchResults);

	//Advance
	JButton buttonAdvance = new JButton(new ImageIcon(buttonNRAdvanceImage));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 3;
	buttonAdvance.setMinimumSize(new Dimension(400, 100));
	buttonAdvance.setPreferredSize(new Dimension(400, 100));
	createButton(buttonAdvance, "", c, layout);
	attachStateChanger(buttonAdvance, new MenuBetweenRounds());

	//Padding
	JTextArea invisi2 = new JTextArea();
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 0;
	layout.setConstraints(invisi2, c);
	invisi2.setPreferredSize(new Dimension(200, 100));
	invisi2.setOpaque(false);
	invisi2.setEditable(false);
	invisi2.setMargin(new Insets(50, 0, 0, 0));
	this.add(invisi2);

	c.weightx = 0.5;
	c.gridheight = 6;
	c.gridwidth = 3;
	c.gridx = 0;
	c.gridy = 0;
	ImagePanel panel = new ImagePanel(new ImageIcon(panelPanelImage).getImage(), c, layout);
	this.add(panel);
    }
}
