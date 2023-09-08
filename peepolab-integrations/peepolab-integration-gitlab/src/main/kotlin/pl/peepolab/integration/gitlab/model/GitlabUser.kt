package pl.peepolab.integration.gitlab.model

import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.datatype.Email

class GitlabUser(
    val id: GitlabUserId,
    val userId: CoreUserId,
    val email: Email,
    val username: String,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GitlabUser

        if (id != other.id) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + userId.hashCode()
        return result
    }
}