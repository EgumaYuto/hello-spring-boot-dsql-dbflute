package org.example.controller

import jakarta.validation.Valid
import org.example.controller.dto.CreateTodoRequest
import org.example.controller.dto.TodoResponse
import org.example.controller.dto.UpdateTodoRequest
import org.example.dbflute.allcommon.CDef
import org.example.repository.TodoModel
import org.example.repository.TodoRepository
import org.example.security.AuthenticatedUser
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

/**
 * Per-user TODO API. All endpoints require a valid JWT (covered by the
 * `anyRequest().authenticated()` rule in SecurityConfig) and operate only on the
 * authenticated user's items. `status` is a TodoStatus classification code.
 */
@RestController
@RequestMapping("/api/todos")
class TodoController(
    private val todoRepository: TodoRepository
) {

    @GetMapping
    fun list(@AuthenticationPrincipal principal: AuthenticatedUser): List<TodoResponse> {
        return todoRepository.findByUser(userId(principal)).map { it.toResponse() }
    }

    @PostMapping
    fun create(
        @AuthenticationPrincipal principal: AuthenticatedUser,
        @Valid @RequestBody request: CreateTodoRequest
    ): TodoResponse {
        return todoRepository.create(userId(principal), request.title.trim()).toResponse()
    }

    @PatchMapping("/{id}")
    fun update(
        @AuthenticationPrincipal principal: AuthenticatedUser,
        @PathVariable id: UUID,
        @Valid @RequestBody request: UpdateTodoRequest
    ): TodoResponse {
        // Validate the status against the TodoStatus classification.
        val status = CDef.TodoStatus.of(request.status).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown status: ${request.status}")
        }
        val updated = todoRepository.updateStatus(userId(principal), id, status.code())
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found")
        return updated.toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @AuthenticationPrincipal principal: AuthenticatedUser,
        @PathVariable id: UUID
    ) {
        val deleted = todoRepository.delete(userId(principal), id)
        if (!deleted) throw ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found")
    }

    private fun userId(principal: AuthenticatedUser): UUID = UUID.fromString(principal.id)

    private fun TodoModel.toResponse() = TodoResponse(id = id, title = title, status = status)
}
