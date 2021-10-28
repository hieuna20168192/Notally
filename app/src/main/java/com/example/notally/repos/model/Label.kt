package com.example.notally.repos.model

data class Label(
    val id: Long = -1L,
    val name: String = "",
    val notes: List<Note> = listOf()
)
