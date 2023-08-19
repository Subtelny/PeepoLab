package pl.peepolab.app

import io.micronaut.runtime.Micronaut
import org.gitlab4j.api.GitLabApi

fun main(args: Array<String>) {
//    Micronaut.run(*args)
//    val api = GitLabApi("https://gitlab.com", "glpat-mYhoVEN3sygzxrKk4Nrq")
//    val api = GitLabApi("http://127.0.0.1", "glpat-i_5EdaTA5yqZzVzrV8-G")
    val api = GitLabApi.oauth2Login("http://127.0.0.1", "peepolab", "cokolwiekTutajWpisze")

//    api.projectApi.getHooks("47121515").forEach { println(it) }
}

//fun main() {
//    val app = App()
//    app.command("/hello") { req, ctx ->
//        println(Thread.currentThread().name)
//        ctx.ack(":wave: Hello, ${req.payload.userName}!")
//    }
//
//    val server = SlackAppServer(app)
//    server.start()
//}