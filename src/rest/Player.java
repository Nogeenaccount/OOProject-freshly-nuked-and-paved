package rest;

public class Player {

    /**
     * attributes player
     */
    /**
     * personal attributes
     */
    private String playerName;
    private int shirtNumber;
    private int price;

    private int endurance;	//Related to chance of injury?
    private int offence;
    private int defence;
    private String position; //Possible positions GK,DF,MF,AT

    /**
     * Attributes for availability
     */
    private int cardCount;
    private int injured; // = number of matches unavailable

    /**
     * Player: constructor
     *
     * @param name
     * @param number
     * @param price
     * @param end
     * @param off
     * @param def
     * @param pos
     * @param cc
     * @param injured
     */
    public Player(String name, int number, int price, int end, int off, int def, String pos, int cc, int injured) {
	playerName = name;
	shirtNumber = number;
	this.price = price;
	endurance = end;
	offence = off;
	defence = def;
	position = pos;
	cardCount = cc;
	this.injured = injured;
    }
    
    public Player(){
        
    }

    public boolean equals(Object other){
            if(other instanceof Player){
                Player that = (Player)other;
                if(playerName.equals(that.getPlayerName())){
                    if((offence==that.getOffence()) && (defence == that.getDefence()) && endurance == that.getEndurance()){
                        return true;
                    }
                }
            }
            return false;
        }
    /**
     * toString: turns Player into a printable String
     *
     * @return String
     */
    public String toString() {
	String str = "<Player(";
	str += playerName + ", " + shirtNumber + ", " + price + ", " + endurance + ", " + offence + ", " + defence + ", " + position + ", " + cardCount + ", " + injured;
	str += ")>";

	return str;
    }

    // getters/setters
    public String getPlayerName() {
	return playerName;
    }

    public void setPlayerName(String playerName) {
	this.playerName = playerName;
    }

    public int getShirtNumber() {
	return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
	this.shirtNumber = shirtNumber;
    }

    public int getPrice() {
	return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }

    public int getEndurance() {
	return endurance;
    }

    public void setEndurance(int endurance) {
	this.endurance = endurance;
    }

    public int getOffence() {
	return offence;
    }

    public void setOffence(int offence) {
	this.offence = offence;
    }

    public int getDefence() {
	return defence;
    }

    public void setDefence(int defence) {
	this.defence = defence;
    }

    public String getPosition() {
	return position;
    }

    public void setPosition(String position) {
	this.position = position;
    }

    public int getCardCount() {
	return cardCount;
    }

    public void setCardCount(int cardCount) {
	this.cardCount = cardCount;
    }

    public int getInjured() {
	return injured;
    }

    public void setInjured(int injured) {
	this.injured = injured;
    }

    public int getAttributes() {
	int totaal = this.getEndurance() + this.getOffence() + this.getDefence();
	return totaal;
    }

}
