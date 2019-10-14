package com.example.kotlinrecyclerview.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinrecyclerview.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAdd.setOnClickListener {
            //users.add(User(editTextName.text.toString(), editTextAddress.text.toString(), ))
        }
    }
}