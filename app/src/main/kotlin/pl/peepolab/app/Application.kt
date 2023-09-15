package pl.peepolab.app

import io.micronaut.runtime.Micronaut
import org.gitlab4j.api.Constants
import org.gitlab4j.api.GitLabApi

fun main(args: Array<String>) {
    System.getProperties().setProperty("org.jooq.no-logo", "true");
    System.getProperties().setProperty("org.jooq.no-tips", "true");
    Micronaut.run(*args)
//    val api = GitLabApi("https://gitlab.com", "glpat-mYhoVEN3sygzxrKk4Nrq")
//    val api = GitLabApi("http://127.0.0.1", "201e6b55caaec51d41a55353707e58cf92a366bee3e4d9eafe79f5de02bbb7b7")

//    GitLabApi(
//        GitLabApi.ApiVersion.V4,
//        "http://127.0.0.1",
//        Constants.TokenType.OAUTH2_ACCESS,
//        "3797eb096ad18d9263a3ce5b80239fa00bb59e6e5645dcde8cdd6acfcb690123",
//        null,
//        emptyMap(),
//    )
    /*

    code :
    app id: c1459865d3573efeb374c5156bcc236bec6195171412bcf585623f5bb0c7a836
    secret: 52f4c73e92e90366c78f7b54b6a06122a2fd94917e09b88d3bdb5021b409b9a9
    rest: https://e563-79-191-179-41.ngrok-free.app/gitlab/success
    scopes: api+read_user+openid+profile+email
     */

    /*
    access: 5774202ead22d99aecd3f699622d225f4bfbfe4471529c07546b8775be6d55a7
    refresh: caeaf72dc23fb05c4bd39bae1b5c7600cf6055ad75c3ae32c10b730e2c3f07ad
     */
}


/*

parameters = 'client_id=APP_ID&code=RETURNED_CODE&grant_type=authorization_code&redirect_uri=REDIRECT_URI&code_verifier=CODE_VERIFIER'
RestClient.post 'http://127.0.0.1/oauth/token/client_id='49569b4d944f7dacf62b5258a26786cc18b818d42db593ad6833a5df28de70d9&code=b3751eb3c6283df1ae61e76bfc386af44d5257d8670ccc525ac2c9130b687edf, parameters

 */


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