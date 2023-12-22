package com.smilesifat.kotlinboilerplate.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.gson.JsonObject
import com.smilesifat.kotlinboilerplate.R
import com.smilesifat.kotlinboilerplate.adapter.CustomRecyclerViewAdapter
import com.smilesifat.kotlinboilerplate.model.ListModel
import com.smilesifat.kotlinboilerplate.repository.ListingRepository
import com.smilesifat.kotlinboilerplate.services.MyAPIServices
import com.smilesifat.kotlinboilerplate.services.RetrofitClientInstance
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Objects

class MainActivity : BaseActivity() {

    private val TAG = MainActivity::class.java.name
    var listingRepository: ListingRepository? = null
    var customRecyclerViewAdapter: CustomRecyclerViewAdapter? = null
    var recyclerView: RecyclerView? = null
    var shimmerFrameLayout: ShimmerFrameLayout? = null
    var listModel: ArrayList<ListModel> = ArrayList<ListModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.colorBackground)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val decor = window.decorView
        decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        initView()
    }

    private fun initView() {
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        shimmerFrameLayout = findViewById<ShimmerFrameLayout>(R.id.shimmerLayout)

        getListings1()
//        shimmerFrameLayout.setVisibility(View.VISIBLE)
//        shimmerFrameLayout.startShimmer()
//        recyclerView.setVisibility(View.GONE)
//        listingRepository = ListingRepository()
//        listingRepository.listings()
//        getListings()
    }

//    private fun getListings() {
//        shimmerFrameLayout!!.stopShimmer()
//        shimmerFrameLayout!!.visibility = View.GONE
//        recyclerView!!.visibility = View.VISIBLE
//        val listViewModel: ListViewModel = ViewModelProvider(this)[ListViewModel::class.java]
//        listViewModel.posts.observe(this, object : Observer<ArrayList<ListModel?>?> {
//            fun onChanged(receivedDataList: ArrayList<ListModel?>) {
//                if (receivedDataList.isEmpty()) {
//                    ShowAlert("No Data Found")
//                } else {
//                    recyclerView!!.layoutManager = LinearLayoutManager(applicationContext)
//                    customRecyclerViewAdapter =
//                        CustomRecyclerViewAdapter(applicationContext, receivedDataList)
//                    recyclerView!!.adapter = customRecyclerViewAdapter
//                }
//            }
//        })
//    }

    fun getListings1() {
        shimmerFrameLayout!!.visibility = View.VISIBLE
        shimmerFrameLayout!!.startShimmer()
        val myAPIService: MyAPIServices =
            RetrofitClientInstance.retrofitInstance?.create(MyAPIServices::class.java)!!

        val call: Call<JsonObject>? = myAPIService.getListings("android", "stars")
        call?.enqueue(object : Callback<JsonObject?> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                shimmerFrameLayout!!.stopShimmer()
                shimmerFrameLayout!!.visibility = View.GONE
                Log.d("Url Responded", response.toString())
                try {
                    val jsonObject = response.body()
                    if (jsonObject != null) {
                        val convertJSON = JSONObject(jsonObject.toString())
                        Log.d("JSONObject Responded", convertJSON.toString())
                        var jsonArray: JSONArray? = null
                        jsonArray = convertJSON.optJSONArray("items")
                        passArray(jsonArray)
                    } else {
                        retrofitErrorHandler(response.code())
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }

            override fun onFailure(call: Call<JsonObject?>, throwable: Throwable) {
                ShowAlert(throwable.message.toString())
                Objects.requireNonNull(throwable.message).let {
                    Log.d("Retrofit Error", it.toString())
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun passArray(jsonArray: JSONArray?) {
        listModel.clear()
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
                val avatar=owner.getString("avatar_url")
                listModel.add(
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

        customRecyclerViewAdapter = CustomRecyclerViewAdapter(applicationContext, listModel)
        recyclerView!!.adapter = customRecyclerViewAdapter
        customRecyclerViewAdapter!!.notifyDataSetChanged()
    }
}
