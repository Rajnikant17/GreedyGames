package com.example.moduleforapiservices.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class ModelAlbumApi (
    @SerializedName("albums")
    @Expose
    var albums: Albums
    )