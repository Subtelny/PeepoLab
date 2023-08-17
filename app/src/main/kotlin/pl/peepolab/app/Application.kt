package pl.peepolab.app

import io.micronaut.runtime.Micronaut

fun main(args: Array<String>) {
    Micronaut.run(*args)
//    val api = GitLabApi("https://gitlab.com", "glpat-mYhoVEN3sygzxrKk4Nrq")
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