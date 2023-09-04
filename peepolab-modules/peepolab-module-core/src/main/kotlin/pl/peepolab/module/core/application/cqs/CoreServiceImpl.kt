package pl.peepolab.module.core.application.cqs

import jakarta.inject.Singleton
import pl.peepolab.module.api.ui.CoreService
import pl.peepolab.module.api.ui.command.CoreCommand
import pl.peepolab.module.api.ui.query.CoreQuery
import pl.peepolab.module.core.infrastructure.CoreCommandQueryBus

@Singleton
class CoreServiceImpl(
    private val coreCommandBus: CoreCommandBus,
    private val coreQueryBus: CoreQueryBus,
) : CoreService {

    override fun execute(command: CoreCommand) {
        coreCommandBus.executeCommand(command)
    }

    override fun <RESULT> query(query: CoreQuery<RESULT>): RESULT {
        return coreQueryBus.executeQuery(query)
    }
}