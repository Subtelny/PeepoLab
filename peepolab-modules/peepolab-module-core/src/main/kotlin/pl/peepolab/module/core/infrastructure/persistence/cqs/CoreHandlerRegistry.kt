package pl.peepolab.module.core.infrastructure.persistence.cqs

import io.micronaut.context.ApplicationContext
import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import pl.peepolab.module.core.application.cqs.CoreCommandHandler
import pl.peepolab.module.core.application.cqs.CoreQueryHandler
import pl.peepolab.utilities.cqs.MicronautHandlerRegistry

@Singleton
class CoreHandlerRegistry(
    private val appContext: ApplicationContext
) : MicronautHandlerRegistry() {

    @EventListener
    fun onStartup(event: StartupEvent) {
        registerHandlers<CoreCommandHandler<*>, CoreQueryHandler<*, *>>(appContext)
    }

}