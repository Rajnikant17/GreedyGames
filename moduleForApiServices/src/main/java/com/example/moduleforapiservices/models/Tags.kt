package com.example.moduleforapiservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tags(
    @SerializedName("tag")
    @Expose
    var tag: ArrayList<GenreTag>
)