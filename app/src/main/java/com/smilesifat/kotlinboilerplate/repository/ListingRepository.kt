package com.smilesifat.kotlinboilerplate.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.smilesifat.kotlinboilerplate.model.ListModel
import com.smilesifat.kotlinboilerplate.services.MyAPIServices
import com.smilesifat.kotlinboilerplate.services.RetrofitClientInstance
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
@Suppress("NAME_SHADOWING")
class ListingRepository {

    @Inject
    private val apiService: MyAPIServices = RetrofitClientInstance.retrofitInstance?.create(MyAPIServices::class.java)!!
    var listModels = ArrayList<ListModel>()

    val listings: LiveData<ArrayList<ListModel>>
        get() {
            val data = MutableLiveData<ArrayList<ListModel>>()
            val call: Call<JsonObject>? = apiService.getListings("android", "stars")
            call?.enqueue(object : Callback<JsonObject?> {
                override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                    if (response.isSuccessful) {
                        val jsonObject = response.body()
                        if (jsonObject != null) {
                            var convertJSON: JSONObject? = null
                            try {
                                convertJSON = JSONObject(jsonObject.toString())
                                Log.d("JSONObject Responded", convertJSON.toString())
                                var jsonArray: JSONArray? = null
                                jsonArray = convertJSON.optJSONArray("items")

                                listModels.clear()

                                for (i in 0 until jsonArray!!.length()) {
                                    val jsonObject = jsonArray.optJSONObject(i)
                                    try {
                                        val ID = jsonObject.getInt("id")
                                        val name = jsonObject.getString("name")
                                        val full_name = jsonObject.getString("full_name")
                                        val description = jsonObject.getString("description")
                                        val url = jsonObject.getString("url")
                                        val created_at = jsonObject.getString("created_at")
                                        val language = jsonObject.getString("language")
                                        val owner = jsonObject.getJSONObject("owner")
                                        val avatar = owner.getString("avatar_url")
                                        listModels.add(
                                            ListModel(
                                                ID,
                                                name,
                                                full_name,
                                                description,
                                                url,
                                                created_at,
                                                language,
                                                avatar,
                                            )
                                        )
                                    } catch (e: JSONException) {
                                        throw RuntimeException(e)
                                    }
                                }
                            } catch (e: JSONException) {
                                throw RuntimeException(e)
                            }
                        }
                        data.value = listModels
                    }
                }

                override fun onFailure(call: Call<JsonObject?>, t: Throwable) {
                    // Handle failure
                }
            })
            return data
        }
}


