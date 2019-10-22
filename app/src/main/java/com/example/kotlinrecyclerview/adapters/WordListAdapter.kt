package com.example.kotlinrecyclerview.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.kotlinrecyclerview.R
import com.example.kotlinrecyclerview.model.User

class WordListAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    override fun getItemCount(): Int {
        return mWords?.size
    }

    private val mInflater: LayoutInflater
    private var mWords: List<User> = emptyList() // Cached copy of words

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).


    init {
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = mInflater.inflate(R.layout.question_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (mWords != null) {
            val current = mWords!![position]
            holder.wordItemView.setText(current.question_id)
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.text = "No Word"
        }
    }

    internal fun setWords(words: List<User>) {
        mWords = words
        notifyDataSetChanged()
    }

    class WordViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        public val wordItemView: TextView

        init {
            wordItemView = itemView.findViewById(R.id.positionNumber)
        }
    }
}