package pl.peepolab.app

import io.micronaut.runtime.Micronaut

fun main(args: Array<String>) {
    Micronaut.run(*args)
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