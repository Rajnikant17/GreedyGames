package com.example.moduleforapiservices.utils

import com.example.moduleforapiservices.models.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("/2.0/")
    suspend fun getGenreApi(@Query("method")topTag:String ,@Query("format")format:String,@Query("api_key")api_key:String) :ModelGenreApi

    @GET("/2.0/")
    suspend fun getAlbumApi(@Query("method")topTag:String ,@Query("tag")tag:String,@Query("format")format:String,@Query("api_key")api_key:String) :ModelAlbumApi

    @GET("/2.0/")
    suspend fun getArtistApi(@Query("method")topTag:String ,@Query("tag")tag:String,@Query("format")format:String,@Query("api_key")api_key:String)  : ModelArtistApi

    @GET("/2.0/")
    suspend fun getTrackApi(@Query("method")topTag:String ,@Query("tag")tag:String,@Query("format")format:String,@Query("api_key")api_key:String) : ModelTrackApi

    @GET("/2.0/")
    suspend fun getAlbumInfoApi(@Query("method")albumInfoTag:String ,@Query("artist")artist:String,@Query("album")album:String,@Query("format")format:String,@Query("api_key")api_key:String) :ModelAlbumInfoApi

    @GET("/2.0/")
    suspend fun getArtistInfoApi(@Query("method")artistInfoTag:String ,@Query("artist")artist:String,@Query("format")format:String,@Query("api_key")api_key:String) :ModelArtistInfoApi

    @GET("/2.0/")
    suspend fun getArtistTopAlbum(@Query("method")artistTopAlbum:String ,@Query("artist")artist:String,@Query("format")format:String,@Query("api_key")api_key:String) :ModelArtistTopAlbum

    @GET("/2.0/")
    suspend fun getArtistTopTrack(@Query("method")artistTopTrack:String ,@Query("artist")artist:String,@Query("format")format:String,@Query("api_key")api_key:String) :ModelArtistTopTrack

    @GET("/2.0/")
    suspend fun getGenreInfo(@Query("method")tagInfo:String ,@Query("tag")artist:String,@Query("format")format:String,@Query("api_key")api_key:String) :ModelGenreInfo
}