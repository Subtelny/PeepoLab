package pl.peepolab.integration.slack.application.view.home

import com.slack.api.model.block.LayoutBlock
import com.slack.api.model.view.View
import jakarta.inject.Singleton
import pl.peepolab.integration.slack.application.cqs.SlackQueryHandler
import pl.peepolab.integration.slack.application.service.SlackAvailableIntegrations
import pl.peepolab.integration.slack.application.view.SlackViewFileLoader
import pl.peepolab.integration.slack.application.view.builder.SlackLayoutBlocksBuilder
import pl.peepolab.integration.slack.application.view.builder.SlackViewParameter
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.module.api.integration.dto.AvailableIntegrationDTO
import pl.peepolab.module.api.integration.dto.IntegrationAuthStrategy
import pl.peepolab.module.api.integration.dto.IntegrationType


private const val AVAILABLE_INTEGRATION_FILE_NAME = "views/homeapp/available_integration.json"
private const val HEADER_FILE_NAME = "views/homeapp/header.json"
private val GITLAB_INTEGRATION_TYPE = IntegrationType("GITLAB")

@Singleton
class HomeViewSlackQueryHandler(
    private val slackAvailableIntegrations: SlackAvailableIntegrations,
) : SlackQueryHandler<CreateHomeViewSlackQuery, View> {
    override fun handle(query: CreateHomeViewSlackQuery): View {
        return View.builder()
            .type("home")
            .blocks(
                header()
                    .plus(availableIntegrations(query.userId))
            )
            .build()
    }

    private fun header(): List<LayoutBlock> {
        val blocksJson = SlackViewFileLoader.loadViewJson(HEADER_FILE_NAME)
        return SlackLayoutBlocksBuilder()
            .addBlocks(blocksJson)
            .build()
    }

    private fun availableIntegrations(userId: SlackUserId): List<LayoutBlock> {
        //TODO - Przerobić gdy będzie więcej integracji
        val integration = slackAvailableIntegrations.findAvailableIntegration(userId, GITLAB_INTEGRATION_TYPE)
        return integration?.let { prepareAvailableIntegration(it) } ?: emptyList()
    }

    private fun prepareAvailableIntegration(
        integration: AvailableIntegrationDTO
    ): List<LayoutBlock> {
        //TODO - Przerobić gdy będą inne opcje autoryzacji
        val authStrategy = integration.authStrategy
        if (authStrategy is IntegrationAuthStrategy.OpenId) {
            val blocksJson = SlackViewFileLoader.loadViewJson(AVAILABLE_INTEGRATION_FILE_NAME)
            val parameters = listOf(
                SlackViewParameter.of("GITLAB_OPENID_URL", authStrategy.url),
            )
            return SlackLayoutBlocksBuilder()
                .addBlocks(blocksJson, parameters)
                .build()
        }
        return emptyList()
    }


}

