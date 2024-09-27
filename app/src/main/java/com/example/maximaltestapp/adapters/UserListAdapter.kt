package com.example.maximaltestapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.maximaltestapp.ApiCall
import com.example.maximaltestapp.models.GitUser
import com.example.maximaltestapp.R
import com.example.maximaltestapp.ReposActivity
import com.example.maximaltestapp.models.RepoResponse


class UserListAdapter(
    private val dataSet: List<GitUser>,
    private val onUserClick: (String) -> Unit
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivAvatar: ImageView
        val tvName: TextView
        val tvCount: TextView

        init {
            // Define click listener for the ViewHolder's View
            ivAvatar = view.findViewById(R.id.ivAvatar)
            tvName = view.findViewById(R.id.tvName)
            tvCount = view.findViewById(R.id.tvCount)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_user, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val user = dataSet[position].login
        viewHolder.tvName.text = user

        Glide.with(viewHolder.itemView)
            .load(dataSet[position].avatar_url)
            //.override(imageWidthPixels, imageHeightPixels)
            .into(viewHolder.ivAvatar)

        ApiCall().findFollowers(user) { followers ->
            viewHolder.tvCount.text = followers.size.toString()
        }

        viewHolder.ivAvatar.setOnClickListener {
            onUserClick(user)
            /*ApiCall().getRepos(user) { repos ->
                onUserClick(repos)
            }*/
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
