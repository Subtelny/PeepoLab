package pl.peepolab.module.core.application.cqs

import jakarta.inject.Singleton
import pl.peepolab.module.api.ui.CoreService
import pl.peepolab.module.api.ui.command.CoreCommand
import pl.peepolab.module.api.ui.query.CoreQuery
import pl.peepolab.module.core.infrastructure.persistence.cqs.CoreCommandQueryBus
import pl.peepolab.utilities.cqs.command.Command
import pl.peepolab.utilities.cqs.query.Query

@Singleton
class CoreServiceImpl(
    private val coreCommandQueryBus: CoreCommandQueryBus
) : CoreService {

    override fun execute(command: CoreCommand) {
        coreCommandQueryBus.executeCommand(command)
    }

    override fun <RESULT> query(query: CoreQuery<RESULT>): RESULT {
        return coreCommandQueryBus.executeQuery(query)
    }
}