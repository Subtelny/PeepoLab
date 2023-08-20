package pl.peepolab.core.domain.mergerequest.event

import pl.peepolab.core.domain.MergeRequestId
import pl.peepolab.core.domain.user.model.UserId
import pl.peepolab.core.domain.DomainEvent

data class ReviewerChangedEvent(
    val mergeRequestId: MergeRequestId,
    val changedTo: UserId?,
    val changedBy: UserId,
) : DomainEvent()
