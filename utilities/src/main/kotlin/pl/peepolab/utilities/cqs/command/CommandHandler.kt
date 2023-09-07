package pl.peepolab.utilities.cqs.command

interface CommandHandler<COMMAND : Command<RESULT>, RESULT> {

    fun handle(command: COMMAND): RESULT

}