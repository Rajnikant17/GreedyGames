package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TopTrack (
    @SerializedName("track")
    @Expose
    var track: ArrayList<Track>
)