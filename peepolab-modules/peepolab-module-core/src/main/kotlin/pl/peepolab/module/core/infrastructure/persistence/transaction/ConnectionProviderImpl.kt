package pl.peepolab.module.core.infrastructure.persistence.transaction

import jakarta.inject.Singleton
import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.slf4j.LoggerFactory
import pl.peepolab.module.api.infrastructure.ConnectionProvider
import pl.peepolab.module.api.infrastructure.TransactionProvider

@Singleton
class ConnectionProviderImpl(
    private val transactionProvider: TransactionProvider,
    private val configuration: Configuration,
) : ConnectionProvider {

    private val logger = LoggerFactory.getLogger(ConnectionProvider::class.java)

    override fun getConnection(): DSLContext {
        logger.info("Getting connection from thread " + Thread.currentThread().name)
        return dslContext1() ?: dslContext()
    }

    private fun dslContext1(): DSLContext? {
        val currentTransaction = transactionProvider.getCurrentTransaction()
        if (currentTransaction == null) {
            logger.info("No transaction found in thread " + Thread.currentThread().name)
        }
        return currentTransaction
    }

    private fun dslContext(): DSLContext {
        logger.info("Creating new connection")
        return DSL.using(configuration)
    }
}