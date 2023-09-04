package pl.peepolab.module.api.infrastructure

import org.jooq.DSLContext

interface TransactionProvider {

    fun transactional(block: () -> Unit)

    fun getCurrentTransaction(): DSLContext?

}