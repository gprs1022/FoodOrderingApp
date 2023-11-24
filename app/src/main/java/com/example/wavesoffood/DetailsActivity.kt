package com.example.wavesoffood

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.wavesoffood.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityDetailsBinding
    private var foodName: String?= null
    private var foodImage: String? = null
    private var foodDescription:String? = null
    private var foodIngredient:String? = null
    private var foodPrice: String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodName = intent.getStringExtra("MenuItemName")
        foodDescription = intent.getStringExtra("MenuItemDescription")
        foodIngredient = intent.getStringExtra("MenuItemIngredients")
         foodPrice = intent.getStringExtra("MenuItemPrice")
        foodImage = intent.getStringExtra("MenuItemImage")

        with(binding){
            detailFoodName.text = foodName
            detailDescription.text= foodDescription
            detailIngredients.text = foodIngredient
            Glide.with(this@DetailsActivity).load(Uri.parse(foodImage)).into(detailFoodImage)



        }


        binding.backButton.setOnClickListener {
            finish()
        }
    }
}