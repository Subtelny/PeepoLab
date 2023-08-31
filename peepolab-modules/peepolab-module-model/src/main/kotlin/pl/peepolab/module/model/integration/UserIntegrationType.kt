package pl.peepolab.module.model.integration

open class UserIntegrationType(val type: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserIntegrationType

        return type == other.type
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }
}