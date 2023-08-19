package pl.peepolab.utilities.datatype

import java.io.Serializable

open class Identity<T : Serializable>(
    val value: T
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Identity<*>

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}
