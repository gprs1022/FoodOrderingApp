package com.example.wavesoffood.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wavesoffood.DetailsActivity
import com.example.wavesoffood.databinding.PapularitemBinding

class PopularAdapter (private val items:List<String>,private val price:List<String>, private val image:List<Int>,private val requreContext: Context) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {

        return PopularViewHolder(PapularitemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }



    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = items[position]
        val images = image[position]
        val price = price[position]
        holder.bind(item,price,images)
        holder.itemView.setOnClickListener {


            val intent = Intent(requreContext, DetailsActivity::class.java)
            intent.putExtra("MenuItemName", item)
            intent.putExtra("MenuItemImage", images)
            requreContext.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
    class PopularViewHolder(private val binding:PapularitemBinding): RecyclerView.ViewHolder(binding.root){
      private val imagesView = binding.imageView4

        fun bind(item: String,price:String, images: Int) {
           binding.foodnamePopularItem.text = item
            binding.PricePopular.text = price
            imagesView.setImageResource(images)
        }


    }
}