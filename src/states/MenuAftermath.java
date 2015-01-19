package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

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
	c.gridy = 2;
	layout.setConstraints(progress, c);
	this.add(progress);

	//Prompt Round Results
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 3;
	layout.setConstraints(matchResults, c);
	matchResults.setPreferredSize(new Dimension(400, 400));
	matchResults.setMinimumSize(new Dimension(400, 400));
        matchResults.setMaximumSize(new Dimension(400, 400));
	matchResults.setBackground(Color.decode("#525151"));
	matchResults.setForeground(Color.white);
	matchResults.setFont(new Font("Arial", Font.PLAIN, 14));
	matchResults.setEditable(false);

        //Preparing strings
        String Bookings = "Kaarten:\n";
        String injuries = "Blessures:\n";
        
        
        ArrayList<Update> last = states.StateManager.getLeague().getLastResultDetailed();
        String result = states.StateManager.getLeague().getLastResult();
        int round = states.StateManager.getLeague().getRounds();
        Match weddie = MatchLogic.findOwnMatch(round);
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
                case 4: 
                    if(n%2 == 0){
                        weddie.setHomeScore(weddie.getHomeScore()+1);
                    }
                    else{
                        weddie.setAwayScore(weddie.getAwayScore()+1);
                    }
                    
                    
                    
                    break;
                default: break;
            }
        }

	String yourRoundResult = states.StateManager.getLeague().getLastResult();
        //Find other results
        String othermatches = "";
	
	//DIT IS ALLEMAAL LOGIC JONGENS, MAAK HIERVOOR KLASSES IN BIJV LEAGUE AAN.
        //Nee.
        Round thisRound = states.StateManager.getLeague().nextRound("Speelschema.xml",states.StateManager.getLeague().getRounds());
        for(int n=0;n<10;n++){
            Match temp = thisRound.getMatch(n);
            if(!temp.equals(MatchLogic.findOwnMatch(states.StateManager.getLeague().getRounds()))){
                Team home = temp.getHomeTeam();
                Team away = temp.getAwayTeam();
                temp = MatchResult.getResult(home,away,15);
                states.StateManager.getLeague().processResult(temp.getHomeTeam(), temp.getAwayTeam(), temp.getHomeScore(), temp.getAwayScore());
                othermatches = othermatches + temp.getHomeTeam().getTeamName() + " " + temp.getHomeScore() + "-" + temp.getAwayScore() + " " + temp.getHomeTeam().getTeamName()+"\n";
            }
            
        }
	String roundResults
		= "Here is the result of your match:" + "\n"
		+ yourRoundResult + "\n" + "\n"
		+ Bookings + "\n" + "\n"
		+ injuries + "\n" + "\n"
		+ "And here are the results of the other matches:" + "\n"
		+ othermatches;

        
        
	states.StateManager.getLeague().processResult(weddie.getHomeTeam(),weddie.getAwayTeam(),weddie.getHomeScore(),weddie.getAwayScore());
        

        states.StateManager.getLeague().setRounds(states.StateManager.getLeague().getRounds()-1);
        
        matchResults.setText(roundResults);
	this.add(matchResults);

	//Advance
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 5;
	createButton(buttonAdvance, "", c, layout);
	attachStateChanger(buttonAdvance, new MenuBetweenRounds());
        buttonAdvance.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
                        StateManager.getLeague().setOffersmade(new ArrayList<String>());
			for(int i = 0; i < 3; i ++) {
                            StateManager.getLeague().generateOffer();
                        }
		}
	});
	setBackground(panelPanelImage);
    }
}
