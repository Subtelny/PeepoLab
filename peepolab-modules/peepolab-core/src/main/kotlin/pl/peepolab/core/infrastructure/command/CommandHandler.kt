package pl.peepolab.core.infrastructure.command

interface CommandHandler<COMMAND : Command<RESULT>, RESULT> {

    fun handle(command: COMMAND): RESULT

}