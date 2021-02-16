package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelArtistTopTrack (
    @SerializedName("toptracks")
    @Expose
    var toptracks: TopTrack
)