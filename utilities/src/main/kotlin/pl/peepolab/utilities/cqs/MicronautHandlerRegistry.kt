package pl.peepolab.utilities.cqs

import io.micronaut.context.ApplicationContext
import pl.peepolab.utilities.cqs.command.CommandHandler
import pl.peepolab.utilities.cqs.query.QueryHandler
import pl.peepolab.utilities.resolveType

abstract class MicronautHandlerRegistry : HandlerRegistry() {

    inline fun <reified CMD_HANDLER : CommandHandler<*>, reified QRY_HANDLER : QueryHandler<*, *>> registerHandlers(
        appContext: ApplicationContext,
    ) {
        val tmpCommandHandlers = findAllBeans<CMD_HANDLER>(appContext)
        commandHandlers.putAll(tmpCommandHandlers)

        val tmpQueryHandlers = findAllBeans<QRY_HANDLER>(appContext)
        queryHandlers.putAll(tmpQueryHandlers)
    }

    inline fun <reified T> findAllBeans(appContext: ApplicationContext): Map<Class<*>, T> {
        val tmpCommandProviders = mutableMapOf<Class<*>, T>()
        appContext.getBeanDefinitions(T::class.java).forEach { beanDefinition ->
            val generics = resolveType(beanDefinition.beanType)
            val keyType: Class<*> = generics[0]
            tmpCommandProviders[keyType] = appContext.getBean(beanDefinition.beanType) as T
        }
        return tmpCommandProviders
    }

}