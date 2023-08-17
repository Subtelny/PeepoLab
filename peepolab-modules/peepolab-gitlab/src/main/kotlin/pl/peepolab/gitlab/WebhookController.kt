package pl.peepolab.gitlab

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.gitlab4j.api.GitLabApi
import org.gitlab4j.api.webhook.Event
import org.gitlab4j.api.webhook.NoteEvent
import org.slf4j.LoggerFactory

@Controller("/webhook/events")
class WebhookController {

    @Post
    fun handleWebhook(@Body event: Event): HttpResponse<String> {
        val api = GitLabApi("https://gitlab.com", "glpat-mYhoVEN3sygzxrKk4Nrq")
        val noteEvent = event as NoteEvent
        api.userApi.findUsers(noteEvent.user.username).forEach {
            LoggerFactory.getLogger(WebhookController::class.java).info("User: ${it.username}, ${it.name}, ${it.email}, ${it.state}")
        }
        return HttpResponse.ok("OK")
    }
}