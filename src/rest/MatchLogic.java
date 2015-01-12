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
    private JLabel text;
    private JLabel updateText;
    private JProgressBar pBar;
    private JButton button;

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
    public MatchLogic(int t, Team t1, Team t2, JLabel txt, JLabel updateTxt, JProgressBar bar, JButton bt) {
	tCurrent = 0;
	tMax = t;
	team1 = t1;
	team2 = t2;
	score1 = 0;
	score2 = 0;

	text = txt;
	updateText = updateTxt;
	pBar = bar;

	button = bt;

	/*		t1Offence =  offenceSum(t1);
	 t1Defence = defenceSum(t1);
	 t1Endurance = enduranceSum(t1);

	 t2Offence = offenceSum(t2);
	 t2Defence = defenceSum(t2);
	 t2Endurance = enduranceSum(t2);*/
    }

    /**
     * scored: return 1 or 0 depending on chance of scoring of the team
     *
     * @param O1
     * @param D2
     * @param E1
     * @param E2
     * @param t
     * @return
     */
    public int scored(double O1, double D2, double E1, double E2, double t) {
	double P;
	double a = 5;
	double b = 0.00015;
	P = (O1 - D2 / 2) * Math.pow((E1 / E2), (t / a)) * b;

	if (Math.random() < P) {
	    return 1;
	} else {
	    return 0;
	}
    }

    /**
     * run: new Thread
     */
    public void run() {

	String html1 = "";
	String html2 = "";
	while (tCurrent <= 90) {
	    if (scored(t1Offence, t2Defence, t1Endurance, t2Endurance, tCurrent) == 1) {
		score1++;

	    };
	    if (scored(t2Offence, t1Defence, t2Endurance, t1Endurance, tCurrent) == 1) {
		score2++;

	    };

	    pBar.setValue(tCurrent);
	    html1 = team1.getTeamName() + " " + score1 + "-" + score2 + " " + team2.getTeamName() + "<br>" + tCurrent + "'<br>";
	    text.setText("<html>" + html1 + "</html>");

	    if (tCurrent % 10 == 0) {
		html2 += "update" + (tCurrent / 10 + 1) + "<br>";
	    }
	    updateText.setText("<html><body><p>" + html2 + "</p></body></html>");

	    tCurrent += 90 / tMax;

	    if (tCurrent == tMax) {
		button.setEnabled(true);
		button.setVisible(true);
	    }

	    try {
		java.lang.Thread.sleep(150);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

}
