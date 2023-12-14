package com.dicoding.picodiploma.loginwithanimation.view.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.loginwithanimation.data.UserRepository
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.ListStoryItem

class MapsViewModel(private val repository: UserRepository) : ViewModel() {

    fun getStoriesWithLocation(): LiveData<List<ListStoryItem>> {
        repository.getStoriesWithLocation()
        return repository._listStoriesWithLocation
    }
}