package org.example.security

import jakarta.servlet.DispatcherType
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig(
    private val jwtService: JwtService
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            // Stateless JWT API: no CSRF tokens, no server-side session.
            .csrf { it.disable() }
            .cors { it.configurationSource(corsConfigurationSource()) }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // The serverless servlet has no real HTTP session. Without this,
                // when our JWT filter authenticates mid-request the
                // SessionManagementFilter applies session-fixation protection and
                // calls request.changeSessionId(), which throws
                // UnsupportedOperationException under the serverless container.
                it.sessionFixation { fixation -> fixation.none() }
            }
            // Return 401 (not 403) when an anonymous request hits a protected resource.
            .exceptionHandling { it.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)) }
            .authorizeHttpRequests {
                // Allow the internal ERROR dispatch through so controller exceptions
                // (e.g. 400 validation, 409 duplicate email) render their real status
                // instead of being blocked at /error and turning into 403.
                it.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                // Use explicit antMatcher(...) rather than String patterns: under the
                // serverless container (aws-serverless-java-container) the registered
                // servlet's getMappings() returns null, and Spring Security's default
                // String-pattern matching introspects servlet mappings on first request
                // and throws NPE. antMatcher(...) skips that servlet introspection.
                // Public API: auth endpoints (register/login/me) and the health check.
                it.requestMatchers(antMatcher("/api/auth/**"), antMatcher("/api/health")).permitAll()
                // Everything else (e.g. /api/users) requires a valid JWT.
                it.anyRequest().authenticated()
            }
            .addFilterBefore(
                JwtAuthenticationFilter(jwtService),
                UsernamePasswordAuthenticationFilter::class.java
            )
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    /**
     * Dev CORS for the Vite dev server. In normal local use the Vite proxy makes
     * requests same-origin, but this also allows hitting the API directly from
     * http://localhost:5173 during development.
     */
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration().apply {
            allowedOrigins = listOf("http://localhost:5173")
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
            allowedHeaders = listOf("*")
        }
        return UrlBasedCorsConfigurationSource().apply { registerCorsConfiguration("/**", config) }
    }
}
