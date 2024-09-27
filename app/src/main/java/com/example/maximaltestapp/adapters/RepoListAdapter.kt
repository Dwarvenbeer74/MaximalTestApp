package com.example.maximaltestapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maximaltestapp.R
import com.example.maximaltestapp.models.RepoResponse


class RepoListAdapter(
    private val repos: List<RepoResponse>
) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView
        val tvDescription: TextView
        val tvUpdated: TextView
        val tvDefaultBranch: TextView
        val tvForksCount: TextView
        val tvStargazersCount: TextView
        val tvLanguage: TextView

        init {
            tvName = view.findViewById(R.id.tvName)
            tvDescription = view.findViewById(R.id.tvDescription)
            tvUpdated = view.findViewById(R.id.tvUpdated)
            tvDefaultBranch = view.findViewById(R.id.tvDefaultBranch)
            tvForksCount = view.findViewById(R.id.tvForksCount)
            tvStargazersCount = view.findViewById(R.id.tvStargazersCount)
            tvLanguage = view.findViewById(R.id.tvLanguage)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_repo, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            tvName.text = "Имя: ${repos[position].name}"
            tvDescription.text = "Описание: ${repos[position].description}"
            tvUpdated.text = "Дата коммита: ${repos[position].updated_at}"
            tvDefaultBranch.text = "Ветка по умолчанию: ${repos[position].default_branch}"
            tvForksCount.text = "Количество форков: ${repos[position].forks_count}"
            tvStargazersCount.text = "Количество звезд: ${repos[position].stargazers_count}"
            tvLanguage.text = "Язык исходного кода: ${repos[position].language?:""}"
        }
    }

    override fun getItemCount() = repos.size

}
