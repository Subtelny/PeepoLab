package pl.peepolab.module.api.infrastructure

import org.jooq.DSLContext

interface TransactionProvider {

    fun transactional(block: () -> Unit)

    fun <T> transactionalResult(block: () -> T): T

    fun getCurrentTransaction(): DSLContext?

}