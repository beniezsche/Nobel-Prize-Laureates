package com.beniezsche.nobelprizehunter.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Laureate {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("firstname")
    @Expose
    var firstname: String?= null
    @SerializedName("motivation")
    @Expose
    var motivation: String ?= null
    @SerializedName("surname")
    @Expose
    var surname: String?= null
    @SerializedName("prizes")
    @Expose
    var prizes: List<Prize>?= null


}