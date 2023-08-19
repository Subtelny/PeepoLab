package pl.peepolab.core.domain.mergerequest

import pl.peepolab.core.domain.Entity
import pl.peepolab.core.domain.MergeRequestId
import pl.peepolab.core.domain.user.UserId
import pl.peepolab.core.domain.mergerequest.event.ReviewerChangedEvent

class MergeRequest(
    val id: MergeRequestId,
    var reviewer: UserId?,
) : Entity() {

    fun assignReviewer(reviewer: UserId?, changedBy: UserId) {
        this.reviewer = reviewer
        addEvent(ReviewerChangedEvent(id, reviewer, changedBy))
    }

    fun unassignReviewer(changedBy: UserId) {
        this.reviewer = null
        addEvent(ReviewerChangedEvent(id, null, changedBy))
    }

}
