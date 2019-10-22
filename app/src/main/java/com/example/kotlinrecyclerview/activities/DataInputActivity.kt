package com.example.kotlinrecyclerview.activities

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.kotlinrecyclerview.DatabaseUsers
import com.example.kotlinrecyclerview.R
import com.example.kotlinrecyclerview.adapters.ListAdapterForRoom
import com.example.kotlinrecyclerview.model.User
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class DataInputActivity : AppCompatActivity() {
    private var databaseUsers: DatabaseUsers? = null
    private var mAdapter: ListAdapterForRoom? = null
    private var mQuestions: MutableList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseUsers = DatabaseUsers.getDatabase(this)!!

        val bundleIdString = intent.getStringExtra("key_id")

        listRecyclerViewForRoom!!.layoutManager = LinearLayoutManager(this)

        buttonAdd.setOnClickListener {
            //users.add(User(editTextName.text.toString(), editTextAddress.text.toString(), ))
            if (editTextName.text.toString().isNotEmpty() || editTextAddress.text.toString().isNotEmpty()) {
                val userForInsert = User(
                    bundleIdString,
                    editTextName.text.toString(),
                    editTextAddress.text.toString()
                )
                InsertAsyncTask(this, userForInsert).execute()
            } else {
                Toast.makeText(this, "Fill username and password..", Toast.LENGTH_SHORT).show()
            }
        }

        buttonGet.setOnClickListener {
            GetAllUsersAsyncTask(this).execute()
        }
    }

    private class InsertAsyncTask(var context: DataInputActivity, var userToInsert: User) :
        AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg params: Void?): Boolean {
            context.databaseUsers!!.userDao().insert(userToInsert)
            return true
        }

        override fun onPostExecute(result: Boolean) {
            if (result) {
                Toast.makeText(context, "Inserted!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private class GetAllUsersAsyncTask(var context: DataInputActivity) :
        AsyncTask<Void, Void, List<User>>() {
        override fun doInBackground(vararg params: Void?): List<User> {
            return context.databaseUsers!!.userDao().getAllUsers()
        }

        override fun onPostExecute(result: List<User>) {
            context.textViewDbCount.text = "Database Entries=" + result.size
            context.mAdapter = ListAdapterForRoom(context, result, R.layout.question_item)
            context.listRecyclerViewForRoom!!.adapter = context.mAdapter
            //context.mAdapter!!.notifyDataSetChanged()
        }
    }
}