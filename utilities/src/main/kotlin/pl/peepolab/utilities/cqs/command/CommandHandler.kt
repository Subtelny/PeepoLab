package pl.peepolab.utilities.cqs.command

interface CommandHandler<COMMAND : Command> {

    fun handle(command: COMMAND)

}