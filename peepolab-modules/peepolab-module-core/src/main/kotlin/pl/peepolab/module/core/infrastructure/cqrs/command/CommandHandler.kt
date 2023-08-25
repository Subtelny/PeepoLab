package pl.peepolab.module.core.infrastructure.cqrs.command

import pl.peepolab.module.api.cqrs.command.Command

interface CommandHandler<COMMAND : Command<RESULT>, RESULT> {

    fun handle(command: COMMAND): RESULT

}