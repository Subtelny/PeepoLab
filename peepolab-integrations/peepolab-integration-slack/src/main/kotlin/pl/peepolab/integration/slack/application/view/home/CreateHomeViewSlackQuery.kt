package pl.peepolab.integration.slack.application.view.home

import com.slack.api.model.view.View
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.utilities.cqs.query.Query

data class CreateHomeViewSlackQuery(val userId: SlackUserId) : Query<View>