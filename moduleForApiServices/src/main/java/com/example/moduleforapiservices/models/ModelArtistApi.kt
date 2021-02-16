package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelArtistApi (
    @SerializedName("topartists")
    @Expose
    var topartists: TopArtists
)