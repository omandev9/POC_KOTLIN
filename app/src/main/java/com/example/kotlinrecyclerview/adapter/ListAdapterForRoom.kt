package com.example.kotlinrecyclerview.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.kotlinrecyclerview.activities.DataInputActivity
import com.example.kotlinrecyclerview.model.User
import kotlinx.android.synthetic.main.question_item.view.*

class ListAdapterForRoom(
    private val context: Context,
    private val mQuestions: List<User>,
    private val mRowLayout: Int
) : RecyclerView.Adapter<ListAdapterForRoom.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mRowLayout, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        var currentModel = mQuestions[position]
        holder.title.text = currentModel.title
        holder.link.text = currentModel.link
        holder.positionNumber.text = currentModel.question_id
    }

    override fun getItemCount(): Int {
        return mQuestions.size
    }

    class QuestionViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {
        val positionNumber: TextView = containerView.positionNumber
        val title: TextView = containerView.title
        val link: TextView = containerView.link
    }
}
