package com.example.wavesoffood.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.R
import com.example.wavesoffood.adapter.CartAdapter
import com.example.wavesoffood.adapter.MenuAdapter
import com.example.wavesoffood.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter
    private val orignalMenuFoodName = listOf(
        "Burger",
        "sandwich",
        "Tea",
        "sandwich",
        "momo",
        "Burger",
        "sandwich",
        "Tea",
        "sandwich",
        "momo"
    )
    private val originalMenuItemPrice =
            listOf("$5", "$7", "$8", "$10", "$10", "$5", "$7", "$8", "$10", "$10")
    private val originalMenuImage = listOf(
        R.drawable.menu1,
        R.drawable.menu2,
        R.drawable.menu3,
        R.drawable.menu4,
        R.drawable.menu5,
        R.drawable.menu6,
        R.drawable.menu7,
        R.drawable.menu1,
        R.drawable.menu2,
        R.drawable.menu3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val filteredMenuFoodName = mutableListOf<String>()
    private val filteredMenuItemPrice = mutableListOf<String>()
    private val filteredMenuImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSearchBinding.inflate(inflater, container, false)
        //  adapter = MenuAdapter(filteredMenuFoodName,filteredMenuItemPrice,filteredMenuImage, requireContext())
        binding.MenuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.MenuRecyclerView.adapter = adapter
        //setup for search view
        setupSearchView()

        // show all menu items

        showAllMenu()
        return binding.root
    }

    private fun showAllMenu() {

        filteredMenuFoodName.clear()
        filteredMenuImage.clear()
        filteredMenuItemPrice.clear()

        filteredMenuFoodName.addAll(orignalMenuFoodName)
        filteredMenuItemPrice.addAll(originalMenuItemPrice)
        filteredMenuImage.addAll(originalMenuImage)
        adapter.notifyDataSetChanged()
    }


    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener( /* listener */ object :
            SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                filterMenuItem(query)
                return true

            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItem(newText)
                return true
            }


        })
    }

    private fun filterMenuItem(query: String) {
        filteredMenuFoodName.clear()
        filteredMenuImage.clear()
        filteredMenuItemPrice.clear()

        orignalMenuFoodName.forEachIndexed { index, foodName ->

            if (foodName.contains(query, ignoreCase = true)) {
                filteredMenuFoodName.add(foodName)
                filteredMenuItemPrice.add(originalMenuItemPrice[index])
                filteredMenuImage.add(originalMenuImage[index])
            }
        }

        adapter.notifyDataSetChanged()
    }

    companion object {

    }
}


