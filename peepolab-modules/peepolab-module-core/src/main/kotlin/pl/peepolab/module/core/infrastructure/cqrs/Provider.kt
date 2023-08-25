package pl.peepolab.module.core.infrastructure.cqrs

import io.micronaut.context.ApplicationContext
import java.lang.reflect.Type

abstract class Provider<HANDLER>(
    private val applicationContext: ApplicationContext,
    private val type: Type,
) {

    @Suppress("UNCHECKED_CAST")
    val handler: HANDLER by lazy { applicationContext.getBean(type as Class<HANDLER>) }

}