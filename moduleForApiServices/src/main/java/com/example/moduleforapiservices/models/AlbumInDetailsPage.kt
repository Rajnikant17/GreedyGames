package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AlbumInDetailsPage(
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("artist")
    @Expose
    var artist: String,
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