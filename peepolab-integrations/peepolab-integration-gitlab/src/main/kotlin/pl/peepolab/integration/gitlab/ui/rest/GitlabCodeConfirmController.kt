package pl.peepolab.integration.gitlab.ui.rest

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import jakarta.ws.rs.PathParam
import org.slf4j.LoggerFactory
import pl.peepolab.integration.gitlab.application.AuthorizationCode
import pl.peepolab.integration.gitlab.application.AuthorizationState
import pl.peepolab.integration.gitlab.application.GitlabAuthorizationService
import java.lang.Exception
import java.util.*

@Controller("/gitlab/code/confirm")
class GitlabCodeConfirmController(
    private val gitlabCodeConfirmService: GitlabAuthorizationService,
) {

    private val logger = LoggerFactory.getLogger(GitlabCodeConfirmController::class.java)

    @Get
    fun handle(@PathParam("code") code: String, @PathParam("state") state: String): HttpResponse<String> {
        if (isUUID(state).not()) {
            return HttpResponse.badRequest("Aha. Nieładnie.")
        }
        try {
            gitlabCodeConfirmService.authorize(AuthorizationCode(code), AuthorizationState(UUID.fromString(state)))
        } catch (e: Exception) {
            logger.error("Error while authorizing user", e)
            return HttpResponse.badRequest("Oops, mamy problem. Spróbuj jeszcze raz.")
        }
        return HttpResponse.ok("Dzięki ( ͡° ͜ʖ ͡°). Teraz możesz wrócić do aplikacji.")
    }

    private fun isUUID(uuid: String): Boolean =
        try {
            UUID.fromString(uuid)
            true
        } catch (e: IllegalArgumentException) {
            false
        }

}