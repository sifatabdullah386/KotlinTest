package com.smilesifat.kotlinboilerplate.view.activity

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.smilesifat.kotlinboilerplate.R
import com.smilesifat.kotlinboilerplate.adapter.CustomRecyclerViewAdapter
import com.smilesifat.kotlinboilerplate.repository.ListingRepository
import com.smilesifat.kotlinboilerplate.viewmodel.ListViewModel

class MainActivity : BaseActivity() {

    var listingRepository: ListingRepository? = null
    var customRecyclerViewAdapter: CustomRecyclerViewAdapter? = null
    var recyclerView: RecyclerView? = null
    var shimmerFrameLayout: ShimmerFrameLayout? = null

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
        listingRepository = ListingRepository()
        listingRepository!!.listings
        getListings()
    }

    private fun getListings() {
        shimmerFrameLayout!!.stopShimmer()
        shimmerFrameLayout!!.visibility = View.GONE
        recyclerView!!.visibility = View.VISIBLE
        val listViewModel: ListViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        listViewModel.posts.observe(
            this
        ) { receivedDataList ->
            if (receivedDataList.isEmpty()) {
                ShowAlert("No Data Found")
            } else {
                recyclerView!!.layoutManager = LinearLayoutManager(applicationContext)
                customRecyclerViewAdapter = CustomRecyclerViewAdapter(applicationContext, receivedDataList)
                recyclerView!!.adapter = customRecyclerViewAdapter
            }
        }
    }
}
