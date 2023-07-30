package com.example.wavesoffood.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.R
import com.example.wavesoffood.adapter.MenuAdapter
import com.example.wavesoffood.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit  var binding: FragmentSearchBinding
    private lateinit var adapter : MenuAdapter
    private val orignalMenuFoodName =  listOf("Burger","sandwich","Tea","sandwich","momo","Burger","sandwich","Tea","sandwich","momo")
    private val originalmenuItemPrice = listOf("$5","$7","$8","$10","$10","$5","$7","$8","$10","$10")
    private val originalmenuImage = listOf(R.drawable.roll,R.drawable.ice,R.drawable.tea,R.drawable.f3,R.drawable.f4,R.drawable.roll,R.drawable.ice,R.drawable.tea,R.drawable.f3,R.drawable.f4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private val filterMenuFoodName = mutableListOf<String>()
    private val filterMenuItemPrice =mutableListOf<String>()
    private val filterMenuImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
        MenuAdapter(filterMenuFoodName, filterMenuItemPrice, filterMenuImage)
        binding.MenuRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.MenuRecyclerView.adapter = adapter

        //setup for search view
              setupSearchView()

        // show all menu items

        showAllMenu()
        return binding.root
    }

    private fun showAllMenu() {

        filterMenuFoodName.clear()
        filterMenuImage.clear()
        filterMenuItemPrice.clear()

        filterMenuFoodName.addAll(orignalMenuFoodName)
        filterMenuItemPrice.addAll(originalmenuItemPrice)
        filterMenuImage.addAll(originalmenuImage)
        adapter.notifyDataSetChanged()
    }


    private fun setupSearchView(){
           binding.searchView.setOnQueryTextListener( /* listener */ object: SearchView.OnQueryTextListener,
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
      filterMenuFoodName.clear()
        filterMenuImage.clear()
        filterMenuItemPrice.clear()

        orignalMenuFoodName.forEachIndexed{index, foodName ->

            if(foodName.contains(query, ignoreCase = true)){
                filterMenuFoodName.add(foodName)
                filterMenuItemPrice.add(originalmenuItemPrice[index])
                filterMenuImage.add(originalmenuImage[index])
            }
        }

        adapter.notifyDataSetChanged()
    }
    companion object {

    }
}


