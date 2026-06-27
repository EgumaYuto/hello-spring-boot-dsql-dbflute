package org.example.repository

import org.example.dbflute.exbhv.TodosBhv
import org.example.dbflute.exentity.Todos
import org.springframework.stereotype.Repository
import java.util.UUID

/**
 * TODO persistence via DBFlute's generated [TodosBhv]. Every query is scoped by
 * userId so a user can only ever see/modify their own items (todos has no FK; the
 * relationship is enforced here — see V3 migration / DSQL notes).
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
        entity.done = false
        todosBhv.insert(entity)
        return entity.toModel()
    }

    /** Updates done for a todo owned by [userId]; returns the updated model, or null if not found. */
    fun setDone(userId: UUID, todoId: UUID, done: Boolean): TodoModel? {
        val found = todosBhv.selectEntity {
            it.query().setId_Equal(todoId)
            it.query().setUserId_Equal(userId)
        }
        if (!found.isPresent) return null
        val entity = found.get()
        entity.done = done
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

    private fun Todos.toModel() = TodoModel(id = id.toString(), title = title, done = done)
}
