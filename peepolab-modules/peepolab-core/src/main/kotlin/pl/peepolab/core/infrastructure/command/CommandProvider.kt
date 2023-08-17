package pl.peepolab.core.infrastructure.command

import io.micronaut.context.ApplicationContext
import pl.peepolab.core.infrastructure.Provider
import java.lang.reflect.Type

class CommandProvider<HANDLER : CommandHandler<*, *>>(
    applicationContext: ApplicationContext,
    type: Type,
) : Provider<HANDLER>(applicationContext, type)