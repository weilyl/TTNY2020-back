package com.cam.ChartyParty.controller;

import com.cam.ChartyParty.dao.ChartyPartyGameDao;
import com.cam.ChartyParty.dao.ChartyPartyOperationDao;
import com.cam.ChartyParty.dao.ChartyPartyRoundDao;
import com.cam.ChartyParty.dao.ChartyPartyUserDao;
import com.cam.ChartyParty.dao.ChartyPartyXcardDao;
import com.cam.ChartyParty.dao.ChartyPartyYcardDao;
import com.cam.ChartyParty.dto.Game;
import com.cam.ChartyParty.dto.Round;
import com.cam.ChartyParty.dto.User;
import com.cam.ChartyParty.dto.Ycard;
import com.cam.ChartyParty.service.ChartyPartyServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chelseamiller
 */
@RestController
@RequestMapping("/api")
public class ChartyPartyController {

    Game currentGame = new Game();
    int numberOfRoundsInGame = 5;

    @Autowired
    ChartyPartyServiceImpl service;

    @Autowired
    ChartyPartyGameDao gameDao;

    @Autowired
    ChartyPartyOperationDao operationDao;

    @Autowired
    ChartyPartyRoundDao roundDao;

    @Autowired
    ChartyPartyUserDao userDao;

    @Autowired
    ChartyPartyXcardDao xCardDao;

    @Autowired
    ChartyPartyYcardDao yCardDao;

    @PostMapping("/createNewGameRoom")
    @ResponseStatus(HttpStatus.CREATED)
    public Game generateNewGameRoom() {
        //generate the code and create the new game in the database
        //make the currentGame = this game just added to the DB
        String entrycode = service.generateEntryCode();
        currentGame.setEntrycode(entrycode);
        currentGame = gameDao.add(currentGame);
        return currentGame;
    }

    @PostMapping("/createNewUser/{gameId}")
    public User addNewUser(@RequestBody User user, HttpServletResponse response) throws IOException {
        //this method will add the user to the user table
        //Then it will get the users of the current game and add this user to that list
        //then add it back to the game
        //if the number of users in the game is >3,
        //we can return a certain http status code?
        User addedUser = userDao.addUser(user);
        List<User> usersOfCurrentGame = currentGame.getUsers();
        usersOfCurrentGame.add(addedUser);
        currentGame.setUsers(usersOfCurrentGame);
        if (usersOfCurrentGame.size() > 2) {
            response.setStatus(200);
        } else {
            response.sendError(418, "User Added to Game. Game is not ready to start");
        }

        return new User();
    }

    @PostMapping("/startGame/{gameId}")
    public Game startGame() {
        //get all the data needed to run the operationDao method
        //also assign the 5 xcards to this game object.
        //verify that the gameId sent in the URL is the same as what is saved

        //need:
        //list of all UIDs
        //gameId
        //list of all Ycards
        //assign judge //FRONTEND, keep  info
        //FRONTEND will need to call on start round
        return currentGame;
    }

    @PostMapping("/startRound/{gameId}/{UID}")
    public Round startARound() {
        //this method will create a round object
        //it will assign one of the x cards to it
        //the x card will be removed from the list for the currentGame
        //then for the user, it will return all of their current cards so they can see them
        //if the user is the judge that round, they will only get one card less than everyone else
        //FRONTEND will need to call on getCardsForUser() after calling on this .
        return new Round();
    }

    @GetMapping("/startRound/{gameId}/{UID}/cards")
    public List<Ycard> getCurrentCardsForUser() {
        //check to see if the user is the current judge. 
        //if so, remove a random card from them. 
        //else, return the list of cards for that user
        //based on the triple table. 
        //FRONTEND, will need to check to see if the user has one less card than everyone else?
        //FRONTEND, is is possible to keep track of the number of rounds that have passed?
        //FRONTEND, if so, then you could just see if the user has 5 - rounds - 1 cards. 
        return new ArrayList<Ycard>();
    }

    @PostMapping("/submitRound/{gameId}/{UID}/{yCardId}")
    public void submitRound() {
        //this method will take in the gameId, the UserId, and the ycardId
        //It will then store this, WHERE?!?!?!?!
        //
    }

    @PostMapping("/endGame")
    public void clearCurrentGame() {
        currentGame = new Game();
    }

}
