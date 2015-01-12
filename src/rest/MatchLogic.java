package rest;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class MatchLogic extends Thread {

    private int tCurrent;
    private final int tMax;
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
		for (int i=0; i<t.getLineUp().getAanvallers().size(); i++){
			S += t.getLineUp().getAanvallers().get(i).getOffence();
		}
		for (int i=0; i<t.getLineUp().getMiddenvelders().size(); i++){
			S += ( t.getLineUp().getMiddenvelders().get(i).getOffence() )/2;
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
		for (int i=0; i<t.getLineUp().getVerdedigers().size(); i++){
			S += t.getLineUp().getVerdedigers().get(i).getDefence();
		}
		for (int i=0; i<t.getLineUp().getMiddenvelders().size(); i++){
			S += ( t.getLineUp().getMiddenvelders().get(i).getDefence() )/2;
		}
		S += t.getLineUp().getKeeper().getDefence(); 
		
		return S;
	}

	/**
	 * enduranceSum: returns the sum of endurance in lineUp
	 * @param t
	 * @return S
	 */
	public double enduranceSum(Team t){
		double S = 0;
		for (int i=0; i<t.getLineUp().getAanvallers().size(); i++){
			S += t.getLineUp().getAanvallers().get(i).getEndurance();
		}
		for (int i=0; i<t.getLineUp().getVerdedigers().size(); i++){
			S += t.getLineUp().getVerdedigers().get(i).getEndurance();
		}
		for (int i=0; i<t.getLineUp().getMiddenvelders().size(); i++){
			S += t.getLineUp().getMiddenvelders().get(i).getEndurance();
		}
		S += t.getLineUp().getKeeper().getEndurance(); 
		
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
		double b = 0.00015;
		P = (O1 - D2/2)*Math.pow((E1/E2),(t/a))*b;
		
		if (Math.random() < P)
			return true;
		else
			return false;
	}

    
    
    public Update tickHome(){
            int typ =0;
            Player spelert = null;
            int min=tCurrent;
            
             double p1=0.01;
             double p2=0.005;
             double p3=0.02;
            
            
            if(scored(offenceSum(team1), defenceSum(team2), enduranceSum(team1), enduranceSum(team2),tCurrent)){
                int a = (int)(Math.round(Math.random()*3-0.5));
                spelert = team1.getLineUp().getAanvallers().get(a);
                return new Update(4, spelert, tCurrent);
            }
            
            else if(Math.random()>0.9){
                
                spelert=team1.getLineUp().getRandomPlayer();
                double temp = Math.random();
                
                if(temp<p1){
                    return new Update(1, spelert, tCurrent);
                }
                if(temp<p2){
                    return new Update(2, spelert, tCurrent);
                }
                if(temp<p3){
                    return new Update(3, spelert, tCurrent);
                }
            }
                        
            return new Update(typ, spelert, min);
        }
        
        public Update tickAway(){
            int typ =0;
            Player spelert = null;
            int min=tCurrent;
            
             double p1=0.01;
             double p2=0.005;
             double p3=0.02;
            
            
            if(scored(offenceSum(team2), defenceSum(team1), enduranceSum(team2), enduranceSum(team1),tCurrent)){
                int a = (int)(Math.round(Math.random()*3-0.5));
                spelert = team2.getLineUp().getAanvallers().get(a);
                return new Update(4, spelert, tCurrent);
            }
            
            else if(Math.random()>0.9){
                
                spelert=team2.getLineUp().getRandomPlayer();
                double temp = Math.random();
                
                if(temp<p1){
                    return new Update(1, spelert, tCurrent);
                }
                if(temp<p2){
                    return new Update(2, spelert, tCurrent);
                }
                if(temp<p3){
                    return new Update(3, spelert, tCurrent);
                }
            }
                        
            return new Update(typ, spelert, min);
        }

}
