/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

/**
 *
 * @author user
 */
public class Exit extends State{
    
    public Exit(){
	
    }
    
    public void createGUI(){
	System.exit(1);
    }
}
