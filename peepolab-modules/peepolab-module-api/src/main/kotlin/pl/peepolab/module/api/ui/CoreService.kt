package pl.peepolab.module.api.ui

import pl.peepolab.module.api.ui.command.CoreCommand
import pl.peepolab.module.api.ui.query.CoreQuery

interface CoreService {

    fun execute(command: CoreCommand)

    fun <RESULT> query(query: CoreQuery<RESULT>): RESULT

}