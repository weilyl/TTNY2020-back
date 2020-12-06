package com.cam.ChartyParty.dao;

import com.cam.ChartyParty.dto.User;
import java.util.List;

/**
 *
 * @author chelseamiller
 */
public interface ChartyPartyUserDao {

    public User getUserById(String userId);

    public User getUserByIdWithCardsForThisGame(String userId, int gameId);

    public List<User> getAllUsers();

    public List<User> getAllUsersForGame(int gameId);

    public User addUser(User user);

    public void updateUser(User user);
}
