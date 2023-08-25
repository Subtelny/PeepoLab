package pl.peepolab.integration.gitlab.auth

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/gitlab/login/success")
class GitlabLoginSuccessController {

    @Get
    fun get(httpRequest: HttpRequest<*>): HttpResponse<String> {
        return HttpResponse.ok("OK hehe")
    }
    @Post
    fun handleLoginSuccess(httpRequest: HttpRequest<*>): HttpResponse<String> {

        return HttpResponse.ok("OK")
    }

}