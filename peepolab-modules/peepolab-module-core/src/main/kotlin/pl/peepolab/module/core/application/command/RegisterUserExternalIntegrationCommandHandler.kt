package pl.peepolab.module.core.application.command

import jakarta.inject.Singleton
import pl.peepolab.module.api.ui.command.RegisterExternalIntegrationCommand
import pl.peepolab.module.core.application.UserExternalIntegrationsService
import pl.peepolab.module.core.application.cqs.CoreCommandHandler

@Singleton
class RegisterUserExternalIntegrationCommandHandler(
    private val userExternalIntegrationsService: UserExternalIntegrationsService,
) : CoreCommandHandler<RegisterExternalIntegrationCommand> {

    override fun handle(command: RegisterExternalIntegrationCommand) {
        userExternalIntegrationsService.registerIntegration(command.externalIntegration)
    }

}