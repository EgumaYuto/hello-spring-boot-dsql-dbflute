package org.example.repository

import org.example.dbflute.exbhv.UsersBhv
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val usersBhv: UsersBhv
) {

    fun findAll(): List<UserModel> {
        // Empty ConditionBean lambda => select all rows.
        return usersBhv.selectList { /* no condition */ }.map {
            UserModel(
                id = it.id.toString(),
                name = it.name,
                email = it.email
            )
        }
    }
}
