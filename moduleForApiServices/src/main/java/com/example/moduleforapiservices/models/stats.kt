package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class stats(
    @SerializedName("listeners")
    @Expose
    var listeners: String,
    @SerializedName("playcount")
    @Expose
    var playcount: String
)