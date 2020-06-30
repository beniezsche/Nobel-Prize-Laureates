package com.beniezsche.nobelprizehunter.networking

import com.beniezsche.nobelprizehunter.models.LaureateListResponse
import com.beniezsche.nobelprizehunter.models.PrizeListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DataService {

    @GET("/v1/prize.json")
    fun getPrizeList(@Query("category") category: String, @Query("year") year:String): Call<PrizeListResponse>

    @GET("/v1/laureate.json")
    fun getLaureateList(): Call<LaureateListResponse>


}