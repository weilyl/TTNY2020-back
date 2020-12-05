
package com.cam.ChartyParty.dto;

/**
 *
 * @author chelseamiller
 */
public class Game {

    int id;
    String answer;
    boolean status;
   List <Round> rounds;
    private int id;
    private int roundscompleted;
    private int winneruserid;
    private int nextjudgeid;

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

    public int getWinneruserid() {
        return winneruserid;
    }

    public void setWinneruserid(int winneruserid) {
        this.winneruserid = winneruserid;
    }

    public int getNextjudgeid() {
        return nextjudgeid;
    }

    public void setNextjudgeid(int nextjudgeid) {
        this.nextjudgeid = nextjudgeid;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
        hash = 17 * hash + this.roundscompleted;
        hash = 17 * hash + this.winneruserid;
        hash = 17 * hash + this.nextjudgeid;
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
        if (this.status != other.status) {
        if (this.roundscompleted != other.roundscompleted) {
            return false;
        }
        if (this.winneruserid != other.winneruserid) {
            return false;
        }
        if (!Objects.equals(this.rounds, other.rounds)) {
        if (this.nextjudgeid != other.nextjudgeid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", answer=" + answer + ", status=" + status + ", rounds=" + rounds + '}';
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
        return "Game{" + "id=" + id + ", roundscompleted=" + roundscompleted + ", winneruserid=" + winneruserid + ", nextjudgeid=" + nextjudgeid + '}';
    }

   
  

}
