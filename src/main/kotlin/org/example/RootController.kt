package org.example

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RootController {

    companion object {
        private const val HELLO_WORLD = "Hello World!"
    }

    // Health check. `/` now serves the React SPA (see SpaController), so the
    // simple text greeting lives at /api/health for quick deploy verification.
    @GetMapping("/api/health")
    fun health() = HELLO_WORLD
}
