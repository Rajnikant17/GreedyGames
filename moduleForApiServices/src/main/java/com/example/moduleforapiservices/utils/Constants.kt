package com.example.moduleforapiservices.utils

class Constants {
    companion object
    {
        val BASEURL = "http://ws.audioscrobbler.com"
        val toptageMethod="tag.getTopTags"
        val getTopAlbums="tag.getTopAlbums"
        val getTopArtists="tag.getTopArtists"
        val getTopTracks="tag.getTopTracks"
        val getAlbumInfo="album.getinfo"
        val getArtistInfo="artist.getinfo"
        val getArtistTopAlbum="artist.getTopAlbums"
        val getArtistTopTrack="artist.getTopTracks"
        val getTagInfo="tag.getInfo"
        val format="json"
        val api_key="73916da0c26298c8ada1b82db40ff63c"
    }
}