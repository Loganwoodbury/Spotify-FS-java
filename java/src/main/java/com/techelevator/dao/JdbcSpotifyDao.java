package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import se.michaelthelin.spotify.model_objects.specification.Artist;

import javax.sql.DataSource;
import java.util.List;

public class JdbcSpotifyDao implements SpotifyDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcSpotifyDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveArtist(int userId, Artist artist) {

        String sql = "INSERT INTO artist " +
                "(artist_name, followers, artist_spotify_id) " +
                "VALUES (?, ?, ?) RETURNING artist_id";
        int artistId = jdbcTemplate.queryForObject(sql, int.class,
                artist.getName(), artist.getFollowers().getTotal(), artist.getId());
        sql = "INSERT INTO artist_users(user_id, artist_id VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, artistId);
    }

    @Override
    public List<Artist> getAllArtistsByUserId(int userId) {
        return List.of();
    }

}
