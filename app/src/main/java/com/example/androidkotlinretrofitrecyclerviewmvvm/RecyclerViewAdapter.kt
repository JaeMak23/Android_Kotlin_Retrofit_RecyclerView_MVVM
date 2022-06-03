package com.example.androidkotlinretrofitrecyclerviewmvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinretrofitrecyclerviewmvvm.databinding.RecyclerRowListBinding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    lateinit var binding: RecyclerRowListBinding

    var userList = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = RecyclerRowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //  val textView = binding.nameTextView
        //  val inflater =
        //      LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_list, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(binding: RecyclerRowListBinding) : RecyclerView.ViewHolder(binding.root) {

        val nameTextView = binding.nameTextView
        val emailTextView = binding.emailTextView
        val statsTextView = binding.statsTextView

        fun bind(data: User) {
            nameTextView.text = data.name
            emailTextView.text=data.email
            statsTextView.text=data.status
        }
    }


}