package com.example.gadsleaderboard.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gadsleaderboard.model.Hours
import com.example.gadsleaderboard.model.SkillIQ
import com.example.gadsleaderboard.service.NetworkCalls
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaderBoardViewModel: ViewModel() {
    private var hoursList = MutableLiveData<List<Hours>>()
    private var skillIQList = MutableLiveData<List<SkillIQ>>()

    init {
        NetworkCalls.getHoursList(object: Callback<List<Hours>>{
            override fun onResponse(call: Call<List<Hours>>, response: Response<List<Hours>>) {
                if(response.isSuccessful)
                    hoursList.value = response.body()
                else
                    Log.d("notSuccessful",response.code().toString())
            }

            override fun onFailure(call: Call<List<Hours>>, t: Throwable) {
                Log.d("failure",t.message.toString())
            }
        })

        NetworkCalls.getSkillIQList(object: Callback<List<SkillIQ>>{
            override fun onResponse(call: Call<List<SkillIQ>>, response: Response<List<SkillIQ>>) {
                if(response.isSuccessful)
                    skillIQList.value = response.body()
                else
                    Log.d("notSuccessful",response.code().toString())
            }

            override fun onFailure(call: Call<List<SkillIQ>>, t: Throwable) {
                Log.d("failure",t.message.toString())
            }
        })
    }

    fun getHoursList(): MutableLiveData<List<Hours>>? {
        return hoursList
    }
    fun getSkillIQList(): MutableLiveData<List<SkillIQ>>? {
        return skillIQList
    }

}