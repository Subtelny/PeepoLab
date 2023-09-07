package pl.peepolab.utilities.cqs.command

interface CommandBus {
    fun <RESULT> executeCommand(command: Command<RESULT>): RESULT

}