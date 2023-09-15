package pl.peepolab.integration.slack.application

import jakarta.inject.Singleton
import pl.peepolab.integration.slack.model.CreateSlackUserData
import pl.peepolab.integration.slack.model.SlackUser
import pl.peepolab.integration.slack.model.SlackUserId
import pl.peepolab.integration.slack.model.SlackUserRepository
import pl.peepolab.integration.slack.model.exception.SlackUserException
import pl.peepolab.module.api.infrastructure.TransactionProvider
import pl.peepolab.module.api.ui.CoreService
import pl.peepolab.module.api.ui.command.CreateCoreUserCommand
import pl.peepolab.module.api.ui.query.GetUserInformationQuery
import pl.peepolab.module.model.user.exception.UserException
import pl.peepolab.module.model.user.model.CoreUserId
import pl.peepolab.utilities.datatype.Email

@Singleton
class SlackUserServiceImpl(
    private val transactionProvider: TransactionProvider,
    private val slackUserRepository: SlackUserRepository,
    private val coreService: CoreService,
) : SlackUserService {

    override fun createSlackUser(data: CreateSlackUserData): SlackUser {
        if (slackUserRepository.findSlackUser(data.slackUserId) != null) {
            throw SlackUserException.AlreadyExists(data.slackUserId)
        }

        val optCoreUserId = findCoreUserId(data.email)
        return transactionProvider.transactionalResult {
            val userId = optCoreUserId ?: coreService.command(CreateCoreUserCommand(data.email))
            slackUserRepository.createSlackUser(userId, data)
        }
    }

    override fun findSlackUser(slackUserId: SlackUserId): SlackUser? {
        return slackUserRepository.findSlackUser(slackUserId)
    }

    private fun findCoreUserId(email: Email): CoreUserId? {
        return try {
            coreService.query(GetUserInformationQuery.ByEmail(email)).userId
        } catch (e: UserException.NotFound) {
            null
        }
    }

}