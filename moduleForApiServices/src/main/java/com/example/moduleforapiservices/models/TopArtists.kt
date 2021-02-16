package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TopArtists (
    @SerializedName("artist")
    @Expose
    var artist: List<Artist>
)