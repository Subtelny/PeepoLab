package pl.peepolab.integration.gitlab.model

import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.datatype.Email

data class CreateGitlabUserData(
    val gitlabUserId: GitlabUserId,
    val coreUserId: CoreUserId,
    val email: Email,
    val username: String,
)