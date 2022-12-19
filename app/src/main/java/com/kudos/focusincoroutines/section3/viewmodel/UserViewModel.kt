package com.kudos.focusincoroutines.section3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kudos.focusincoroutines.section3.db.entity.User
import com.kudos.focusincoroutines.section3.repository.UserRepository
import com.kudos.focusincoroutines.section3.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val _loginComplete = MutableLiveData<Resource<User>>()
    val loginComplete: LiveData<Resource<User>> = _loginComplete

    fun loginUser(userEmail: String, password: String) {
        _loginComplete.value = Resource.loading(null)
        coroutineScope.launch {
            val user = userRepository.getUser(userEmail)
            if (user == null) {
                withContext(Dispatchers.Main) {
                    _loginComplete.value = Resource.error("User Not Found")
                }
            } else {
                if (user.password == password.hashCode()) {
                    withContext(Dispatchers.Main) {
                        _loginComplete.value = Resource.success(user)
                    }
                }else{
                    withContext(Dispatchers.Main) {
                        _loginComplete.value = Resource.error("Incorrect Password")
                    }
                }
            }
        }
    }

    private val _insertUser = MutableLiveData<Resource<Long>>()
    val insertUser: LiveData<Resource<Long>> = _insertUser

    fun insertNewUser(userName: String, userEmail: String, userPassword: String) {
        _insertUser.value = Resource.loading(null)
        coroutineScope.launch {
            val user = userRepository.getUser(userEmail)
            if (user == null) {
                //Create user
                val insertUserResponse = userRepository.insertUser(
                    User(
                        userName = userName,
                        userEmail = userEmail,
                        password = userPassword.hashCode()
                    )
                )
                withContext(Dispatchers.Main) {
                    _insertUser.value = Resource.success(insertUserResponse)
                }
            } else {
                withContext(Dispatchers.Main) {
                    _insertUser.value = Resource.error("User Already Exists")
                }
            }
        }
    }

    private val _getUser = MutableLiveData<Resource<User>>()
    val getUser: LiveData<Resource<User>> = _getUser

    fun getUser(userEmail: String) {
        _getUser.value = Resource.loading(null)
        coroutineScope.launch {
            val userResponse = userRepository.getUser(userEmail)
            if (userResponse != null) {
                withContext(Dispatchers.Main) {
                    _getUser.value = Resource.success(userResponse)
                }
            } else {
                withContext(Dispatchers.Main) {
                    _getUser.value = Resource.error("User Not Found")
                }
            }
        }
    }

    private val _userDeleted = MutableLiveData<Resource<Boolean>>()
    val userDeleted: LiveData<Resource<Boolean>> = _userDeleted

    fun deleteUser(user: User) {
        _userDeleted.value = Resource.loading(null)
        coroutineScope.launch {
            userRepository.deleteUser(user)
            withContext(Dispatchers.Main) {
                _userDeleted.value = Resource.success(true)
            }
        }
    }
}