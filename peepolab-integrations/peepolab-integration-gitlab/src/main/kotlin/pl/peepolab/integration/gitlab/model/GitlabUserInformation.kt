package pl.peepolab.integration.gitlab.model

import pl.peepolab.utilities.datatype.Email

data class GitlabUserInformation(
    val username: String,
    val name: String,
    val avatarUrl: String,
    val publicEmail: Email?,
    val email: Email,
    val admin: Boolean,
)