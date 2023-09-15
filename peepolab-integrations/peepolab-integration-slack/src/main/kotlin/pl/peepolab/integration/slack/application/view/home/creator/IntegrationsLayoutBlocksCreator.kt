package pl.peepolab.integration.slack.application.view.home.creator

import com.slack.api.model.block.LayoutBlock
import pl.peepolab.integration.slack.application.integration.ExternalUserIntegrationStatus
import pl.peepolab.integration.slack.application.integration.ExternalUserIntegrationsService
import pl.peepolab.integration.slack.application.view.LayoutBlocksCreator
import pl.peepolab.integration.slack.application.view.SlackViewFileLoader
import pl.peepolab.integration.slack.application.view.builder.SlackLayoutBlocksBuilder
import pl.peepolab.integration.slack.application.view.builder.SlackViewParameter
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.module.api.integration.strategy.ExternalIntegrationAuthStrategy
import pl.peepolab.module.model.integration.ExternalIntegrationType

/***
 * Ten creator przewiduje obecnie, że jedyną integracją będzie GitLab. Do przepisania gdy będzie ich więcej.
 */

private val GITLAB_INTEGRATION_TYPE = ExternalIntegrationType("GITLAB")
private const val GITLAB_INTEGRATION_FILE_NAME = "views/homeapp/gitlab_integration.json"
private const val GITLAB_UNAUTHENTICATED_FILE_NAME = "views/homeapp/gitlab_unauthenticated.json"

class IntegrationsLayoutBlocksCreator(
    private val externalUserIntegrationsService: ExternalUserIntegrationsService,
) : LayoutBlocksCreator {

    override fun create(slackUserId: SlackUserId): List<LayoutBlock> {
        val info = externalUserIntegrationsService.getExternalUserIntegrationInformation(slackUserId, GITLAB_INTEGRATION_TYPE)
        return prepareIntegrationStatus(info)
    }

    private fun prepareIntegrationStatus(
        integration: ExternalUserIntegrationStatus,
    ): List<LayoutBlock> {
        return when (integration) {
            is ExternalUserIntegrationStatus.Integrated -> emptyList()
            is ExternalUserIntegrationStatus.Unauthenticated -> unauthenticated(integration.authStrategy)
            is ExternalUserIntegrationStatus.NotIntegrated -> unauthenticated(integration.authStrategy, false)
        }
    }

    private fun unauthenticated(
        authStrategy: ExternalIntegrationAuthStrategy,
        integrated: Boolean = true,
    ): List<LayoutBlock> {
        if (authStrategy is ExternalIntegrationAuthStrategy.OIDC) {
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

    private fun ExternalIntegrationAuthStrategy.OIDC.buildUrl(): String {
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