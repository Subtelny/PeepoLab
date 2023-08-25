package pl.peepolab.module.core.application

import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import pl.peepolab.module.api.cqrs.command.Command
import pl.peepolab.module.core.infrastructure.cqrs.command.CommandHandler

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