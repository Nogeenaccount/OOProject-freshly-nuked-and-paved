/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;
import java.util.ArrayList;

/**
 *
 * @author Arjan
 */
public class Round {
    private ArrayList<Match> list = new ArrayList<Match>(10);
    
    public Match getMatch(int num){
        return list.get(num);
    }
    
    public boolean contains(Match mat){
        
        for(int n=0;n<list.size();n++){
            if(list.get(n).equals(mat)){
                return true;
            }
        }
        return false;
    }
    
    public void addMatch(Match mat){
        
        list.add(mat);
    }
}
