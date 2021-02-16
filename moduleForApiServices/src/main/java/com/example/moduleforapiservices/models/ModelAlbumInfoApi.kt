package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelAlbumInfoApi (
    @SerializedName("album")
    @Expose
     var album: AlbumInDetailsPage
)