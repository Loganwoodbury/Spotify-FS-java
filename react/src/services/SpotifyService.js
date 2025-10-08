import axios from "axios";

export default {
    getArtist(artistName){
        return axios.get(`/spotify/${artistName}`)
    },

    getAlbums(artistId){
        return axios.get(`spotify/albums/${artistId}`)
    },

    getTracksByAlbumId(albumId){
        return axios.get(`/spotify/albums/${albumId}/tracks`)
    }
}