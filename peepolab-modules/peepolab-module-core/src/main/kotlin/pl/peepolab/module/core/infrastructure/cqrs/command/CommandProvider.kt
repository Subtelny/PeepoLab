package pl.peepolab.module.core.infrastructure.cqrs.command

import io.micronaut.context.ApplicationContext
import pl.peepolab.module.core.infrastructure.cqrs.Provider
import java.lang.reflect.Type

class CommandProvider<HANDLER : CommandHandler<*, *>>(
    applicationContext: ApplicationContext,
    type: Type,
) : Provider<HANDLER>(applicationContext, type)