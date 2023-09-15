package pl.peepolab.integration.gitlab.application

import java.util.*

@JvmInline
value class AuthorizationCode(val value: String)

@JvmInline
value class AuthorizationState(val value: UUID)