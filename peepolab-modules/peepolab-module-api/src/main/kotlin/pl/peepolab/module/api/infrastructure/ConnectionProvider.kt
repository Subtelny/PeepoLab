package pl.peepolab.module.api.infrastructure

import org.jooq.DSLContext

interface ConnectionProvider {

    fun getConnection(): DSLContext

}