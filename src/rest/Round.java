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
   /**
    * Method to get the team the chosenteam is playing against
    * @param team your team
    * @return the opponent, either home or away
    */
  public Team getOpponent(Team team){
      Team opponent = new Team("","",0);
      for(int i = 0; i< 10; i++){
          if(list.get(i).getHomeTeam() == team){
              opponent = list.get(i).getHomeTeam();
          }else if(list.get(i).getAwayTeam() == team){
              opponent = list.get(i).getAwayTeam();
          }
      }
      return opponent;
  }
    
    public void addMatch(Match mat){
        list.add(mat);
    }
    
    
}
