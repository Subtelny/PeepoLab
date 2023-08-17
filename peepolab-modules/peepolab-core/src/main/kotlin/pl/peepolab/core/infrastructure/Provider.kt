package pl.peepolab.core.infrastructure

import io.micronaut.context.ApplicationContext
import java.lang.reflect.Type

abstract class Provider<HANDLER>(
    private val applicationContext: ApplicationContext,
    private val type: Type,
) {

    val handler: HANDLER by lazy { applicationContext.getBean(type as Class<HANDLER>) }

}