package com.example.taskapp.repo

import amr_handheld.network.NetworkState
import com.example.taskapp.databse.PostDatabase
import com.example.taskapp.model.PostModel
import com.example.taskapp.retrofit.RetrofitInterface

class Repository(private val retrofitInterface: RetrofitInterface, private val postDatabase: PostDatabase) {


    suspend fun getPosts(): NetworkState<List<PostModel>> {
        val response = retrofitInterface.getPosts()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }



}