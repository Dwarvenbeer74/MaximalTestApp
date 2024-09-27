package com.example.maximaltestapp.models

data class RepoResponse(
    var name: String,           //имя репозитория
    var description: String,    //описание
    var updated_at: String,     //дата последнего коммита
    var default_branch: String, //ветка по умолчанию
    var forks_count: Int,       //количество форков
    var stargazers_count: Int,  //количество звезд
    var language: String        //ЯП исходного кода проекта
)
