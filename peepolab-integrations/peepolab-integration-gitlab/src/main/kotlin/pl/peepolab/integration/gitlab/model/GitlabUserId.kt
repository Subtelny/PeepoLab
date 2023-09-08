package pl.peepolab.integration.gitlab.model

import pl.peepolab.utilities.datatype.Identity

class GitlabUserId private constructor(value: Long) : Identity<Long>(value) {

    companion object {
        fun of(value: Long): GitlabUserId {
            require(value > 0) { "GitlabUserId value must be greater than 0" }
            return GitlabUserId(value)
        }
    }

}