package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Track (
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("artist")
    @Expose
    var artist: Artist,
    @SerializedName("image")
    @Expose
    var image: List<Image>
)