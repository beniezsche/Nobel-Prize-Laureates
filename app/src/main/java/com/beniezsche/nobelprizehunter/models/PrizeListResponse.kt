package com.beniezsche.nobelprizehunter.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PrizeListResponse {

    @SerializedName("prizes")
    @Expose
    var prizes:List<Prize> ?= null
}