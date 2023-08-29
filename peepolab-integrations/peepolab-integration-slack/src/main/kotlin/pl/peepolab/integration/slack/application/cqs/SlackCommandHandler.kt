package pl.peepolab.integration.slack.application.cqs

import pl.peepolab.utilities.cqs.command.Command
import pl.peepolab.utilities.cqs.command.CommandHandler

interface SlackCommandHandler<COMMAND : Command> : CommandHandler<COMMAND>