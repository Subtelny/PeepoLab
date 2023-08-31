package pl.peepolab.module.core.infrastructure.persistence.cqs

import jakarta.inject.Singleton
import pl.peepolab.module.core.application.cqs.CoreCommandBus
import pl.peepolab.module.core.application.cqs.CoreQueryBus
import pl.peepolab.utilities.cqs.CommandQueryBus
import pl.peepolab.utilities.cqs.command.Command
import pl.peepolab.utilities.cqs.query.Query

@Singleton
class CoreCommandQueryBus(
    coreHandlerRegistry: CoreHandlerRegistry,
) : CoreCommandBus, CoreQueryBus {

    private val commandQueryBus: CommandQueryBus = CommandQueryBus(coreHandlerRegistry)
    override fun executeCommand(command: Command) = commandQueryBus.executeCommand(command)

    override fun <RESULT> executeQuery(query: Query<RESULT>): RESULT = commandQueryBus.executeQuery(query)
}