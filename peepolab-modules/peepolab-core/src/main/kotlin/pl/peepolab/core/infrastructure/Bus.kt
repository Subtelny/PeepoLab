package pl.peepolab.core.infrastructure

import pl.peepolab.core.infrastructure.command.Command
import pl.peepolab.core.infrastructure.query.Query

interface Bus {
    fun <RESULT> executeCommand(command: Command<RESULT>): RESULT

    fun <RESULT> executeQuery(query: Query<RESULT>): RESULT

}