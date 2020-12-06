package com.cam.ChartyParty.dto;

import java.time.LocalDateTime;
import java.util.Objects;


/**
 *
 * @author chelseamiller
 */
public class Round {

    private int id;
    private int gameid;
    private String winneruserid;
    private int xcardid;
    private int winnerycardid;
    private LocalDateTime endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public String getWinneruserid() {
        return winneruserid;
    }

    public void setWinneruserid(String winneruserid) {
        this.winneruserid = winneruserid;
    }

    public int getXcardid() {
        return xcardid;
    }

    public void setXcardid(int xcardid) {
        this.xcardid = xcardid;
    }

    public int getWinnerycardid() {
        return winnerycardid;
    }

    public void setWinnerycardid(int winnerycardid) {
        this.winnerycardid = winnerycardid;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + this.gameid;
        hash = 89 * hash + Objects.hashCode(this.winneruserid);
        hash = 89 * hash + this.xcardid;
        hash = 89 * hash + this.winnerycardid;
        hash = 89 * hash + Objects.hashCode(this.endTime);
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
        final Round other = (Round) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.gameid != other.gameid) {
            return false;
        }
        if (this.xcardid != other.xcardid) {
            return false;
        }
        if (this.winnerycardid != other.winnerycardid) {
            return false;
        }
        if (!Objects.equals(this.winneruserid, other.winneruserid)) {
            return false;
        }
        if (!Objects.equals(this.endTime, other.endTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Round{" + "id=" + id + ", gameid=" + gameid + ", winneruserid=" + winneruserid + ", xcardid=" + xcardid + ", winnerycardid=" + winnerycardid + ", endTime=" + endTime + '}';
    }

    
    
    

}
