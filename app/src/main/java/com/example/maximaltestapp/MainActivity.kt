package com.example.maximaltestapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maximaltestapp.adapters.UserListAdapter
import com.example.maximaltestapp.models.RepoResponse

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etUser = findViewById<EditText>(R.id.etUser)
        val btnFind = findViewById<Button>(R.id.btnFind)

        val recyclerView = findViewById<RecyclerView>(R.id.rvUsers)
        recyclerView.layoutManager = LinearLayoutManager(this)

        btnFind.setOnClickListener {
            //Log.e("qwerty", "btn click")
            ApiCall().findUsers(applicationContext, etUser.text.toString()) { users ->
                recyclerView.adapter = UserListAdapter(users.items) { user ->
                    val intent = Intent(applicationContext, ReposActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                }
            }
        }
    }
}