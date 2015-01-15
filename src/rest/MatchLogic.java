package rest;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.util.ArrayList;
import java.util.List;

public class MatchLogic{

    private int tCurrent;
    private int tMax;
    private int score1;
    private int score2;
    private Team team1;
    private Team team2;
    

    private double t1Offence;
    private double t1Defence;
    private double t1Endurance;

    private double t2Offence;
    private double t2Defence;
    private double t2Endurance;

    private ArrayList<Update> fullUpdateList = new ArrayList<Update>();
    
    /**
     * matchLogic: constructor
     *
     * @param t amount of time
     * @param t1 team one
     * @param t2 team two
     * @param txt html text
     * @param bar loading bar
     */
    public MatchLogic(int t, Team t1, Team t2) {
	tCurrent = 0;
	tMax = t;
	team1 = t1;
	team2 = t2;
	score1 = 0;
	score2 = 0;


	/*		t1Offence =  offenceSum(t1);
	 t1Defence = defenceSum(t1);
	 t1Endurance = enduranceSum(t1);

	 t2Offence = offenceSum(t2);
	 t2Defence = defenceSum(t2);
	 t2Endurance = enduranceSum(t2);*/
    }

   /**
	 * offenceSum: returns the sum of offence in lineUp
	 * @param t
	 * @return S
	 */
	public double offenceSum(Team t){
		double S = 0;
		for (int i=0; i<t.getDefaultLineUp().getAanvallers().size(); i++){
			S += t.getDefaultLineUp().getAanvallers().get(i).getOffence();
		}
		for (int i=0; i<t.getDefaultLineUp().getMiddenvelders().size(); i++){
			S += ( t.getDefaultLineUp().getMiddenvelders().get(i).getOffence() )/2;
		}
		
		return S;
	}
	
	/**
	 * defenceSum: returns the sum of defence in lineUp
	 * @param t
	 * @return S
	 */
	public double defenceSum(Team t){
		double S = 0;
		for (int i=0; i<t.getDefaultLineUp().getVerdedigers().size(); i++){
			S += t.getDefaultLineUp().getVerdedigers().get(i).getDefence();
		}
		for (int i=0; i<t.getDefaultLineUp().getMiddenvelders().size(); i++){
			S += ( t.getDefaultLineUp().getMiddenvelders().get(i).getDefence() )/2;
		}
		S += t.getDefaultLineUp().getKeeper().getDefence(); 
		
		return S;
	}

	/**
	 * enduranceSum: returns the sum of endurance in lineUp
	 * @param t
	 * @return S
	 */
	public double enduranceSum(Team t){
		double S = 0;
		for (int i=0; i<t.getDefaultLineUp().getAanvallers().size(); i++){
			S += t.getDefaultLineUp().getAanvallers().get(i).getEndurance();
		}
		for (int i=0; i<t.getDefaultLineUp().getVerdedigers().size(); i++){
			S += t.getDefaultLineUp().getVerdedigers().get(i).getEndurance();
		}
		for (int i=0; i<t.getDefaultLineUp().getMiddenvelders().size(); i++){
			S += t.getDefaultLineUp().getMiddenvelders().get(i).getEndurance();
		}
		S += t.getDefaultLineUp().getKeeper().getEndurance(); 
		
		return S;
	}

	/**
	 * scored: return 1 or 0 depending on chance of scoring of the team
	 * @param O1
	 * @param D2
	 * @param E1
	 * @param E2
	 * @param t
	 * @return
	 */
	public boolean scored(double O1, double D2, double E1, double E2, double t) {
		double P;
		double a = 5;
		double b = 0.0006;
		P = (O1 - D2/2)*Math.pow((E1/E2),(t/a))*b;
		
		if (Math.random() < P)
			return true;
		else
			return false;
	}

    
    
    public Update tickHome(){
            int typ =0;
            Player spelert = null;
            if(gettCurrent()==0){
                tCurrent=2;
            }
            int min=gettCurrent();
            
             double p1=0.05; //gele kaart thuisteam
             double p2=0.005; //rode kaart thuisteam
             double p3=0.04; //blessure thuisteam
            
            
            if(scored(offenceSum(getTeam1()), defenceSum(getTeam2()), enduranceSum(getTeam1()), enduranceSum(getTeam2()), gettCurrent())){
                int a = (int)(Math.round(Math.random()*3-0.5));
                spelert = getTeam1().getDefaultLineUp().getAanvallers().get(a);
                return new Update(4, spelert, gettCurrent());
            }
            
            else{
                
                spelert=getTeam1().getDefaultLineUp().getRandomPlayer();
                double temp = Math.random();
                
                if(temp<p1){
                    return new Update(1, spelert, gettCurrent());
                }
                if(temp<p2){
                    return new Update(2, spelert, gettCurrent());
                }
                if(temp<p3){
                    return new Update(3, spelert, gettCurrent());
                }
            }
                        
            return new Update(typ, spelert, min);
        }
        
