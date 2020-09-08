package com.example.gadsleaderboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gadsleaderboard.model.Hours
import com.example.gadsleaderboard.R

class HoursAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var hoursList =  ArrayList<Hours>()as List<Hours>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HoursViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_hours,parent,false)
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HoursViewHolder ->{
                holder.bind(hoursList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return hoursList.size
    }

    fun sumbitList(hoursList: List<Hours>){
        this.hoursList = hoursList
    }

    class HoursViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val hoursItemName: TextView = itemView.findViewById(R.id.hours_item_name_text_view)
        private val hoursItemDetails: TextView = itemView.findViewById(R.id.hours_item_details_text_view)
        private val imageView: ImageView = itemView.findViewById(R.id.hours_badge_image_view)
        fun bind(hours: Hours){
            hoursItemName.text = hours.name
            hoursItemDetails.text =
                """${hours.hours} learning hours, ${hours.country}."""
            Glide
                .with(itemView)
                .load(hours.badgeUrl)
                .into(imageView);
        }

    }
}

