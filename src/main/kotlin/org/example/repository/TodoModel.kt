package org.example.repository

// API-facing TODO shape. id is the UUID as a String (DSQL favours UUID keys).
data class TodoModel(val id: String, val title: String, val done: Boolean)
