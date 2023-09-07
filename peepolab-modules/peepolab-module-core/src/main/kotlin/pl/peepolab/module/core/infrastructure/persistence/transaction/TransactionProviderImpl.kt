package pl.peepolab.module.core.infrastructure.persistence.transaction

import jakarta.inject.Singleton
import org.jooq.DSLContext
import org.jooq.impl.DSL
import pl.peepolab.module.api.infrastructure.TransactionProvider

@Singleton
class TransactionProviderImpl(
    private val dslContext: DSLContext,
) : TransactionProvider {

    private val transaction = ThreadLocal<DSLContext>()

    override fun transactional(block: () -> Unit) {
        DSL.using(dslContext.configuration()).transaction { config ->
            transaction.set(DSL.using(config))
            catchException(block)
            transaction.remove()
        }
    }

    override fun <T> transactionalResult(block: () -> T): T {
        return DSL.using(dslContext.configuration()).transactionResult { config ->
            transaction.set(DSL.using(config))
            val result = catchException(block)
            transaction.remove()
            result
        }
    }

    override fun getCurrentTransaction(): DSLContext? {
        return transaction.get()
    }

    private fun <T> catchException(block: () -> T): T {
        try {
            return block()
        } catch (e: Exception) {
            transaction.remove()
            throw e
        }
    }

}