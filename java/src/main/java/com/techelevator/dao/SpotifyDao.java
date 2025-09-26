package com.techelevator.dao;

import se.michaelthelin.spotify.model_objects.specification.Artist;

import java.util.List;

public interface SpotifyDao {

    void saveArtist(int userId, Artist artist);

    List<Artist> getAllArtistsByUserId(int userId);
}
