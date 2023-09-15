package pl.peepolab.integration.slack.application.view.home.creator

import com.slack.api.model.block.LayoutBlock
import pl.peepolab.integration.slack.application.view.LayoutBlocksCreator
import pl.peepolab.integration.slack.model.SlackUserId

class VersionControlLayoutBlocksCreator : LayoutBlocksCreator {
    override fun create(slackUserId: SlackUserId): List<LayoutBlock> {
        return emptyList()
    }

}