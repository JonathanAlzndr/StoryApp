package com.dicoding.picodiploma.loginwithanimation.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.loginwithanimation.data.UserRepository
import com.dicoding.picodiploma.loginwithanimation.data.pref.UserModel
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    private fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.login(email, password)
                if(response.error == false) {
                    saveSession(UserModel(email, response.loginResult?.token!!, true))
                    _response.value = response.message!!
                    Log.d(TAG, "$_response")
                } else {
                    _response.value = response.message!!
                    Log.d(TAG, "Token gagal disimpan")
                }
                _isLoading.value = false
            } catch(e: Exception) {
                Log.d(TAG, e.message.toString())
                _isLoading.value = false
                _response.value = "Login gagal"
            }
        }
    }

    companion object {
        const val TAG = "LoginViewModel"
    }
}