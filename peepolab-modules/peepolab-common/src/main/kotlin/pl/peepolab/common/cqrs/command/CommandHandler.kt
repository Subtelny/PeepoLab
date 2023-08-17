package pl.peepolab.common.cqrs.command

interface CommandHandler<COMMAND : Command<RESULT>, RESULT> {

    fun handle(command: COMMAND): RESULT

}