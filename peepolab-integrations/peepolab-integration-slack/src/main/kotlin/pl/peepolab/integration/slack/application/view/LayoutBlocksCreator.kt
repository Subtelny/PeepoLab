package pl.peepolab.integration.slack.application.view

import com.slack.api.model.block.LayoutBlock
import pl.peepolab.integration.slack.model.SlackUserId

interface LayoutBlocksCreator {

    fun create(slackUserId: SlackUserId): List<LayoutBlock>

}