package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelGenreInfo (
    @SerializedName("tag")
    @Expose
    var tag: Tag
    )