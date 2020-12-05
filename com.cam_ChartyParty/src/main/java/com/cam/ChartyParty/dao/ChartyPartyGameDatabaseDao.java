package com.cam.ChartyParty.dao;

import com.cam.ChartyParty.dto.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chelseamiller
 */
@Repository
@Profile("database")
public class ChartyPartyGameDatabaseDao implements ChartyPartyGameDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Game add(Game game) {
        final String sql = "INSERT INTO Game(Answer, Status) VALUES(?,?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getAnswer());
            statement.setBoolean(2, game.isStatus());
            return statement;

        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public List<Game> getAll() throws DataNotFoundException {
        final String sql = "SELECT g.id, g.Answer, g.Status FROM Game g;";
        List<Game> allGames = jdbcTemplate.query(sql, new GameMapper());

        if (allGames.isEmpty()) {
            throw new DataNotFoundException("No games have been created yet.");
        }
        return allGames;
    }

    @Override
    public Game findById(int id) throws DataNotFoundException {
        Game game = null;

        final String sql = "SELECT id, Answer, Status "
                + "FROM Game WHERE id = ?;";
        try {

            game = jdbcTemplate.queryForObject(sql, new GameMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new DataNotFoundException("Game with ID# " + id + " does not exist.");
        }

        return game;
    }

    @Override
    public boolean update(Game game) {
        final String sql = "UPDATE Game SET "
                + "Answer = ?, "
                + "Status = ? "
                + "WHERE id = ?;";

        return jdbcTemplate.update(sql,
                game.getAnswer(),
                game.isStatus(),
                game.getId()) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        final String sql = "DELETE FROM Game WHERE id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("id"));
            game.setAnswer(rs.getString("Answer"));
            game.setStatus(rs.getBoolean("Status"));

            return game;
        }

    }
}
