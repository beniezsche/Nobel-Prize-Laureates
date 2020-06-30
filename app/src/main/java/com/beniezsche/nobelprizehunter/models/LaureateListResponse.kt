package com.beniezsche.nobelprizehunter.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LaureateListResponse {

    @SerializedName("laureates")
    @Expose
    var laureates:List<Laureate> ?= null
}