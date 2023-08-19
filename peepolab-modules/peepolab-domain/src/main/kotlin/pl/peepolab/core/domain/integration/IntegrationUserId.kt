package pl.peepolab.core.domain.integration

import pl.peepolab.utilities.datatype.Identity

class IntegrationUserId(
    val id: String,
    val integrationType: IntegrationType,
) : Identity<String>("$id-${integrationType.value}") {

    override fun toString(): String {
        return "IntegrationUserId(value='$value', integrationType=$integrationType)"
    }

}