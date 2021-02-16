package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tracks (
    @SerializedName("track")
    @Expose
    var track: List<Track>
)