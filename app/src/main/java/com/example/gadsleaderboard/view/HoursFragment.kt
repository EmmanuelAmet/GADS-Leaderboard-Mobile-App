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
import com.example.gadsleaderboard.model.Hours
import com.example.gadsleaderboard.R
import com.example.gadsleaderboard.viewmodel.LeaderBoardViewModel
import com.example.gadsleaderboard.adapter.HoursAdapter


class HoursFragment : Fragment() {

    private lateinit var viewModel: LeaderBoardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_hours,container,false)

        val hoursAdapter = HoursAdapter()
        val learningLeadersRecycleView: RecyclerView = rootView.findViewById(R.id.hours_recycle_view)
        learningLeadersRecycleView.apply{
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = hoursAdapter
        }


        viewModel = ViewModelProvider(this).get(LeaderBoardViewModel::class.java)
        viewModel.getHoursList()?.observe(viewLifecycleOwner,
            Observer<List<Hours>> { hoursList ->
                hoursAdapter.sumbitList(hoursList)
                hoursAdapter.notifyDataSetChanged()
            })

        return rootView
    }
}