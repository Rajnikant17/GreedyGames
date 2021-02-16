package com.example.moduleforapiservices.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Toptags (
    @SerializedName("tag")
    @Expose
     var tag: ArrayList<GenreTag>
)