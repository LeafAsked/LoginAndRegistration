package com.mistershorr.loginandregistration

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mistershorr.loginandregistration.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // retrieve any information from the intent using the extras keys
        val username = intent.getStringExtra(LoginActivity.EXTRA_USERNAME) ?: ""
        val password = intent.getStringExtra(LoginActivity.EXTRA_PASSWORD) ?: ""

        // prefill the username & password fields
        // for EditTexts, you actually have to use the setText functions
        binding.editTextRegistrationUsername.setText(username)
        binding.editTextTextPassword.setText(password)

        // register an account and send back the username & password
        // to the login activity to prefill those fields
        binding.buttonRegistrationRegister.setOnClickListener {
            val password = binding.editTextTextPassword.text.toString()
            val confirm = binding.editTextRegistrationConfirmPassword.text.toString()
            val username = binding.editTextRegistrationUsername.text.toString()
            if(RegistrationUtil.validatePassword(password, confirm) &&
                RegistrationUtil.validateUsername(username))  {  // && do the rest of the validations
                // apply lambda will call the functions inside it on the object
                // that apply is called on
                val resultIntent = Intent().apply {
                    // apply { putExtra() } is doing the same thing as resultIntent.putExtra()
                    putExtra(
                        LoginActivity.EXTRA_USERNAME,
                        binding.editTextRegistrationUsername.text.toString()
                    )
                    putExtra(LoginActivity.EXTRA_PASSWORD, password)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }

    }
}