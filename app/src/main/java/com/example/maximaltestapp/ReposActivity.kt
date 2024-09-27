package com.example.maximaltestapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maximaltestapp.adapters.RepoListAdapter


class ReposActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_repos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.repos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val user = intent.getStringExtra("user")

        val tvUserName = findViewById<TextView>(R.id.tvUserName)
        tvUserName.text = user

        val recyclerView = findViewById<RecyclerView>(R.id.rvRepos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        ApiCall().getRepos(user) { repos ->
            recyclerView.adapter = RepoListAdapter(repos)
        }

    }
}