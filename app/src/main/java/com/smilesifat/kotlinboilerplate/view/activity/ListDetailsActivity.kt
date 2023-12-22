package com.smilesifat.kotlinboilerplate.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.smilesifat.kotlinboilerplate.R

class ListDetailsActivity : AppCompatActivity() {

    var id: String? = null
    var name: String? = null
    var full_name: String? = null
    var id: String? = null
    var id: String? = null
    var id: String? = null
    var id: String? = null



    var image: ImageView? = nullgit
    var name: TextView? = null
    var full_name: TextView? = null
    var description: TextView? = null
    var url: TextView? = null
    var created_at: TextView? = null
    var language: TextView? = null

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

        image = findViewById<ImageView>(R.id.ad_image)
        name = findViewById<TextView>(R.id.name)
        full_name = findViewById<TextView>(R.id.full_name)
        description = findViewById<TextView>(R.id.description)
        url = findViewById<TextView>(R.id.url)
        created_at = findViewById<TextView>(R.id.created_at)
        language = findViewById<TextView>(R.id.language)


        if (avatar.isNotEmpty()) {
            val into = Glide.with().load(avatar).apply(
                RequestOptions().placeholder(R.drawable.no_image_found)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.no_image_found)
            ).into(image)
        }

    }
}