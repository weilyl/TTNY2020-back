package com.cam.ChartyParty.controller;

import com.cam.ChartyParty.dao.DataNotFoundException;
import com.cam.ChartyParty.dto.Game;
import com.cam.ChartyParty.dto.Round;
import com.cam.ChartyParty.service.BadUserInputException;
import com.cam.ChartyParty.service.ChartyPartyServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chelseamiller
 */
@RestController
@RequestMapping("/api/bullsandcows")
public class ChartyPartyController {

    Game currentGame;

    @Autowired
    private ChartyPartyServiceImpl service;


    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public String Begin() {
        currentGame = new Game();
        currentGame.setAnswer(service.generateAnswer());
        currentGame.setStatus(true);
        currentGame = service.startNewGame(currentGame);
        return "Game Id: " + currentGame.getId();
    }

    /*
     @PostMapping("/guess")
     public Round itWorks(@RequestBody Round round){
         return round;
     }
    
     */
    @PostMapping("/guess")
    public String Guess(@RequestBody Round round) throws DataNotFoundException, BadUserInputException {
        String score;
        String result;
        List<Round> allRounds = new ArrayList<Round>();

        int id = round.getGameId();
        String guess = round.getGuess();

        currentGame  = service.getGameById(id);
        

        String timestamp = service.generateTimestamp();

        score = service.generateScore(currentGame.getAnswer(), guess);
        round.setResult(score);
        round.setTimestamp(timestamp);
        round = service.createRound(round);

        try {
            allRounds = currentGame.getRounds();
        } catch (NullPointerException e) {
        }

        if (allRounds == null) {
            allRounds = new ArrayList<>();
        }
        allRounds.add(round);

        currentGame.setRounds(allRounds);
        currentGame.setStatus(service.setGameStatus(score, currentGame));
        service.updateGame(id, currentGame);
        if (currentGame.isStatus() == false) {
            if (currentGame.getRounds().size() == 1) {
                result = score + System.lineSeparator() + "Congratulations you found the solution in 1 guess!";
            } else {
                result = score + System.lineSeparator() + "Congratulations you found the solution in "
                        + currentGame.getRounds().size()
                        + " guesses!";
            }
            return result;

        } else {
            result = ("Guess # " + currentGame.getRounds().size() + " score:"
                    + System.lineSeparator()
                    + score);
            return result;
        }
    }

    @GetMapping("/game")
    public String getAllGames() throws DataNotFoundException{
        return service.getAllGames();
    }

    @GetMapping("/game/{id}")
    public Game findById(@PathVariable int id) throws DataNotFoundException{
       
     
            Game result = service.getGameById(id);
            
      
       
            return result;
        
    }

    //*******************needs exception for null id
    @GetMapping("/rounds/{id}")
    public List<Round> listRoundsbyId(@PathVariable int id) throws DataNotFoundException{
        List<Round> roundByGameId;
        roundByGameId = service.getRoundsByGameId(id);
        return roundByGameId;
    }

    //********************CRUD methods not used in game play************************  
    @PutMapping("/editgame/{id}")
    public ResponseEntity updateGame(@PathVariable int id, @RequestBody Game game) {

        return service.updateGame(id, game);
    }

    @PutMapping("/editRound/{id}")
    public ResponseEntity updateRound(@PathVariable int id, @RequestBody Round round) {

        return service.updateRound(id, round);
    }

    @DeleteMapping("/deletegame={id}")
    public ResponseEntity delete(@PathVariable int id) {

        return service.deleteGame(id);
    }

    @DeleteMapping("/deleteround={id}")
    public ResponseEntity deleteRound(@PathVariable int id) {

        return service.deleteRound(id);
    }
}
