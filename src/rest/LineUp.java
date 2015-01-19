package rest;

import java.util.ArrayList;

public class LineUp {

    private ArrayList<Player> aanvallers;
    private ArrayList<Player> middenvelders;
    private ArrayList<Player> verdedigers;
    private Player keeper;

    public LineUp() {
	aanvallers = new ArrayList<Player>();
	middenvelders = new ArrayList<Player>();
	verdedigers = new ArrayList<Player>();
    }

     public Player getRandomPlayer(){
            double positie = Math.random();
            double id = Math.random();
            
            if(positie<0.33){
                return verdedigers.get((int)Math.floor(id*3.99));
            }
            
            if(positie<0.66){
                return middenvelders.get((int)Math.floor(id*2.99));
            }
            
            if(positie<0.99){
                return aanvallers.get((int)Math.floor(id*1.99));
            }
            return keeper;
            
            
        }
    /**
     * addAanvaller: add an aanvaller to the lineup
     *
     * @param p Player
     */
    public void addAanvaller(Player p) {
	if ((!aanvallers.contains(p)) && (aanvallers.size() < 3)) {
	    aanvallers.add(p);
	}
    }
    
         public String lineUpToXML(){
            String res = "";
            res += this.getKeeper().getShirtNumber()+", ";
            for(int i = 0; i<4; i++){
                res += this.getVerdedigers().get(i).getShirtNumber()+", ";
            }
               for(int i = 0; i<3; i++){
                res += this.getMiddenvelders().get(i).getShirtNumber()+", ";
            }
                 for(int i = 0; i<3; i++){
                res += this.getAanvallers().get(i).getShirtNumber()+", ";
            }
            res = res.substring(0, res.length()-2);
            return res;
        }

    /**
     * addMiddenvelder: add an middenvelder to the lineup
     *
     * @param p Player
     */
    public void addMiddenvelder(Player p) {
	if ((!middenvelders.contains(p)) && (middenvelders.size() < 3)) {
	    middenvelders.add(p);
	}
    }

    /**
     * addVerdediger: add an verdediger to the lineup
     *
     * @param p Player
     */
    public void addVerdediger(Player p) {
	if ((!verdedigers.contains(p)) && (verdedigers.size() < 4)) {
	    verdedigers.add(p);
	}
    }

        /**
     * equals: tests if two LineUp instances are equal
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof LineUp){
            LineUp L = (LineUp) obj;
            if (this.getAanvallers().equals(L.getAanvallers()) && this.getMiddenvelders().equals(L.getMiddenvelders()) && this.getVerdedigers().equals(L.getVerdedigers()) && this.getKeeper().equals(L.getKeeper()))
                return true;
        }
        return false;
    }

    
    /*
     * GETTERS AND SETTERS
     */
    public ArrayList<Player> getAanvallers() {
	return aanvallers;
    }

    public void setAanvallers(ArrayList<Player> aanvallers) {
	this.aanvallers = aanvallers;
    }

    public ArrayList<Player> getMiddenvelders() {
	return middenvelders;
    }

    public void setMiddenvelders(ArrayList<Player> middenvelders) {
	this.middenvelders = middenvelders;
    }

    public ArrayList<Player> getVerdedigers() {
	return verdedigers;
    }

    public void setVerdedigers(ArrayList<Player> verdedigers) {
	this.verdedigers = verdedigers;
    }

    public Player getKeeper() {
	return keeper;
    }

    public void setKeeper(Player keeper) {
	this.keeper = keeper;
    }

}
