package rest;

import javax.swing.JTextArea;

public class ThreadTest implements Runnable {

    private static JTextArea workSpace;

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
        
        for(int n=0;n<15;n++){
            Update tickHome = thisMatch.tickHome();
            Update tickAway = thisMatch.tickAway();
            switch(tickHome.getType()){
                case 0: break;
                case 1: methodeOutput = methodeOutput + "Gele kaart voor " + tickHome.getSpeler() + "!/n"; break;
                case 2: methodeOutput = methodeOutput + "Rode kaart voor " + tickHome.getSpeler() + "!/n"; break;
                case 3: methodeOutput = methodeOutput + tickHome.getSpeler() + "is geblesseerd geraakt!/n"; break;
                case 4: methodeOutput = methodeOutput + "GOAL! Doelpuntenmaker: " +tickHome.getSpeler() + "!/n"; break;
            }
            switch(tickAway.getType()){
                case 0: break;
                case 1: methodeOutput = methodeOutput + "Gele kaart voor " + tickAway.getSpeler() + "!/n"; break;
                case 2: methodeOutput = methodeOutput + "Rode kaart voor " + tickAway.getSpeler() + "!/n"; break;
                case 3: methodeOutput = methodeOutput + tickAway.getSpeler() + "is geblesseerd geraakt!/n"; break;
                case 4: methodeOutput = methodeOutput + "GOAL! Doelpuntenmaker: " +tickAway.getSpeler() + "!/n"; break;
            }
            String display = beginText + methodeOutput;
            workSpace.setText(display);
            try{
                Thread.sleep(1500);
            }
            catch(InterruptedException e){
                System.out.println(e);
            }
            }
    }

    public static JTextArea getWorkSpace() {
	return workSpace;
    }

    public static void setWorkSpace(JTextArea workSpace) {
	ThreadTest.workSpace = workSpace;
    }
}
