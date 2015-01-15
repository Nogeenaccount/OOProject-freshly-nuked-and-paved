package rest;

import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;
public class ThreadTest implements Runnable {

    
    private static JTextArea workSpace;
    private static JButton button;
    
    public ThreadTest(JButton button2) {
        button = button2;
    }

    @Override
    public void run() {
	String beginText = "Here is the progress of your match:\n\n";
	workSpace.setText(beginText);

	//String advancedText = "You lost";
	//beginText = beginText + advancedText;
	//workSpace.setText(beginText);

        System.out.println("Thread has set 'ongoingMatchText'");
        League league = states.StateManager.getLeague();
        Team home = league.getByName(league.getChosenTeam());
        Team away = league.nextRound("Speelschema.xml", (league.getRounds())).getOpponent(home);
        //Twee teams
        
        //Echte stuff
        int round = states.StateManager.getLeague().getRounds();
        Match weddie = MatchLogic.findOwnMatch(round);
        Team homeTeam = weddie.getHomeTeam();
        Team awayTeam = weddie.getAwayTeam();
        MatchLogic thisMatch = new MatchLogic(15,homeTeam,awayTeam);
        String newLine = System.getProperty("line.separator");
        System.out.println(thisMatch.getTeam1().getTeamName().toString());
        String updateText = "";
        for(int n=0;n<15;n++){
                      
            String mainText = thisMatch.gettCurrent()
                      + "e Minuut" + ", Stand: " 
                      + thisMatch.getTeam1().getTeamName()
                      + " "+thisMatch.getScore1()
                      + "-" 
                      + thisMatch.getScore2()
                      + " "
                      +thisMatch.getTeam2().getTeamName()
                      + newLine;
            
            ArrayList<Update> tick = thisMatch.oneTick();
            
            updateText = updateText 
                        + thisMatch.LineGenerator(tick.get(0),thisMatch.getTeam1())
                        + thisMatch.LineGenerator(tick.get(1),thisMatch.getTeam2());
            
            
            
                workSpace.setText(beginText + mainText + updateText);
                try{
                    Thread.sleep(100);
                }
                catch(InterruptedException e){
                    System.out.println(e);
                }
            }
        button.setEnabled(true);
    }

    public static JTextArea getWorkSpace() {
	return workSpace;
    }

    public static void setWorkSpace(JTextArea workSpace) {
	ThreadTest.workSpace = workSpace;
    }
}
