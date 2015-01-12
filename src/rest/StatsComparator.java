package rest;

import java.util.Comparator;

public class StatsComparator implements Comparator<Player> {

    @Override
    public int compare(Player p1, Player p2) {

	if (p1.getAttributes() > p2.getAttributes()) {
	    return -1;
	} else if (p1.getAttributes() < p2.getAttributes()) {
	    return 1;
	} else {
	    return 0;
	}

    }

}
