/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import states.StateManager;

/**
 *
 * @author user
 */
public class Launcher {
    static League league = new League("", 0, "", "");

    public static void main(String[] args) {
	StateManager sm = new StateManager();
    }
    
    public static League getLeague() {
    	return league;
    }
    
    public static void setLeague(League someLeague) {
    	league = someLeague;
    }
    
}
