package org.example.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

/**
 * Reads `Authorization: Bearer <token>`, validates the JWT, and populates the
 * SecurityContext with the authenticated user so downstream `@RequestMapping`
 * handlers and `@AuthenticationPrincipal` work. Runs once per request.
 *
 * Deliberately NOT a `@Component`: it is constructed in [SecurityConfig] and added
 * to the Spring Security filter chain only. If it were also a Filter bean, Spring
 * Boot would auto-register it as a top-level servlet filter too; under the AWS
 * serverless container that copy runs before Spring Security's
 * SecurityContextHolderFilter, which then resets the context and discards the
 * authentication this filter set (resulting in 401s despite a valid token).
 */
class JwtAuthenticationFilter(
    private val jwtService: JwtService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = resolveAuthorizationHeader(request)
        if (header != null && header.startsWith(BEARER_PREFIX)) {
            val user = jwtService.parse(header.removePrefix(BEARER_PREFIX))
            if (user != null && SecurityContextHolder.getContext().authentication == null) {
                val authentication = UsernamePasswordAuthenticationToken(user, null, emptyList())
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
        filterChain.doFilter(request, response)
    }

    /**
     * Resolve the Authorization header tolerantly. The servlet spec says getHeader
     * is case-insensitive, but Lambda Function URLs deliver header names lowercased
     * (payload v2) and some serverless servlet adapters do an exact-case lookup, so
     * fall back to scanning the header names case-insensitively.
     */
    private fun resolveAuthorizationHeader(request: HttpServletRequest): String? {
        request.getHeader(AUTHORIZATION)?.let { return it }
        // Fallback: Function URL delivers header names lowercased; scan case-insensitively.
        return request.headerNames
            ?.toList()
            ?.firstOrNull { it.equals(AUTHORIZATION, ignoreCase = true) }
            ?.let(request::getHeader)
    }

    companion object {
        private const val BEARER_PREFIX = "Bearer "
        private const val AUTHORIZATION = "Authorization"
    }
}
