package com.example.wavesoffood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wavesoffood.databinding.ActivityPayOutBinding
import com.example.wavesoffood.fragment.CongratsBottomSheet

class PayOutActivity : AppCompatActivity() {
  lateinit var binding :ActivityPayOutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                binding = ActivityPayOutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.PlaceMyOrder.setOnClickListener {
            val bottomSheetDeialog = CongratsBottomSheet()
            bottomSheetDeialog.show(supportFragmentManager,"Test")
        }

    }
}