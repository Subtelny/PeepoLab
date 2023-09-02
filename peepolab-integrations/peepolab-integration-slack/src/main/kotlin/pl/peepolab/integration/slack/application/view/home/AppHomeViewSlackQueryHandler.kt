package pl.peepolab.integration.slack.application.view.home

import com.slack.api.model.block.LayoutBlock
import com.slack.api.model.view.View
import jakarta.inject.Singleton
import pl.peepolab.integration.slack.application.cqs.SlackQueryHandler
import pl.peepolab.integration.slack.application.integration.ExternalUserIntegrationsService
import pl.peepolab.integration.slack.application.view.SlackViewFileLoader
import pl.peepolab.integration.slack.application.view.builder.SlackLayoutBlocksBuilder
import pl.peepolab.integration.slack.application.view.home.creator.IntegrationsLayoutBlocksCreator
import pl.peepolab.integration.slack.application.view.home.creator.VersionControlLayoutBlocksCreator
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.utilities.cqs.query.Query

private const val HEADER_FILE_NAME = "views/homeapp/header.json"

@Singleton
class HomeViewSlackQueryHandler(
    externalUserIntegrationsService: ExternalUserIntegrationsService,
) : SlackQueryHandler<GetAppHomeViewSlackQuery, View> {

    private val integrationsLayoutBlocksCreator = IntegrationsLayoutBlocksCreator(externalUserIntegrationsService)
    private val versionControlsLayoutBlocksCreator = VersionControlLayoutBlocksCreator()
    override fun handle(query: GetAppHomeViewSlackQuery): View {
        val integrations = integrationsLayoutBlocksCreator.create(query.userId)
        val versionControls = versionControlsLayoutBlocksCreator.create(query.userId)

        return View.builder()
            .type("home")
            .blocks(
                header()
                    .plus(integrations)
                    .plus(versionControls)
            )
            .build()
    }

    private fun header(): List<LayoutBlock> {
        val blocksJson = SlackViewFileLoader.loadViewJson(HEADER_FILE_NAME)
        return SlackLayoutBlocksBuilder()
            .addBlocks(blocksJson)
            .build()
    }

}

data class GetAppHomeViewSlackQuery(val userId: SlackUserId) : Query<View>

