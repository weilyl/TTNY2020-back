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

/**
 *
 * @author chelseamiller
 */
@Service
public class ChartyPartyServiceImpl implements ChartyPartyService{

    @Autowired
    private ChartyPartyGameDao gameDao;
    
    @Autowired
    private ChartyPartyRoundDao roundDao;

    

    int a;
    int b;
    int c;
    int d;

    @Override
    public Game startNewGame(Game game) {
        return gameDao.add(game);
    }

    @Override
    public Round createRound(Round round) {

        return roundDao.add(round);
    }

    @Override
    public ResponseEntity updateGame(@PathVariable int id, @RequestBody Game game) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (id != game.getId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (gameDao.update(game)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @Override
    public Game getGameById(int id) throws DataNotFoundException{
        Game result = gameDao.findById(id);
        try{
        List<Round> roundsForGame = roundDao.findRoundsByGameId(id);
        result.setRounds(roundsForGame);
        }
        catch(DataNotFoundException ex){
           
        }
       
        return result;
    }

    @Override
    public String getAllGames() throws DataNotFoundException{

        String allGames = "";
        Map<Game, List<Round>> mapofGamesandRounds = new HashMap<Game, List<Round>>();
        List<Game> games = new ArrayList<>();
        List<Round> allRounds = new ArrayList<>();
        try {
            games = gameDao.getAll();
        } catch (NullPointerException e) {
            //  ********************need to program exception to be thrown here*********
        }
        try {
            allRounds = roundDao.getAll();

        } catch (NullPointerException e) {

            //  ********************need to program exception to be thrown here*********
        }
        if (allRounds != null) {
            for (Game game : games) {
                ArrayList<Round> roundsPerGame = new ArrayList<Round>();
                int id = game.getId();

                for (Round round : allRounds) {

                    if (round.getGameId() == id) {
                        roundsPerGame.add(round);
                    }
                }
                mapofGamesandRounds.put(game, roundsPerGame);
            }

            for (Map.Entry<Game, List<Round>> pair : mapofGamesandRounds.entrySet()) {
                Game thisGame = pair.getKey();
                thisGame.setRounds(pair.getValue());

            }

        }
        if (games != null) {

            for (Game i : games) {
                if (i.isStatus() == false) {
                    allGames += "Game ID: " + i.getId() + System.lineSeparator()
                            + "Game in progress: " + i.isStatus() + System.lineSeparator()
                            + "Solution: " + i.getAnswer() + System.lineSeparator()
                            + "Number of guesses: " + i.getRounds().size()
                            + System.lineSeparator();

                } else {
                    List<Round> theseRounds = new ArrayList();
                    try {
                        theseRounds = i.getRounds();
                    } catch (NullPointerException e) {

                    }

                    allGames += "Game ID: " + i.getId() + System.lineSeparator()
                            + "Game in progress: " + i.isStatus() + System.lineSeparator()
                            + "Solution: XXXX" + System.lineSeparator();

                    if (theseRounds != null) {
                        List<Round> someRounds = i.getRounds();
                        for (Round x : someRounds) {
                            allGames += x.getTimestamp() + "      Guess: " + x.getGuess() + " Score: " + x.getResult() + System.lineSeparator();
                        }

                    }

                }
            }
            return allGames;
        } else {
            return "There aren't any games started yet!";
        }
    }

    @Override
    public List<Round> getRoundsByGameId(int gameID) throws DataNotFoundException{
        List<Round> allRounds;

        allRounds = roundDao.findRoundsByGameId(gameID);

        return allRounds;
    }

    @Override
    public String generateTimestamp() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss a");
        LocalDateTime now = LocalDateTime.now();

        String current = dtf.format(now);

        return current;
    }

    @Override
    public String generateAnswer() {
        int min = 0;
        int max = 9;
        String answer;
        a = (int) (Math.random() * (max - min + 1) + min);

        do {
            b = (int) (Math.random() * (max - min + 1) + min);
        } while (b == a);

        do {
            c = (int) (Math.random() * (max - min + 1) + min);
        } while (c == a || c == b);

        do {
            d = (int) (Math.random() * (max - min + 1) + min);
        } while (d == a || d == b || d == c);

        answer = (Integer.toString(a)) + (Integer.toString(b)) + (Integer.toString(c)) + (Integer.toString(d));

        return answer;
    }

    @Override
    public String generateScore(String answer, String guess) throws BadUserInputException{
        try{
            int isInt=Integer.parseInt(guess);
            
        }catch(NumberFormatException e)
        {
            throw new BadUserInputException("Guess must be numerical");
        }
        
        String score;
        int e = 0;
        int p = 0;
        if (answer.equals(guess)) {
            e = 4;
            p = 0;
        } else {
            if (answer.substring(0, 1).equals(guess.substring(0, 1))) {
                e++;
            } else if (guess.contains(answer.substring(0, 1))) {
                p++;
            }
            if (answer.substring(1, 2).equals(guess.substring(1, 2))) {
                e++;
            } else if (guess.contains(answer.substring(1, 2))) {
                p++;
            }
            if (answer.substring(2, 3).equals(guess.substring(2, 3))) {
                e++;
            } else if (guess.contains(answer.substring(2, 3))) {
                p++;
            }
            if (answer.substring(3, 4).equals(guess.substring(3, 4))) {
                e++;
            } else if (guess.contains(answer.substring(3, 4))) {
                p++;
            }

        }
        String exact = Integer.toString(e);
        String partial = Integer.toString(p);
        score = "e:" + exact + ":p:" + partial;
        return score;
    }
    
@Override
    public boolean setGameStatus(String score, Game currentGame) {
        String exactGuessCount = Character.toString(score.charAt(2));
        return !exactGuessCount.equals("4");
    }

    //*******************CRUD functions not needed for game play****************
   
    @Override
    public ResponseEntity<Round> getRoundById(int id) throws DataNotFoundException{
        Round result = roundDao.findById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(result);
    }
    
    @Override
    public List<Round> getAllRoundsById(int id) throws DataNotFoundException{
        List<Round> allRounds;

        allRounds = roundDao.getAll();

        return allRounds;
    }

    @Override
    public ResponseEntity updateRound(@PathVariable int id, @RequestBody Round round) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (id != round.getId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (roundDao.update(round)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @Override
    public ResponseEntity deleteGame(@PathVariable int id) {
        if (gameDao.deleteById(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity deleteRound(@PathVariable int id) {
        if (roundDao.deleteById(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

}
