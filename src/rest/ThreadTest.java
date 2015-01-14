package rest;

import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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

        String methodeOutput = "";
        
	System.out.println("Thread has set 'ongoingMatchText'");
        
        //Twee teams
        
        //Echte stuff
        MatchLogic thisMatch = new MatchLogic(15,states.StateManager.getLeague().getTeams().get(0),states.StateManager.getLeague().getTeams().get(1));
        String newLine = System.getProperty("line.separator");
        int HomeGoals =0;
        int AwayGoals =0;
        for(int n=0;n<15;n++){
            Update tickHome = thisMatch.tickHome();
            Update tickAway = thisMatch.tickAway();
            switch(tickHome.getType()){
                case 0: break;
                case 1: methodeOutput = methodeOutput + Math.round(6.4*n) + "' " + "Gele kaart voor " + tickHome.getSpeler().getPlayerName() + "!"+newLine; break;
                case 2: methodeOutput = methodeOutput + Math.round(6.4*n) + "' " + "Rode kaart voor " + tickHome.getSpeler().getPlayerName() + "!"+newLine; break;
                case 3: methodeOutput = methodeOutput + Math.round(6.4*n) + "' " + tickHome.getSpeler().getPlayerName() + " is geblesseerd geraakt!"+newLine; break;
                case 4: methodeOutput = methodeOutput + Math.round(6.4*n) + "' " + "GOAL voor " + thisMatch.getTeam1().getTeamName() + "! Doelpuntenmaker: " +tickHome.getSpeler().getPlayerName() + "!"+newLine; HomeGoals ++; break;
            }
            switch(tickAway.getType()){
                case 0: break;
                case 1: methodeOutput = methodeOutput + Math.round(6.4*n) + "' " + "Gele kaart voor " + tickAway.getSpeler().getPlayerName() + "!"+newLine; break;
                case 2: methodeOutput = methodeOutput + Math.round(6.4*n) + "' " + "Rode kaart voor " + tickAway.getSpeler().getPlayerName() + "!"+newLine; break;
                case 3: methodeOutput = methodeOutput + Math.round(6.4*n) + "' " + tickAway.getSpeler().getPlayerName() + " is geblesseerd geraakt!"+newLine; break;
                case 4: methodeOutput = methodeOutput + Math.round(6.4*n) + "' " + "GOAL voor " + thisMatch.getTeam2().getTeamName() + " ! Doelpuntenmaker: " +tickAway.getSpeler().getPlayerName() + "!"+newLine; AwayGoals++; break;
            }
            String display = beginText + Math.round(6.4*n) + "e Minuut" + ", Stand: " +thisMatch.getTeam1().getTeamName()+ " "+HomeGoals + "-" + AwayGoals+" "+thisMatch.getTeam2().getTeamName()+ newLine + methodeOutput;
            workSpace.setText(display + newLine);
            try{
                Thread.sleep(500);
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
