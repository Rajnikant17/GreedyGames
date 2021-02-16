package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
class GenreTag (
    @SerializedName("name")
    @Expose
    var name: String ,
    @SerializedName("count")
    @Expose
    var count: Int ,
    @SerializedName("reach")
    @Expose
    var reach: Int
)