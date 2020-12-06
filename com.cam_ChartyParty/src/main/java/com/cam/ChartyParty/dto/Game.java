
package com.cam.ChartyParty.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author chelseamiller
 */
public class Game {

    private int id;
    private String entrycode;
    private int roundscompleted;
    private String winneruserid;
    private String nextjudgeid;
    private List<User> users = new ArrayList();
    private Set<Xcard> xcards= new HashSet();
    private long startTime;
    

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

    public String getEntrycode() {
        return entrycode;
    }

    public void setEntrycode(String entrycode) {
        this.entrycode = entrycode;
    }

    public Set<Xcard> getXcards() {
        return xcards;
    }

    public void setXcards(Set<Xcard> xcards) {
        this.xcards = xcards;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.entrycode);
        hash = 29 * hash + this.roundscompleted;
        hash = 29 * hash + Objects.hashCode(this.winneruserid);
        hash = 29 * hash + Objects.hashCode(this.nextjudgeid);
        hash = 29 * hash + Objects.hashCode(this.users);
        hash = 29 * hash + Objects.hashCode(this.xcards);
        hash = 29 * hash + (int) (this.startTime ^ (this.startTime >>> 32));
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
        if (this.startTime != other.startTime) {
            return false;
        }
        if (!Objects.equals(this.entrycode, other.entrycode)) {
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
        if (!Objects.equals(this.xcards, other.xcards)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", entrycode=" + entrycode + ", roundscompleted=" + roundscompleted + ", winneruserid=" + winneruserid + ", nextjudgeid=" + nextjudgeid + ", users=" + users + ", xcards=" + xcards + ", startTime=" + startTime + '}';
    }


    
    


}
