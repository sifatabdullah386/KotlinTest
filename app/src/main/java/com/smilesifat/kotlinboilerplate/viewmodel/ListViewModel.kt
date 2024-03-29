package com.smilesifat.kotlinboilerplate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.smilesifat.kotlinboilerplate.model.ListModel
import com.smilesifat.kotlinboilerplate.repository.ListingRepository


class ListViewModel : ViewModel() {
    private val listings: LiveData<ArrayList<ListModel>>

    init {
        val repository = ListingRepository()
        listings = repository.listings
    }

    val posts: LiveData<ArrayList<ListModel>>
        get() = listings
}
