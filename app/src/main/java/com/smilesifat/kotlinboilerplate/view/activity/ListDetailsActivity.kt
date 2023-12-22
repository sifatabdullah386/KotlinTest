package com.smilesifat.kotlinboilerplate.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.smilesifat.kotlinboilerplate.R
import com.smilesifat.kotlinboilerplate.utility.Helper

class ListDetailsActivity : AppCompatActivity() {

    var id: String? = null
    var name: String? = null
    var full_name: String? = null
    var description: String? = null
    var avatar: String? = null
    var url: String? = null
    var created_at: String? = null
    var language: String? = null

    var imageView: ImageView? = null
    var backView: ImageView? = null
    var nameView: TextView? = null
    var full_nameView: TextView? = null
    var descriptionView: TextView? = null
    var urlView: TextView? = null
    var created_atView: TextView? = null
    var languageView: TextView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_details_view)

        id = intent.getStringExtra("id")
        name = intent.getStringExtra("name")
        full_name = intent.getStringExtra("full_name")
        avatar = intent.getStringExtra("avatar")
        description = intent.getStringExtra("description")
        language = intent.getStringExtra("language")
        created_at = intent.getStringExtra("created_at")

        imageView = findViewById<ImageView>(R.id.ad_image)
        backView = findViewById<ImageView>(R.id.iv_back)
        nameView = findViewById<TextView>(R.id.name)
        nameView?.text = name

        full_nameView = findViewById<TextView>(R.id.full_name)
        full_nameView?.text = full_name
        descriptionView = findViewById<TextView>(R.id.description)
        descriptionView?.text = description
        urlView = findViewById<TextView>(R.id.url)
        urlView?.text = url

        created_atView = findViewById<TextView>(R.id.created_at)
        created_atView?.text = Helper.DateFormat(created_at)

        languageView = findViewById<TextView>(R.id.language)
        languageView?.text = language

        if (avatar!!.isNotEmpty()) {
            imageView?.let {
                Glide.with(applicationContext)
                    .load(avatar)
                    .into(it)
            }
        }

        backView?.let {
            it.setOnClickListener(View.OnClickListener {
                onBackPressed()
            })
        }


    }
}