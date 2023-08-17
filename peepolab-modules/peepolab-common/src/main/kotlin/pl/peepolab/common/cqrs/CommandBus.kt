package pl.peepolab.common.cqrs

import pl.peepolab.common.cqrs.command.Command

interface CommandBus {
    fun <RESULT> executeCommand(command: Command<RESULT>): RESULT

}