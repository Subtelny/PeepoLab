package pl.peepolab.module.core.infrastructure

import io.micronaut.context.ApplicationContext
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import pl.peepolab.module.api.event.CoreInitializedEvent
import pl.peepolab.module.core.application.UserExternalIntegrationsService
import pl.peepolab.module.core.application.cqs.CoreCommandHandler
import pl.peepolab.module.core.application.cqs.CoreQueryHandler
import pl.peepolab.utilities.cqs.MicronautHandlerRegistryBuilder

@Singleton
internal class CoreStartupEventListener(
    private val applicationContext: ApplicationContext,
    private val coreCommandQueryBus: CoreCommandQueryBus,
    private val userExternalIntegrationsService: UserExternalIntegrationsService,
    private val applicationEventPublisher: ApplicationEventPublisher<CoreInitializedEvent>,
) {

    @EventListener
    fun onStartup(event: StartupEvent) {
        registerCoreBusHandlerRegistries()
        publishCoreInitializedEvent()
    }

    private fun registerCoreBusHandlerRegistries() =
        MicronautHandlerRegistryBuilder(applicationContext)
            .registerCommandHandlers(CoreCommandHandler::class.java)
            .registerQueryHandlers(CoreQueryHandler::class.java)
            .build()
            .run {
                coreCommandQueryBus.setupHandlerRegistry(this)
            }

    private fun publishCoreInitializedEvent() {
        val coreContext = AppCoreContext(userExternalIntegrationsService)
        applicationEventPublisher.publishEvent(CoreInitializedEvent(coreContext))
    }

}