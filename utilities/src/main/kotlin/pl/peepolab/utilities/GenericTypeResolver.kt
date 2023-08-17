package pl.peepolab.utilities

import java.lang.reflect.ParameterizedType

fun resoleType(clazz: Class<*>, type: Class<*>): Array<out Class<*>> {
    return clazz.genericInterfaces
        .filterIsInstance<ParameterizedType>()
        .firstOrNull { it.rawType == type }
        ?.actualTypeArguments
        ?.map { it as Class<*> }
        ?.toTypedArray()
        .orEmpty()
}