        public Update tickAway(){
            int typ =0;
            Player spelert = null;
            
            
            
            int min=gettCurrent();
            
             double p1=0.07; //gele kaart uitteam
             double p2=0.005; //rode kaart uitteam
             double p3=0.05; //blessure uitteam
            
            
            if(scored(offenceSum(getTeam2()), defenceSum(getTeam1()), enduranceSum(getTeam2()), enduranceSum(getTeam1()), gettCurrent())){
                int a = (int)(Math.round(Math.random()*3-0.5));
                spelert = getTeam2().getDefaultLineUp().getAanvallers().get(a);
                return new Update(4, spelert, gettCurrent());
            }
            
            else{
                
                spelert=getTeam2().getDefaultLineUp().getRandomPlayer();
                double temp = Math.random();
                
                if(temp<p1){
                    return new Update(1, spelert, gettCurrent());
                }
                if(temp<p2){
                    return new Update(2, spelert, gettCurrent());
                }
                if(temp<p3){
                    return new Update(3, spelert, gettCurrent());
                }
            }
                   
            return new Update(typ, spelert, min);
        }
        
        public String getScoreMethod(){
           double r = Math.random();
           if(r<0.25){
            return "een prachtige pass van ";
           }
           if(r<0.5){
               return "een mooie actie van ";
           }
           if(r<0.75){
               return ("een goed genomen corner van ");
           }
           return "een scherpe steekpass van ";
        }

        public String LineGenerator(Update update, Team t, Team s){
            String newLine = System.getProperty("line.separator");
            String tab = "\t";
            if(update.getMinuut()==0){
                update.setMinuut(2);
            }
            String result = "";
            switch(update.getType()){
                case 0: break;
                case 1: result = result + update.getMinuut() + "' " + "Gele kaart bij " + t.getTeamName() + "!" 
                            + newLine + tab + update.getSpeler().getPlayerName() + " krijgt geel na een harde " + newLine + tab + "tackle op " + s.getDefaultLineUp().getRandomPlayer().getPlayerName() + "!" + newLine + newLine; 
                        break;
                case 2: result = result + update.getMinuut() + "' " + "Rode kaart bij " + t.getTeamName() + "!" 
                        + newLine + tab + update.getSpeler().getPlayerName() + " krijgt rood na een schandalige overtreding op " + s.getDefaultLineUp().getRandomPlayer().getPlayerName() + "!" + newLine + newLine; 
                        break;
                case 3: result = result + update.getMinuut() + "' " + "Blessure bij " + t.getTeamName() + "!" 
                            + newLine + tab + update.getSpeler().getPlayerName() + " stort dramatisch ten " + newLine + tab + "aarde en kan de volgende wedstrijd " + newLine + tab + "waarschijnlijk niet spelen!" + newLine + newLine; 
                        break;
                case 4: 
                    Player assistMan=new Player();
                    do{
                    assistMan = t.getDefaultLineUp().getRandomPlayer();
                    }while (assistMan.equals(update.getSpeler()));
                    result = result + update.getMinuut() + "' " + "GOAL voor " + t.getTeamName() + "!" + newLine + tab + update.getSpeler().getPlayerName() + " scoort na " + newLine + tab + getScoreMethod() + assistMan.getPlayerName() + "!" + newLine + newLine;
                        break;
            }       
            
            
            return result;
        }
        
