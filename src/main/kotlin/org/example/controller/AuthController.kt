package org.example.controller

import jakarta.validation.Valid
import org.example.controller.dto.AuthResponse
import org.example.controller.dto.LoginRequest
import org.example.controller.dto.RegisterRequest
import org.example.controller.dto.UserResponse
import org.example.repository.UserRepository
import org.example.security.AuthenticatedUser
import org.example.security.JwtService
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterRequest): AuthResponse {
        if (userRepository.findByEmail(request.email) != null) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "Email already registered")
        }
        val user = userRepository.create(
            name = request.name,
            email = request.email,
            passwordHash = passwordEncoder.encode(request.password)
        )
        return authResponseFor(user.id, user.name, user.email)
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequest): AuthResponse {
        val record = userRepository.findByEmail(request.email)
        if (record?.passwordHash == null ||
            !passwordEncoder.matches(request.password, record.passwordHash)
        ) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password")
        }
        return authResponseFor(record.id, record.name, record.email)
    }

    @GetMapping("/me")
    fun me(@AuthenticationPrincipal principal: AuthenticatedUser?): UserResponse {
        val user = principal ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        return UserResponse(user.id, user.name, user.email)
    }

    private fun authResponseFor(id: String, name: String, email: String): AuthResponse {
        val token = jwtService.generateToken(id, email, name)
        return AuthResponse(token, UserResponse(id, name, email))
    }
}
