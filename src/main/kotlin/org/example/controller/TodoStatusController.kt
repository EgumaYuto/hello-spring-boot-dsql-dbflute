package org.example.controller

import org.example.controller.dto.TodoStatusResponse
import org.example.dbflute.allcommon.CDef
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Exposes the TodoStatus classification (TODO/DOING/DONE) so the frontend can render
 * a status selector. Values come from CDef (generated from the cls_todo_status master).
 */
@RestController
@RequestMapping("/api/todo-statuses")
class TodoStatusController {

    @GetMapping
    fun list(): List<TodoStatusResponse> {
        return CDef.TodoStatus.listAll().map { TodoStatusResponse(code = it.code(), name = it.alias()) }
    }
}
