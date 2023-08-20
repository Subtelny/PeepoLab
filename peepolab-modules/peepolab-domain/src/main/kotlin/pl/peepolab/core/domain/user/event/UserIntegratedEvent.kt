package pl.peepolab.core.domain.user.event

import pl.peepolab.core.domain.DomainEvent
import pl.peepolab.core.domain.integration.model.IntegrationUserId
import pl.peepolab.core.domain.user.model.UserId

data class UserIntegratedEvent(
    val userId: UserId,
    val integrationUserId: IntegrationUserId,
) : DomainEvent()
