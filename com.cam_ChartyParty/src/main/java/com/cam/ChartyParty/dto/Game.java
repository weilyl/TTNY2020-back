
package com.cam.ChartyParty.dto;

import java.util.Objects;

/**
 *
 * @author chelseamiller
 */
public class Game {

    private int id;
    private int roundscompleted;
    private String winneruserid;
    private String nextjudgeid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoundscompleted() {
        return roundscompleted;
    }

    public void setRoundscompleted(int roundscompleted) {
        this.roundscompleted = roundscompleted;
    }

    public String getWinneruserid() {
        return winneruserid;
    }

    public void setWinneruserid(String winneruserid) {
        this.winneruserid = winneruserid;
    }

    public String getNextjudgeid() {
        return nextjudgeid;
    }

    public void setNextjudgeid(String nextjudgeid) {
        this.nextjudgeid = nextjudgeid;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
        hash = 17 * hash + this.roundscompleted;
        hash = 17 * hash + Objects.hashCode(this.winneruserid);
        hash = 17 * hash + Objects.hashCode(this.nextjudgeid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.roundscompleted != other.roundscompleted) {
            return false;
        }
        if (!Objects.equals(this.winneruserid, other.winneruserid)) {
            return false;
        }
        if (!Objects.equals(this.nextjudgeid, other.nextjudgeid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", roundscompleted=" + roundscompleted + ", winneruserid=" + winneruserid + ", nextjudgeid=" + nextjudgeid + '}';
    }


   
  

}
