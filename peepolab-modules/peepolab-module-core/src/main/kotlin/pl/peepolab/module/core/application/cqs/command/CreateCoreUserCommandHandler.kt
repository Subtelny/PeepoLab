package pl.peepolab.module.core.application.cqs.command

import jakarta.inject.Singleton
import pl.peepolab.module.api.ui.command.CreateCoreUserCommand
import pl.peepolab.module.core.application.cqs.CoreCommandHandler
import pl.peepolab.module.core.model.CoreUserRepository
import pl.peepolab.module.model.user.model.CoreUserId

@Singleton
class CreateCoreUserCommandHandler(
    private val coreUserRepository: CoreUserRepository,
) : CoreCommandHandler<CreateCoreUserCommand, CoreUserId> {
    override fun handle(command: CreateCoreUserCommand): CoreUserId {
        val coreUser = coreUserRepository.createCoreUser(command.email)
        return coreUser.id
    }
}