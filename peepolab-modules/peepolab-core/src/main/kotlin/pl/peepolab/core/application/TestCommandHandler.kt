package pl.peepolab.core.application

import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import pl.peepolab.core.infrastructure.command.Command
import pl.peepolab.core.infrastructure.command.CommandHandler

@Singleton
class TestCommandHandler : CommandHandler<TestCommand, TestCommandResult> {
    override fun handle(command: TestCommand): TestCommandResult {
        LoggerFactory.getLogger(TestCommandHandler::class.java).info("TestCommandHandler")
        return TestCommandResult()
    }
}

class TestCommandResult {

}

class TestCommand : Command<TestCommandResult> {

}