package com.example.wavesoffood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wavesoffood.databinding.ActivityLoginBinding
import com.example.wavesoffood.databinding.ActivityMainBinding
import com.example.wavesoffood.databinding.ActivitySignupBinding

class signupActivity : AppCompatActivity() {
    private  val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.CreateAccount.setOnClickListener {
            val intent = Intent(this, chooseLocation::class.java)
            startActivity(intent)
        }
    }
}