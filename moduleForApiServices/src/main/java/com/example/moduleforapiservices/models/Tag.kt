package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tag (
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("wiki")
    @Expose
    var wiki: Wiki
)