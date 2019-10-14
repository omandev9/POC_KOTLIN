package com.example.kotlinrecyclerview.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlinrecyclerview.R
import com.example.kotlinrecyclerview.model.User

class CustomAdapter(val userList: ArrayList<User>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(parent: CustomAdapter.MyViewHolder, position: Int) {
        val currentUser = userList[position]
        parent.name.text = currentUser.title
        parent.address.text = currentUser.link
        parent.question_id.text = currentUser.question_id
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.textViewUsername)
        val address = itemView.findViewById<TextView>(R.id.textViewAddress)
        val question_id = itemView.findViewById<TextView>(R.id.textViewQuestionId)
    }
}