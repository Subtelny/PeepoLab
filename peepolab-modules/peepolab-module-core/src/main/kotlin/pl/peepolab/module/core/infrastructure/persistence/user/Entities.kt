package pl.peepolab.module.core.infrastructure.persistence.user

import java.util.UUID

data class CoreUserEntity(
    val id: UUID,
    val email: String,
)