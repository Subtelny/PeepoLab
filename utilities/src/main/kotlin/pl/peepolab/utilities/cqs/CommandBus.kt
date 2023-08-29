package pl.peepolab.utilities.cqs

import pl.peepolab.utilities.cqs.command.Command

interface CommandBus {
    fun executeCommand(command: Command)

}