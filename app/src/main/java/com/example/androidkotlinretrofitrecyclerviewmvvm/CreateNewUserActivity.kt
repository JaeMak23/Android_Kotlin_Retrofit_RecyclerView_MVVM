package com.example.androidkotlinretrofitrecyclerviewmvvm

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.androidkotlinretrofitrecyclerviewmvvm.databinding.ActivityCreateNewUserBinding


class CreateNewUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateNewUserBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCreateNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}