package org.example.repository

import org.example.dbflute.allcommon.CDef
import org.example.dbflute.exbhv.TodosBhv
import org.example.dbflute.exentity.Todos
import org.springframework.stereotype.Repository
import java.util.UUID

/**
 * TODO persistence via DBFlute's generated [TodosBhv]. Every query is scoped by
 * userId so a user can only ever see/modify their own items (todos has no real FK;
 * the relationship is enforced here — see the V3 migration / DSQL notes).
 *
 * `status` is the TodoStatus classification code (TODO/DOING/DONE), backed by the
 * cls_todo_status master via a pseudo-FK.
 */
@Repository
class TodoRepository(
    private val todosBhv: TodosBhv
) {

    fun findByUser(userId: UUID): List<TodoModel> {
        return todosBhv.selectList {
            it.query().setUserId_Equal(userId)
            it.query().addOrderBy_CreatedAt_Asc()
        }.map { it.toModel() }
    }

    fun create(userId: UUID, title: String): TodoModel {
        val entity = Todos()
        entity.id = UUID.randomUUID()
        entity.userId = userId
        entity.title = title
        // status has a classification deployed, so the plain setter is protected;
        // use the generated typed accessors.
        entity.setStatus_ToDo()
        todosBhv.insert(entity)
        return entity.toModel()
    }

    /** Updates status for a todo owned by [userId]; returns the updated model, or null if not found. */
    fun updateStatus(userId: UUID, todoId: UUID, status: String): TodoModel? {
        val found = todosBhv.selectEntity {
            it.query().setId_Equal(todoId)
            it.query().setUserId_Equal(userId)
        }
        if (!found.isPresent) return null
        val entity = found.get()
        entity.setStatusAsTodoStatus(CDef.TodoStatus.of(status).get())
        todosBhv.update(entity)
        return entity.toModel()
    }

    /** Returns true if a todo owned by [userId] was deleted. */
    fun delete(userId: UUID, todoId: UUID): Boolean {
        val deleted = todosBhv.queryDelete {
            it.query().setId_Equal(todoId)
            it.query().setUserId_Equal(userId)
        }
        return deleted > 0
    }

    private fun Todos.toModel() =
        TodoModel(id = id.toString(), title = title, status = statusAsTodoStatus?.code() ?: "")
}
