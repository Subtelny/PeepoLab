package pl.peepolab.integration.slack.application

import jakarta.inject.Singleton
import pl.peepolab.integration.slack.model.SlackUser
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.integration.slack.model.SlackUserRepository
import pl.peepolab.module.api.infrastructure.TransactionProvider
import pl.peepolab.module.api.ui.CoreService
import pl.peepolab.module.api.ui.command.CreateCoreUserCommand

@Singleton
class SlackUserServiceImpl(
    private val transactionProvider: TransactionProvider,
    private val slackUserRepository: SlackUserRepository,
    private val coreService: CoreService,
) : SlackUserService {

    override fun createSlackUser(data: CreateSlackUserData): SlackUser {
        return transactionProvider.transactionalResult {
            val userId = coreService.command(CreateCoreUserCommand(data.email))
            val slackUser = slackUserRepository.createSlackUser(userId, data)
            slackUser
        }
    }

    override fun findSlackUser(slackUserId: SlackUserId): SlackUser? {
        return slackUserRepository.findSlackUser(slackUserId)
    }

}