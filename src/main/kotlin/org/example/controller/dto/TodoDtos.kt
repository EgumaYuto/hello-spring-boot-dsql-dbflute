package org.example.controller.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateTodoRequest(
    @field:NotBlank
    @field:Size(max = 500)
    val title: String
)

data class UpdateTodoRequest(
    val done: Boolean
)

data class TodoResponse(val id: String, val title: String, val done: Boolean)
