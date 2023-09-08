package pl.peepolab.module.core.infrastructure.persistence.user

import pl.peepolab.module.model.user.model.CoreUser
import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.datatype.Email

fun CoreUserEntity.toModel() =
    CoreUser(CoreUserId.of(id), Email(email))

fun CoreUser.toEntity() = CoreUserEntity(
    id = id.value,
    email = email.value,
)