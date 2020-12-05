package com.cam.ChartyParty.dto;

import java.util.Objects;


/**
 *
 * @author chelseamiller
 */
public class Round {

    int id;
    int Game_id;
    String timestamp;
    String guess;
    String result;

    public int getGameId() {
        return Game_id;
    }

    public void setGameId(int gameId) {
        this.Game_id = gameId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

  

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;
        hash = 71 * hash + this.Game_id;
        hash = 71 * hash + Objects.hashCode(this.timestamp);
        hash = 71 * hash + Objects.hashCode(this.guess);
        hash = 71 * hash + Objects.hashCode(this.result);
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
        if (this.Game_id != other.Game_id) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Round{" + "id=" + id + ", Game_id=" + Game_id + ", timestamp=" + timestamp + ", guess=" + guess + ", result=" + result + '}';
    }

}
