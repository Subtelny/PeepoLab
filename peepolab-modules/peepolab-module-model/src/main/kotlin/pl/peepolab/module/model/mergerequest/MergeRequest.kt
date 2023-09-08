package pl.peepolab.module.model.mergerequest

import pl.peepolab.module.model.MergeRequestId
import pl.peepolab.module.model.user.model.CoreUserId

data class MergeRequest(
    val id: MergeRequestId,
    var reviewer: CoreUserId?,
)