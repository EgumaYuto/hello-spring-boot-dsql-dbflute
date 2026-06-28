package org.example.controller.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateTodoRequest(
    @field:NotBlank
    @field:Size(max = 500)
    val title: String
)

data class UpdateTodoRequest(
    @field:NotBlank
    val status: String
)

data class TodoResponse(val id: String, val title: String, val status: String)

// One entry of the TodoStatus classification (for the frontend's status selector).
data class TodoStatusResponse(val code: String, val name: String)
