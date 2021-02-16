package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelTrackApi (
    @SerializedName("tracks")
    @Expose
    var tracks: Tracks
)