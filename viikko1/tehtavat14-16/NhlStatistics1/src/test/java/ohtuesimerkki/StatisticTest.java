/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Tomas
 */
public class StatisticTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void joukkueenPelaajaMaaraOikein() {
        assertEquals(3, stats.team("EDM").size());
    }
    
    @Test
    public void searchLoytaa() {
        Player player = stats.search("Semenko");
        
        assertTrue(player.getName().equals("Semenko"));
    }
    
    @Test
    public void searchPalauttaTyhjan() {
        
        boolean playerExists = false;
        if(stats.search("R")!=null){
            playerExists = true;
        }
        assertTrue(!playerExists);
    }

    @Test
    public void topScorersPalauttaaOikeanMaaran() {
        stats.topScorers(2);
        
        assertEquals(2, stats.topScorers(2).size());
    }

}
