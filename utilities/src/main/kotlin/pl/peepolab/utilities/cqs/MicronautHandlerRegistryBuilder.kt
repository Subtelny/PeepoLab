package pl.peepolab.utilities.cqs

import io.micronaut.context.ApplicationContext
import pl.peepolab.utilities.cqs.command.CommandHandler
import pl.peepolab.utilities.cqs.query.QueryHandler
import pl.peepolab.utilities.resolveType

class MicronautHandlerRegistryBuilder(
    private val appContext: ApplicationContext,
) {

    private val commandHandlers = mutableMapOf<Class<*>, CommandHandler<*>>()
    private val queryHandlers = mutableMapOf<Class<*>, QueryHandler<*, *>>()

    fun <T : CommandHandler<*>> registerCommandHandlers(commandHandlerType: Class<T>): MicronautHandlerRegistryBuilder {
        appContext.getBeanDefinitions(commandHandlerType).forEach { beanDefinition ->
            val generics = resolveType(beanDefinition.beanType)
            val keyType: Class<*> = generics[0]
            commandHandlers[keyType] = appContext.getBean(beanDefinition.beanType)
        }
        return this
    }

    fun <T : QueryHandler<*, *>> registerQueryHandlers(queryHandlerType: Class<T>): MicronautHandlerRegistryBuilder {
        appContext.getBeanDefinitions(queryHandlerType).forEach { beanDefinition ->
            val generics = resolveType(beanDefinition.beanType)
            val keyType: Class<*> = generics[0]
            queryHandlers[keyType] = appContext.getBean(beanDefinition.beanType)
        }
        return this
    }

    fun build(): HandlerRegistry {
        return BasicHandlerRegistry(commandHandlers.toMap(), queryHandlers.toMap())
    }

}