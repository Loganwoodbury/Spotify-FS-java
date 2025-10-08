package com.techelevator.controller;

import com.techelevator.service.SpotifyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/spotify")
public class SpotifyController {

    SpotifyService service;

    public SpotifyController(SpotifyService service) {
        this.service = service;
    }

    @RequestMapping(path="/{artistName}", method = RequestMethod.GET)
    public Artist getArtistByArtistName(@PathVariable String artistName){
        return service.getArtist(artistName);
    }

    @GetMapping("/albums/{artistId}")
    public AlbumSimplified[] getAlbumsByArtistId(@PathVariable String artistId){
        return service.getAlbumsByArtistId(artistId);
    }

    @GetMapping(path="/albums/{albumId}/tracks")
    public TrackSimplified[] getTracksByAlbumId(@PathVariable String albumId){
        return service.getTracksByAlbumId(albumId);
    }
}
