/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import rest.League;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout.Constraints;

/**
 *
 * @author user
 */
public class StateManager {

    static State currentState;
    static JFrame guiFrame = new JFrame();
    static League tempLeague = new League("", 0, "", "");
    //public static StateManager statemanager = new StateManager();
    
    public enum States {
	MAIN_MENU, NEW_GAME, TOURNAMENT_VIEW, EXIT;
    }

    public StateManager() {
	currentState = new MenuMain();
	currentState.createGUI();
	currentState.enableButtons();
	guiFrame.add(currentState);
	guiFrame.setSize(1200, 800);
	guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	guiFrame.setTitle("Soccer Manager");
	guiFrame.setVisible(true);
    }

    public static void ChangeState(State requestedState){
	State newState = null;
        newState = requestedState;
	guiFrame.remove(currentState);
	newState.createGUI();
	newState.enableButtons();
	guiFrame.add(newState);
	guiFrame.validate();
	currentState = newState;
    }
    
    public static League getLeague() {
    	return tempLeague;
    }
    
    public static void setLeague(League someLeague) {
    	tempLeague = someLeague;
    }
    
    public static void main(String[] args) {
	StateManager sm = new StateManager();
    }
}