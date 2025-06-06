package com.example.cleanarchitecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _users = MutableLiveData<ArrayList<User>>()
    val users: LiveData<ArrayList<User>> = _users

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    var currentPage = 1

    fun fetchUsers(isRefresh: Boolean = false) {
        viewModelScope.launch {
            _loading.value = true
            try {
                if (isRefresh) currentPage = 1

                val result = getUsersUseCase.invoke(currentPage)
                if (isRefresh) {
                    _users.postValue(result)
                } else {
                    val updated = (_users.value ?: ArrayList()) + result
                    _users.postValue(ArrayList(updated))
                }
                currentPage++
            } catch (e: Exception) {
                _error.postValue(e.message ?: "Unknown error")
            } finally {
                _loading.value = false
            }
        }
    }
}
