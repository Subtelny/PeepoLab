package pl.peepolab.module.core.infrastructure.cqrs

import io.micronaut.context.ApplicationContext
import jakarta.inject.Singleton
import pl.peepolab.module.api.cqrs.command.Command
import pl.peepolab.module.core.infrastructure.cqrs.command.CommandHandler
import pl.peepolab.module.core.infrastructure.cqrs.command.CommandProvider
import pl.peepolab.module.api.cqrs.query.Query
import pl.peepolab.module.core.infrastructure.cqrs.query.QueryHandler
import pl.peepolab.module.core.infrastructure.cqrs.query.QueryProvider
import pl.peepolab.utilities.resoleType

@Singleton
class HandlerRegistry(appContext: ApplicationContext) {

    private val commandProviders: Map<Class<*>, CommandProvider<CommandHandler<*, *>>>
    private val queryProviders: Map<Class<*>, QueryProvider<QueryHandler<*, *>>>

    init {
        val tmpCommandProviders = mutableMapOf<Class<*>, CommandProvider<CommandHandler<*, *>>>()
        registerHandlers(appContext, CommandHandler::class.java, tmpCommandProviders) { context, beanType ->
            CommandProvider(context, beanType)
        }
        val tmpQueryProviders = mutableMapOf<Class<*>, QueryProvider<QueryHandler<*, *>>>()
        registerHandlers(appContext, QueryHandler::class.java, tmpQueryProviders) { context, beanType ->
            QueryProvider(context, beanType)
        }
        commandProviders = tmpCommandProviders
        queryProviders = tmpQueryProviders
    }

    private fun <HANDLER, PROVIDER : Provider<HANDLER>> registerHandlers(
        appContext: ApplicationContext,
        handlerType: Class<HANDLER>,
        registry: MutableMap<Class<*>, PROVIDER>,
        providerFactory: (ApplicationContext, Class<HANDLER>) -> PROVIDER
    ) {
        appContext.getBeanDefinitions(handlerType).forEach { beanDefinition ->
            val generics = resoleType(beanDefinition.beanType, handlerType)
            val commandType: Class<*> = generics[0]
            val provider = providerFactory(appContext, beanDefinition.beanType)
            registry[commandType] = provider
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <RESULT, COMMAND : Command<RESULT>> getCommandHandler(commandType: Class<COMMAND>): CommandHandler<COMMAND, RESULT> {
        return commandProviders[commandType]?.handler as? CommandHandler<COMMAND, RESULT>
            ?: throw IllegalArgumentException("Handler not found for command type: ${commandType.name}")
    }

    @Suppress("UNCHECKED_CAST")
    fun <RESULT, QUERY : Query<RESULT>> getQueryHandler(queryType: Class<QUERY>): QueryHandler<QUERY, RESULT> {
        return queryProviders[queryType]?.handler as? QueryHandler<QUERY, RESULT>
            ?: throw IllegalArgumentException("Handler not found for query type: ${queryType.name}")
    }

}