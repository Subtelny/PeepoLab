package pl.peepolab.module.api.integration.dto

open class IntegrationType(val type: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as IntegrationType

        return type == other.type
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }
}