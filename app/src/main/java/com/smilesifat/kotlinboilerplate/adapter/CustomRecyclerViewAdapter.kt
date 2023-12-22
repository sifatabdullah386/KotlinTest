package com.smilesifat.kotlinboilerplate.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.smilesifat.kotlinboilerplate.R
import com.smilesifat.kotlinboilerplate.model.ListModel
import com.smilesifat.kotlinboilerplate.utility.Helper
import com.smilesifat.kotlinboilerplate.view.activity.ListDetailsActivity

class CustomRecyclerViewAdapter(private var context: Context, listModels: ArrayList<ListModel>) :
    RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>() {
    private var listModels: ArrayList<ListModel>

    init {
        this.listModels = listModels
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_listing, viewGroup, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.itemView.setOnClickListener {
            val i = Intent(context, ListDetailsActivity::class.java)
            i.putExtra("id", java.lang.String.valueOf(listModels[position].id))
            i.putExtra("name", listModels[position].name)
            i.putExtra("full_name", listModels[position].full_name)
            i.putExtra("avatar", listModels[position].avatar)
            i.putExtra("description", listModels[position].description)
            i.putExtra("language", listModels[position].language)
            i.putExtra("created_at", listModels[position].created_at)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }

        val imagePath: String = listModels[position].avatar
        if (imagePath.isNotEmpty()) {
            Glide.with(context).load(imagePath).apply(RequestOptions().placeholder(R.drawable.no_image_found)
                .diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.no_image_found)
            ).into(holder.iv_image)
        }

        holder.text1.setText(listModels[position].name)
        holder.text2.setText(listModels[position].full_name)
        holder.text3.setText(Helper.DateFormat(listModels[position].description))
        holder.text4.setText(Helper.DateFormat(listModels[position].language))
        holder.text5.setText(Helper.DateFormat(listModels[position].created_at))
    }

    override fun getItemCount(): Int {
        return listModels.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_image: ImageView
        var text1: TextView
        var text2: TextView
        var text3: TextView
        var text4: TextView
        var text5: TextView

        init {
            iv_image = view.findViewById<ImageView>(R.id.image)
            text1 = view.findViewById<TextView>(R.id.text1)
            text2 = view.findViewById<TextView>(R.id.text2)
            text3 = view.findViewById<TextView>(R.id.text3)
            text4 = view.findViewById<TextView>(R.id.text4)
            text5 = view.findViewById<TextView>(R.id.text5)
        }
    }
}
