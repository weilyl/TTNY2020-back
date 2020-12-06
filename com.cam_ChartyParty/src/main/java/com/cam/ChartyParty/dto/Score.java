
package com.cam.ChartyParty.dto;


import java.util.List;


/**
 *
 * @author chelseamiller
 */
public class Score {
    private String winner;
    private List<String> stats;

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<String> getStats() {
        return stats;
    }

    public void setStats(List<String> stats) {
        this.stats = stats;
    }

 
    @Override
    public String toString() {
        return "Score{" + "winner=" + winner + ", stats=" + stats + '}';
    }
    
     
    
}
