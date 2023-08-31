package pl.peepolab.integration.slack.application.view.home

import com.slack.api.model.block.LayoutBlock
import com.slack.api.model.view.View
import jakarta.inject.Singleton
import pl.peepolab.integration.slack.application.GITLAB_INTEGRATION_TYPE
import pl.peepolab.integration.slack.application.cqs.SlackQueryHandler
import pl.peepolab.integration.slack.application.integration.ExternalUserIntegrationStatus
import pl.peepolab.integration.slack.application.integration.ExternalUserIntegrationsService
import pl.peepolab.integration.slack.application.view.SlackViewFileLoader
import pl.peepolab.integration.slack.application.view.builder.SlackLayoutBlocksBuilder
import pl.peepolab.integration.slack.application.view.builder.SlackViewParameter
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.module.api.integration.dto.IntegrationAuthStrategyDTO

/***
 * Ten handler przewiduje obecnie, że jedyną inną integracją będzie GitLab. Do przepisania gdy będzie ich więcej.
 */

private const val GITLAB_INTEGRATION_FILE_NAME = "views/homeapp/gitlab_integration.json"
private const val GITLAB_UNAUTHENTICATED_FILE_NAME = "views/homeapp/gitlab_unauthenticated.json"
private const val HEADER_FILE_NAME = "views/homeapp/header.json"

@Singleton
class HomeViewSlackQueryHandler(
    private val externalUserIntegrationsService: ExternalUserIntegrationsService,
) : SlackQueryHandler<CreateHomeViewSlackQuery, View> {
    override fun handle(query: CreateHomeViewSlackQuery): View {
        return View.builder()
            .type("home")
            .blocks(
                header()
                    .plus(integrationsStatus(query.userId))
            )
            .build()
    }

    private fun header(): List<LayoutBlock> {
        val blocksJson = SlackViewFileLoader.loadViewJson(HEADER_FILE_NAME)
        return SlackLayoutBlocksBuilder()
            .addBlocks(blocksJson)
            .build()
    }

    private fun integrationsStatus(userId: SlackUserId): List<LayoutBlock> {
        val info = externalUserIntegrationsService.getUserIntegrationInformation(userId, GITLAB_INTEGRATION_TYPE)
        return prepareIntegrationStatus(info)
    }

    private fun prepareIntegrationStatus(
        integration: ExternalUserIntegrationStatus
    ): List<LayoutBlock> {
        return when (integration) {
            is ExternalUserIntegrationStatus.Integrated -> emptyList()
            is ExternalUserIntegrationStatus.Unauthenticated -> unauthenticated(integration.authStrategy)
            is ExternalUserIntegrationStatus.NotIntegrated -> unauthenticated(integration.authStrategy, false)
        }
    }

    private fun unauthenticated(
        authStrategy: IntegrationAuthStrategyDTO,
        integrated: Boolean = true
    ): List<LayoutBlock> {
        if (authStrategy is IntegrationAuthStrategyDTO.OIDC) {
            val view = if (integrated) GITLAB_UNAUTHENTICATED_FILE_NAME else GITLAB_INTEGRATION_FILE_NAME
            val blocksJson = SlackViewFileLoader.loadViewJson(view)
            val parameters = listOf(
                SlackViewParameter.of("GITLAB_OPENID_URL", authStrategy.buildUrl()),
            )
            return SlackLayoutBlocksBuilder()
                .addBlocks(blocksJson, parameters)
                .build()
        }
        return emptyList()
    }

    private fun IntegrationAuthStrategyDTO.OIDC.buildUrl(): String {
        val scopes = scope.joinToString(separator = "+")
        return buildString {
            append(authorizationEndpoint)
                .append("?").append("client_id=$clientId")
                .append("&").append("response_type=code")
                .append("&").append("scope=$scopes")
                .append("&").append("redirect_uri=$redirectUri")
                .append("&").append("state=$state")
        }
    }

}

