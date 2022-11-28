package com.grand.kotlinnews.retrofit

import com.grand.kotlinnews.data.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface Api {
  @GET("/r/kotlin/.json")
    fun calldata(): Call<DataModel>

}