package com.example.wavesoffood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wavesoffood.databinding.ActivityLoginBinding
import com.example.wavesoffood.databinding.ActivityMainBinding

class loginActivity : AppCompatActivity() {
    private  val binding: ActivityLoginBinding by lazy {
         ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
     binding.appCompatButton.setOnClickListener {
         val intent = Intent(this,signupActivity::class.java)
         startActivity(intent)
     }

    }
}


