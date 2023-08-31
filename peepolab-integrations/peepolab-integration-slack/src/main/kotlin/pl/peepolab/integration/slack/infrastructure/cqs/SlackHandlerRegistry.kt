package pl.peepolab.integration.slack.infrastructure.cqs

import io.micronaut.context.ApplicationContext
import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import pl.peepolab.integration.slack.application.cqs.SlackCommandHandler
import pl.peepolab.integration.slack.application.cqs.SlackQueryHandler
import pl.peepolab.utilities.cqs.HandlerRegistry
import pl.peepolab.utilities.cqs.MicronautHandlerRegistry
import pl.peepolab.utilities.resolveType

@Singleton
class SlackHandlerRegistry(
    private val appContext: ApplicationContext
) : MicronautHandlerRegistry() {

    @EventListener
    fun onStartup(event: StartupEvent) {
        val tmpCommandHandlers = findAllBeans<SlackCommandHandler<*>>(appContext)
        commandHandlers.putAll(tmpCommandHandlers)

        val tmpQueryHandlers = findAllBeans<SlackQueryHandler<*, *>>(appContext)
        queryHandlers.putAll(tmpQueryHandlers)
    }

}