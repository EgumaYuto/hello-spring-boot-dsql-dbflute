package org.example.repository

// Internal user representation used for authentication. Unlike UserModel (the API
// response shape) this carries the BCrypt password hash, so it must never be
// serialized to clients.
data class UserRecord(
    val id: String,
    val name: String,
    val email: String,
    val passwordHash: String?
)
