package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Album(
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("artist")
    @Expose
    var artist: Artist,
    @SerializedName("image")
    @Expose
    var image: List<Image>,
    @SerializedName("tags")
    @Expose
    var tags: Tags,
    @SerializedName("wiki")
    @Expose
    var wiki: Wiki

)
