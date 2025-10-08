package com.techelevator.service;

import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumsTracksRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsAlbumsRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;

import java.io.FileInputStream;
import java.net.URI;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


@Service
public class SpotifyService {

    //private final String API_URL = "https://api.spotify.com/v1/";
    private String CLIENT_ID;
    private String CLIENT_SECRET;


    private SpotifyApi spotifyApi;
//    = new SpotifyApi.Builder()
//            .setClientId(CLIENT_ID)
//            .setClientSecret(CLIENT_SECRET)
//            .setRedirectUri(URI.create("http://localhost:9000/login"))
//            .build();

    private ClientCredentialsRequest CLIENT_CREDENTIAL_REQUEST; // =
    //spotifyApi.clientCredentials().build();

    public SpotifyService() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(".env")){
            properties.load(fis);
            CLIENT_ID = properties.getProperty("spotify.client.id");
            CLIENT_SECRET = properties.getProperty("spotify.client.secret");
            spotifyApi = new SpotifyApi.Builder()
                    .setClientId(CLIENT_ID)
                    .setClientSecret(CLIENT_SECRET)
                    .setRedirectUri(URI.create("http://localhost:9000/login"))
                    .build();
            CLIENT_CREDENTIAL_REQUEST =
                    spotifyApi.clientCredentials().build();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        try {
            ClientCredentials credentials =
                    CLIENT_CREDENTIAL_REQUEST.execute();
            spotifyApi.setAccessToken(credentials.getAccessToken());
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            System.out.println("Error getting client credentials");
            System.exit(1); // end the program gracefully
        }

    }

    public Artist getArtist(String artistName) {
        System.out.println(artistName);
        SearchArtistsRequest searchArtistsRequest =
                spotifyApi.searchArtists(artistName).build();
        try {
            final Paging<Artist> artistPaging = searchArtistsRequest.execute();
            return artistPaging.getItems()[0];
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    public Track[] getArtistTracks(String artistId) {


        GetArtistsTopTracksRequest getArtistsTopTracksRequest = spotifyApi
                .getArtistsTopTracks(artistId, CountryCode.US)
                .build();

        try {
            final Track[] tracks = getArtistsTopTracksRequest.execute();

            System.out.println("Length: " + tracks.length);
            return tracks;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public AlbumSimplified[] getAlbumsByArtistId(String artistId){

        GetArtistsAlbumsRequest getArtistsAlbumsRequest =
                spotifyApi.getArtistsAlbums(artistId)
                        .build();
        try {
            Paging<AlbumSimplified> albumSimplifiedPaging =
                    getArtistsAlbumsRequest.execute();

            return albumSimplifiedPaging.getItems();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public TrackSimplified[] getTracksByAlbumId(String albumId){

        GetAlbumsTracksRequest getAlbumsTracksRequest =
                spotifyApi.getAlbumsTracks(albumId)
                        .build();

        try{
            Paging<TrackSimplified> trackSimplifiedPaging =
                    getAlbumsTracksRequest.execute();

            return trackSimplifiedPaging.getItems();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}