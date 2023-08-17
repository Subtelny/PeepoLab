package pl.peepolab.core.infrastructure.cqrs.command

import io.micronaut.context.ApplicationContext
import pl.peepolab.common.cqrs.command.CommandHandler
import pl.peepolab.core.infrastructure.cqrs.Provider
import java.lang.reflect.Type

class CommandProvider<HANDLER : CommandHandler<*, *>>(
    applicationContext: ApplicationContext,
    type: Type,
) : Provider<HANDLER>(applicationContext, type)