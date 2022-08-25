package com.cherry.testapp5p.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherry.testapp5p.domain.EmptyFieldsException
import com.cherry.testapp5p.domain.UserRepository
import com.cherry.testapp5p.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private var _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun getUser(phoneNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentUser.value = repository.getUser(phoneNumber)
        }
    }

    @Throws(EmptyFieldsException::class)
    suspend fun loginWithPhoneAndPassword(fullPhoneNumber: String, password: String) {
        when {
            fullPhoneNumber.isBlank() || password.isBlank() -> {
                throw EmptyFieldsException("empty phoneNumber or password")
            }
            fullPhoneNumber.isNotBlank() || password.isNotBlank() -> {
                repository.loginAndInsertUser(fullPhoneNumber, password)
            }
        }
    }
}