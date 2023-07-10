package com.example.taskapp.viewmodels

import amr_handheld.network.NetworkState
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.databse.PostDatabase
import com.example.taskapp.model.PostModel
import com.example.taskapp.repo.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PostsViewModel constructor(private val repository: Repository, private val postDatabase: PostDatabase):
    ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    val postsList= MutableLiveData<List<PostModel>>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()

    fun getPosts() {

        viewModelScope.launch {
            Log.d("Thread Inside", Thread.currentThread().name)
            try {
                loading.postValue(true)
                when (val response = repository.getPosts()){
                    is NetworkState.Success -> {
                        postDatabase.appDao().insertItems(response.data)
                        postsList.postValue(postDatabase.appDao().getPosts())
                        loading.postValue(false)
                    }

                    is NetworkState.Error -> {
                        loading.postValue(false)
                        if (response.response.code() == 401) {
                            //  loginresult.postValue(NetworkState.Error())
                        } else {
                            //  loginresult.postValue(NetworkState.Error)
                        }
                    }
                }
            }catch (e: Exception){
                onError(e.message.toString())
                loading.postValue(false)
            }
        }
    }

    private fun onError(message: String) {
        _errorMessage.value = message
        loading.value = false
    }


}