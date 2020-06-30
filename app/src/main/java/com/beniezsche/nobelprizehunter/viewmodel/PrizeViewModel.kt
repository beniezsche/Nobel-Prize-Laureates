package com.beniezsche.nobelprizehunter.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.beniezsche.nobelprizehunter.database.AppDatabase
import com.beniezsche.nobelprizehunter.models.LaureateListResponse
import com.beniezsche.nobelprizehunter.models.PrizeListResponse
import com.beniezsche.nobelprizehunter.networking.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrizeViewModel: ViewModel() {

    var retrofitClient = RetrofitClient.getClient()

    fun getPrizeListData(category: String, year: String): MutableLiveData<PrizeListResponse>{

        var data = MutableLiveData<PrizeListResponse>()


        retrofitClient!!.getPrizeList(category, year).enqueue(object : Callback<PrizeListResponse>{
            override fun onFailure(call: Call<PrizeListResponse>, t: Throwable?) {
                data.value = null
            }

            override fun onResponse(
                call: Call<PrizeListResponse>,
                response: Response<PrizeListResponse>
            ) {
                if (response.isSuccessful){

                    data.value = response.body()
                }
            }


        })

        return data

    }

    fun getLaureateList(): MutableLiveData<LaureateListResponse>{

        var data = MutableLiveData<LaureateListResponse>()


        retrofitClient!!.getLaureateList().enqueue(object : Callback<LaureateListResponse>{
            override fun onFailure(call: Call<LaureateListResponse>, t: Throwable?) {
                Log.d("LaureateResponse","failed")
                data.value = null
            }

            override fun onResponse(
                call: Call<LaureateListResponse>,
                response: Response<LaureateListResponse>
            ) {
                if (response.isSuccessful){

                    data.value = response.body()

                    Log.d("LaureateResponse",Gson().toJson(response.body()))
                }
            }
        })
        return data
    }

}