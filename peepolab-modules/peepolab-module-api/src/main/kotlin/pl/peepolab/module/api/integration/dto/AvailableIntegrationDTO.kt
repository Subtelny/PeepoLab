package pl.peepolab.module.api.integration.dto

class AvailableIntegrationDTO(
    val type: IntegrationType,
    val authStrategy: IntegrationAuthStrategy,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AvailableIntegrationDTO

        return type == other.type
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }
}
