package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Bio (
    @SerializedName("summary")
    @Expose
    var description: String
)