package pl.peepolab.integration.gitlab.model

import pl.peepolab.module.model.user.model.UserId

class GitlabUser(
    val userId: UserId,
    val gitlabUserId: GitlabUserId,
    val information: GitlabUserInformation,
    var token: GitlabToken,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GitlabUser

        if (userId != other.userId) return false
        if (gitlabUserId != other.gitlabUserId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + gitlabUserId.hashCode()
        return result
    }
}