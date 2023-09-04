package pl.peepolab.utilities.cqs.command

interface CommandBus {
    fun executeCommand(command: Command)

}