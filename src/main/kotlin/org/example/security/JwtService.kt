package org.example.security

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

/**
 * Issues and verifies stateless HS256 JWTs. The token carries the user id
 * (subject) plus email/name claims, so a request can be authenticated without
 * any server-side session (fits the Lambda + Aurora DSQL stateless model).
 */
@Service
class JwtService(
    @Value("\${app.jwt.secret}") secret: String,
    @Value("\${app.jwt.expiration-minutes}") private val expirationMinutes: Long
) {
    private val key: SecretKey = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(userId: String, email: String, name: String): String {
        val now = Date()
        val expiry = Date(now.time + expirationMinutes * MILLIS_PER_MINUTE)
        return Jwts.builder()
            .subject(userId)
            .claim("email", email)
            .claim("name", name)
            .issuedAt(now)
            .expiration(expiry)
            .signWith(key)
            .compact()
    }

    /** Returns the authenticated user, or null if the token is missing/invalid/expired. */
    fun parse(token: String): AuthenticatedUser? =
        try {
            val claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).payload
            AuthenticatedUser(
                id = claims.subject,
                email = claims["email"] as String,
                name = claims["name"] as String
            )
        } catch (e: JwtException) {
            log.debug("Rejected invalid JWT", e)
            null
        } catch (e: IllegalArgumentException) {
            log.debug("Rejected malformed JWT", e)
            null
        }

    companion object {
        private const val MILLIS_PER_MINUTE = 60_000L
        private val log = LoggerFactory.getLogger(JwtService::class.java)
    }
}

data class AuthenticatedUser(val id: String, val email: String, val name: String)
