package com.kudos.focusincoroutines.section3.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kudos.focusincoroutines.databinding.ActivityLoginBinding
import com.kudos.focusincoroutines.section3.utils.Status
import com.kudos.focusincoroutines.section3.utils.ViewUtils.toast
import com.kudos.focusincoroutines.section3.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            loginButton.setOnClickListener {

                val userEmail = emailEditText.text.toString()
                val userPassword = passwordEditText.text.toString()

                if (userEmail.isEmpty() || userPassword.isEmpty()) {
                    toast("Please fill all details")
                } else {
                    loginUser(userEmail, userPassword)
                }

            }

            signupButton.setOnClickListener {
                startActivity(Intent(this@LoginActivity,SignupActivity::class.java))
            }

        }
    }

    private fun loginUser(userEmail: String, userPassword: String) {
        userViewModel.loginUser(userEmail, userPassword)

        userViewModel.loginComplete.observe(this) {
            when (it.status) {
                Status.LOADING -> {

                }
                Status.SUCCESS -> {
                    toast("Logging in...")
                    startActivity(
                        Intent(this, HomeActivity::class.java).putExtra(
                            "LoggedInUser",
                            it.data
                        )
                    )
                    finish()
                }
                Status.ERROR -> {
                    toast(it.message.toString())
                }
            }
        }
    }


}