        public ArrayList<Update> oneTick(){
            Update updateHome = tickHome();
            Update updateAway = tickAway();
            if(tCurrent>60)
                tCurrent++;
            tCurrent+=6;
            
            switch(updateHome.getType()){
                case 0: break;
                case 1: updateHome.getSpeler().setCardCount(updateHome.getSpeler().getCardCount()+1); break;
                case 2: updateHome.getSpeler().setCardCount(updateHome.getSpeler().getCardCount()+5); break;
                case 3: updateHome.getSpeler().setInjured(1); break;
                case 4: score1++;
            }
            
            switch(updateAway.getType()){
                case 0: break;
                case 1: updateAway.getSpeler().setCardCount(updateAway.getSpeler().getCardCount()+1); break;
                case 2: updateAway.getSpeler().setCardCount(updateAway.getSpeler().getCardCount()+5); break;
                case 3: updateAway.getSpeler().setInjured(1); break;
                case 4: score2++;
            }
            
            ArrayList<Update> updateList = new ArrayList<Update>();
            updateList.add(updateHome);
            updateList.add(updateAway);
            states.StateManager.getLeague().addToLastResultDetailed(updateList.get(0));
            states.StateManager.getLeague().addToLastResultDetailed(updateList.get(1));
            
            
            return updateList;
        }
        
        
    public static Match findOwnMatch(int round){
       Round temp = states.StateManager.getLeague().nextRound("Speelschema.xml",round);
       Team myTeam = states.StateManager.getLeague().getTeamByString(states.StateManager.getLeague().getChosenTeam());
       for(int n=0;n<10;n++){
           if(temp.getMatch(n).getHomeTeam().equals(myTeam)||temp.getMatch(n).getAwayTeam().equals(myTeam)){
               return temp.getMatch(n);
           }
       }
       return new Match();
    }
    
    public static String randomInjury(){
        int aantal = 5;
        double a = Math.random();
        if(a<1/aantal){
            return "Kniebanden verrekt";
        }
        if(a<2/aantal){
            return "Schouder uit de kom";
        }
        if(a<3/aantal){
            return "Gebroken middenvoetsbeentje";
        }
        if(a<4/aantal){
            return "Teen gestoten";
        }
        return "Aanstelleritus";
        
    }
    
    /**
     * @return the tCurrent
     */
    public int gettCurrent() {
        return tCurrent;
    }

    /**
     * @param tCurrent the tCurrent to set
     */
    public void settCurrent(int tCurrent) {
        this.tCurrent = tCurrent;
    }

    /**
     * @return the tMax
     */
    public int gettMax() {
        return tMax;
    }

    /**
     * @param tMax the tMax to set
     */
    public void settMax(int tMax) {
        this.tMax = tMax;
    }

    /**
     * @return the score1
     */
    public int getScore1() {
        return score1;
    }

    /**
     * @param score1 the score1 to set
     */
    public void setScore1(int score1) {
        this.score1 = score1;
    }

    /**
     * @return the score2
     */
    public int getScore2() {
        return score2;
    }

    /**
     * @param score2 the score2 to set
     */
    public void setScore2(int score2) {
        this.score2 = score2;
    }

    /**
     * @return the team1
     */
    public Team getTeam1() {
        return team1;
    }

    /**
     * @param team1 the team1 to set
     */
    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    /**
     * @return the team2
     */
    public Team getTeam2() {
        return team2;
    }

    /**
     * @param team2 the team2 to set
     */
    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    /**
     * @return the t1Offence
     */
    public double getT1Offence() {
        return t1Offence;
    }

    /**
     * @param t1Offence the t1Offence to set
     */
    public void setT1Offence(double t1Offence) {
        this.t1Offence = t1Offence;
    }

    /**
     * @return the t1Defence
     */
    public double getT1Defence() {
        return t1Defence;
    }

    /**
     * @param t1Defence the t1Defence to set
     */
    public void setT1Defence(double t1Defence) {
        this.t1Defence = t1Defence;
    }

    /**
     * @return the t1Endurance
     */
    public double getT1Endurance() {
        return t1Endurance;
    }

    /**
     * @param t1Endurance the t1Endurance to set
     */
    public void setT1Endurance(double t1Endurance) {
        this.t1Endurance = t1Endurance;
    }

    /**
     * @return the t2Offence
     */
    public double getT2Offence() {
        return t2Offence;
    }

    /**
     * @param t2Offence the t2Offence to set
     */
    public void setT2Offence(double t2Offence) {
        this.t2Offence = t2Offence;
    }

    /**
     * @return the t2Defence
     */
    public double getT2Defence() {
        return t2Defence;
    }

    /**
     * @param t2Defence the t2Defence to set
     */
    public void setT2Defence(double t2Defence) {
        this.t2Defence = t2Defence;
    }

    /**
     * @return the t2Endurance
     */
    public double getT2Endurance() {
        return t2Endurance;
    }

    /**
     * @param t2Endurance the t2Endurance to set
     */
    public void setT2Endurance(double t2Endurance) {
        this.t2Endurance = t2Endurance;
    }

    

   
        
}
