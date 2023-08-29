package pl.peepolab.integration.slack.application.view

import pl.peepolab.integration.slack.application.view.builder.SlackViewFile

object SlackViewFileLoader {

    private val cachedViewJson: MutableMap<String, String> = mutableMapOf()

    fun loadViewJson(viewFilePath: String): String {
        return cachedViewJson.computeIfAbsent(viewFilePath) {
            SlackViewFile(viewFilePath).readJson()
        }
    }

}