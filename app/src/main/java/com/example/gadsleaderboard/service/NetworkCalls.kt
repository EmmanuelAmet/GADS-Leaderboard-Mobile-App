package com.example.gadsleaderboard.service

import com.example.gadsleaderboard.model.Hours
import com.example.gadsleaderboard.model.SkillIQ
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

object NetworkCalls{

    private val LEADERBOARD_BASE_URL = "https://gadsapi.herokuapp.com/"                                                                                                                                                                                                                                                                                                         
    private val PROJECTSUBMISSIION_BASE_URL = "https://docs.google.com/forms/d/e/"

    val leaderBoardApi = Retrofit.Builder()
        .baseUrl(LEADERBOARD_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    interface LeadersBoardApi {
        @GET("api/hours")
        fun getHoursList(): Call<List<Hours>>

        @GET("api/skilliq")
        fun getSkillIQList(): Call<List<SkillIQ>>
    }

    interface ProjectSubmissionAPI{
        @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
        @FormUrlEncoded
        fun submit(
            @Field("entry.1824927963")emailAddress:String,
            @Field("entry.1877115667")firstName: String,
            @Field("entry.2006916086")lastName: String,
            @Field("entry.284483984")projectUrl: String
        ):Call<Void>
    }

    fun submitProject(firstName: String, lastName: String, emailAddress: String, projectUrl: String, callback: Callback<Void>){
        val projectSubmissionApi = Retrofit.Builder()
            .baseUrl(PROJECTSUBMISSIION_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiRequest = projectSubmissionApi.create(ProjectSubmissionAPI::class.java)
        val call = apiRequest.submit(emailAddress,firstName,lastName,projectUrl)
        call.enqueue(callback)
    }

    fun getHoursList(callback: Callback<List<Hours>>) {
        val apiRequest = leaderBoardApi.create(LeadersBoardApi::class.java)
        val call = apiRequest.getHoursList()
        call.enqueue(callback)
    }

    fun getSkillIQList(callback: Callback<List<SkillIQ>>) {
        val apiRequest = leaderBoardApi.create(LeadersBoardApi::class.java)
        val call = apiRequest.getSkillIQList()
        call.enqueue(callback)
    }

}

