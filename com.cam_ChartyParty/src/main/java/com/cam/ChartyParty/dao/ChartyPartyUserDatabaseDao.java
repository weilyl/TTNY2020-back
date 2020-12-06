package com.cam.ChartyParty.dao;

import com.cam.ChartyParty.dao.ChartyPartyYcardDatabaseDao.YcardMapper;
import com.cam.ChartyParty.dto.User;
import com.cam.ChartyParty.dto.Ycard;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chelseamiller
 */
@Repository
public class ChartyPartyUserDatabaseDao implements ChartyPartyUserDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public User getUserById(String userId) {
        try {
            final String SELECT_USER_BY_ID = "SELECT * FROM user "
                    + "WHERE UID = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), userId);
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public User getUserByIdWithCardsForThisGame(String userId, int gameId) {
        try {
            final String SELECT_USER_BY_ID = "SELECT * FROM user "
                    + "WHERE UID = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), userId);

            List<Ycard> cardsForUser = getCardsForUserByUIDAndGameId(userId, gameId);

            user.setHand(cardsForUser);

            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    private List<Ycard> getCardsForUserByUIDAndGameId(String userId, int gameId) throws DataAccessException {
        final String SELECT_CARDS_FOR_USER = "SELECT * FROM gameuserycard guy "
                + "JOIN ycard y on guy.ycard_yCardId = y.yCardId "
                + "WHERE guy.user_UID = ? and guy.game_gameId = ?";
        List<Ycard> cardsForUser = jdbc.query(SELECT_CARDS_FOR_USER, new YcardMapper(), userId, gameId);
        return cardsForUser;
    }

    @Override
    public List<User> getAllUsers() {
        final String SELECT_USERS = "SELECT * FROM user";

        List<User> allUsers = jdbc.query(SELECT_USERS, new UserMapper());

        return allUsers;
    }

    @Override
    public List<User> getAllUsersForGame(int gameId) {
        final String SELECT_USERS_FOR_GAME = "SELECT * FROM user u "
                + "JOIN gameuserycard guy ON u.UID = guy.s "
                + "WHERE guy.game_gameId = ?";

        List<User> allUsersForThisGame = jdbc.query(SELECT_USERS_FOR_GAME, new UserMapper());

        for (User user : allUsersForThisGame) {
            user.setHand(getCardsForUserByUIDAndGameId(user.getId(), gameId));
        }

        return allUsersForThisGame;
    }

    @Override
    public User addUser(User user) {
        final String INSERT_USER = "INSERT INTO user(UID) "
                + "VALUES(?)";
        jdbc.update(INSERT_USER,
                user.getId());

        return user;

    }

    @Override
    @Transactional
    public void updateUser(User user) {
        final String UPDATE_USER = "UPDATE user SET "
                + "username = ? "
                + "WHERE userId = ?";
        jdbc.update(UPDATE_USER,
                user.getUsername(),
                user.getId());
    }

    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setId(rs.getString("UID"));
            user.setUsername(rs.getString("username"));
            return user;
        }
    }
}
