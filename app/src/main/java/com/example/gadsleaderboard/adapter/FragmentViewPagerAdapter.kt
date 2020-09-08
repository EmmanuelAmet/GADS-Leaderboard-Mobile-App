package com.example.gadsleaderboard.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gadsleaderboard.view.HoursFragment
import com.example.gadsleaderboard.view.SkillIQFragment

class FragmentViewPagerAdapter(private val myContext: Context, manager: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(manager,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0-> { return HoursFragment() }
            else ->{ return SkillIQFragment()}
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}