package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Artist(
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("mbid")
    @Expose
    var mbid: String,
    @SerializedName("url")
    @Expose
    var url: String,
    @SerializedName("image")
    @Expose
    var image: List<Image>,
    @SerializedName("stats")
    @Expose
    var stats: stats,
    @SerializedName("bio")
    @Expose
    var bio: Bio,
    @SerializedName("tags")
    @Expose
    var tags: Tags
)