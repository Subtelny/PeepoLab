package pl.peepolab.integration.gitlab.webhook

import io.micronaut.http.HttpRequest
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
    fun handleWebhook(@Body event: Map<String, Any>): HttpResponse<String> {
        return HttpResponse.ok("OK")
    }
}