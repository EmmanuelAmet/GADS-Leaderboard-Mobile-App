package com.example.gadsleaderboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gadsleaderboard.model.SkillIQ
import com.example.gadsleaderboard.R

class SkillIQAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var skillIQList = ArrayList<SkillIQ>() as List<SkillIQ>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SkillIQViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_iq_skills,parent,false)
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SkillIQViewHolder ->{
                holder.bind(skillIQList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return skillIQList.size
    }

    fun sumbitList(skillIQList: List<SkillIQ>){
        this.skillIQList = skillIQList
    }

    class SkillIQViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val skillIQItemName: TextView = itemView.findViewById(R.id.skill_iq_item_name_text_view)
        private val skillIQItemDetails: TextView = itemView.findViewById(R.id.skill_iq_item_details_text_view)
        private val skillIQBadgeImageView: ImageView = itemView.findViewById(R.id.skill_iq_badge_image_view)

        fun bind(skillIQ: SkillIQ){
            skillIQItemName.text = skillIQ.name
            skillIQItemDetails.text =
                """${skillIQ.score} skill IQ Score, ${skillIQ.country}"""
            Glide.with(itemView)
                .load(skillIQ.badgeUrl)
                .into(skillIQBadgeImageView)
        }

    }
}
