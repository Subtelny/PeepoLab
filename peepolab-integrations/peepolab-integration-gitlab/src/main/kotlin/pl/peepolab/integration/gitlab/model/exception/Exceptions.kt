package pl.peepolab.integration.gitlab.model.exception

sealed class GitlabException(message: String) : RuntimeException(message) {

    class UserNotFound : GitlabException("User not found")
    class NotAuthorized : GitlabException("Not authorized")

    class UserAlreadyExists : GitlabException("User already exists")

}