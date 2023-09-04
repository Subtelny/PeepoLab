package pl.peepolab.module.core.infrastructure.persistence.transaction

import jakarta.inject.Singleton
import org.jooq.DSLContext
import pl.peepolab.module.api.infrastructure.ConnectionProvider
import pl.peepolab.module.api.infrastructure.TransactionProvider

@Singleton
class ConnectionProviderImpl(
    private val transactionProvider: TransactionProvider,
    private val dslContext: DSLContext,
) : ConnectionProvider {
    override fun getConnection(): DSLContext {
        return transactionProvider.getCurrentTransaction() ?: dslContext
    }
}