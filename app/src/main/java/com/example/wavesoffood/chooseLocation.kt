package com.example.wavesoffood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.wavesoffood.databinding.ActivityChooseLocationBinding
import com.example.wavesoffood.databinding.ActivityLoginBinding

class chooseLocation : AppCompatActivity() {
    private  val binding: ActivityChooseLocationBinding by lazy {
        ActivityChooseLocationBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val locatinlist = arrayOf("satna","rewa","panna","Amarpatan")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,locatinlist)
        val autoCompleteTextView = binding.listOfLocation
        autoCompleteTextView.setAdapter(adapter)
    }
}