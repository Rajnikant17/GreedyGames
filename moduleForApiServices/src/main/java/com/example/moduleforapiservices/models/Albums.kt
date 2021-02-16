package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Albums (
    @SerializedName("album")
    @Expose
    var album: List<Album>
    )