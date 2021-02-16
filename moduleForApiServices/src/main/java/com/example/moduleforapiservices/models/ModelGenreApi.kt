package com.example.moduleforapiservices.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ModelGenreApi (
    @SerializedName("toptags")
    @Expose
    var toptags: Toptags
)