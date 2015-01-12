/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author user
 */
public class LineUpTest {
    LineUp lineup;
    Player p1;
    Player p2;
    Player p3;
    Player p4;
    Player p5;
    
    @Before
    public void before(){
	lineup = new LineUp();
	p1 = new Player("Harry Testema",12,1000000,80,70,60,"verdediger",5,0);
	p2 = new Player("Harry Testema",12,1000000,81,70,60,"verdediger",5,0);
	p3 = new Player("Harry Testema",12,1000000,82,70,60,"verdediger",5,0);
	p4 = new Player("Harry Testema",12,1000000,83,70,60,"verdediger",5,0);
	p5 = new Player("Harry Testema",12,1000000,84,70,60,"verdediger",5,0);
    }
    
    @Test
    public void addTest(){
	assertEquals(lineup.getAanvallers().size(),0);
	lineup.addAanvaller(p1);
	assertEquals(lineup.getAanvallers().size(),1);
	lineup.addAanvaller(p1);
	assertEquals(lineup.getAanvallers().size(),1);
	lineup.addAanvaller(p2);
	lineup.addAanvaller(p3);
	lineup.addAanvaller(p4);
	assertEquals(lineup.getAanvallers().size(),3);
	
	assertEquals(lineup.getMiddenvelders().size(),0);
	lineup.addMiddenvelder(p2);
	assertEquals(lineup.getMiddenvelders().size(),1);
	lineup.addMiddenvelder(p2);
	assertEquals(lineup.getMiddenvelders().size(),1);
	lineup.addMiddenvelder(p1);
	lineup.addMiddenvelder(p3);
	lineup.addMiddenvelder(p4);
	assertEquals(lineup.getMiddenvelders().size(),3);
	
	assertEquals(lineup.getVerdedigers().size(),0);
	lineup.addVerdediger(p3);
	assertEquals(lineup.getVerdedigers().size(),1);
	lineup.addVerdediger(p3);
	assertEquals(lineup.getVerdedigers().size(),1);
	lineup.addVerdediger(p1);
	lineup.addVerdediger(p2);
	lineup.addVerdediger(p4);
	lineup.addVerdediger(p5);
	assertEquals(lineup.getVerdedigers().size(),4);
    }
    
    @Test
    public void setTest(){
	ArrayList<Player> aanvallers = new ArrayList<Player>();
	aanvallers.add(p2);
	aanvallers.add(p3);
	aanvallers.add(p4);
	lineup.setAanvallers(aanvallers);
	assertEquals(lineup.getAanvallers(),aanvallers);
	
	ArrayList<Player> middenvelders = new ArrayList<Player>();
	middenvelders.add(p2);
	middenvelders.add(p3);
	middenvelders.add(p4);
	lineup.setMiddenvelders(middenvelders);
	assertEquals(lineup.getMiddenvelders(),middenvelders);
	
	ArrayList<Player> verdedigers = new ArrayList<Player>();
	verdedigers.add(p2);
	verdedigers.add(p3);
	verdedigers.add(p4);
	lineup.setVerdedigers(verdedigers);
	assertEquals(lineup.getVerdedigers(),verdedigers);

	lineup.setKeeper(p4);
	assertEquals(lineup.getKeeper(),p4);
    }
}
