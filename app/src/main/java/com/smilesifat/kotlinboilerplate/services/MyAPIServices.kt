package com.smilesifat.kotlinboilerplate.services

import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface MyAPIServices {

    @GET("repositories")
    fun getListings(
        @Query("q") p: String?,
        @Query("sort") sort: String?
    ): Call<JsonObject>?


    @FormUrlEncoded
    @POST("getlistingdetails")
    fun getListingDetails(
        @Header("Content-Type") ContentType: String?,
        @FieldMap data: HashMap<String?, String?>?
    ): Call<ResponseBody?>?

}
