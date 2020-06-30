package com.beniezsche.nobelprizehunter.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class Prize() {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "year")
    @SerializedName("year")
    @Expose
    var year: Int ?= null
    @ColumnInfo(name = "category")
    @SerializedName("category")
    @Expose
    var category: String ?= null
    @SerializedName("laureates")
    @Expose
    var laureates: List<Laureate> ?= null


}