package com.example.gadsleaderboard.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gadsleaderboard.model.SkillIQ
import com.example.gadsleaderboard.R
import com.example.gadsleaderboard.viewmodel.LeaderBoardViewModel
import com.example.gadsleaderboard.adapter.SkillIQAdapter

class SkillIQFragment : Fragment() {

    private lateinit var viewModel: LeaderBoardViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_skill_iq, container, false)

        val skillIQAdapter = SkillIQAdapter()
        val skillIQRecycleView: RecyclerView = rootView.findViewById(R.id.skill_iq_recycle_view)
        skillIQRecycleView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = skillIQAdapter
        }

        viewModel = ViewModelProvider(this).get(LeaderBoardViewModel::class.java)
        viewModel.getSkillIQList()?.observe(viewLifecycleOwner,object: Observer<List<SkillIQ>>{
            override fun onChanged(skillIqList: List<SkillIQ>) {
                skillIQAdapter.sumbitList(skillIqList)
                skillIQAdapter.notifyDataSetChanged()
            }
        })

        return rootView
    }

}