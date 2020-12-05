
package com.cam.ChartyParty.dto;

import java.util.ArrayList;
import java.util.List;
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
    private List<User> users = new ArrayList();
    

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + this.roundscompleted;
        hash = 47 * hash + Objects.hashCode(this.winneruserid);
        hash = 47 * hash + Objects.hashCode(this.nextjudgeid);
        hash = 47 * hash + Objects.hashCode(this.users);
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
        if (!Objects.equals(this.users, other.users)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", roundscompleted=" + roundscompleted + ", winneruserid=" + winneruserid + ", nextjudgeid=" + nextjudgeid + ", users=" + users + '}';
    }

    
    
    

}
