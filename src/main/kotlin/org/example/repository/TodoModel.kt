package org.example.repository

// API-facing TODO shape. status is a TodoStatus classification code (TODO/DOING/DONE).
data class TodoModel(val id: String, val title: String, val status: String)
