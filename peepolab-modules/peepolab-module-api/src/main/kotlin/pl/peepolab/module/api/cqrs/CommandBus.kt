package pl.peepolab.module.api.cqrs

import pl.peepolab.module.api.cqrs.command.Command

interface CommandBus {
    fun <RESULT> executeCommand(command: Command<RESULT>): RESULT

}