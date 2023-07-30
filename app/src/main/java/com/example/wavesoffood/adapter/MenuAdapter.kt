package com.example.wavesoffood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wavesoffood.databinding.MenuItemBinding

class MenuAdapter(
    private val menuItemsNames:MutableList<String>,
    private val menuItemPrice:MutableList<String>,
    private val MenuImage:MutableList<Int>
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context),parent , false)
     return MenuViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {

        holder.bind(position)
    }
    override fun getItemCount(): Int = menuItemsNames.size

    inner class MenuViewHolder(private val binding :MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
          binding.apply {
              menuFoodName.text = menuItemsNames[position]
              menuPrice.text = menuItemPrice[position]
              menuImage.setImageResource(MenuImage[position])
          }
        }

    }
}