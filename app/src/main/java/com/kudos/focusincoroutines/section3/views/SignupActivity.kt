package com.kudos.focusincoroutines.section3.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kudos.focusincoroutines.databinding.ActivitySignupBinding
import com.kudos.focusincoroutines.section3.utils.Status
import com.kudos.focusincoroutines.section3.utils.ViewUtils.toast
import com.kudos.focusincoroutines.section3.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            signupButton.setOnClickListener {
                val userName = userNameEditText.text.toString()
                val userEmail = emailEditText.text.toString()
                val userPassword = passwordEditText.text.toString()

                if (userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
                    toast("Please fill all fields")
                } else {
                    signupUser(userName, userEmail, userPassword)
                }
            }
        }
    }

    private fun signupUser(userName: String, userEmail: String, userPassword: String) {
        userViewModel.insertNewUser(userName, userEmail, userPassword)

        userViewModel.insertUser.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    toast("Signing up...")
                }
                Status.SUCCESS -> {
                    toast("Signup Successful")
                    finish()
                }
                Status.ERROR -> {
                    toast(it.message.toString())
                }
            }
        }
    }
}