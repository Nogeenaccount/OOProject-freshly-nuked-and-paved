package rest;

public class MatchResult {

    /**
     * offenceSum: returns the sum of offence in lineUp
     *
     * @param t
     * @return S
     */
    public static double offenceSum(Team t) {
	double S = 0;
	for (int i = 0; i < t.getLineUp().getAanvallers().size(); i++) {
	    S += t.getLineUp().getAanvallers().get(i).getOffence();
	}
	for (int i = 0; i < t.getLineUp().getMiddenvelders().size(); i++) {
	    S += (t.getLineUp().getMiddenvelders().get(i).getOffence()) / 2;
	}

	return S;
    }

    /**
     * defenceSum: returns the sum of defence in lineUp
     *
     * @param t
     * @return S
     */
    public static double defenceSum(Team t) {
	double S = 0;
	for (int i = 0; i < t.getLineUp().getVerdedigers().size(); i++) {
	    S += t.getLineUp().getVerdedigers().get(i).getDefence();
	}
	for (int i = 0; i < t.getLineUp().getMiddenvelders().size(); i++) {
	    S += (t.getLineUp().getMiddenvelders().get(i).getDefence()) / 2;
	}
	S += t.getLineUp().getKeeper().getDefence();

	return S;
    }

    /**
     * enduranceSum: returns the sum of endurance in lineUp
     *
     * @param t
     * @return S
     */
    public static double enduranceSum(Team t) {
	double S = 0;
	for (int i = 0; i < t.getLineUp().getAanvallers().size(); i++) {
	    S += t.getLineUp().getAanvallers().get(i).getEndurance();
	}
	for (int i = 0; i < t.getLineUp().getVerdedigers().size(); i++) {
	    S += t.getLineUp().getVerdedigers().get(i).getEndurance();
	}
	for (int i = 0; i < t.getLineUp().getMiddenvelders().size(); i++) {
	    S += t.getLineUp().getMiddenvelders().get(i).getEndurance();
	}
	S += t.getLineUp().getKeeper().getEndurance();

	return S;
    }

    /**
     * scored: return 1 or 0 depending on chance of scoring of the team
     *
     * @param O1 off
     * @param D2 def
     * @param E1 end
     * @param E2 end
     * @param t time
     * @return
     */
    public static int scored(double O1, double D2, double E1, double E2, double t) {
	double P;
	double a = 20;
	double b = 0.0002;
	P = (O1 - D2 / 2) * Math.pow((E1 / E2), (t / a)) * b;

	if (Math.random() < P) {
	    return 1;
	} else {
	    return 0;
	}
    }

    public static String getResult(Team t1, Team t2, int time) {
	int t1Result = 0;
	int t2Result = 0;

	for (int i = 0; i < time; i++) {
	    t1Result += scored(offenceSum(t1), defenceSum(t2), enduranceSum(t1), enduranceSum(t2), time);
	    t2Result += scored(offenceSum(t2), defenceSum(t1), enduranceSum(t2), enduranceSum(t1), time);
	}

	String str = t1Result + " - " + t2Result;

	return str;
    }
}
