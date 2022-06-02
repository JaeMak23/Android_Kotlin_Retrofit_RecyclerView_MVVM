package com.example.androidkotlinretrofitrecyclerviewmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinretrofitrecyclerviewmvvm.databinding.ActivityMainBinding

lateinit var recyclerView: RecyclerView
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var recycleViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecycleView()
        searchUser()
    }

    private fun searchUser() {
        binding.searchButton.setOnClickListener {
            if (TextUtils.isEmpty(binding.searchButton.text.toString())) {
                viewModel.searchUser(binding.searchButton.text.toString())
            } else {
                viewModel.getUserList()
            }
        }
    }

    private fun initRecycleView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration =
                DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)

            addItemDecoration(decoration)
            recycleViewAdapter = RecyclerViewAdapter()
            adapter = recycleViewAdapter
        }
    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getUserListObservable().observe(this, Observer<UserList> {
            if (it == null) {
                Toast.makeText(this@MainActivity, "no result found...", Toast.LENGTH_LONG).show()
            } else {
                recycleViewAdapter.userList = it.data.toMutableList()
                recycleViewAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getUserList()
    }


}