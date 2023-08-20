package pl.peepolab.core.domain

import java.time.LocalDateTime

open class DomainEvent(
    val date: LocalDateTime = LocalDateTime.now()
)