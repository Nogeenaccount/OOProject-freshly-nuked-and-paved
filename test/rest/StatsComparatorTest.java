/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class StatsComparatorTest {

    
    Player player1 = new Player("Harry Testema",12,1000000,80,70,60,"verdediger",5,0);
    Player player2 = new Player("Harry Testema",12,1000000,82,70,60,"verdediger",5,0);
    Player player3 = new Player("Harry Testema",12,1000000,80,73,60,"verdediger",5,0);
    Player player4 = new Player("Harry Testema",12,1000000,80,70,64,"verdediger",5,0);
    
    @Before
    public void setUp() {
    }


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void comparatorTest() {
	StatsComparator sc = new StatsComparator();
	assertEquals(sc.compare(player1, player1),0);
	assertEquals(sc.compare(player2, player1),-1);
	assertEquals(sc.compare(player1, player2),1);
    }
}
