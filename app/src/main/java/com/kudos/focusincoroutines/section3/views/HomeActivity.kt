package com.kudos.focusincoroutines.section3.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kudos.focusincoroutines.databinding.ActivityHomeBinding
import com.kudos.focusincoroutines.section3.db.entity.User
import com.kudos.focusincoroutines.section3.utils.Status
import com.kudos.focusincoroutines.section3.utils.ViewUtils.toast
import com.kudos.focusincoroutines.section3.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var loggedInUser: User
    private lateinit var binding: ActivityHomeBinding

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loggedInUser = intent.extras?.get("LoggedInUser") as User
        getUserDetails()
        binding.apply {
            logoutButton.setOnClickListener {
                openLogin()
            }
            deleteUserButton.setOnClickListener {
                deleteUser()
            }
        }
    }

    private fun openLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun deleteUser() {
        userViewModel.deleteUser(loggedInUser)

        userViewModel.userDeleted.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    toast("User Deleted")
                    openLogin()
                }
                Status.ERROR -> {
                    toast(it.message.toString())
                }
            }
        }
    }

    private fun getUserDetails() {
        userViewModel.getUser(loggedInUser.userEmail)

        userViewModel.getUser.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    showUserInfo(it.data)
                }
                Status.ERROR -> {
                    toast(it.message.toString())
                }
            }
        }
    }

    private fun showUserInfo(data: User?) {
        binding.apply {
            userNameTextView.text = "You are signed in as - ${data?.userName}"
        }
    }
}