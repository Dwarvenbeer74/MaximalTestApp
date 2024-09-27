package com.example.maximaltestapp.models

data class GitResponse(
    var total_count: Int,
    var incomplete_results: Boolean,
    var items: List<GitUser>
)
