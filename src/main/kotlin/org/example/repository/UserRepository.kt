package org.example.repository

import org.example.dbflute.exbhv.UsersBhv
import org.example.dbflute.exentity.Users
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class UserRepository(
    private val usersBhv: UsersBhv
) {

    fun findAll(): List<UserModel> {
        // Empty ConditionBean lambda => select all rows.
        return usersBhv.selectList { /* no condition */ }.map { it.toModel() }
    }

    /**
     * Look up a user by email for authentication. Returns the internal record
     * (including the BCrypt hash); never expose this directly over the API.
     * Email is not UNIQUE in the DB (DSQL secondary-index constraint), so this
     * relies on the application keeping emails unique on registration.
     */
    fun findByEmail(email: String): UserRecord? {
        val found = usersBhv.selectEntity { it.query().setEmail_Equal(email) }
        return if (found.isPresent) found.get().toRecord() else null
    }

    fun create(name: String, email: String, passwordHash: String): UserModel {
        val entity = Users()
        // Generate the UUID in the app rather than relying on the DB default, so
        // the same insert path works on both local Postgres and Aurora DSQL.
        entity.id = UUID.randomUUID()
        entity.name = name
        entity.email = email
        entity.passwordHash = passwordHash
        usersBhv.insert(entity)
        return entity.toModel()
    }

    private fun Users.toModel() = UserModel(id = id.toString(), name = name, email = email)

    private fun Users.toRecord() =
        UserRecord(id = id.toString(), name = name, email = email, passwordHash = passwordHash)
}
