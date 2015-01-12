package rest;

import java.util.Comparator;

public class PriceComparator implements Comparator<Player> {

    public int compare(Player p1, Player p2) {

	if (p1.getPrice() > p2.getPrice()) {
	    return -1;
	} else if (p1.getPrice() < p2.getPrice()) {
	    return 1;
	} else {
	    return 0;
	}

    }

}
