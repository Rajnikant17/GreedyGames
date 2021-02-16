package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wiki (
    @SerializedName("summary")
    @Expose
    var description: String
    )