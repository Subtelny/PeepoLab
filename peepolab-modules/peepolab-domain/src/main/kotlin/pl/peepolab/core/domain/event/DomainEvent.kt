package pl.peepolab.core.domain.event

import java.time.LocalDateTime

open class DomainEvent(
    val date : LocalDateTime
)