package com.dicoding.picodiploma.loginwithanimation.view.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.loginwithanimation.data.UserRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignupViewModel(private val repository: UserRepository) : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val registerResponse = repository.register(name, email, password)
                _response.value = registerResponse.message.toString()
                Log.d(TAG, registerResponse.message.toString())
                _isLoading.value = false
            } catch (e: HttpException) {
                _response.value = "Gagal mendaftar"
                Log.d(TAG, e.message.toString())
                _isLoading.value = false
            }
        }
    }

    companion object {
        const val TAG = "SignupViewModel"
    }
}