package pl.peepolab.core.domain.mergerequest.event

import pl.peepolab.core.domain.MergeRequestId
import pl.peepolab.core.domain.UserId
import pl.peepolab.core.domain.event.DomainEvent
import java.time.LocalDateTime

data class ReviewerChangedEvent(
    val mergeRequestId: MergeRequestId,
    val changedTo: UserId?,
    val changedBy: UserId,
) : DomainEvent(LocalDateTime.now())
