package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelArtistTopAlbum (
    @SerializedName("topalbums")
    @Expose
    var topalbums: TopAlbums
)