package org.example.controller.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegisterRequest(
    @field:NotBlank
    val name: String,
    @field:Email
    @field:NotBlank
    val email: String,
    @field:Size(min = 6, max = 100)
    val password: String
)

data class LoginRequest(
    @field:Email
    @field:NotBlank
    val email: String,
    @field:NotBlank
    val password: String
)

// Public-facing user shape (no password hash).
data class UserResponse(val id: String, val name: String, val email: String)

data class AuthResponse(val token: String, val user: UserResponse)
