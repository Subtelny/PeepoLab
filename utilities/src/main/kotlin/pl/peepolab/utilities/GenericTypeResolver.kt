package pl.peepolab.utilities

import java.lang.reflect.ParameterizedType

fun resolveType(clazz: Class<*>): Array<out Class<*>> {
    return clazz.genericInterfaces
        .filterIsInstance<ParameterizedType>()
        .firstOrNull()
        ?.actualTypeArguments
        ?.map { it as Class<*> }
        ?.toTypedArray()
        .orEmpty()
}