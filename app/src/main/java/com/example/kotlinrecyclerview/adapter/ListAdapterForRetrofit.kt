package com.example.kotlinrecyclerview.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kotlinrecyclerview.activities.MainActivity
import com.example.kotlinrecyclerview.model.User
import kotlinx.android.synthetic.main.question_item.view.*

class ListAdapterForRetrofit(
    private val context: Context,
    private val mQuestions: List<User>,
    private val mRowLayout: Int
) : RecyclerView.Adapter<ListAdapterForRetrofit.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mRowLayout, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.title?.text = mQuestions[position].title
        holder.link?.text = mQuestions[position].link
        holder.positionNumber?.text = mQuestions[position].question_id
        holder.containerView.setOnClickListener {
            Toast.makeText(context, "Clicked on: " + holder.title.text, Toast.LENGTH_SHORT).show();
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return mQuestions.size
    }

    class QuestionViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {
        val positionNumber = containerView.positionNumber;
        val title = containerView.title;
        val link = containerView.link;
    }
}
