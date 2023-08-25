package pl.peepolab.app

import io.micronaut.runtime.Micronaut
import org.gitlab4j.api.Constants
import org.gitlab4j.api.GitLabApi

fun main(args: Array<String>) {
    Micronaut.run(*args)
//    val api = GitLabApi("https://gitlab.com", "glpat-mYhoVEN3sygzxrKk4Nrq")
//    val api = GitLabApi("http://127.0.0.1", "201e6b55caaec51d41a55353707e58cf92a366bee3e4d9eafe79f5de02bbb7b7")

    GitLabApi(
        GitLabApi.ApiVersion.V4,
        "http://127.0.0.1",
        Constants.TokenType.OAUTH2_ACCESS,
        "3797eb096ad18d9263a3ce5b80239fa00bb59e6e5645dcde8cdd6acfcb690123",
        null,
        emptyMap(),
    )
    /*

code : b3751eb3c6283df1ae61e76bfc386af44d5257d8670ccc525ac2c9130b687edf
    app id: ead283d0ac45a2f22b4b49ff7dae23c840ce7ba21f8c1f70f9f5fdacbe104ca5
    secret: f1a0c13a94349ed0616a6692824ee411e3880669089c1e87cf61197828703bf5
     */

    /*

    access: 0e5d8853a51692bb0c555cd631f7e0fc6a1be73a46a84068d8884b5e8c853c18
    refresh: 113a35f1733d0092d1c1d9b495f41ee1a35de123ce184bd68fb108a7170188f0
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