package com.cam.ChartyParty.service;

import com.cam.ChartyParty.dao.DataNotFoundException;
import com.cam.ChartyParty.dto.Game;
import com.cam.ChartyParty.dto.Round;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.cam.ChartyParty.dao.ChartyPartyGameDao;
import com.cam.ChartyParty.dao.ChartyPartyRoundDao;
import com.cam.ChartyParty.dao.ChartyPartyRoundDatabaseDao;
import com.cam.ChartyParty.dao.ChartyPartyUserDatabaseDao;
import com.cam.ChartyParty.dto.Score;
import com.cam.ChartyParty.dto.User;
import java.util.UUID;

/**
 *
 * @author chelseamiller
 */
@Service
public class ChartyPartyServiceImpl {

    @Autowired
    ChartyPartyRoundDatabaseDao roundDB;
    
     @Autowired
    ChartyPartyUserDatabaseDao userDB;

    public String generateEntryCode() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid.substring(0, 6);

    }

    public String generateScore(int gameid) throws BadUserInputException, DataNotFoundException {
        List<Round> roundsByGame = roundDB.findRoundsByGameId(gameid);
        List<String> winningUsers = new ArrayList();
        List<User> participants = new ArrayList();
        int highscore= 0;
        String winningUser="";
        int score;
        for (Round i : roundsByGame) {
         winningUsers.add(i.getWinneruserid());
       
        }
        for (String j: winningUsers){
            int count=0;
            for(String k: winningUsers){
                if(j.equals(k)){
                 count++;   
                }
            }
            if(count>0){
           score=count/5*100;
            }
            else{
                score=0;
            }
         User user=userDB.getUserById(j);
         user.setScore(score);
         participants.add(user);
        }
        
        
        for(User l: participants){
          if(highscore < l.getScore())
          {
           highscore = l.getScore();
           winningUser=l.getUsername();
           
        }

    }
        Score results=new Score();
        results.setWinner(winningUser+" is the winner with a score of "+highscore+"!");

}
