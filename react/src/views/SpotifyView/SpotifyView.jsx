import { useState } from "react";
import SpotifyService from "../../services/SpotifyService";
import AlbumComponent from "../../components/AlbumComponent/AlbumComponent";
import TrackListComponent from "../../components/TrackListComponent/TrackListComponent";

export default function SpotifyView(){
    const [artistName, setArtistName] = useState('');
    const [artist, setArtist] = useState(null);
    const [albums, setAlbums] = useState(null);
    const [tracks, setTracks] = useState(null);
    const [displayTracks, setDisplayTracks] = useState(false);
    const [displayAlbums, setDisplayAlbums] = useState(false);
    const [albumArt, setAlbumArt] = useState(null);

    function handleChange(event) {
        setArtistName(event.target.value);
        setDisplayAlbums(false);
        setDisplayTracks(false);
    }

    function getArtistInfo() {
        SpotifyService.getArtist(artistName)
            .then((response) => {
                // console.log(response);
                setArtist(response.data);
            })
    }

    function getAlbumInfo() {
        SpotifyService.getAlbums(artist.id)
        .then((response) => {
            // console.log(response);
            setAlbums(response.data);
            setDisplayAlbums(true);
            setDisplayTracks(false);
        })
    }

    function getAlbumTrackInfo(albumId, albumArt){
        SpotifyService.getTracksByAlbumId(albumId)
        .then((response) => {
            console.log(response);
            setTracks(response.data);
            setAlbumArt(albumArt);
            setDisplayAlbums(false);
            setDisplayTracks(true);
        })
    }

    return (
        <>
            <h2>This is my Spotify Page!</h2>
            <label htmlFor="artistName">Artist Name:</label>
            <input type="text" value={artistName} onChange={handleChange} />
            <button onClick={getArtistInfo}>Get Info!</button>
            <div>
                {artist && (
                    <>
                        <h3>{artist.name}</h3>
                        <h4>Number of Followers: {artist.followers.total}</h4>
                        <br />
                        <button onClick={getAlbumInfo}>Get Albums!</button>
                        { albums && displayAlbums && (
                            <AlbumComponent albums={albums} handleAlbumClick={getAlbumTrackInfo}/>
                        )}
                        { displayTracks &&(
                            <TrackListComponent tracks={tracks} albumArt={albumArt}/>
                        )}
                    </>
                )}
            </div>
        </>
    )



}